package org.ihc.esa.controllers

import grails.plugins.springsecurity.Secured

import org.ihc.esa.domain.EsaUser
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class EsaUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [esaUserInstanceList: EsaUser.list(params), esaUserInstanceTotal: EsaUser.count()]
    }

    def create() {
        [esaUserInstance: new EsaUser(params)]
    }

    def save() {
        def esaUserInstance = new EsaUser(params)
        if (!esaUserInstance.save(flush: true)) {
            render(view: "create", model: [esaUserInstance: esaUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), esaUserInstance.id])
        redirect(action: "show", id: esaUserInstance.id)
    }

    def show(Long id) {
        def esaUserInstance = EsaUser.get(id)
        if (!esaUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), id])
            redirect(action: "list")
            return
        }

        [esaUserInstance: esaUserInstance]
    }

    def edit(Long id) {
        def esaUserInstance = EsaUser.get(id)
        if (!esaUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), id])
            redirect(action: "list")
            return
        }

        [esaUserInstance: esaUserInstance]
    }

    def update(Long id, Long version) {
        def esaUserInstance = EsaUser.get(id)
        if (!esaUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (esaUserInstance.version > version) {
                esaUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'esaUser.label', default: 'EsaUser')] as Object[],
                          "Another user has updated this EsaUser while you were editing")
                render(view: "edit", model: [esaUserInstance: esaUserInstance])
                return
            }
        }

        esaUserInstance.properties = params

        if (!esaUserInstance.save(flush: true)) {
            render(view: "edit", model: [esaUserInstance: esaUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), esaUserInstance.id])
        redirect(action: "show", id: esaUserInstance.id)
    }

    def delete(Long id) {
        def esaUserInstance = EsaUser.get(id)
        if (!esaUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), id])
            redirect(action: "list")
            return
        }

        try {
            esaUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'esaUser.label', default: 'EsaUser'), id])
            redirect(action: "show", id: id)
        }
    }
}
