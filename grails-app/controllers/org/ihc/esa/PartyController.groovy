package org.ihc.esa

import org.springframework.dao.DataIntegrityViolationException

class PartyController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
    def index() {
		log.debug("redirecting to list action of PartyController")
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [partyInstanceList: Party.list(params), partyInstanceTotal: Party.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[partyInstance: new Party(params)]
			break
		case 'POST':
	        def partyInstance = new Party(params)
	        if (!partyInstance.save(flush: true)) {
	            render view: 'create', model: [partyInstance: partyInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'party.label', default: 'Party'), partyInstance.id])
	        redirect action: 'show', id: partyInstance.id
			break
		}
    }

    def show() {
        def partyInstance = Party.get(params.id)
        if (!partyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'party.label', default: 'Party'), params.id])
            redirect action: 'list'
            return
        }

        [partyInstance: partyInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def partyInstance = Party.get(params.id)
	        if (!partyInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'party.label', default: 'Party'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [partyInstance: partyInstance]
			break
		case 'POST':
	        def partyInstance = Party.get(params.id)
	        if (!partyInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'party.label', default: 'Party'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (partyInstance.version > version) {
	                partyInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'party.label', default: 'Party')] as Object[],
	                          "Another user has updated this Party while you were editing")
	                render view: 'edit', model: [partyInstance: partyInstance]
	                return
	            }
	        }

	        partyInstance.properties = params

	        if (!partyInstance.save(flush: true)) {
	            render view: 'edit', model: [partyInstance: partyInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'party.label', default: 'Party'), partyInstance.id])
	        redirect action: 'show', id: partyInstance.id
			break
		}
    }

    def delete() {
        def partyInstance = Party.get(params.id)
        if (!partyInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'party.label', default: 'Party'), params.id])
            redirect action: 'list'
            return
        }

        try {
            partyInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'party.label', default: 'Party'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'party.label', default: 'Party'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
