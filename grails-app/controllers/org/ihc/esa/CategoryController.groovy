package org.ihc.esa

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

class CategoryController {

	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

	def springSecurityService

	def index() {
		redirect action: 'list', params: params
	}

	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		params.sort = params.sort ?: "id"
		params.order = params.order ?: "asc"
		[categoryInstanceList: Category.list(params), categoryInstanceTotal: Category.count()]
	}

	@Secured(['ROLE_ESA_CONTENT_CONTRIBUTOR', 'ROLE_ESA_CONTENT_ADMIN', 'ROLE_ESA_ADMIN', 'IS_AUTHENTICATED_FULLY'])
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

	@Secured(['ROLE_ESA_CONTENT_CONTRIBUTOR', 'ROLE_ESA_CONTENT_ADMIN', 'ROLE_ESA_ADMIN', 'IS_AUTHENTICATED_FULLY'])
	def edit() {

		def user = springSecurityService.currentUser

		log.debug("====================================================================================")
		log.debug("edit() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_USER or ROLE_ESA_ADMIN")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")

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

	@Secured(['ROLE_ESA_CONTENT_ADMIN', 'ROLE_ESA_ADMIN', 'IS_AUTHENTICATED_FULLY'])
	def delete() {

		def user = springSecurityService.currentUser

		log.debug("====================================================================================")
		log.debug("delete() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_USER or ROLE_ESA_ADMIN")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")

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

	def cancel() {
		log.debug("====================================================================================")
		log.debug("cancel() action in exception controller called with: " + params)
		log.debug("====================================================================================")

		flash.message = "Edit canceled by user"

		redirect action: 'list'
	}
}
