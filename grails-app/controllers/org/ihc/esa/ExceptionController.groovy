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
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def create()
	{
		log.debug("this is empty, do I need something here?")
		// do something here if needed.
	}
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def old_create()
	{
		// TODO fix hard coded form for exception
		// TODO add error trapping for when form 1 is not found
		def exceptionForm = Form.get(1)	// 1 is seeded in the FORM table as the exception form
		// use a deque for thisFormSectionList so you can pop of each one as they come through. will need to order by desc because of LIFO
		if (exceptionForm == null) {
			//flash.message = "Failed to find Form 1, which is the Exception Form"
			render(status: 503, text: 'Failed to find Form 1, designated as the Exception Form')
		} else {
		
			// setup section numbers
			def listOfSectionNumbers = 'select distinct ff.sectionNumber from FormField ff where ff.form=' + exceptionForm.id + ' order by ff.sectionNumber asc'
			ArrayDeque sectionStack = FormField.executeQuery(listOfSectionNumbers)
			def currentSection = sectionStack.pop()
			
			// setup new exception document
			String requestorString = "-individual filling out this form-"					// Get from spring-security
			String requestorEmailString = "-email of individual filling out this form-"		// Get from spring-security
			String createdByString = requestorString										// Get from spring-security
			String updatedByString = requestorString										// Get from spring-security
			String ownerString = "-owner of the system-"									// Needs a preliminary screen
			String ownerEmailString = "-email of owner of the system-"						// Needs a preliminary screen
			String justificationString = "-this needs to be modified-"
			
			Party party = Party.get(1)
			
			Document doc = new Document(form:exceptionForm, requestor: requestorString, requestorEmail: requestorEmailString, owner: ownerString, 
				ownerEmail: ownerEmailString, createdBy: createdByString, updatedBy: updatedByString, justification: justificationString, 
				vendorRepresentativeParty: party)
			doc.save()
			
			
			[documentInstance: doc, formid: exceptionForm.id, section: currentSection, sectionStack: sectionStack,
				formFields: FormField.findAllByFormAndSectionNumber(exceptionForm, currentSection, [sort: "id"])]
		}
	}
	
	def show() {
		def exceptionInstance = Document.get(params.id)
		if (!exceptionInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'exception.label', default: 'Exception'), params.id])
			redirect action: 'list'
			return
		}

		[exceptionInstance: exceptionInstance]
	}
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def save_section() {
		// TODO NPE check needed here
		def document = params.document
		// TODO NPE check needed here
		def form = Form.get(params.formid)
		// TODO NPE check needed here
		ArrayDeque sectionStack = new ArrayDeque(params.list("sectionStack"))
		if (!sectionStack.isEmpty()) {
			def currentSection = sectionStack.pop()
			render(view: "create", model: [document: document, formid: form.id, section: currentSection, sectionStack: sectionStack,
				formFields: FormField.findAllByFormAndSectionNumber(form, currentSection, [sort: "id"])])
		} else {
			render("Form Submitted!")
		}
	}
	
	/**
	 * this saves a brand new @see org.ihc.esa.Document
	 * and nothing else.
	 */
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def save() {
		def exceptionForm = Form.get(1) // TODO fix this
		def party = Party.get(1) 		// TODO remove this requirement
		Document doc = null
		
		if (exceptionForm == null) {
			//flash.message = "Failed to find Form 1, which is the Exception Form"
			render(status: 503, text: 'Failed to find Form 1, designated as the Exception Form')
			return
		}
		
		def user = springSecurityService.currentUser
		doc = new Document(form: exceptionForm, requestor: params.requestor, requestorEmail: params.requestorEmail, owner: params.owner,
			ownerEmail: params.ownerEmail, createdBy: user.username, updatedBy: user.username, justification: params.justification,
			vendorRepresentativeParty: party)
		doc.save()
		render(text: "Succeeded!")
	}
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def old_save() {
		def documentInstance = new Document(params)
		if (!documentInstance.save(flush: true)) {
			render(view: "create", model: [documentInstance: documentInstance])
			return
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'document.label', default: 'Document'), documentInstance.id])
		redirect(action: "show_old", id: documentInstance.id)
	}
	
	def show_old() {
		def documentInstance = Document.get(params.id)
		if (!documentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "list")
			return
		}
		
		[documentInstance: documentInstance]
	}
	
	def edit() {
		switch (request.method) {
			case 'GET':
				def exceptionInstance = Document.get(params.id)
				if (!exceptionInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'exception.label', default: 'Exception'), params.id])
					redirect action: 'list'
					return
				}
	
				[exceptionInstance: exceptionInstance]
				break
				
			case 'POST':
				def exceptionInstance = Document.get(params.id)
				if (!exceptionInstance) {
					flash.message = message(code: 'default.not.found.message', args: [message(code: 'exception.label', default: 'Exception'), params.id])
					redirect action: 'list'
					return
				}
	
				exceptionInstance.properties = params
	
				if (!exceptionInstance.save(flush: true)) {
					render view: 'edit', model: [exceptionInstance: exceptionInstance]
					return
				}
	
				flash.message = message(code: 'default.updated.message', args: [message(code: 'exception.label', default: 'Exception'), exceptionInstance.id])
				redirect action: 'show', id: exceptionInstance.id
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
			documentInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "show_old", id: params.id)
		}
	}
}
