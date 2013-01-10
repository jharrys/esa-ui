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
}
