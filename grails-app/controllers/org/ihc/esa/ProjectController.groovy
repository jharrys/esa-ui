package org.ihc.esa

import grails.plugins.springsecurity.Secured

import java.text.SimpleDateFormat

import org.springframework.dao.DataIntegrityViolationException

class ProjectController
{

	static allowedMethods = [index: 'GET', list: ['GET', 'POST']]

	def springSecurityService

	def projectService

	def index()
	{
		log.debug("***********************************************************************************************")
		log.debug("index() in project controller called with params: " + params)
		log.debug("***********************************************************************************************")

		//redirect action: 'list', params: params
		log.debug("*** [index] redirecting to list")
		redirect action: 'list'
		log.debug("*** [index] back from list")
	}

	def list()
	{
		log.debug("***********************************************************************************************")
		log.debug("list() in project controller called with params: " + params)
		log.debug("***********************************************************************************************")

			if (params.mine && params.mine.equals('true'))
			{
				log.debug("*** [list] param 'mine' found and is 'true'")
				log.debug("*** [list] redirecting to filter")
				redirect action: 'filter', params: params
				log.debug("*** [list] back from filter")
			} else
			{
				log.debug("*** [list] param 'mine' not set or not 'true'")

				if ((!params.setFilter) || params.setFilter.equals('off')) {
					log.debug("*** [list] setFilter is: '${params.setFilter}'")
					log.debug("*** [list] executing a simple projectService.findProjectByArchitectId")

					// set local vars
					def filterResult = null
					def projectInstanceTotal
					def projectInstanceList

					// clear filter and find All projects
					log.debug("*** [list] start clear filter")

					params.filter = "off"
					params.max = Math.min(params.max ? params.int('max') : 10, 100)
					params.remove('filterByType')
					params.remove('filterByStatus')
					params.remove('filterByArchitect')
					params.remove('filterByName')

					// calling service with cleared filter settings
					log.debug("*** [list] calling projectService.findProjectByArchitectId(null, ${params})")
					filterResult = projectService.findProjectByArchitectId(null, params)
					log.debug("*** [list] finished clear filter")

					// set instance vars
					projectInstanceList = filterResult.projectInstanceList
					projectInstanceTotal = filterResult.projectInstanceTotal

					log.debug("*** [list] returning the following:")
					log.debug("*** [list] projectInstanceList: " + projectInstanceList)
					log.debug("*** [list] projectInstanceTotal: " + projectInstanceTotal)

					log.debug("*** [list] finished post method")
					log.debug("*** [list] passing torch over to viewer")

					return [projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceTotal, filterByType: params.filterByType,
					        filterByStatus: params.filterByStatus, filterByArchitect: params.filterByArchitect, filter: params.filter]

				} else {
					log.debug("*** [list] setFilter is on")
					log.debug("*** [list] calling filter")
					log.debug("*** [list] redirecting to filter")
					redirect action: 'filter', params: params
					log.debug("*** [list] back from filter")
				}
			}
	}

	def filter(def params) {

		log.debug("***********************************************************************************************")
		log.debug("filter() method called with params: ${params}")
		log.debug("***********************************************************************************************")

		// set params.filter to on
		// Set max, offset. sort & order parameters for resultset
		params.filter = "on"
		log.debug("*** [filter] set params.filter to '${params.filter}'")

		params.sort = "lastUpdated"
		log.debug("*** [filter] set params.sort to '${params.sort}'")

		params.order = "desc"
		log.debug("*** [filter] set params.order to '${params.order}'")

		params.max = Math.min(params.max ? params.int('max'): 10, 100)
		log.debug("*** [filter] set params.max to '${params.max}'")

		// figure out architect
		def architectId = null
		if (session['architectId'])
		{
			architectId = session['architectId']
			log.debug("*** [filter] architectId value from session is '${architectId}'")
		} else
		{
			architectId = springSecurityService.currentUser.party.id
			session['architectId'] = architectId
			log.debug("*** [filter] architectId value from springSecurityService is '${architectId}'")
			log.debug("*** [filter] placed architectId into session")
		}

		// variable to hold final resultsets
		def result = null

		/*
		 * Simple 'mine' query just call projectService.findProjectByArchitectId directly
		 */
		log.debug("*** [filter params.mine is '${params.mine}'")
		if (params.mine)	 {
			log.debug("*** [filter] requested my projects, so do a simple find")
			result = projectService.findProjectByArchitectId(architectId, params)
		} else {
			log.debug("*** [filter] more complex filter query")
			log.debug("*** [filter] flash.projectControllerPreviousQuery is '${flash.projectControllerPreviousQuery}'")
			result = projectService.executeFilterQuery(params, flash.projectControllerPreviousQuery ?: "")

			log.debug("*** [filter] flash.projectControllerPreviousQuery before update is '${flash.projectControllerPreviousQuery}'")
			flash.projectControllerPreviousQuery = result.projectControllerPreviousQuery
			log.debug("*** [filter] set flash.projectControllerPreviousQuery to '${flash.projectControllerPreviousQuery}'")
		}

		log.debug("*** [filter] returning the following:")
		log.debug("*** [filter] projectInstanceList: '${result.projectInstanceList}'")
		log.debug("*** [filter] projectInstanceTotal: '${result.projectInstanceTotal}'")

		log.debug("*** [filter] finished get method")
		log.debug("*** [filter] passing torch over to viewer")

		//return [projectInstanceList: result.projectInstanceList, projectInstanceTotal: result.projectInstanceTotal, params: params]
		render view: 'list', model: [projectInstanceList: result.projectInstanceList, projectInstanceTotal: result.projectInstanceTotal, params: params]
	}

