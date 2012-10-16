grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text/plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"

// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true

// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true

// packages to include in Spring bean scanning
grails.spring.bean.packages = []

// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// enable query caching by default
grails.hibernate.cache.queries = true

// configure mail plugin
//grails {
//	mail {
//		host = "smtp.co.ihc.com"
//		port = 25
//	}
//}

grails {
	mail {
		host = "smtp.gmail.com"
		port = 465
		username = "harris.johnny@gmail.com"
		password = "eyrodujhipahspco"
		props = ["mail.smtp.auth":"true",
				"mail.smtp.socketFactory.port":"465",
				"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
				"mail.smtp.socketFactory.fallback":"false"]
	}
}

environments {
    development {
        grails.logging.jul.usebridge = true
		
		// mask out secure fields - keep this off in development. FIXME: create tests for this
		// grails.exceptionresolver.params.exclude = ['password', 'creditCard']
		
		// spring-security-mock plugin
		// for user details service see conf/spring/resources.groovy
		grails.plugins.springsecurity.mock.active = false
		//	grails.plugins.springsecurity.mock.fullName = "Mock Mockster"
		//	grails.plugins.springsecurity.mock.email = "harris.johnny@gmail.com"
		//	grails.plugins.springsecurity.mock.username =  "mmockster"
		//	grails.plugins.springsecurity.mock.roles = [ 'ROLE_USER', 'ROLE_ADMIN', 'ROLE_EXCEPTION' ]
		//	grails.plugins.springsecurity.ipRestrictions = [ '/**': ['127.0.0.0/8', '::1/128'] ]
		
		// All 3 of these must be set to true if you want spring-security-mock to load roles from ldap rather than mock.roles setting
		grails.plugins.springsecurity.ldap.active = false
		grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = false
		// the full DN will be equivalent to "cn=${username},${userDnBase}"
		grails.plugins.springsecurity.ldap.usernameMapper.userDnDBase = false
    }
	
	test {
		grails.logging.jul.usebridge = true
		
		// mask out secure fields
		grails.exceptionresolver.params.exclude = ['password', 'creditCard']
		
		// spring-security-mock exposes a large security hole
		grails.plugins.springsecurity.mock.active = false
		
		// All 3 of these must be set to true if you want spring-security-mock to load roles from ldap rather than mock.roles setting
		grails.plugins.springsecurity.ldap.active = false
		grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = false
		// the full DN will be equivalent to "cn=${username},${userDnBase}"
		grails.plugins.springsecurity.ldap.usernameMapper.userDnDBase = false
	}
	
    production {
        grails.logging.jul.usebridge = true
		
		// mask out secure fields
		grails.exceptionresolver.params.exclude = ['password', 'creditCard']
		
		// spring-security-mock exposes a large security hole
		grails.plugins.springsecurity.mock.active = false
		
		// All 3 of these must be set to true if you want spring-security-mock to load roles from ldap rather than mock.roles setting
		grails.plugins.springsecurity.ldap.active = true
		grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = true
		// the full DN will be equivalent to "cn=${username},${userDnBase}"
		grails.plugins.springsecurity.ldap.usernameMapper.userDnDBase = true
		
        //grails.serverURL = ""
    }
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

log4j =
{
	// sensible defaults under Tomcat 6.x and Tomcat 7.x
	def catalinaBase = System.properties.getProperty('catalina.base')
	if (!catalinaBase) catalinaBase = '.'
	//	def logDirectory = "${catalinaBase}/logs"
	def logf = "c:/tmp/esaui.log"
	
	//println "logDirectory: " + logDirectory
	
	appenders {
		console		name: 'stdout'
		file		name: 'file', file: logf, append: true
	}
	
	environments {
		
		development {
			/*
			 * 'org.hibernate.SQL'  // sql output
			 * 'org.hibernate.type' // binding variables for sql output
			 * 'grails.app.services.grails.plugins.springsecurity.ui.SpringSecurityUiService'
			 */
			
			debug 	'grails.app.controllers.org.ihc.esa',					//My Controllers
					'grails.app.domain.org.ihc.esa',						//My Domain
					'grails.app.taglib.org.ihc.esa'							//My Tag library
					//'org.hibernate.SQL'									//SQL
					//'org.codehaus.groovy.grails.web.sitemesh',			//Layout
					//'org.codehaus.groovy.grails.orm.hibernate',
					//'net.sf.ehcache.hibernate',
					//'org.springframework',
					//'grails.app.domain',
					//'grails.app'
					//'grails.plugins.twitterbootstrap'						//css twitter bootstrap plugin
			
			trace	'org.hibernate.SQL',									//with param values
					'org.hibernate.type'
			
			//warn 	'grails.app.services.grails.plugins.springsecurity.ui.SpringSecurityUiService'
		}
		
		test {
			/*
			 * 'org.hibernate.SQL'  // sql output
			 * 'org.hibernate.type' // binding variables for sql output
			 * 'grails.app.services.grails.plugins.springsecurity.ui.SpringSecurityUiService'
			 */
			
			debug 	'grails.app.controllers.org.ihc.esa',					//My Controllers
					'grails.app.domain.org.ihc.esa',						//My Domain
					'grails.app.taglib.org.ihc.esa'							//My Tag library
					//'org.hibernate.SQL'									//SQL
					//'org.codehaus.groovy.grails.web.sitemesh',			//Layout
					//'org.codehaus.groovy.grails.orm.hibernate',
					//'net.sf.ehcache.hibernate',
					//'org.springframework',
					//'grails.app.domain',
					//'grails.app'
					//'grails.plugins.twitterbootstrap'						//css twitter bootstrap plugin
			
			trace	'grails.app.services.grails.plugins.springsecurity'
		}
		
		production {
			/*
			 * 'org.hibernate.SQL'  // sql output
			 * 'org.hibernate.type' // binding variables for sql output
			 * 'grails.app.services.grails.plugins.springsecurity.ui.SpringSecurityUiService'
			 */
			
			// set appropriate defaults for production
		}
		
	}
	
	/*
	root {
		error 'stdout'
	}*/
}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Added by the Joda-Time plugin:
grails.gorm.default.mapping = {
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentDateMidnight, class: org.joda.time.DateMidnight
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentDateTime, class: org.joda.time.DateTime
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentDateTimeZoneAsString, class: org.joda.time.DateTimeZone
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentDurationAsString, class: org.joda.time.Duration
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentInstantAsMillisLong, class: org.joda.time.Instant
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentInterval, class: org.joda.time.Interval
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentLocalDate, class: org.joda.time.LocalDate
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime, class: org.joda.time.LocalDateTime
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentLocalTime, class: org.joda.time.LocalTime
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentPeriodAsString, class: org.joda.time.Period
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentTimeOfDay, class: org.joda.time.TimeOfDay
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentYearMonthDay, class: org.joda.time.YearMonthDay
	"user-type" type: org.jadira.usertype.dateandtime.joda.PersistentYears, class: org.joda.time.Years
}

jodatime.format.html5 = true

grails.plugins.twitterbootstrap.fixtaglib = true
grails.plugins.twitterbootstrap.defaultBundle = 'bundle_bootstrap'

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'org.ihc.esa.EsaUser'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'org.ihc.esa.EsaUserEsaRole'
grails.plugins.springsecurity.authority.className = 'org.ihc.esa.EsaRole'
//grails.plugins.springsecurity.securityConfigType = SecurityConfigType.InterceptUrlMap
//grails.plugins.springsecurity.interceptUrlMap = [
//	'/user/**':		['ROLE_ADMIN', 'IS_AUTHENTICATED_FULLY']	
//]

// Spring Security UI
grails.plugins.springsecurity.ui.register.postRegisterUrl = '/welcome'	//DEPLOY: make this sensible
grails.plugins.springsecurity.ui.register.emailBody = '...'				//DEPLOY: security registration
grails.plugins.springsecurity.ui.register.emailFrom = '...'				//DEPLOY: security registration
grails.plugins.springsecurity.ui.register.emailSubject = '...'			//DEPLOY: security registration
grails.plugins.springsecurity.ui.register.defaultRoleNames = [] 		//DEPLOY: no roles
//FIXME: spring security UI uses the mail plugin, so need to configure smtp
//FIXME: grails s2ui-override register to copy the registration controller and GSPs into your application to be customized
//FIXME: s2-create-persistent-token
//FIXME: customizations: s2ui-override <type> <controller-package>
//FIXME: The plugin defines its CSS styles in web-app/css/spring-security-ui.css and most of the jQuery plugins have corresponding CSS files. 
//These can be overridden by overriding the springSecurityUI.gsp template and including your CSS file(s).

// See http://grails.org/doc/latest/guide/conf.html#configExternalized
grails.config.locations = [ "classpath:log4j.properties" ]

// Add the ability to have a configuration file external to the WAR defined by environment variable pointed to by ESA_EXTERNAL_CONFIG
def ESA_EXTERNAL_CONFIG = "ESAUI_CONFIG_FILE"
boolean locationSet = false
if(!grails.config.locations || !(grails.config.locations instanceof List)) {
	grails.config.locations = []
} else {
	locationSet = true
}

// Check environment and add external configuration file
if(System.getenv(ESA_EXTERNAL_CONFIG)) {
	grails.config.locations << "file:" + System.getenv(ESA_EXTERNAL_CONFIG)
	locationSet = true
} else if(System.getProperty(ESA_EXTERNAL_CONFIG)) {
	grails.config.locations << "file:" + System.getProperty(ESA_EXTERNAL_CONFIG)
	locationSet = true
}

// Print out whether location was set or not
if (locationSet) {
	println "grails.config.locations: " + grails.config.locations
} else {
	println "grails.config.locations: <not set>"
}

