package org.ihc.esa

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
		isSearchable =
		{ domainClass ->
			ClassPropertyFetcher.forClass( domainClass ).getStaticPropertyValue( "search", Closure ) || AnnotationUtils.isAnnotationDeclaredLocally( Indexed, domainClass )
		}

		grailsApplication.domainClasses*.clazz.findAll( isSearchable ).each
		{

			println "Creating Lucene index for entity [${it.name}]...."

			it.search().createIndexAndWait()
		}

		println "Done."
	}
}
