package org.ihc.esa

import org.springframework.dao.DataIntegrityViolationException

class ConfigurationCatalogController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
    def index() {
		log.debug("redirecting to list action for ConfigurationCatalogController")
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [configurationCatalogInstanceList: ConfigurationCatalog.list(params), configurationCatalogInstanceTotal: ConfigurationCatalog.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[configurationCatalogInstance: new ConfigurationCatalog(params)]
			break
		case 'POST':
	        def configurationCatalogInstance = new ConfigurationCatalog(params)
	        if (!configurationCatalogInstance.save(flush: true)) {
	            render view: 'create', model: [configurationCatalogInstance: configurationCatalogInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), configurationCatalogInstance.id])
	        redirect action: 'show', id: configurationCatalogInstance.id
			break
		}
    }

    def show() {
        def configurationCatalogInstance = ConfigurationCatalog.get(params.id)
        if (!configurationCatalogInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), params.id])
            redirect action: 'list'
            return
        }

        [configurationCatalogInstance: configurationCatalogInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def configurationCatalogInstance = ConfigurationCatalog.get(params.id)
	        if (!configurationCatalogInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [configurationCatalogInstance: configurationCatalogInstance]
			break
		case 'POST':
	        def configurationCatalogInstance = ConfigurationCatalog.get(params.id)
	        if (!configurationCatalogInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (configurationCatalogInstance.version > version) {
	                configurationCatalogInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog')] as Object[],
	                          "Another user has updated this ConfigurationCatalog while you were editing")
	                render view: 'edit', model: [configurationCatalogInstance: configurationCatalogInstance]
	                return
	            }
	        }

	        configurationCatalogInstance.properties = params

	        if (!configurationCatalogInstance.save(flush: true)) {
	            render view: 'edit', model: [configurationCatalogInstance: configurationCatalogInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), configurationCatalogInstance.id])
	        redirect action: 'show', id: configurationCatalogInstance.id
			break
		}
    }

    def delete() {
        def configurationCatalogInstance = ConfigurationCatalog.get(params.id)
        if (!configurationCatalogInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), params.id])
            redirect action: 'list'
            return
        }

        try {
            configurationCatalogInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'configurationCatalog.label', default: 'ConfigurationCatalog'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
