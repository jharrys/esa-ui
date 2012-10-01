package org.ihc.esa

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

class ExceptionController
{
	private final static int EXCEPTION_FORM=1
	
	static allowedMethods = [edit: ['GET', 'POST'], save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService
	
	def index()
	{
		log.debug("redirection to list action of ExceptionController")
		redirect(action: "list", params: params)
	}
	
	def list()
	{
		log.debug("EXCEPTION_FORM set to: " + EXCEPTION_FORM)
		def exceptionForm = Form.get(EXCEPTION_FORM)
		
		if (exceptionForm) log.debug("form of type exception<id:" + exceptionForm.id + "> acquired")
		
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[documentInstanceList: Document.findAllByForm(exceptionForm, [max: params.max, offset: params.offset, sort: "id"]), documentInstanceTotal: Document.count()]
	}
	
	def show()
	{
		def exceptionInstance = Document.get(params.id)
		if (!exceptionInstance)
		{
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'exception.label', default: 'Exception'), params.id])
			redirect action: 'list'
			return
		}
		
		[exceptionInstance: exceptionInstance]
	}
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def create()
	{
		log.debug("this is empty, do I need something here?")
	}
	
	/**
	 * this saves a brand new @see org.ihc.esa.Document
	 * and nothing else.
	 */
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def save() {
		
		// Setup Form type
		def exceptionForm = Form.get(EXCEPTION_FORM)
		if (exceptionForm == null) {
			flash.message = "Failed to find form type 'Exception'"
							render(view: "/error")
							return
		}
		
		// Setup Document Instance
		Document documentInstance = null
		
		def user = springSecurityService.currentUser
		documentInstance = new Document(form: exceptionForm, createdBy: user.username, updatedBy: user.username)
		
		if(!documentInstance.save()) {
			log.error("Could not save instance of document.")
			flash.message = "Could not save instance of document. Please try again."
			render(view: "create")
			return
		} else {
			log.debug("doc: " + documentInstance + " saved.")
		}

		// Setup Title with QuestionResponse table		
		def qr = new QuestionResponse(document: documentInstance, formField: documentInstance.titleFormField, createdBy: user.username, updatedBy: user.username, stringValue: params.title)
		log.debug("trying to save new qr. doc:<" + documentInstance + ">, formfield:<" + documentInstance.titleFormField + ">")
		
		if(!qr.save()) {
			log.error("Could not save Title <" + params.title + "> to QuestionResponse table.")
			qr.errors.allErrors.each { log.error(it) }
			flash.message = "Could not save Title <" + params.title + "> to QuestionResponse table."
			render(view: "create")
			return
		}
		
		flash.message = "New exception titled \"" + params.title + "\" initialized."
		
		// Setup section numbers
		def listOfSectionNumbers = 'select distinct ff.sectionNumber from FormField ff where ff.form=' + exceptionForm.id + ' order by ff.sectionNumber asc'
		ArrayDeque sectionStack = FormField.executeQuery(listOfSectionNumbers)
		def currentSection = sectionStack.pop()
		
		log.debug("documentInstance: " + documentInstance.id)
		
		def sectionTitle = FormField.findByFormAndSectionNumberAndDataType(exceptionForm, currentSection, "SectionHeader").question
		log.debug("sectionTitle: \"" + sectionTitle + "\"")
		
		log.debug("Delegating rendering to save_section.gsp...")
		render(view: "save_section", model: [documentInstance: documentInstance, formid: exceptionForm.id, section: currentSection, sectionStack: sectionStack,
			formFields: FormField.findAllByFormAndSectionNumber(exceptionForm, currentSection, [sort: "id"]), sectionTitle: sectionTitle])
	}
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def save_section() {
		
		log.debug("save_section in exception controller called with: " + params)
		
		// TODO NPE check needed here
		def documentInstance = Document.get(params.id)
		
		// TODO NPE check needed here
		def exceptionForm = Form.get(params.formid)
		
		// TODO NPE check needed here
		ArrayDeque sectionStack = new ArrayDeque(params.list("sectionStack"))
		
		if (!sectionStack.isEmpty()) {
			def currentSection = sectionStack.pop()
			
			def sectionTitle = FormField.findByFormAndSectionNumberAndDataType(exceptionForm, currentSection, "SectionHeader").question
			log.debug("sectionTitle: \"" + sectionTitle + "\"")
			
			render(view: "save_section", model: [documentInstance: documentInstance, formid: exceptionForm.id, section: currentSection, sectionStack: sectionStack,
				formFields: FormField.findAllByFormAndSectionNumber(exceptionForm, currentSection, [sort: "id"]), sectionTitle: sectionTitle])
		} else {
			log.debug("documentInstance id: " + documentInstance)
			flash.message = "Exception successfully submitted."
			redirect action: 'show', id: documentInstance.id
		}
	}
	
	def edit() {
		switch (request.method) {
		case 'GET':
	        def documentInstance = Document.get(params.id)
	        if (!documentInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [documentInstance: documentInstance, username: principal.username]
			break
		case 'POST':
			log.debug("Updating document id: " + params.id)
	        def documentInstance = Document.get(params.id)
	        if (!documentInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
	            redirect action: 'list'
	            return
	        }

			def title = params.title
			log.debug("Web form title is set to: " + title)
			if (!title.equals(documentInstance.title)) {
				log.debug("Document <" + params.id + "> title is \"" + documentInstance.title + "\"; Updating to: \"" + title + "\"")
				log.debug("FormField to find: " + documentInstance.titleFormField)
				def qr = QuestionResponse.findByFormFieldAndDocument(documentInstance.titleFormField, documentInstance)
				log.debug("QuestionResponse id to update is: " + qr.id)
				qr.stringValue = title
				qr.save()
			}
			
	        documentInstance.properties = params

	        if (!documentInstance.save(flush: true)) {
	            render view: 'edit', model: [documentInstance: documentInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'document.label', default: 'Document'), documentInstance.id])
	        redirect action: 'show', id: documentInstance.id
			break
		}
    }
	
	def update() {
		def documentInstance = Document.get(params.id)
		if (!documentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "list")
			return
		}
		
		if (params.version) {
			def version = params.version.toLong()
			if (documentInstance.version > version) {
				documentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
				[message(code: 'document.label', default: 'Document')] as Object[],
				"Another user has updated this Document while you were editing")
				render(view: "edit", model: [documentInstance: documentInstance])
				return
			}
		}
		
		documentInstance.properties = params
		
		if (!documentInstance.save(flush: true)) {
			render(view: "edit", model: [documentInstance: documentInstance])
			return
		}
		
		flash.message = message(code: 'default.updated.message', args: [message(code: 'document.label', default: 'Document'), documentInstance.id])
		redirect(action: "show_old", id: documentInstance.id)
	}
	
	def delete() {
		def documentInstance = Document.get(params.id)
		if (!documentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "list")
			return
		}
		
		try {
			def title = documentInstance.title
			documentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'document.label', default: 'Document'), title])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "show", id: params.id)
		}
	}
	
	def error() {
		//TODO enable this before sending to production
//		sendMail {
//			to "eisa-repository-notify@imail.org"
//			subject "esa-ui error"
//			body flash.message + "\n" + params.exception
//		}
		
		flash.message = "ESA Team notified. We will try to respond within the next few hours. If it's urgent please contact (801) 442-5527 directly."
		
		render(view:"/confirmation")
	}
}
