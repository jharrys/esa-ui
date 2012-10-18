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
			url = "jdbc:h2:mem:testDb;MVCC=TRUE"	//use this URL to connect through http://server/grailsapp/dbconsole
			//logSql = true
		}
		hibernate
		{
			cache.use_second_level_cache = true
			cache.use_query_cache = false
			show_sql = true
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
			url = "jdbc:oracle:thin:@192.168.56.101:1521:esa"
			//logSql = true
		}
		hibernate
		{
			cache.use_second_level_cache = true
			cache.use_query_cache = true
			show_sql = false
			format_sql = false
			use_sql_comments = false
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
			url = "jdbc:oracle:thin:@159.212.164.180:1521:esa"
			// logSql = true
		}
		hibernate
		{
			cache.use_second_level_cache = true
			cache.use_query_cache = true
			show_sql = false
			format_sql = false
			use_sql_comments = false
			cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
		}
	}
}
