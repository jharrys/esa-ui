package org.ihc.esa

import org.ihc.esa.LookupList;
import org.springframework.dao.DataIntegrityViolationException

class LookupListController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [lookupListInstanceList: LookupList.list(params), lookupListInstanceTotal: LookupList.count()]
    }

    def create() {
        [lookupListInstance: new LookupList(params)]
    }

    def save() {
        def lookupListInstance = new LookupList(params)
        if (!lookupListInstance.save(flush: true)) {
            render(view: "create", model: [lookupListInstance: lookupListInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lookupList.label', default: 'LookupList'), lookupListInstance.id])
        redirect(action: "show", id: lookupListInstance.id)
    }

    def show(Long id) {
        def lookupListInstance = LookupList.get(id)
        if (!lookupListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), id])
            redirect(action: "list")
            return
        }

        [lookupListInstance: lookupListInstance]
    }

    def edit(Long id) {
        def lookupListInstance = LookupList.get(id)
        if (!lookupListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), id])
            redirect(action: "list")
            return
        }

        [lookupListInstance: lookupListInstance]
    }

    def update(Long id, Long version) {
        def lookupListInstance = LookupList.get(id)
        if (!lookupListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (lookupListInstance.version > version) {
                lookupListInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'lookupList.label', default: 'LookupList')] as Object[],
                          "Another user has updated this LookupList while you were editing")
                render(view: "edit", model: [lookupListInstance: lookupListInstance])
                return
            }
        }

        lookupListInstance.properties = params

        if (!lookupListInstance.save(flush: true)) {
            render(view: "edit", model: [lookupListInstance: lookupListInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lookupList.label', default: 'LookupList'), lookupListInstance.id])
        redirect(action: "show", id: lookupListInstance.id)
    }

    def delete(Long id) {
        def lookupListInstance = LookupList.get(id)
        if (!lookupListInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), id])
            redirect(action: "list")
            return
        }

        try {
            lookupListInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lookupList.label', default: 'LookupList'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lookupList.label', default: 'LookupList'), id])
            redirect(action: "show", id: id)
        }
    }
}
