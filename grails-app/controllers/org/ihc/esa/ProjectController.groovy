package org.ihc.esa

import grails.plugins.springsecurity.Secured

import java.text.SimpleDateFormat

import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ESA_ARCHITECT', 'IS_AUTHENTICATED_REMEMBERED'])
class ProjectController
{

	static allowedMethods = [index: 'GET', list: ['GET', 'POST']]

	def springSecurityService

	def projectService

	def index()
	{
		log.debug("***********************************************************************************************")
		log.debug("index() in project controller called with params: " + params)
		log.debug("redirecting to list")
		log.debug("***********************************************************************************************")

		redirect action: 'list', params: params
	}

	def list()
	{
		log.debug("***********************************************************************************************")
		log.debug("list() in project controller called with params: " + params)
		log.debug("***********************************************************************************************")

		def architectId = null
		if (session['architectId'])
		{
			architectId = session['architectId']
			log.debug("*** architectId value from session is <" + architectId + "> ***")
		} else
		{
			architectId = springSecurityService.currentUser.party.id
			session['architectId'] = architectId
			log.debug("*** architectId value from springSecurityService is <" + architectId + "> ***")
			log.debug("*** placed architectId into session ***")
		}

		List<Project> projectInstanceList = null
		int projectInstanceTotal = 0

		switch (request.method)
		{
			case 'GET':
			log.debug("*** in GET method ***")

			def result = null

			// Set max, offset. sort & order parameters for resultset
			params.sort = "lastUpdated"
			params.order = "desc"

			if (params.mine && params.mine.equals('true'))
			{
				params.filter = "on"
				log.debug("*** param 'mine' found and is 'true' ***")
				log.debug("*** filter is set to 'on' ***")
				result = projectService.findProjectByArchitectId(architectId, params)
			} else
			{
				log.debug("*** no filter set, returning full list of projects")
				params.filter = "off"
				result = projectService.findProjectByArchitectId(null, params)
			}

			projectInstanceList = result.projectInstanceList
			projectInstanceTotal = result.projectInstanceTotal

			log.debug("*** returning the following:")
			log.debug("*** projectInstanceList: " + projectInstanceList)
			log.debug("*** projectInstanceTotal: " + projectInstanceTotal)

			log.debug("*** finished get method ***")
			log.debug("*** passing torch over to viewer ***")
			return [projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceTotal, params: params]

			break		// end GET

			case 'POST':
			log.debug("*** in POST method ***")
			String previousQuery = flash.projectControllerPreviousQuery ?: ""
			log.debug("*** previousQuery: " + previousQuery)

			if (params.setFilter.equals('off')) {
				log.debug("*** start clear filter ***")
				params.max = Math.min(params.max ? params.int('max') : 10, 100)
				params.remove('filterByType')
				params.remove('filterByStatus')
				params.remove('filterByArchitect')
				params.filter = "off"
				projectInstanceList = Project.list(params)
				projectInstanceTotal = Project.count()
				log.debug("*** finished clear filter ***")
			} else {
				log.debug("*** adding filter")
				params.filter = "on"
				params.max = Math.min(params.max ? params.int('max') : 10, 100)

				List<Long> projectListForArchitect = new ArrayList<Long>()
				if (params.filterByArchitect) {
					log.debug("*** start querying project_architect by architect ***")
					Party architect = Party.get(params.filterByArchitect)
					ProjectArchitect.findAllByParty(architect).each { projectListForArchitect.add(it.project.id) }
					log.debug("*** finished querying project_architect by architect ***")
				} else {
					log.debug("*** filterByArchitect not set, skipping...")
				}

				def query = "FROM Project"

				if (params.filterByType) {
					query = query + " WHERE type = '${params.filterByType}'"
					log.debug("*** query after type filter: " + query)
				}

				if (params.filterByStatus) {
					query = query + (params.filterByType ? " AND " : " ") + "status = ${params.filterByStatus}"
					log.debug("*** query after status filter: " + query)
				}

				if (params.filterByArchitect) {
					query = query + ((params.filterByType || params.filterByStatus) ? " AND " : " ") + "id in ${projectListForArchitect}"
					log.debug("*** query after architect filter: " + query)
				}

				log.debug("*** final query: " + query)

				flash.projectControllerPreviousQuery = query

				if (!previousQuery.equals(query)) {
					projectInstanceTotal = Project.findAll(query, [cache: true]).size()
					flash.projectControllerProjectInstanceTotal
					log.debug("*** executed findAll on query")
				} else {
					projectInstanceTotal = flash.projectControllerProjectInstanceTotal
				}
				params.offset = params.offset ? params.int('offset') : 0
				projectInstanceList = Project.findAll(query, [max: params.max, offset: params.offset, cache: true])

			}

			log.debug("*** returning the following:")
			log.debug("*** projectInstanceList: " + projectInstanceList)
			log.debug("*** projectInstanceTotal: " + projectInstanceTotal)

			log.debug("*** finished post method ***")
			log.debug("*** passing torch over to viewer ***")

			return [projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceTotal, filterByType: params.filterByType,
				filterByStatus: params.filterByStatus, filterByArchitect: params.filterByArchitect, filter: params.filter]

			break	// end POST

		} // end switch

	}

