package org.ihc.esa

import org.springframework.dao.DataIntegrityViolationException

class DocumentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [documentInstanceList: Document.list(params), documentInstanceTotal: Document.count()]
    }

    def create() {
        [documentInstance: new Document(params)]
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

    def show(Long id) {
        def documentInstance = Document.get(id)
        if (!documentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), id])
            redirect(action: "list")
            return
        }

        [documentInstance: documentInstance]
    }

    def edit(Long id) {
        def documentInstance = Document.get(id)
        if (!documentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), id])
            redirect(action: "list")
            return
        }

        [documentInstance: documentInstance]
    }

    def update(Long id) {
        def documentInstance = Document.get(id)
        if (!documentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), id])
            redirect(action: "list")
            return
        }

        documentInstance.properties = params

        if (!documentInstance.save(flush: true)) {
            render(view: "edit", model: [documentInstance: documentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'document.label', default: 'Document'), documentInstance.id])
        redirect(action: "show", id: documentInstance.id)
    }

    def delete(Long id) {
        def documentInstance = Document.get(id)
        if (!documentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'document.label', default: 'Document'), id])
            redirect(action: "list")
            return
        }

        try {
            documentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'document.label', default: 'Document'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'document.label', default: 'Document'), id])
            redirect(action: "show", id: id)
        }
    }
}
