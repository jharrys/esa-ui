package org.ihc.esa

import org.springframework.dao.DataIntegrityViolationException

class LookupListController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
    def index() {
		log.debug("redirecting to list action of LookupListController")
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [lookupListInstanceList: LookupList.list(params), lookupListInstanceTotal: LookupList.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[lookupListInstance: new LookupList(params)]
			break
		case 'POST':
	        def lookupListInstance = new LookupList(params)
	        if (!lookupListInstance.save(flush: true)) {
	            render view: 'create', model: [lookupListInstance: lookupListInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'lookupList.label', default: 'LookupList'), lookupListInstance.id])
	        redirect action: 'show', id: lookupListInstance.id
			break
		}
    }

    def show() {
        def lookupListInstance = LookupList.get(params.id)
        if (!lookupListInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), params.id])
            redirect action: 'list'
            return
        }

        [lookupListInstance: lookupListInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def lookupListInstance = LookupList.get(params.id)
	        if (!lookupListInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [lookupListInstance: lookupListInstance]
			break
		case 'POST':
	        def lookupListInstance = LookupList.get(params.id)
	        if (!lookupListInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (lookupListInstance.version > version) {
	                lookupListInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'lookupList.label', default: 'LookupList')] as Object[],
	                          "Another user has updated this LookupList while you were editing")
	                render view: 'edit', model: [lookupListInstance: lookupListInstance]
	                return
	            }
	        }

	        lookupListInstance.properties = params

	        if (!lookupListInstance.save(flush: true)) {
	            render view: 'edit', model: [lookupListInstance: lookupListInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'lookupList.label', default: 'LookupList'), lookupListInstance.id])
	        redirect action: 'show', id: lookupListInstance.id
			break
		}
    }

    def delete() {
        def lookupListInstance = LookupList.get(params.id)
        if (!lookupListInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'lookupList.label', default: 'LookupList'), params.id])
            redirect action: 'list'
            return
        }

        try {
            lookupListInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'lookupList.label', default: 'LookupList'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lookupList.label', default: 'LookupList'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
