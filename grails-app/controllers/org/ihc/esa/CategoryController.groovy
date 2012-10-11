package org.ihc.esa

import org.springframework.dao.DataIntegrityViolationException

class CategoryController {
	
	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
	def index() {
		redirect action: 'list', params: params
	}
	
	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "id"
		params.order = params.order ?: "asc"
		[categoryInstanceList: Category.list(params), categoryInstanceTotal: Category.count()]
	}
	
	def create() {
		switch (request.method) {
			case 'GET':
				[categoryInstance: new Category(params)]
				break
			case 'POST':
				def categoryInstance = new Category(params)
				if (!categoryInstance.save(flush: true)) {
					render view: 'create', model: [categoryInstance: categoryInstance]
					return
				}
			
				flash.message = message(code: 'default.created.message', args: [message(code: 'category.label', default: 'Category'), categoryInstance.id])
				redirect action: 'show', id: categoryInstance.id
				break
		}
	}
	
	def show() {
		def categoryInstance = Category.get(params.id)
		if (!categoryInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), params.id])
			redirect action: 'list'
			return
		}
		
		[categoryInstance: categoryInstance]
	}
	
	def edit() {
		switch (request.method) {
			case 'GET':
				def categoryInstance = Category.get(params.id)
				if (!categoryInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), params.id])
					redirect action: 'list'
					return
				}
			
				[categoryInstance: categoryInstance]
				break
			case 'POST':
				def categoryInstance = Category.get(params.id)
				if (!categoryInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), params.id])
					redirect action: 'list'
					return
				}
			
				if (params.version) {
					def version = params.version.toLong()
					if (categoryInstance.version > version) {
						categoryInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
										[message(code: 'category.label', default: 'Category')] as Object[],
										"Another user has updated this Category while you were editing")
						render view: 'edit', model: [categoryInstance: categoryInstance]
						return
					}
				}
			
				categoryInstance.properties = params
			
				if (!categoryInstance.save(flush: true)) {
					render view: 'edit', model: [categoryInstance: categoryInstance]
					return
				}
			
				flash.message = message(code: 'default.updated.message', args: [message(code: 'category.label', default: 'Category'), categoryInstance.id])
				redirect action: 'show', id: categoryInstance.id
				break
		}
	}
	
	def imports() {
		BufferedReader br = new BufferedReader(new FileReader("/tmp/category_seed_data.sql"))
		String line = null
		Map map = new HashMap()
		while((line = br.readLine()) != null) {
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
				Category leafCategory = new Category(parentCategory: nearestParentCategory,parentCategoryPath: path, name: element, createdBy: "bulkloader", updatedBy: "bulkloader")
				leafCategory.save(flush:true,failOnError:true)
			}
		}
	}
	
	def delete() {
		def categoryInstance = Category.get(params.id)
		if (!categoryInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'category.label', default: 'Category'), params.id])
			redirect action: 'list'
			return
		}
		
		try {
			categoryInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'category.label', default: 'Category'), params.id])
			redirect action: 'list'
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'category.label', default: 'Category'), params.id])
			redirect action: 'show', id: params.id
		}
	}
}
