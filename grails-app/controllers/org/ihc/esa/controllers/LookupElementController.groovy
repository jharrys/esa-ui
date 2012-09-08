package org.ihc.esa.controllers

import org.ihc.esa.domain.LookupElement;
import org.springframework.dao.DataIntegrityViolationException

class LookupElementController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [lookupElementInstanceList: LookupElement.list(params), lookupElementInstanceTotal: LookupElement.count()]
    }

    def create() {
        [lookupElementInstance: new LookupElement(params)]
    }

    def save() {
        def lookupElementInstance = new LookupElement(params)
        if (!lookupElementInstance.save(flush: true)) {
            render(view: "create", model: [lookupElementInstance: lookupElementInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), lookupElementInstance.id])
        redirect(action: "show", id: lookupElementInstance.id)
    }

    def show(Long id) {
        def lookupElementInstance = LookupElement.get(id)
        if (!lookupElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), id])
            redirect(action: "list")
            return
        }

        [lookupElementInstance: lookupElementInstance]
    }

    def edit(Long id) {
        def lookupElementInstance = LookupElement.get(id)
        if (!lookupElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), id])
            redirect(action: "list")
            return
        }

        [lookupElementInstance: lookupElementInstance]
    }

    def update(Long id, Long version) {
        def lookupElementInstance = LookupElement.get(id)
        if (!lookupElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (lookupElementInstance.version > version) {
                lookupElementInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'lookupElement.label', default: 'LookupElement')] as Object[],
                          "Another user has updated this LookupElement while you were editing")
                render(view: "edit", model: [lookupElementInstance: lookupElementInstance])
                return
            }
        }

        lookupElementInstance.properties = params

        if (!lookupElementInstance.save(flush: true)) {
            render(view: "edit", model: [lookupElementInstance: lookupElementInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), lookupElementInstance.id])
        redirect(action: "show", id: lookupElementInstance.id)
    }

    def delete(Long id) {
        def lookupElementInstance = LookupElement.get(id)
        if (!lookupElementInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), id])
            redirect(action: "list")
            return
        }

        try {
            lookupElementInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), id])
            redirect(action: "show", id: id)
        }
    }
}
