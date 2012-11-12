package org.ihc.esa

import grails.plugins.springsecurity.Secured

class StandardsController
{
	static allowedMethods = [addItemToCategory: 'POST', addNewItem: ['GET', 'POST'], cancel: 'POST', deleteItem: 'POST', editByCategory: 'GET',
		editItem: ['GET', 'POST'], error: 'GET', index: 'GET', itemsInCategory: 'POST', list: 'GET', removeItemFromCategory: 'POST', renameCategory: 'POST']

	def springSecurityService

	def index()
	{
		redirect action: 'list', params: params
	}

	def list()
	{
		params.max = Math.min(params.max ? params.int('max') : 20, 100)
		params.sort = params.sort ?: 'name'
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

		Item item = null
		Category category = null

		if (!params.itemId) {
			log.error("itemId was null so sending 412 error code")
			// http response code 412 is Precondition Failed - the request evaluated to false (null item)
			render status: 412, text: "No item was selected."
			return
		} else {
			item = Item.get(params.itemId)
			log.debug("found item \"" + item?.name + "\"")
			if (!item) {
				log.error("sending response code 406 because we couldn't find the item with id " + params.itemId)
				// http response code 406 is Not Acceptable - the request evaluated to false (null item)
				render status: 406, text: "The item you selected was not found, please refresh page."
				return
			}
		}

		if (!params.toCategoryId) {
			log.error("toCategoryId was null, so sending 412 code.")
			// http response code 412 is Precondition Failed - the request evaluated to false (null item)
			render status: 412, text: "No category was sent."
			return
		} else {
			category = Category.get(params.toCategoryId)
			log.debug("found category \"" + category?.name + "\"")
			if (!category) {
				log.error("sending response code 406 because we couldn't find the category with id " + params.toCategoryId)
				// http response code 406 is Not Acceptable - the request evaluated to false (null item)
				render status: 406, text: "The category you selected was not found, please refresh page."
				return
			}
		}

		log.debug(item)
		log.debug(category)

		ItemCategory ic = new ItemCategory(item: item, category: category, createdBy: user.username, updatedBy: user.username)

		if (ic.save(flush:true, failOnError:true)) {
			flash.message = item.name + " was successfully associated with " + category.name
			render status: 200
			return
		} else {
			flash.message = "An error occurred attempting to associate item with id \"" + item.id + "\" to category with id \"" + category.id + "\""
			render status: 500
			return
		}
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

		Item item = null
		Category category = null

		if (!params.itemId) {
			log.error("itemId was null so sending 412 error code")
			// http response code 412 is Precondition Failed - the request evaluated to false (null item)
			render status: 412, text: "No item was selected."
			return
		} else {
			item = Item.get(params.itemId)
			log.debug("found item \"" + item?.name + "\"")
			if (!item) {
				log.error("sending response code 406 because we couldn't find the item with id " + params.itemId)
				// http response code 406 is Not Acceptable - the request evaluated to false (null item)
				render status: 406, text: "The item you selected was not found, please refresh page."
				return
			}
		}

		if (!params.fromCategoryId) {
			log.error("fromCategoryId was null, so sending 412 code.")
			// http response code 412 is Precondition Failed - the request evaluated to false (null item)
			render status: 412, text: "No category was sent."
			return
		} else {
			category = Category.get(params.fromCategoryId)
			log.debug("found category \"" + category?.name + "\"")
			if (!category) {
				log.error("sending response code 406 because we couldn't find the category with id " + params.fromCategoryId)
				// http response code 406 is Not Acceptable - the request evaluated to false (null item)
				render status: 406, text: "The category you selected was not found, please refresh page."
				return
			}
		}

		category.removeFromItems(item)

		if (!category.save(flush:true, failOnError:true)) {
			flash.message = "succeeded removing item from category"
		} else {
			flash.message = "error removing item from category"
		}
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
			Category c = Category.get(params.categoryId)

			if (c.name.equals(params.categoryName)) {
				successMessage = "Submitted name is not different, no change has been made."
			} else {
				List<Category> children = Category.list()

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

				boolean error = false
				Item item = new Item(params)

				if (!item.save(flush:true)) {
					Date d = new Date()
					log.error("user: " + user?.username + " attempted to create a new item, but the item failed to save.")
					log.error("params are: " + params)
					flash.message = item.name + " could not be saved. The error was logged at " + d
					render status: 500
					return
				} else {
					log.debug("successfully saved: \"" + item.name + "\"")
					// allow instruction to fall through to next if
				}

				/*
				 * if a category was submitted and successfully looked up in the database then user wants to add the item and associate it with
				 * a particular category.
				 */
				if (category) {
					ItemCategory ic = new ItemCategory(createdBy: params.createdBy, updatedBy: params.updatedBy)
					ic.item = item
					ic.category = category

					if (!ic.save(flush:true)) {
						Date d = new Date()
						log.error("user: " + user?.username + " was able to create item " + item.name + " however the service failed to save to join table item_category")
						log.error("error saving \"" + item.name + "\" to \"" + category.name + "\"")
						flash.message = item.name + " was saved, but could not be associated with the category " + category.name + ". This error occurred at " + d
						render status: 500
						return
					} else {
						log.debug("successfully saved: \"" + item.name + "\" to \"" + category.name + "\"")
						render status: 201, text: item.name + " created successfully with ID of " + item.id + " and associated with category " + category.name
						return
					}
				} else {
					// since we're here then item saved correctly and so did item_category
					render status: 201, text: item.name + " was created successfully with ID of " + item.id
				}

				break
		}

	}

