package org.ihc.esa

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

class ExceptionController
{
	private final Form EXCEPTION_FORM
	
	static allowedMethods = [edit: ['GET', 'POST'], save: "POST", update: "POST", delete: "POST"]
	
	def springSecurityService
	
	ExceptionController() {
		EXCEPTION_FORM = Form.findByNameIlike("exception")
	}
	
	def index()
	{
		log.debug("====================================================================================")
		log.debug("index() action in exception controller called with: " + params)
		log.debug("====================================================================================")
		
		log.debug("redirection to list action of ExceptionController")
		redirect(action: "list", params: params)
	}
	
	def list()
	{
		log.debug("====================================================================================")
		log.debug("list() action in exception controller called with: " + params)
		log.debug("====================================================================================")
		
		log.debug("EXCEPTION_FORM set to: " + EXCEPTION_FORM)
		def exceptionForm = EXCEPTION_FORM
		
		if (exceptionForm) log.debug("form " + exceptionForm.id + " found, called \"" + exceptionForm.name + "\"")
		
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
		def user = null
		if (log.debugEnabled) {
			user = springSecurityService.currentUser
		}

		log.debug("====================================================================================")
		log.debug("create() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_USER or ROLE_ESA_ADMIN")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		log.debug("pass rendering to create.gsp")
	}
	
