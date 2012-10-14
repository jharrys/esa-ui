package org.ihc.esa

class Importer
{
	def importCategory()
	{
		BufferedReader br = new BufferedReader(new FileReader("esa-content/preliminary_category_seed_data.sql"))
		String line = null
		Map map = new HashMap()
		while((line = br.readLine()) != null)
		{
			String[] strArray = line.split("&")
			
			String key = strArray[1]
			String val = map.get(key)
			
			String newVal = (val) ? (val + "&" + strArray[0]) : strArray[0]
			map.put(key, newVal)
		}
		
		//root elements
		String[] roots = map.get("/").split("&")
		int x = 0
		roots.each {
			Category c = new Category(id: ++x, parentCategoryPath: "/", name: it, createdBy: "bulkloader", updatedBy: "bulkloader")
			c.parentCategory = c
			c.save(flush:true, failOnError:true)
		}
		
		map.remove("/")
		
		map.each {
			// build up parents
			println "---------------------------"
			String[] parents = it.key.split("/")
			Category nearestParentCategory = null
			for(int y=1;y<parents.length;y++) {
				String newCandidateParent = parents[y]
				println "newCandidateParent: " + newCandidateParent
				Category ncp = Category.findByName(newCandidateParent)
				println "ncp: " + ncp
				Category immediateParentCategory = null
				if(y>1 && !ncp) {
					println "checking for immediateParent"
					immediateParentCategory = Category.findByName(parents[y-1])
				}
				
				if(!ncp) {
					println "creating new candidate"
					println "y: " + y
					println "parents: " + parents
					String[] tmp = Arrays.copyOfRange(parents, 0, y)
					println "tmp: " + tmp
					String path = (y==1) ? "/" : Arrays.copyOfRange(parents, 0, y).join("/")
					println "path: " + path
					Category c = new Category(parentCategoryPath: path, name: newCandidateParent, createdBy: "bulkloader", updatedBy: "bulkloader")
					c.parentCategory = (immediateParentCategory ?: c)
					c.save(flush:true,failOnError:true)
					println "c created: " + c
				}
			}
			
			println "nearest parent string: " + parents[parents.length-1]
			nearestParentCategory = Category.findByName(parents[parents.length-1])
			println "nearest parent domain class: " + nearestParentCategory
			
			String[] category_leaf_nodes = it.value.split("&")
			println "parent path: " + parents
			println "leaf nodes: " + category_leaf_nodes
			category_leaf_nodes.each { element ->
				println "element: " + element
				String path = (nearestParentCategory?.parentCategoryPath.equals("/") ? "/" + nearestParentCategory?.name : nearestParentCategory?.parentCategoryPath + "/" + nearestParentCategory?.name)
				println "path: " + path
				Category catexists = Category.findByNameAndParentCategoryPath(element, path)
				if(!catexists) {
					Category leafCategory = new Category(parentCategory: nearestParentCategory,parentCategoryPath: path, name: element, createdBy: "bulkloader", updatedBy: "bulkloader")
					leafCategory.save(flush:true,failOnError:true)
				} else {
					println "element <" + element + "> with path: " + path + " already exists - not duping"
				}
			}
		}
	}
	
	def importItem() {
		def SS = "EISA EXCEL DOCUMENT"
		def BY = "bulkloader"
		
		
		BufferedReader br = new BufferedReader(new FileReader("esa-content/preliminary_item_seed_data.sql"))
		String line = null
		Map base_map = new HashMap()
		boolean firstLine = true
		while((line = br.readLine()) != null) {
			if (firstLine) {
				firstLine = false
				continue
			}
			String[] strArray = line.split("&")
			
			String key = strArray[3]		         //name of item
			Map property_map = new HashMap()       // properties of item
			
			println "working on: \"" + key + "\""
			
			for(int x=0;x<strArray.length;x++) {
				def propertyName = null
				switch (x) {
					case 0:
						propertyName = 'standard'
						break
					case 1:
						propertyName = 'exceptionRequired'
						break
					case 2:
						propertyName = 'exceptionCriteria'
						break
					case 3:
						propertyName = 'name'
						break
					case 4:
						propertyName = 'description'
						break
					case 5:
						propertyName = 'technologyGroup'
						break
					case 6:
						propertyName = 'usefulLife'
						break
					case 7:
						propertyName = 'comments'
						break
				}
				println "setting \"" + propertyName + "\" to \"" + strArray[x] + "\""
				property_map.put(propertyName,strArray[x])
			}
			println "setting base_map for key \"" + key + "\" and val -> " + property_map
			base_map.put(key, property_map)
		}
		
		def errormap = new HashMap()
		
		println "==== iterating through base_map..."
		base_map.each { key, propertymap ->
			println "----------"
			println "key \"" + key + "\" - propertymap: " + propertymap
			
			def splitDescription = propertymap['description'].split(";")
			def path = splitDescription[0].split("/")
			def realDescription = Arrays.copyOfRange(splitDescription, 1, splitDescription.length).join("\n")
			println "path is \"" + path + "\""
			println "realDescription is \"" + realDescription + "\""
			propertymap['description'] = realDescription
			
			def parent = path[path.length-1]
			def parentpath = (path - parent).join("/")
			println "parent is \"" + parent + "\""
			println "parentpath is \"" + parentpath + "\""
			
			def c = Category.findAllByNameAndParentCategoryPath(parent, parentpath)
			
			if (c.size >1) {
				errormap[key] = "got multiple values for \"" + parent + "\" and \"" + parentpath + "\""
			} else if (c.size <1) {
				errormap[key] = "empty result set for \"" + parent + "\" and \"" + parentpath + "\""
			} else {
				Category category = c[0]
				if (category == null) {
					errormap[key] = propertymap
				} else {
					Item item = new Item(createdBy:BY,updatedBy:BY,sourceSystem:SS)
					propertymap.each { prop, val ->
						item.setProperty(prop, val)
					}
					
					//item.save(flush:true, failOnError:true)
					category.addToItems(item)
					category.save(flush:true, failOnError:true)
				}
			}
		}
		
		println "===errors===="
		errormap.each { println it.key + ":" + it.value }
	}
}
