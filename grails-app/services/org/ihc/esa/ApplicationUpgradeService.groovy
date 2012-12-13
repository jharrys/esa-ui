package org.ihc.esa

import org.codehaus.groovy.grails.commons.ClassPropertyFetcher
import org.hibernate.search.annotations.Indexed
import org.springframework.core.annotation.AnnotationUtils

class ApplicationUpgradeService
{
	static transactional = false

	static expose = ['jmx']

	def grailsApplication

	ApplicationUpgradeService()
	{
		super()
	}

	void updateAllDomainClassIndices()
	{
		def isSearchable =
		{ domainClass ->
			ClassPropertyFetcher.forClass( domainClass ).getStaticPropertyValue( "search", Closure ) || AnnotationUtils.isAnnotationDeclaredLocally( Indexed, domainClass )
		}

		grailsApplication.domainClasses*.clazz.findAll( isSearchable ).each
		{

			log.debug("Creating Lucene index for entity [${it.name}]....")

			it.search().createIndexAndWait()
		}

		log.debug("Finished indexing all domain classes.")
	}
}
