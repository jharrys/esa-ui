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
		
		pagination.max = Math.min(params.max ? params.int('max') : 5, 100)
		pagination.offset = params.int('offset')
		pagination.cache = true
		
		if (architectId)
		{
			log.debug("*** filtering for projects owned by " + architectId)
			projectInstanceList = Project.findAllByPartyId(architectId, pagination)
			projectInstanceTotal = Project.countAllByPartyId(architectId)
		} else
		{
			log.debug("*** architectId is null or 0 so returning the full list")
			projectInstanceList = Project.listOrderByLastUpdated(pagination)
			projectInstanceTotal = Project.count()
		}
		
		return [projectInstanceList: projectInstanceList, projectInstanceTotal: projectInstanceTotal]
	}
	
	boolean updateProject(Project project, def props)
	{
		boolean result = false
		
		def attr = [createdBy: props.createdBy, updatedBy: props.updatedBy]
		
		result = updateProjectArchitects(project, props.architects, attr)
		
		return result
	}
	
	private boolean updateProjectArchitects(Project project, List<Party> architectsToAdd, def attr)
	{
		boolean result = false
		
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
					log.debug("*** new projectarchitect saved with ${architect.name}")
					result = true
				}
			}
		}
		
		if (!architectsToRemove.empty)
		{
			for (Party architect in architectsToRemove)
			{
				ProjectArchitect pa = new ProjectArchitect(party: architect, project: project, createdBy: attr.createdBy, updatedBy: attr.updatedBy)
				if (pa.save(flush: true))
				{
					log.debug("*** removed projectarchitect with ${architect.name}")
					result = true
				}
			}
			
			return result
		}
	}
}
