grails.servlet.version = "2.5"
grails.project.work.dir = "target"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "esa-ui.war"
//grails.war.resources =
//{ stagingDir, args ->
//	copy(file: "grails-app/conf/log4j.groovy",
//					tofile: "${stagingDir}/WEB-INF/classes/log4j.groovy")
//}

grails.project.dependency.resolution =
{
	
	inherits("global")
	log "error"
	checksums true
	
	repositories
	{
		inherits true // Whether to inherit repository definitions from plugins
		grailsPlugins()
		grailsHome()
		grailsCentral()
		mavenCentral()
	}
	
	dependencies
	{ 
		compile "org.jadira.usertype:usertype.jodatime:1.9" 
		test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
	}
	
	plugins
	{
		compile ":hibernate:$grailsVersion"
		compile ":hibernate-search:0.7"
		compile ":joda-time:1.4"
		compile ":fields:1.3"
		compile ":jquery:1.8.3"
		compile ":resources:1.2.RC2"
		compile ":famfamfam:1.0.1"
		compile ":jquery-ui:1.8.24"
		compile ":mail:1.0.1"
		compile ":spring-security-core:1.2.7.3"
		compile ":spring-security-eventlog:0.2"
		compile ":spring-security-ldap:1.0.6"
		compile ":spring-security-ui:0.2"
		compile ":jasper:1.6.1"
		compile ":webxml:1.4.1"
		compile ":jmx:0.8"
		compile ":twitter-bootstrap:2.2.2"
		compile ":external-config-reload:1.2.2"
		
		runtime ":cache-headers:1.1.5"
		runtime ":zipped-resources:1.0"
		runtime ":cached-resources:1.0"
		
		test (":spock:0.7") {
			exclude "spock-grails-support"
		}
		
		build ":tomcat:$grailsVersion"
	}
}