	def show() {

		log.debug("***********************************************************************************************")
		log.debug("show() method called with params: ${params}")
		log.debug("***********************************************************************************************")

		def projectInstance = Project.get(params.id)

		if (projectInstance) {
			// I'm not using/configuring 2nd level cache correctly - I shouldn't have to do this?
			projectInstance.refresh()
		} else {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'project.label', default: 'Project'), params.id])
			redirect action: 'list'
			return
		}

		[projectInstance: projectInstance]
	}

	@Secured(['ROLE_ESA_ARCHITECT', 'IS_AUTHENTICATED_REMEMBERED'])
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
				if (params.architects instanceof String) {
					log.debug("*** [create] only one architect id passed in ${params.architects}")
					architects.add(Party.load(params.architects))
				} else {
					for (String architectId in params.architects) {
						log.debug("*** [create] multiple architects; adding architect id ${architectId}")
						architects.add(Party.get(architectId))
					}
				}
			}

			params.remove("architects")

			/*
			 * parse through notes, if defined
			 */

			def paramsNote = [:]
			if (params.newNote) {
				paramsNote.text = params.newNote.trim()
				paramsNote.createdBy = params.createdBy
				paramsNote.updatedBy = params.updatedBy
			}

			params.remove("newNote")

			/*
			 * create new project instance
			 */

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

			/*
			 * create new note instance
			 */

			if (paramsNote) {
				paramsNote.project = projectInstance
				def noteInstance = new Note(paramsNote)
				if (!noteInstance.save(flush: true)) {
					log.error('Unable to save note for ${projectInstance.name}, with text: "${paramsNote.text}"')
				} else {
					log.debug("Successfully saved note id ${noteInstance.id}")
				}
			}

			flash.message = userMessage
			redirect action: 'show', id: projectInstance.id
			break
		}
	}

	@Secured(['ROLE_ESA_ARCHITECT', 'IS_AUTHENTICATED_REMEMBERED'])
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

			log.debug("*** [edit] in POST case of edit method")
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

			Map paramsProject = [:]

			if (params.name && (!params.name.equalsIgnoreCase(projectInstance.name))) {
				paramsProject.name = params.name
				log.debug("*** set projectProperties.name to ${paramsProject.name}")
			}

			if (params.type && (!params.type.equals(projectInstance.type))) {
				paramsProject.type = Project.ProjectType."${params.type}"
				log.debug("*** set projectProperties.type to ${paramsProject.type}")
			}

			if (params.status && (!params.status.equals(projectInstance.status))) {
				paramsProject.status = Project.ProjectStatus."${params.status}"
				log.debug("*** set projectProperties.status to ${paramsProject.status}")
			}

			if (params.externalProjectNumber && (!params.externalProjectNumber.equals(projectInstance.externalProjectNumber))) {
				paramsProject.externalProjectNumber = params.externalProjectNumber
				log.debug("*** set projectProperties.externalProjectNumber to ${paramsProject.externalProjectNumber}")
			}

			if (params.projectManager && (!params.projectManager.equals(projectInstance.projectManager))) {
				paramsProject.projectManager = Party.get(params.projectManager)
				log.debug("*** set projectProperties.projectManager to ${paramsProject.projectManager}")
			}

			if (params.dateStart && (!params.dateStart.equals(projectInstance.dateStart))) {
				paramsProject.dateStart = sdf.parse(params.dateStart)
			}

			if (params.dateCompleted && (!params.dateCompleted.equals(projectInstance.dateCompleted))) {
				paramsProject.dateCompleted = sdf.parse(params.dateCompleted)
			}

			if (params.notes && (!params.notes.equals(projectInstance.notes))) {
				paramsProject.notes = params.notes
				log.debug("*** set projectProperties.notes to ${paramsProject.notes}")
			}

			def architects = []
			if (params.architects) {
				if (params.architects instanceof String) {
					log.debug("*** [edit] only one architect id passed in ${params.architects}")
					architects.add(Party.load(params.architects))
				} else {
					for (String architectId in params.architects) {
						log.debug("*** [edit] multiple architects; adding architect id ${architectId}")
						architects.add(Party.get(architectId))
					}
				}

				paramsProject.architects = architects
			}

			paramsProject.updatedBy = params.updatedBy

			/*
			 * handle new notes
			 */

			def paramsNote = [:]
			if (params.newNote) {
				log.debug("*** [edit] params.newNote defined - creating paramsNote for adding a note")
				paramsNote.project = projectInstance
				paramsNote.text = params.newNote.trim()
				paramsNote.createdBy = params.createdBy
				paramsNote.updatedBy = params.updatedBy

				params.remove("newNote")
			}

			log.debug("*** [edit] calling projectService")

			log.debug("*** [edit] projectInstance is ${projectInstance.id} ")
			log.debug("*** [edit] paramsProject is ${paramsProject} ")
			log.debug("*** [edit] paramsNote is ${paramsNote} ")
			if (!projectService.updateProject(projectInstance, paramsProject, paramsNote)) {
				render view: 'edit', model: [projectInstance: projectInstance]
				return
			}

			userMessage = "ACID " + projectInstance.id + " updated."

			flash.message = userMessage
			redirect action: 'show', id: projectInstance.id
			break
		}
	}

	@Secured(['ROLE_ESA_ARCHITECT', 'IS_AUTHENTICATED_REMEMBERED'])
	def delete() {
		// TODO: delete associated notes and other associations not handled by cascades
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
