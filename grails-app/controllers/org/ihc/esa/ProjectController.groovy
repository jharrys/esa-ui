package org.ihc.esa

import grails.plugins.springsecurity.Secured

import java.text.SimpleDateFormat

import org.ihc.esa.Project.ProjectStatus
import org.springframework.dao.DataIntegrityViolationException


class ProjectController
{
	
	static allowedMethods = [index: 'GET', list: ['GET', 'POST'], deleteNote: 'POST']
	
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
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_CREATE',
		'ROLE_ESA_PROJECT_UPDATE',
		'ROLE_ESA_PROJECT_DELETE',
		'ROLE_ESA_PROJECT_RO',
		'IS_AUTHENTICATED_REMEMBERED'
	])
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
			
			if ((!params.setFilter) || params.setFilter.equals('off'))
			{
				log.debug("*** [list] setFilter is: '${params.setFilter}'")
				log.debug("*** [list] executing a simple projectService.findProjectByArchitectId")
				
				// set local vars
				def filterResult = null
				def projectInstanceTotal = (params.projectInstanceTotal ? params.int('projectInstanceTotal') : 0)
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
				filterResult = projectService.findProjectByArchitectId(new Long(0), params, projectInstanceTotal)
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
				
			} else
			{
				log.debug("*** [list] setFilter is on")
				log.debug("*** [list] calling filter")
				log.debug("*** [list] redirecting to filter")
				redirect action: 'filter', params: params
				log.debug("*** [list] back from filter")
			}
		}
	}
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_CREATE',
		'ROLE_ESA_PROJECT_UPDATE',
		'ROLE_ESA_PROJECT_DELETE',
		'ROLE_ESA_PROJECT_RO',
		'IS_AUTHENTICATED_REMEMBERED'
	])
	def filter(def params)
	{
		
		log.debug("***********************************************************************************************")
		log.debug("filter() method called with params: ${params}")
		log.debug("***********************************************************************************************")
		
		// set params.filter to on
		// Set max, offset. sort & order parameters for resultset
		params.filter = "on"
		log.debug("*** [filter] set params.filter to '${params.filter}'")
		
		params.sort = params.sort ? params.sort : "lastUpdated"
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
		def resultTotal = (params.projectInstanceTotal ? params.int('projectInstanceTotal'): null)
		
		/*
		 * Simple 'mine' query just call projectService.findProjectByArchitectId directly
		 */
		log.debug("*** [filter] params.mine is '${params.mine}'")
		if (params.mine)	 {
			log.debug("*** [filter] requested my projects, so do a simple find")
			result = projectService.findProjectByArchitectId(architectId, params, resultTotal)
			params['filterByArchitect'] = architectId
		} else {
			log.debug("*** [filter] ${params}")
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
		
		log.debug("*** [filter] params: ${params}")
		
		//return [projectInstanceList: result.projectInstanceList, projectInstanceTotal: result.projectInstanceTotal, params: params]
		render view: 'list', model: [projectInstanceList: result.projectInstanceList, projectInstanceTotal: result.projectInstanceTotal, params: params]
	}
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_CREATE',
		'ROLE_ESA_PROJECT_UPDATE',
		'ROLE_ESA_PROJECT_DELETE',
		'ROLE_ESA_PROJECT_RO',
		'IS_AUTHENTICATED_REMEMBERED'
	])
	def show() {
		
		log.debug("***********************************************************************************************")
		log.debug("show() method called with params: ${params}")
		log.debug("***********************************************************************************************")
		
		def projectInstance = Project.get(params.id)
		
		if (projectInstance) {
			// I'm not using/configuring 2nd level cache correctly - I shouldn't have to do this?
			projectInstance.refresh()
		} else {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'project.label', default: 'Project'),
				params.id
			])
			redirect action: 'list'
			return
		}
		
		[projectInstance: projectInstance]
	}
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_CREATE',
		'IS_AUTHENTICATED_REMEMBERED'
	])
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
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_UPDATE',
		'IS_AUTHENTICATED_REMEMBERED'
	])
	def edit() {
		
		log.debug("====================================================================================")
		log.debug("edit() in project controller called with params: " + params)
		log.debug("====================================================================================")
		
		switch (request.method) {
			case 'GET':
				def projectInstance = Project.get(params.id)
				if (!projectInstance) {
					flash.message = message(code: 'default.not.found.message', args: [
						message(code: 'project.label', default: 'Project'),
						params.id
					])
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
					flash.message = message(code: 'default.not.found.message', args: [
						message(code: 'project.label', default: 'Project'),
						params.id
					])
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
			
			// check existing notes
				for (entry in params) {
					if (entry.key.startsWith("note_")	) {
						def noteId = entry.key.split(/_/)[1]
						def note = Note.get(noteId)
						if (!entry.value.equals(note.text)) {
							log.debug("*** note (${entry}) is not the same, so updating...")
							note.text = entry.value
							note.save()
						}
					}
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
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_UPDATE',
		'IS_AUTHENTICATED_REMEMBERED'
	])
	def ajaxClose() {
		
		log.debug("====================================================================================")
		log.debug("ajaxClose() in project controller called with params: " + params)
		log.debug("====================================================================================")
		
		Project project = Project.get(params.project_id)
		
		if (project) {
			log.debug("*** [ajaxClose] Found project <${project.id}>")
			Date d = new Date()
			project.status = ProjectStatus.CLOSED
			project.dateCompleted = d
			project.save()
			log.debug("*** [ajaxClose] Project <${project.id}> closed.")
		} else {
			log.debug("*** [ajaxClose] Project <${params.project_id}> not found.")
		}
		
		render "succeeded"
	}
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_UPDATE',
		'IS_AUTHENTICATED_REMEMBERED'
	])
	def deleteNote() {
		
		log.debug("====================================================================================")
		log.debug("deleteNote() in project controller called with params: " + params)
		log.debug("====================================================================================")
		
		log.debug("*** [deleteNote] hydrating note with id of ${params.id}")
		Note note = Note.get(params.int('id'))
		
		log.debug("*** [deleteNote] deleting note...")
		note.delete(flush: true)
		
		render "succeeded"
	}
	
	@Secured([
		'ROLE_ESA_PROJECT_FULL',
		'ROLE_ESA_PROJECT_DELETE',
		'IS_AUTHENTICATED_REMEMBERED'
	])
	def delete() {
		// TODO: delete associated notes and other associations not handled by cascades
		def projectInstance = Project.get(params.id)
		if (!projectInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'project.label', default: 'Project'),
				params.id
			])
			redirect action: 'list'
			return
		}
		
		try {
			ProjectArchitect.removeAll(projectInstance)
			projectInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'project.label', default: 'Project'),
				params.id
			])
			redirect action: 'list'
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'project.label', default: 'Project'),
				params.id
			])
			redirect action: 'show', id: params.id
		}
	}
	
	def ajaxAddNote() {
		
		log.debug("====================================================================================")
		log.debug("ajaxAddNote() in project controller called with params: " + params)
		log.debug("====================================================================================")
		
		if (params.notetext) {
			log.debug("*** [ajaxAddNote] found a notetext to add to this project.")
			def projectId = params.project_id
			Project currentProject = null
			Note note = null
			
			if (projectId) {
				currentProject = Project.get(projectId)
				log.debug("*** [ajaxAddNote] found project for id <${projectId}>.")
				
				note = new Note()
				note.project = currentProject
				note.text = params.notetext.trim().encodeAsHTML()
				note.createdBy = params.createdBy
				note.updatedBy = params.updatedBy
				currentProject.updatedBy = note.updatedBy
				currentProject.lastUpdated = new Date()
				
				if (note.save(flush: true)) {
					log.debug("*** [ajaxAddNote] Note for project <${currentProject.id}> saved, updating related project's updatedby field.")
					if(currentProject.save(flush: true)) {
						log.debug("*** [ajaxAddNote] project <${currentProject.id}> saved.")
					} else {
						log.error("*** [ajaxAddNote] project with id projectId could not be saved.")
					}
				} else {
					log.error("*** [ajaxAddNote] unable to save Note for project <${currentProject.id}> with text: ${note.text} created by user: ${note.createdBy}")
				}
			}
			
			render(contentType: "application/json") { [project: currentProject, note: note] }
		} else {
			log.debug("*** [ajaxAddNote] no notetext found, so exiting.")
		}
	}
	
}
