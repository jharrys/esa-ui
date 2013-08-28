package org.ihc.esa

class ProjectService
{
	static scope = "singleton"
	
	static transactional = false
	
	Map<String, Object> findProjectByArchitectId(Long architectId, def params, Integer projectInstanceTotal)
	{
		List<Project> projectInstanceList = new ArrayList<Project>()
		Map pagination = new HashMap()
		
		pagination.max = Math.min(params.max ? params.int('max') : 10, 100)
		pagination.offset = params.int('offset')
		//pagination.cache = true
		
		if (architectId)
		{
			log.debug("--- filtering for projects owned by " + architectId)
			def resultMap = Project.findAllActiveByPartyId(architectId, pagination, projectInstanceTotal)
			projectInstanceList = resultMap['result']
			projectInstanceTotal = resultMap['resultSize']
			log.debug("--- projectInstanceList: ${projectInstanceList}")
			log.debug("--- projectInstanceTotal: ${projectInstanceTotal}")
		} else
		{
			log.debug("--- architectId is null or 0 so returning the full list")
			if (params.sort) { pagination.sort = params.sort }
			if (params.order) { pagination.order = params.order }
			projectInstanceList = Project.list(pagination)
			projectInstanceTotal = Project.count()
		}
		
		return [projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceTotal]
	}
	
	Map executeFilterQuery(def params, def previousQuery) {
		log.debug("***********************************************************************************************")
		log.debug("executeFilterQuery(def params, def previousQuery) method called with previousQuery: '${previousQuery}', params: '${params}'")
		log.debug("***********************************************************************************************")
		
		
		log.debug("--- executeFilterQuery called with params: ${params}")
		log.debug("--- previousQuery: " + previousQuery)
		
		// initialize query string
		def query = "FROM Project"
		
		if (params.filterByType) {
			query = query + " WHERE type = '${params.filterByType}'"
			log.debug("--- query after type filter: " + query)
		}
		
		// if filterByStatus is null, then default to string 'ACTIVE'
		if (params.filterByStatus) {
			query = query + (params.filterByType ? " AND " : " WHERE ") + "status = '${params.filterByStatus}'"
			log.debug("--- query after status filter: " + query)
		}
		
		if (params.filterByArchitect) {
			
			log.debug("--- start querying project_architect by architect ---")
			Party architect = Party.get(params.filterByArchitect)
			boolean first = true
			StringBuilder projectList = new StringBuilder("(")
			ProjectArchitect.findAllByParty(architect).each { pa ->
				def pId = pa.project.id
				log.debug("architect: '${architect.id}, ${architect.name}' has project Id: '${pId}'")
				(!first) ? projectList.append(",") : (first = false)
				projectList.append(pId)
			}
			projectList.append(")")
			
			log.debug("--- finished querying project_architect by architect ---")
			
			query = query + ((params.filterByType || params.filterByStatus) ? " AND " : " WHERE ") + "id in ${projectList.toString()}"
			log.debug("--- query after architect filter: " + query)
		}
		
		if (params.filterByName) {
			
			query = query + ((params.filterByType || params.filterByStatus || params.filterByArchitect) ? " AND " : " WHERE ") + "lower(name) LIKE '%${params.filterByName}%'"
			log.debug("--- query after name filter: " + query)
		}
		
		log.debug("--- final query: " + query)
		
		def projectInstanceTotal = 0
		def projectInstanceList = null
		
		// Run query to get total number of rows returned by query
		projectInstanceTotal = Project.findAll(query, [cache: true]).size()
		log.debug("--- executed findAll on query for return count (projectInstanceTotal) is '${projectInstanceTotal}'")
		
		// Run query with offset (for pagination) results for viewing
		params.offset = params.offset ? params.int('offset') : 0
		projectInstanceList = Project.findAll(query, [max: params.max, offset: params.offset, cache: true])
		
		return [projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceTotal, projectControllerPreviousQuery: query, params: params]
	}
	