	def show() {
		def projectInstance = Project.get(params.id)
		// I'm not using/configuring 2nd level cache correctly - I shouldn't have to do this?
		projectInstance.refresh()
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
			String userMessage = ""

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

			if (params.projectManager) {
				params.projectManager = Party.get(params.projectManager)
			} else {
				params.remove("projectManager")
			}

			List<Party> architects = new ArrayList<Party>()
			if (params.architects) {
				for (String architectId in params.architects) {
					architects.add(Party.get(architectId))
				}
				params.remove("architects")
			} else {
				params.remove("architects")
			}

			def projectInstance = new Project(params)
			if (!projectInstance.save(flush: true)) {
				render view: 'create', model: [projectInstance: projectInstance]
				return
			}

			userMessage = "ACID " + projectInstance.id + " created successfully."

			if (!architects.empty) {
				for (Party architect in architects) {
					ProjectArchitect pa = new ProjectArchitect(party: architect, project: projectInstance, createdBy: params.createdBy, updatedBy: params.updatedBy)
					if (!pa.save(flush: true)) {
						userMessage = ((userMessage) ? userMessage + "<br>Unable to save association to Architect with PARTY_ID <" + architect.id + "> and PROJECT_ID <" +
						projectInstance.id + ">" : "Unable to save association to Architect with PARTY_ID <" + architect.id + "> and PROJECT_ID <" + projectInstance.id + ">" )
					}
				}
			}

			flash.message = userMessage
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

			log.debug("*** in POST case of edit method")
			def projectInstance = Project.get(params.id)
			String userMessage = ""

			if (!projectInstance) {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
				redirect action: 'list'
				return
			}

			/*
			 * fix/convert appropriate parameters
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy")

			def projectProperties = [:]

			if (params.projectManager) {
				projectProperties.projectManager = Party.get(params.projectManager)
			}

			if (params.dateStart) {
				projectProperties.dateStart = sdf.parse(params.dateStart)
			}

			if (params.dateCompleted) {
				projectProperties.dateCompleted = sdf.parse(params.dateCompleted)
			}

			def architects = []
			if (params.architects) {
				for (String architectId in params.architects) {
					architects.add(Party.get(architectId))
				}

				projectProperties.architects = architects
			}

			projectProperties.createdBy = params.createdBy
			projectProperties.updatedBy = params.updatedBy

			log.debug("*** calling projectService")

			if (!projectService.updateProject(projectInstance, projectProperties)) {
				render view: 'edit', model: [projectInstance: projectInstance]
				return
			}

			userMessage = "ACID " + projectInstance.id + " updated."

			flash.message = userMessage
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
			ProjectArchitect.removeAll(projectInstance)
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