	/**
	 * this saves a brand new @see org.ihc.esa.Document
	 * and nothing else.
	 */
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def save()
	{
		def user = null
		if (log.debugEnabled) {
			user = springSecurityService.currentUser
		}
		
		log.debug("====================================================================================")
		log.debug("save() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_USER or ROLE_ESA_ADMIN")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		// Setup Form type
		def exceptionForm = EXCEPTION_FORM
		if (exceptionForm == null) {
			log.error("EXCEPTION_FORM does not exist. Check that the FORM table contains a FORM with the NAME value of \"EXCEPTION\"")
			flash.message = "Failed to find form type 'Exception'"
			render(view: "/error")
			return
		}
		
		// Setup Document Instance
		Document documentInstance = null
		
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
		
		// user profile authenticated to this instance
		def user = null
		if (log.debugEnabled) {
			user = springSecurityService.currentUser
		}

		log.debug("====================================================================================")
		log.debug("save_section() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_USER or ROLE_ESA_ADMIN")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
		// TODO NPE check needed here
		def documentInstance = Document.get(params.id)
		
		// TODO NPE check needed here
		def exceptionForm = EXCEPTION_FORM
		
		// TODO NPE check needed here
		ArrayDeque sectionStack = new ArrayDeque(params.list("sectionStack"))
		
		
		/***********************************
		 *  Perform save
		 **********************************/
		Map map = new HashMap()
		log.debug("[[ parsing params for qr*-value and qr*-type ]]")
		params.entrySet().findAll {
			it.key ==~ /qr.*-value/
		}.each {
			def qr = it.key.split("-")[0]
			def value = params[qr + "-value"] ?: "null"
			def type = params[qr + "-type"]
			def qrid = qr.split("qr")[1]
			def m = [type: type, value: value]
			map["$qrid"] = m
			log.debug("map: " + map)
		}

		log.debug("[[ begin parsing for each formfield ]]")		
		map.each { key, m ->
			log.debug("=== formField: " + key + " ===")
			log.debug("dataType == \"" + m.type + "\"")
			log.debug("value == \"" + m.value + "\"")
			def formField = FormField.get(key)
			def qr = null
			
			if ((m.value != null) && (!m.value.equals("null"))) {
				log.debug("m.value is valid, switching on m.type")
				switch(m.type.toUpperCase()) {
					case "DATE_VALUE":
						log.debug("case of " + m.type)
						qr = new QuestionResponse(document: documentInstance, formField: formField, createdBy: user.username, updatedBy: user.username, 
							dateValue: m.value)
						log.debug("QuestionResponse instance created for " + key)
						break
						
					case "FLOAT_VALUE":
						log.debug("case of " + m.type)
						qr = new QuestionResponse(document: documentInstance, formField: formField, createdBy: user.username, updatedBy: user.username,
							floatValue: m.value)
						log.debug("QuestionResponse instance created for " + key)
						break
						
					case "STRING_VALUE":
						log.debug("case of " + m.type)
					
					default:
						log.debug("case of \"default\" switch")
						if ((m.value) && (!m.value.equals("null")) && ((!m.value.equals("type here")))) {
							log.debug("test for valid m.value passed")
							qr = new QuestionResponse(document: documentInstance, formField: formField, createdBy: user.username, updatedBy: user.username,
								stringValue: m.value)
							log.debug("QuestionResponse instance created for " + key)
						} else {
							log.debug("test for valid m.value did **not** pass")
							log.debug("QuestionResponse instance was **not** created for " + key)
						}
				}
				
				if (qr != null) {
					log.debug("assertion that qr is not null passed -> attempting to save qr instance now...")
					
					//TODO need to flag in database so that user can come back and correct later
					if(!qr.save()) {
						log.debug("qr instance for formField " + key + " was **not** saved due to an error condition")
						log.debug("answer value (m.value) == \"" + m.value + "\"")
						log.error("error saving to QuestionResponse table for FormField " + key + " with value \"" + m.value + "\"")
						qr.errors.allErrors.each { log.error(it) }
						flash.message = "Error saving to QuestionResponse table for FormField " + key + " with value \"" + m.value + "\""
					} else {
						log.debug("QuestionResponse instance with id " + qr.id + " has been saved for Document " + qr.document.id + 
							" with FormField of " + qr.formField.id + " and value of \"" + qr.value + "\"")
					}
				} else {
					log.debug("assertion that qr is not null failed! -> this may be legitimate, user may not have filled in a non-required field.")
				}
			} else {
				log.debug("QuestionResponse **not** saved! formField " + key + " did not have an answer, answer was null or answer was default text:\"" + m.value + "\"")
			}
		}
		
		/***********************************
		 *  Prepare model for view
		 **********************************/
		
		log.debug("====================================================================================")
		log.debug("Preparing model for view")
		log.debug("====================================================================================")
		
		if (!sectionStack.isEmpty()) {
			def currentSection = sectionStack.pop()
			
			def sectionTitle = FormField.findByFormAndSectionNumberAndDataType(exceptionForm, currentSection, "SectionHeader").question
			log.debug("found sectionTitle: \"" + sectionTitle + "\"")
			
			log.debug("submitting to save_section view for rendering.")
			render(view: "save_section", model: [documentInstance: documentInstance, formid: exceptionForm.id, section: currentSection, sectionStack: sectionStack,
				formFields: FormField.findAllByFormAndSectionNumber(exceptionForm, currentSection, [sort: "id"]), sectionTitle: sectionTitle])
		} else {
			log.debug("no more sections to present for form id " + exceptionForm.id + " and document id: " + documentInstance.id)
			flash.message = "Exception ID " + documentInstance.id + " \"" + documentInstance.title + "\" successfully completed.<br>Please note ID for future reference."
			
			log.debug("redirecting to show view for document " + documentInstance.id)
			redirect action: 'show', id: documentInstance.id
		}
	}
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def edit() {
		
		// user profile authenticated to this instance
		def user = null
		if (log.debugEnabled) {
			user = springSecurityService.currentUser
		}

		log.debug("====================================================================================")
		log.debug("edit() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_USER or ROLE_ESA_ADMIN")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
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
	
	@Secured(['ROLE_ESA_USER', 'ROLE_ESA_ADMIN'])
	def update() {
		
		// user profile authenticated to this instance
		def user = null
		if (log.debugEnabled) {
			user = springSecurityService.currentUser
		}

		log.debug("====================================================================================")
		log.debug("update() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_USER or ROLE_ESA_ADMIN")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
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
	
	@Secured(['ROLE_ESA_ADMIN', 'IS_AUTHENTICATED_FULLY'])
	def delete() {
		
		// user profile authenticated to this instance
		def user = null
		if (log.debugEnabled) {
			user = springSecurityService.currentUser
		}

		log.debug("====================================================================================")
		log.debug("save_section() action in exception controller called with: " + params)
		log.debug("requires ROLE_ESA_ADMIN and user must be FULLY_AUTHENTICATED")
		log.debug("username == " + user?.username)
		log.debug("roles == " + user?.authorities)
		log.debug("====================================================================================")
		
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
