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
	
	def itemsInCategoryList() {
		log.debug("listing items in category")
		log.debug("params are: " + params)
		
		Category c = Category.get(params.catId)
		grails.converters.JSON itemList = c.items as grails.converters.JSON
		
		log.debug(itemList)
		
		render (itemList)
	}
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def addItemToCategory() {
		def user = null
		user = springSecurityService.currentUser
		
		log.debug(user.username)
		
		log.error("====================================================================================")
		log.error("addItemToCategory() in standards controller called with params: " + params)
		log.error("username == " + user?.username)
		log.error("roles == " + user?.authorities)
		log.error("====================================================================================")
		
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
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def removeItemFromCategory() {
		
		def user = null
		user = springSecurityService.currentUser
		
		log.error("====================================================================================")
		log.error("removeItemFromCategory() in standards controller called with params: " + params)
		log.error("username == " + user?.username)
		log.error("roles == " + user?.authorities)
		log.error("====================================================================================")
		
		Item i = Item.get(params.itemId)
		Category c = Category.get(params.fromCategory)
		
		c.removeFromItems(i)
		
		boolean result = false
		
		if (!c.save(flush:true)) {
			result = true
		}
		
		render result
	}
	
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
		
		log.error("====================================================================================")
		log.error("error() in standards controller called with params: " + params)
		log.error("username == " + user?.username)
		log.error("roles == " + user?.authorities)
		log.error("====================================================================================")
		
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
