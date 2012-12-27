package org.ihc.esa

import grails.plugins.springsecurity.Secured

import java.text.SimpleDateFormat

import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ESA_ARCHITECT', 'IS_AUTHENTICATED_REMEMBERED'])
class ProjectController
{

	static allowedMethods = [index: 'GET', list: ['GET', 'POST']]

	def springSecurityService

	def index()
	{
		log.debug("====================================================================================")
		log.debug("index() in project controller called with params: " + params)
		log.debug("redirecting to list")
		log.debug("====================================================================================")

		redirect action: 'list', params: params
	}

	def list()
	{
		log.debug("====================================================================================")
		log.debug("list() in project controller called with params: " + params)
		log.debug("====================================================================================")

		def architectId = session['architectId'] ?: springSecurityService.currentUser.party.id
		log.debug("architectId value from session is <" + architectId + ">")

		def projectInstanceList = null

		switch (request.method)
		{

			case 'GET':
			log.debug("in GET method")

			if (params.mine && params.mine.equals('true'))
			{
				params.filter = "on"
				if (architectId)
				{
					log.debug("architectId value from session does not exist, getting it from currentUser")
					architectId = springSecurityService.currentUser.party.id
					log.debug("architectId value from currentUser is <" + architectId + ">")

					log.debug("architectId value from currentUser is <" + architectId + ">")
					session['architectId'] = architectId
					log.debug("placed architectId value into session")

					params.max = Math.min(params.max ? params.int('max') : 20, 100)
					params.sort = params.sort ?: 'name'

					def metaParams = [max: params.max, sort: params.sort, order: params.order, offset: params.offset]

					projectInstanceList = Project.findAllByArchitect(Party.load(architectId), metaParams)
					params.filterByArchitect = architectId
				} else
				{
					log.debug("no architectId found ...")
					projectInstanceList = [:]
				}
			} else
			{
				params.filter = "off"
				params.max = Math.min(params.max ? params.int('max') : 20, 100)
				params.sort = params.sort ?: 'name'
				projectInstanceList = Project.list(params)
			}

			break

			case 'POST':
			log.debug("in POST method")

			if (params.setFilter.equals('off')) {
				log.debug("asked to clear filter")
				params.filterByType = null
				params.filterByStatus = null
				params.filterByArchitect = null
				params.filter = "off"
				projectInstanceList = Project.list(params)
			} else {
				log.debug("adding filter")
				params.filter = "on"
				projectInstanceList = Project.findAll {
					if (params.filterByType) {
						type == Project.ProjectType."${params.filterByType}"
					}

					if (params.filterByStatus) {
						status == Project.ProjectStatus."${params.filterByStatus}"
					}

					if (params.filterByArchitect) {
						architect == Party.get(params.filterByArchitect)
					}
				}
			}

			break

		}

		[projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceList?.size(), filterByType: params.filterByType,
			filterByStatus: params.filterByStatus, filterByArchitect: params.filterByArchitect, filter: params.filter]
	}

	def show() {
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
			redirect action: 'list'
			return
		}

		[projectInstance: projectInstance]
	}

	def create() {

		log.debug("====================================================================================")
		log.debug("create() in project controller called with params: " + params)
		log.debug("====================================================================================")

		switch (request.method) {
		case 'GET':
			params.createdBy = springSecurityService.currentUser.username
			params.updatedBy = springSecurityService.currentUser.username
			[projectInstance: new Project(params)]
			break
		case 'POST':

			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy")

			if (params.dateStart) {
				params.dateStart = sdf.parse(params.dateStart)
			} else {
				params.remove("dateStart")
			}

			if (params.dateCompleted) {
				params.dateCompleted = sdf.parse(params.dateCompleted)
			} else {
				params.remove("dateCompleted")
			}

			def projectInstance = new Project(params)
			if (!projectInstance.save(flush: true)) {
				render view: 'create', model: [projectInstance: projectInstance]
				return
			}

			flash.message = message(code: 'default.created.message', args: ['ACID', projectInstance.id])
			redirect action: 'show', id: projectInstance.id
			break
		}
	}

	def edit() {

		log.debug("====================================================================================")
		log.debug("edit() in project controller called with params: " + params)
		log.debug("====================================================================================")

		switch (request.method) {
		case 'GET':
			def projectInstance = Project.get(params.id)
			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect action: 'list'
				return
			}

			[projectInstance: projectInstance]
			break
		case 'POST':
			def projectInstance = Project.get(params.id)
			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect action: 'list'
				return
			}

			/*
			 * fixed/convert appropriate parameters
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy")

			if (params.architect) {
				params.architect = Party.get(params.architect)
			} else {
				params.remove("architect")
			}

			if (params.projectManager) {
				params.projectManager = Party.get(params.projectManager)
			} else {
				params.remove("projectManager")
			}

			if (params.dateStart) {
				params.dateStart = sdf.parse(params.dateStart)
			} else {
				params.remove("dateStart")
			}

			if (params.dateCompleted) {
				params.dateCompleted = sdf.parse(params.dateCompleted)
			} else {
				params.remove("dateCompleted")
			}

			projectInstance.properties = params

			if (!projectInstance.save(flush: true)) {
				render view: 'edit', model: [projectInstance: projectInstance]
				return
			}

			flash.message = message(code: 'default.updated.message', args: [message(code: 'project.label', default: 'Project'), projectInstance.id])
			redirect action: 'show', id: projectInstance.id
			break
		}
	}

	def delete() {
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
			redirect action: 'list'
			return
		}

		try {
			projectInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])
			redirect action: 'list'
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'project.label', default: 'Project'), params.id])
			redirect action: 'show', id: params.id
		}
	}
}
