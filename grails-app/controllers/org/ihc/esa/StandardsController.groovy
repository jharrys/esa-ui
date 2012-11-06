package org.ihc.esa

import grails.plugins.springsecurity.Secured

class StandardsController
{
	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST', list: 'GET', editByCategory: 'GET', itemsInCategory: 'POST', addItemToCategory: 'POST', 
		removeItemFromCategory: 'POST', renameCategory: 'POST', addNewItem: ['GET', 'POST'], error: 'GET']
	
	def springSecurityService
	
	def index()
	{
		redirect action: 'list', params: params
	}
	
	def list()
	{
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params['standard'] = 'Y'
		params['sort'] = 'name'
		[itemInstanceList: Item.list(params), itemInstanceTotal: Item.count()]
	}
	
	/**
	 * 
	 * @return if catId is not null, then return the list of {@link Item}s associated with this {@link Category}
	 */
	def itemsInCategoryList() {
		
		log.debug("====================================================================================")
		log.debug("itemsInCategoryList() in standards controller called with params: " + params)
		log.debug("====================================================================================")
		
		def itemList = null
		
		if (params.catId) {
			Category c = Category.get(params.catId)
			itemList = c.items
			
			itemList = itemList.sort { a,b ->
				a.name.toLowerCase() <=> b.name.toLowerCase()
			}
			
			log.debug("found itemList: " + itemList)
			render (itemList as grails.converters.JSON)
		} else {
			log.debug("catId is null, returning empty result")
			render ""
		}
	}
	
	/**
	 * used as an ajax call to add the sent {@link Item} to the provided {@link Category}
	 * @return true if successful, false otherwise
	 */
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def addItemToCategory() {
		def user = null
		user = springSecurityService.currentUser
		
		log.debug(user.username)
		
		log.debug("====================================================================================")
		log.debug("addItemToCategory() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		Item i = Item.get(params.itemId)
		Category c = Category.get(params.toCategory)
		
		log.debug(i)
		log.debug(c)
		
		ItemCategory ic = new ItemCategory(item: i, category: c, createdBy: user.username, updatedBy: user.username)
		
		String result = ""
		if (ic.save(flush:true, failOnError:true)) {
			result = "succeeded"
		}
		
		render result
	}
	
	/**
	 * used as an ajax call to remove supplied {@link Item} from provided {@link Category}
	 * @return true if successful, false otherwise
	 */
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def removeItemFromCategory() {
		
		def user = null
		user = springSecurityService.currentUser
		
		log.debug("====================================================================================")
		log.debug("removeItemFromCategory() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		Item i = Item.get(params.itemId)
		Category c = Category.get(params.fromCategory)
		
		c.removeFromItems(i)
		
		String result = ""
		
		if (!c.save(flush:true, failOnError:true)) {
			result = "succeeded"
		}
		
		render result
	}
	
	/**
	 * used as an ajax call to rename the supplied {@link Category} name
	 * FIXME: ugly, ugly, ugly ... need to move this to a service and/or change the implementation of Category name and Category parentCategoryPath
	 * @return a success message as a string
	 */
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def renameCategory() {
		def user = null
		user = springSecurityService.currentUser
		
		log.debug("====================================================================================")
		log.debug("renameCategory() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		log.debug("params: " + params)
		
		String successMessage = ""
		
		successMessage = params.categoryId ? "" : "No Category was selected."
		
		successMessage = successMessage + (params.categoryName ? "" : "New name cannot be empty.")
		
		// FIXME: Actually - the database implementation of category is not a good idea. We actually have to iterate through all progeny, not just nearest children!
		
		if (!successMessage) {
			Category c = Category.get(params.categoryId);
			
			if (c.name.equals(params.categoryName)) {
				successMessage = "Submitted name is not different, no change has been made."
			} else {
				List<Category> children = Category.list();
				
				String regex = /((\/[^\/]*){0,})\/${c.name}((\/[^\/]+){0,})/
				
				c.name = params.categoryName
				
				children.each { cat ->
					java.util.regex.Matcher matcher = (cat.parentCategoryPath =~ regex)
					
					log.debug("regex is: " + regex)
					log.debug("matcher is: " + matcher)
					
					if (matcher.matches()) {
						log.debug("**************match found!******************")
						log.debug("old path: " + cat.parentCategoryPath)
						cat.parentCategoryPath = matcher[0][1] + "/" + c.name + matcher[0][3]
						log.debug("new path: " + cat.parentCategoryPath)
						log.debug("********************************************")
						
						// FIXME: add transaction checking
						cat.save(flush:true)
					} else {
						log.debug("no match found for " + cat.name + "(" + cat.parentCategoryPath + ")")
					}
				}
				
				if (c.save(flush:true)) {
					successMessage = "Category name changed successfully!" 
				} else {
					successMessage = "Category was not saved."
				} 
			}
		}
		
		def categories = this.getCategories()
		
		render categories as grails.converters.JSON
	}
	
	/**
	 * provides the controlling mechanism for adding/removing {@link Item}s from a {@link Category} and renaming of the
	 * {@link Category} name. These activites are done through ajax calls themselves.
	 * 
	 * see {@link #renameCategory}, {@link #addItemToCategory} and {@link #removeItemFromCategory}
	 * 
	 * @return list of all {@link Category}ies sorted by path
	 */
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def editByCategory()
	{
		[categories: this.getCategories(), itemInstance: new Item()]
	}
	
	private List<Category> getCategories() {
		List<Category> categories = Category.list()
		
		categories = categories.sort { a,b ->
			String aSlash = a.parentCategoryPath.equals("/") ? '' : '/'
			String bSlash = b.parentCategoryPath.equals("/") ? '' : '/'
			String aCombined = a.parentCategoryPath + aSlash + a.name
			String bCombined = b.parentCategoryPath + bSlash + b.name
			
			return aCombined.toLowerCase().compareTo(bCombined.toLowerCase())
		}
		
		return categories
	}
	
	def addNewItem() {
		def user = null
		user = springSecurityService.currentUser
		
		log.debug("====================================================================================")
		log.debug("addNewItem() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		switch (request.method) {
			case 'GET': 
				Item itemInstance = new Item()
				render view: 'addNewItem', model: [itemInstance: itemInstance, categoryId: params.categoryId]
				break
			
			case 'POST':
				Category category = null
				if (params.categoryId) {
					category = Category.get(params.categoryId)
				}

				params.party = params.party ? Party.get(params.party) : null
				params.document = params.document ? Document.get(params.document) : null
				params.createdBy = user?.username
				params.updatedBy = user?.username
				params.ihcProposedDecomissioned = params.ihcProposedDecomissioned ? new Date(params.ihcProposedDecomissioned) : null
				params.availableDate = params.availableDate ? new Date(params.availableDate) : null
				params.ihcActualDecomissioned = params.ihcActualDecomissioned ? new Date(params.ihcActualDecomissioned) : null
				params.vendorDecomissioned = params.vendorDecomissioned ? new Date(params.vendorDecomissioned) : null
																																				
				Item item = new Item(params)
				if (!item.save(flush:true)) {
					log.error("unable to save new Item")
					render view: 'addNewItem'
					return
				} else {
					log.debug("successfully saved: \"" + item + "\"")
				}
				
				if (category) {
					ItemCategory ic = new ItemCategory(createdBy: params.createdBy, updatedBy: params.updatedBy)
					ic.item = item
					ic.category = category
					
					if (!ic.save(flush:true)) {
						log.error("error saving \"" + item + "\" to \"" + category + "\"")
						flash.message = "unable to save item"
						render view: 'addNewItem'
					} else {
						log.debug("successfully saved: \"" + item + "\" to \"" + category + "\"")
						render view: 'editByCategory'
					}
				}
				break
		}
		
	}
	
	def error() {
		
		def user = null
		user = springSecurityService.currentUser
		
		log.debug("====================================================================================")
		log.debug("error() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		def subjectString = "error with esa-ui version " + grailsApplication.metadata['app.version']
		def htmlBodyString = "<p>params this error page was called with: " + params + "</p>"
		htmlBodyString += "<p> username currently logged in: " + user?.username
		htmlBodyString += "<br> roles are: " + user?.authorities + "</p>"
		htmlBodyString += "<p> exception: " + params.exception + "</p>"
		
		//DEPLOY: enable this before sending to production
		log.debug("sending email")
		sendMail {
			from "john.harris@imail.org"
			to "eisa-repository-notify@imail.org"
			subject subjectString
			html htmlBodyString
		}
		
		flash.message = "ESA Team notified. We will try to respond within the next few hours. If it's urgent please contact (801) 442-5527 directly."
		
		log.debug("pass rendering to /confirmation.gsp")
		render(view:"/confirmation")
	}
}
