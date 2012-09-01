package org.ihc.esa.controllers

import org.ihc.esa.domain.Document
import org.ihc.esa.domain.Form
import org.ihc.esa.domain.FormField
import org.springframework.dao.DataIntegrityViolationException

class ExceptionController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index() {
		redirect(action: "list", params: params)
	}
	
	def list() {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[documentInstanceList: Document.list(params), documentInstanceTotal: Document.count()]
	}
	
	def create() {
		// TODO fix hard coded form for exception
		// TODO add error trapping for when form 1 is not found
		def f = Form.get(1)	// 1 is seeded in the FORM table as the exception form
		// use a deque for thisFormSectionList so you can pop of each one as they come through. will need to order by desc because of LIFO
		if (f == null) {
			flash.message = "Failed to find Form 1, which is the Exception Form"
			//render(status: 503, text: 'Failed to find Form 1, designated as the Exception Form')
		} else {
			def query = 'select distinct ff.sectionNumber from FormField ff where ff.form=' + f.id + ' order by ff.sectionNumber asc'
			ArrayDeque sectionStack = FormField.executeQuery(query)
			def currentSection = sectionStack.pop()
			[documentInstance: new Document(params), formid: f.id, section: currentSection, sectionStack: sectionStack, formFields: FormField.findAllByFormAndSectionNumber(f, currentSection)]
		}
	}
	
	def create_next() {
		// TODO NPE check needed here
		def document = params.document
		// TODO NPE check needed here
		def form = Form.get(params.formid)
		// TODO NPE check needed here
		ArrayDeque sectionStack = new ArrayDeque(params.list("sectionStack"))
		def currentSection = sectionStack.pop()
		// TODO in gsp add check for sectionStack.peek() == null OR sectionStack.empty() ... if null or empty then set form button to save.
		
		render(view: "create", model: [document: document, formid: form.id, section: currentSection, sectionStack: sectionStack, formFields: FormField.findAllByFormAndSectionNumber(form, currentSection)])
	}
	
	def save() {
		def documentInstance = new Document(params)
		if (!documentInstance.save(flush: true)) {
			render(view: "create", model: [documentInstance: documentInstance])
			return
		}
		
		flash.message = message(code: 'default.created.message', args: [message(code: 'document.label', default: 'Document'), documentInstance.id])
		redirect(action: "show", id: documentInstance.id)
	}
	
	def show() {
		def documentInstance = Document.get(params.id)
		if (!documentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "list")
			return
		}
		
		[documentInstance: documentInstance]
	}
	
	def edit() {
		def documentInstance = Document.get(params.id)
		if (!documentInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), params.id])
			redirect(action: "list")
			return
		}
		
		[documentInstance: documentInstance]
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
		redirect(action: "show", id: documentInstance.id)
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
			redirect(action: "show", id: params.id)
		}
	}
}
