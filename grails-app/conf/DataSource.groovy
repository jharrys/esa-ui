dataSource
{
	pooled = true
	driverClassName = "org.h2.Driver"
	username = "sa"
	password = ""
}

hibernate
{
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
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
	}
}
