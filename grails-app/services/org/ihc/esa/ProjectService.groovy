package org.ihc.esa

class ProjectService
{
	static scope = "singleton"
	
	static transactional = false
	
	Map<String, Object> findProjectByArchitectId(Long architectId, def params)
	{
		List<Project> projectInstanceList = new ArrayList<Project>()
		Integer projectInstanceTotal = 0
		Map pagination = new HashMap()
		
		pagination.max = Math.min(params.max ? params.int('max') : 10, 100)
		pagination.offset = params.int('offset')
		//pagination.cache = true
		
		if (architectId)
		{
			log.debug("*** filtering for projects owned by " + architectId)
			projectInstanceList = Project.findAllByPartyId(architectId, pagination)
			projectInstanceTotal = Project.countAllByPartyId(architectId)
		} else
		{
			log.debug("*** architectId is null or 0 so returning the full list")
			if (params.sort) { pagination.sort = params.sort }
			if (params.order) { pagination.order = params.order }
			projectInstanceList = Project.list(pagination)
			projectInstanceTotal = Project.count()
		}
		
		return [projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceTotal]
	}
	
	boolean updateProject(Project project, def props)
	{
		boolean result = false
		
		def attr = [createdBy: props.createdBy, updatedBy: props.updatedBy]
		
		result = updateProjectArchitects(project, props.architects, attr)
		log.debug("*** updateProjectArchitects resulted in ${result}")
		
		return result
	}
	
	private boolean updateProjectArchitects(Project project, List<Party> architectsToAdd, def attr)
	{
		boolean success = true
		
		List<Party> allCurrentArchitects = ProjectArchitect.where
						{ project == project }.projections
						{ property('party') }.list()
		
		log.debug("*** current architects for ${project.id} are ${allCurrentArchitects}")
		
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
		
		log.debug("*** architects to remove from ${project.id} are ${architectsToRemove}")
		
		if (!architectsToAdd.empty)
		{
			for (Party architect in architectsToAdd)
			{
				ProjectArchitect pa = new ProjectArchitect(party: architect, project: project, createdBy: attr.createdBy, updatedBy: attr.updatedBy)
				if (pa.save(flush: true))
				{
					log.debug("*** new association between ${project.name} and ${architect.name} has been saved correctly.")
				} else {
					log.error("*** unable to save a new association between ${project.name} and ${architect.name}.")
					success = false
				}
			}
		}
		
		if (!architectsToRemove.empty)
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
						log.error("*** error occurred trying to delete ProjectArchitect ${logPaId} of Project (${project.name}) association with architect (${architect.name})")
						log.error("*** the domain class instance pa (ProjectArchitect):")
						log.error("*** id: ${logPaId}")
						log.error("*** projectId: ${logPaProjectId}")
						log.error("*** partyId: ${logPaPartyId}")
						log.error("*** pa is attached: ${logPaAttached}")
						log.error("*** null test returned:  ${logPaInfo}")
						// this isn't a deal breaker. the user can try again later. so keeping success flag true
					}
				} else {
					log.error("*** error occurred trying to delete ProjectArchitect (${project.name}) association with architect (${architect.name}); no row found")
				}
			}
		}
		
		log.debug("*** returning to caller with result set to ${success}")
		return success
	} //close updateProjectArchitects
}
