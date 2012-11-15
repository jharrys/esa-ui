package org.ihc.esa

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ESA_ADMIN', 'IS_AUTHENTICATED_FULLY'])
class AdminController {

	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
		log.debug("redirecting to admin action")
		redirect action: 'admin', params: params
	}

	def admin() {
		log.debug("in admin action of AdminController")
	}

	def items() {
		log.debug("redirect to item controller")
		redirect(controller: "item")
	}

	def lookupLists() {
		log.debug("redirect to lookupList controller")
		redirect(controller: "lookupList")
	}

	def lookupElements() {
		log.debug("redirect to lookupElement controller")
		redirect(controller: "lookupElement")
	}

	def documents() {
		log.debug("redirect to document controller")
		redirect(controller: "document")
	}

	def parties() {
		log.debug("redirect to party controller")
		redirect(controller: "party")
	}

	def addresses() {
		log.debug("redirect to address controller")
		redirect(controller: "address")
	}

	def exceptions() {
		log.debug("redirect to exception controller")
		redirect(controller: "exception")
	}



}
