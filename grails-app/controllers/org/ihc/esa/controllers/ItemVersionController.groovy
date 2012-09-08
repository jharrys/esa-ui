package org.ihc.esa.controllers

import org.ihc.esa.domain.ItemVersion
import org.springframework.dao.DataIntegrityViolationException

class ItemVersionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [itemVersionInstanceList: ItemVersion.list(params), itemVersionInstanceTotal: ItemVersion.count()]
    }

    def create() {
        [itemVersionInstance: new ItemVersion(params)]
    }

    def save() {
        def itemVersionInstance = new ItemVersion(params)
        if (!itemVersionInstance.save(flush: true)) {
            render(view: "create", model: [itemVersionInstance: itemVersionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), itemVersionInstance.id])
        redirect(action: "show", id: itemVersionInstance.id)
    }

    def show(Long id) {
        def itemVersionInstance = ItemVersion.get(id)
        if (!itemVersionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), id])
            redirect(action: "list")
            return
        }

        [itemVersionInstance: itemVersionInstance]
    }

    def edit(Long id) {
        def itemVersionInstance = ItemVersion.get(id)
        if (!itemVersionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), id])
            redirect(action: "list")
            return
        }

        [itemVersionInstance: itemVersionInstance]
    }

    def update(Long id, Long version) {
        def itemVersionInstance = ItemVersion.get(id)
        if (!itemVersionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (itemVersionInstance.version > version) {
                itemVersionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'itemVersion.label', default: 'ItemVersion')] as Object[],
                          "Another user has updated this ItemVersion while you were editing")
                render(view: "edit", model: [itemVersionInstance: itemVersionInstance])
                return
            }
        }

        itemVersionInstance.properties = params

        if (!itemVersionInstance.save(flush: true)) {
            render(view: "edit", model: [itemVersionInstance: itemVersionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), itemVersionInstance.id])
        redirect(action: "show", id: itemVersionInstance.id)
    }

    def delete(Long id) {
        def itemVersionInstance = ItemVersion.get(id)
        if (!itemVersionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), id])
            redirect(action: "list")
            return
        }

        try {
            itemVersionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'itemVersion.label', default: 'ItemVersion'), id])
            redirect(action: "show", id: id)
        }
    }
}
