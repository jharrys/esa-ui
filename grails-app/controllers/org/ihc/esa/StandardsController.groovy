package org.ihc.esa

import grails.plugins.springsecurity.Secured

import org.codehaus.groovy.grails.commons.GrailsApplication

class StandardsController
{
	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST', itemsInCategory: 'POST']
	
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
				a.name <=> b.name
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
		
		boolean result = false
		if (!ic.save(flush:true, failOnError:true)) {
			result = true
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
		
		boolean result = false
		
		if (!c.save(flush:true, failOnError:true)) {
			result = true
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
				List<Category> children = Category.findAllByParentCategory(c)
				
				String pathToReplace = c.parentCategoryPath + "/" + c.name
				String newPath = c.parentCategoryPath + "/" + params.categoryName
				
				c.name = params.categoryName
				
				children.each { cat ->
					log.debug("old path: " + cat.parentCategoryPath)
					cat.parentCategoryPath = cat.parentCategoryPath.replaceAll(pathToReplace, newPath)
					log.debug("new path: " + cat.parentCategoryPath)
					
					// FIXME: add transaction checking
					cat.save(flush:true)
				}
				
				if (c.save(flush:true)) {
					successMessage = "Category name changed successfully!" 
				} else {
					successMessage = "Category was not saved."
				} 
			}
		}
		
		render successMessage
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
		int result = 0
		def categories = Category.list()
		
		categories = categories.sort { a,b ->
			String aSlash = a.parentCategoryPath.equals("/") ? '' : '/'
			String bSlash = b.parentCategoryPath.equals("/") ? '' : '/'
			String aCombined = a.parentCategoryPath + aSlash + a.name
			String bCombined = b.parentCategoryPath + bSlash + b.name
			
			return aCombined.compareTo(bCombined)
		}
		
		[categories: categories]
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
