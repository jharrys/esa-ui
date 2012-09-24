package org.ihc.esa

import org.springframework.dao.DataIntegrityViolationException

class LookupElementController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
		log.debug("redirecting to list action of LookupElementController")
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [lookupElementInstanceList: LookupElement.list(params), lookupElementInstanceTotal: LookupElement.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[lookupElementInstance: new LookupElement(params)]
			break
		case 'POST':
	        def lookupElementInstance = new LookupElement(params)
	        if (!lookupElementInstance.save(flush: true)) {
	            render view: 'create', model: [lookupElementInstance: lookupElementInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), lookupElementInstance.id])
	        redirect action: 'show', id: lookupElementInstance.id
			break
		}
    }

    def show() {
        def lookupElementInstance = LookupElement.get(params.id)
        if (!lookupElementInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), params.id])
            redirect action: 'list'
            return
        }

        [lookupElementInstance: lookupElementInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def lookupElementInstance = LookupElement.get(params.id)
	        if (!lookupElementInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [lookupElementInstance: lookupElementInstance]
			break
		case 'POST':
	        def lookupElementInstance = LookupElement.get(params.id)
	        if (!lookupElementInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (lookupElementInstance.version > version) {
	                lookupElementInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'lookupElement.label', default: 'LookupElement')] as Object[],
	                          "Another user has updated this LookupElement while you were editing")
	                render view: 'edit', model: [lookupElementInstance: lookupElementInstance]
	                return
	            }
	        }

	        lookupElementInstance.properties = params

	        if (!lookupElementInstance.save(flush: true)) {
	            render view: 'edit', model: [lookupElementInstance: lookupElementInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), lookupElementInstance.id])
	        redirect action: 'show', id: lookupElementInstance.id
			break
		}
    }

    def delete() {
        def lookupElementInstance = LookupElement.get(params.id)
        if (!lookupElementInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), params.id])
            redirect action: 'list'
            return
        }

        try {
            lookupElementInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lookupElement.label', default: 'LookupElement'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