	boolean updateProject(Project project, Map paramsProject, Map paramsNote)
	{
		log.debug("--- updateProject called with project ${project.name} and props: ${paramsProject}")
		boolean result = false
		
		if (paramsProject.architects) {
			log.debug("--- calling updateProjectArchitects with ${project.name} and architects: ${paramsProject.architects} and '${paramsProject.createdBy}' and '${paramsProject.updatedBy}'")
			result = updateProjectArchitects(project, paramsProject.architects, [createdBy: paramsProject.createdBy, updatedBy: paramsProject.updatedBy])
			
			if (result) {
				log.debug("--- result came back ${result}")
				project.lastUpdated = new Date()
			}
			
			paramsProject.remove('architects')
			log.debug("--- removed 'architects' from props map")
		}
		
		if (paramsProject.notes) {
			log.debug("--- got notes changes to update")
			log.debug("--- ${paramsProject.notes}")
			for (Note note in notes) {
				note.save()
				project.lastUpdated = new Date()
			}
			
			paramsProject.remove('notes')
			log.debug("--- removed 'notest' props map")
		}
		
		if (!paramsProject.empty) {
			project.properties = paramsProject
			log.debug("--- bound ${project.name} to ${paramsProject}")
		}
		
		if (project.isDirty()) {
			if (project.save()) {
				log.debug("--- project saved successfully")
				result = true
			} else {
				log.debug("--- project did not save successfully")
			}
		}
		
		if (paramsNote) {
			log.debug("--- paramsNote set, adding note")
			Note note = new Note(paramsNote)
			
			if (!note.save(flush: true)) {
				log.error('--- Unable to save note for ${projectInstance.name}, with text: "${paramsNote.text}"')
			} else {
				log.debug("--- Successfully saved note id ${note.id}")
			}
		} else {
			log.debug("--- New note was not submitted")
		}
		
		log.debug("--- updateProjectArchitects returning with result set to ${result}")
		
		return result
	}
	
	private boolean updateProjectArchitects(Project project, List<Party> architectsToAdd, def attr)
	{
		log.debug("--- updateProjectArchitects called with project ${project.name} and attr: ${attr}")
		log.debug("--- architectsToAdd: ${architectsToAdd}")
		log.debug("--- attr: ${attr}")
		boolean success = true
		
		List<Party> allCurrentArchitects = ProjectArchitect.where
						{ project == project }.projections
						{ property('party') }.list()
		
		log.debug("--- current architects for ${project.id} are ${allCurrentArchitects}")
		
		List<Party> architectsToRemove = new ArrayList<Party>()
		
		allCurrentArchitects.each
		{ currentArchitect ->
			if (currentArchitect in architectsToAdd)
			{
				// we don't need to re-add them
				architectsToAdd.remove(currentArchitect)
			} else
			{
				// if they are not specified in the list, it means we're removing them
				architectsToRemove.add(currentArchitect)
			}
		}
		
		log.debug("--- architects to remove from ${project.id} are ${architectsToRemove}")
		
		if (!architectsToAdd?.empty)
		{
			for (Party architect in architectsToAdd)
			{
				// note createdBy is the same as updatedBy - this is intentional since this is an association table and will only do inserts or del no updates
				ProjectArchitect pa = new ProjectArchitect(party: architect, project: project, createdBy: attr.updatedBy, updatedBy: attr.updatedBy)
				if (pa.save(flush: true))
				{
					log.debug("--- new association between ${project.name} and ${architect.name} has been saved correctly.")
				} else {
					log.error("--- unable to save a new association between ${project.name} and ${architect.name}.")
					success = false
				}
			}
		}
		
		if (!architectsToRemove?.empty)
		{
			for (Party architect in architectsToRemove)
			{
				ProjectArchitect pa = ProjectArchitect.findByProjectAndParty(project, architect)
				String logPaInfo = (pa ? "pa object exists" : "pa object does not exist")
				String logPaAttached = pa.isAttached()
				String logPaId = pa?.id.toString()
				String logPaProjectId = pa?.project.id.toString()
				String logPaPartyId = pa?.party.id.toString()
				
				if (pa) {
					try {
						// gonna go ahead and make sure we're attached to object
						pa.attach()
						pa.delete()
					} catch (java.lang.Throwable e) {
						log.error(e.getMessage())
						log.error("--- error occurred trying to delete ProjectArchitect ${logPaId} of Project (${project.name}) association with architect (${architect.name})")
						log.error("--- the domain class instance pa (ProjectArchitect):")
						log.error("--- id: ${logPaId}")
						log.error("--- projectId: ${logPaProjectId}")
						log.error("--- partyId: ${logPaPartyId}")
						log.error("--- pa is attached: ${logPaAttached}")
						log.error("--- null test returned:  ${logPaInfo}")
						// this isn't a deal breaker. the user can try again later. so keeping success flag true
					}
				} else {
					log.error("--- error occurred trying to delete ProjectArchitect (${project.name}) association with architect (${architect.name}); no row found")
				}
			}
		}
		
		log.debug("--- returning to caller with result set to ${success}")
		return success
	} //close updateProjectArchitects
}