	def editItem() {

		def user = null
		user = springSecurityService.currentUser

		log.debug("====================================================================================")
		log.debug("editItem() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")

		switch (request.method) {
			case 'GET':
				// some calls may specify id (show), others may be itemId (editByCategory)
				def itemId = params.id ?: params.itemId
				log.debug("in GET method retrieving item with id " + itemId)
				Item itemInstance = Item.get(itemId)
				render view: 'editItem', model: [itemInstance: itemInstance]
				break

			case 'POST':
				log.debug("in POST method pretending to save an item")
				render view: 'editByCategory'
				break
		}

	}

	def deleteItem() {

		def user = null
		user = springSecurityService.currentUser

		log.debug("====================================================================================")
		log.debug("deleteItem() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")

		switch (request.method) {
			case 'POST':
				//some calls may specify id (show), others may be itemId (editByCategory)
				def itemId = params.id ?: params.itemId

				log.debug("in POST method getting ready to delete item \"" + itemId + "\"")

				Item item = null

				if (!itemId) {
					log.error("itemId was null so sending 412 error code")
					// http response code 412 is Precondition Failed - the request evaluated to false (null item)
					render status: 412, text: "No item was selected."
					return
				} else {
					item = Item.get(itemId)
					log.debug("found item \"" + item?.name + "\"")
					if (!item) {
						log.error("sending response code 406 because we couldn't find the item with id " + params.itemId)
						// http response code 406 is Not Acceptable - the request evaluated to false (null item)
						render status: 406, text: "The item you selected was not found, please refresh page."
						return
					}
				}

				List<ItemCategory> icList = ItemCategory.findAllByItem(item)
				for (ItemCategory ic in icList) {
					ic.delete(flush: true)
				}

				item.delete(flush: true)

				render status: 200
				break
		}

	}

	def cancel() {
		def user = null
		user = springSecurityService.currentUser

		log.debug("====================================================================================")
		log.debug("cancel() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")

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

	def show() {
		def user = null
		user = springSecurityService.currentUser

		log.debug("====================================================================================")
		log.debug("show() in standards controller called with params: " + params)
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")

		//some calls may specify id (show), others may be itemId (editByCategory)
		def itemId = params.id ?: params.itemId

		Item item = null

		if (!itemId) {
			log.error("itemId was null so sending 412 error code")
			// http response code 412 is Precondition Failed - the request evaluated to false (null item)
			render status: 412, text: "No item was selected."
			return
		} else {
			item = Item.get(itemId)
			log.debug("found item \"" + item?.name + "\"")
			if (!item) {
				log.error("sending response code 406 because we couldn't find the item with id " + params.itemId)
				// http response code 406 is Not Acceptable - the request evaluated to false (null item)
				redirect action: 'list'
				return
			}
		}

		[itemInstance: item]
	}
}
