package org.ihc.esa

import org.springframework.dao.DataIntegrityViolationException

class PartyAddressController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
    def index() {
		log.debug("redirecting to list action of PartyAddressController")
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [partyAddressInstanceList: PartyAddress.list(params), partyAddressInstanceTotal: PartyAddress.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[partyAddressInstance: new PartyAddress(params)]
			break
		case 'POST':
	        def partyAddressInstance = new PartyAddress(params)
	        if (!partyAddressInstance.save(flush: true)) {
	            render view: 'create', model: [partyAddressInstance: partyAddressInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), partyAddressInstance.id])
	        redirect action: 'show', id: partyAddressInstance.id
			break
		}
    }

    def show() {
        def partyAddressInstance = PartyAddress.get(params.id)
        if (!partyAddressInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), params.id])
            redirect action: 'list'
            return
        }

        [partyAddressInstance: partyAddressInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def partyAddressInstance = PartyAddress.get(params.id)
	        if (!partyAddressInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [partyAddressInstance: partyAddressInstance]
			break
		case 'POST':
	        def partyAddressInstance = PartyAddress.get(params.id)
	        if (!partyAddressInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (partyAddressInstance.version > version) {
	                partyAddressInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'partyAddress.label', default: 'PartyAddress')] as Object[],
	                          "Another user has updated this PartyAddress while you were editing")
	                render view: 'edit', model: [partyAddressInstance: partyAddressInstance]
	                return
	            }
	        }

	        partyAddressInstance.properties = params

	        if (!partyAddressInstance.save(flush: true)) {
	            render view: 'edit', model: [partyAddressInstance: partyAddressInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), partyAddressInstance.id])
	        redirect action: 'show', id: partyAddressInstance.id
			break
		}
    }

    def delete() {
        def partyAddressInstance = PartyAddress.get(params.id)
        if (!partyAddressInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), params.id])
            redirect action: 'list'
            return
        }

        try {
            partyAddressInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'partyAddress.label', default: 'PartyAddress'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
