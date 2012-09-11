package org.ihc.esa.controllers

import grails.plugins.springsecurity.Secured

import org.ihc.esa.domain.EsaRole
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class EsaRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [esaRoleInstanceList: EsaRole.list(params), esaRoleInstanceTotal: EsaRole.count()]
    }

    def create() {
        [esaRoleInstance: new EsaRole(params)]
    }

    def save() {
        def esaRoleInstance = new EsaRole(params)
        if (!esaRoleInstance.save(flush: true)) {
            render(view: "create", model: [esaRoleInstance: esaRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), esaRoleInstance.id])
        redirect(action: "show", id: esaRoleInstance.id)
    }

    def show(Long id) {
        def esaRoleInstance = EsaRole.get(id)
        if (!esaRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), id])
            redirect(action: "list")
            return
        }

        [esaRoleInstance: esaRoleInstance]
    }

    def edit(Long id) {
        def esaRoleInstance = EsaRole.get(id)
        if (!esaRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), id])
            redirect(action: "list")
            return
        }

        [esaRoleInstance: esaRoleInstance]
    }

    def update(Long id, Long version) {
        def esaRoleInstance = EsaRole.get(id)
        if (!esaRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (esaRoleInstance.version > version) {
                esaRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'esaRole.label', default: 'EsaRole')] as Object[],
                          "Another user has updated this EsaRole while you were editing")
                render(view: "edit", model: [esaRoleInstance: esaRoleInstance])
                return
            }
        }

        esaRoleInstance.properties = params

        if (!esaRoleInstance.save(flush: true)) {
            render(view: "edit", model: [esaRoleInstance: esaRoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), esaRoleInstance.id])
        redirect(action: "show", id: esaRoleInstance.id)
    }

    def delete(Long id) {
        def esaRoleInstance = EsaRole.get(id)
        if (!esaRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), id])
            redirect(action: "list")
            return
        }

        try {
            esaRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'esaRole.label', default: 'EsaRole'), id])
            redirect(action: "show", id: id)
        }
    }
}
