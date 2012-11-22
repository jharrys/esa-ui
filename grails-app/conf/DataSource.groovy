dataSource
{
	pooled = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""
}

environments
{
	development
	{
		dataSource
		{
			// one of 'create', 'create-drop', 'update', 'validate', ''
			dbCreate = "create"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;MODE=Oracle"	//use this URL to connect through http://server/grailsapp/dbconsole
			dialect = "org.hibernate.dialect.Oracle10gDialect"
		}
		hibernate
		{
			cache.use_second_level_cache = true
			cache.use_query_cache = false
			format_sql = true
			use_sql_comments = true
			cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
		}
	}
	
	test
	{
		dataSource
		{
			// one of 'create', 'create-drop', 'update', 'validate', ''
			dbCreate = "validate"
			driverClassName = "oracle.jdbc.OracleDriver"
			username = "esa"
			password = "esa"
			dialect = "org.hibernate.dialect.Oracle10gDialect"
			url = "jdbc:oracle:thin:@solutions-db:1521:esa"
		}
		hibernate
		{
			cache.use_second_level_cache = true
			cache.use_query_cache = false
			format_sql = true
			use_sql_comments = true
			cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
		}
	}
	
	production
	{
		dataSource
		{
			// one of 'create', 'create-drop', 'update', 'validate', ''
			dbCreate = "validate"
			driverClassName = "oracle.jdbc.OracleDriver"
			username = "esa"
			password = "esa"
			dialect = "org.hibernate.dialect.Oracle10gDialect"
			url = "jdbc:oracle:thin:@solutions-db:1521:esa"
		}
		hibernate
		{
			cache.use_second_level_cache = true
			cache.use_query_cache = true
			format_sql = false
			use_sql_comments = false
			cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
		}
	}
}
