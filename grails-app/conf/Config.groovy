/* ******************************************************************************************************************
 * Main configuration that defines global settings, including sensible defaults
 *
 * Many of these settings can be overridden as shown in the code at the end of this script.
 * Automatic reloading of changed configurations has been addded in this application as a plugin
 * called external-config-reload.
 *
 * Author: John Harris
 * *****************************************************************************************************************/

grails.project.groupId = appName 		// change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true 		// enables the parsing of file extensions from URLs into the request format
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

/* ******************************************************************************************************************
 * mail plugin configuration parameters
 * *****************************************************************************************************************/

grails {
	mail {
		host = "smtp.co.ihc.com"
		port = 25
	}
}

/* ******************************************************************************************************************
 * Environment specific settings
 * *****************************************************************************************************************/
environments {
    development {
		grails.gorm.failOnError = true

        grails.logging.jul.usebridge = true

		// mask out secure fields - keep this off in development. FIXME: create tests for this
		// grails.exceptionresolver.params.exclude = ['password', 'creditCard']

		/* *****************************************************************************
		 * spring-security settings
		 * for details about service see conf/spring/resources.groovy
		 * the full DN will be equivalent to "cn=${username},${userDnBase}"
		 * ****************************************************************************/

		//	grails.plugins.springsecurity.ipRestrictions = [ '/**': ['127.0.0.0/8', '::1/128'] ]
		grails.plugins.springsecurity.ldap.active = false
		grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = false
		grails.plugins.springsecurity.ldap.usernameMapper.userDnDBase = false

		// hibernate-search plugin configuration
		grails.plugins.hibernatesearch = {
			rebuildIndexOnStart true
			//batchSizeToLoadObjects 30
			//threadsForSubsequentFetching 8
			//threadsToLoadObjects 4
			//threadsForIndexWriter 3
			//cacheMode CacheMode.NORMAL
		}
    }

	test {
		grails.gorm.failOnError = true

		grails.logging.jul.usebridge = true

		// mask out secure fields
		grails.exceptionresolver.params.exclude = ['password', 'creditCard']

		/* *****************************************************************************
		 * spring-security settings
		 * for details about service see conf/spring/resources.groovy
		 * the full DN will be equivalent to "cn=${username},${userDnBase}"
		 * ****************************************************************************/

		grails.plugins.springsecurity.ldap.active = false
		grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = false
		grails.plugins.springsecurity.ldap.usernameMapper.userDnDBase = false
	}

    production {
		// set to false once you are comfortable in production
		grails.gorm.failOnError = true

        grails.logging.jul.usebridge = true

		// mask out secure fields
		grails.exceptionresolver.params.exclude = ['password', 'creditCard']

		/* *****************************************************************************
		 * spring-security settings
		 * for details about service see conf/spring/resources.groovy
		 * the full DN will be equivalent to "cn=${username},${userDnBase}"
		 * ****************************************************************************/

		grails.plugins.springsecurity.ldap.active = false
		grails.plugins.springsecurity.ldap.authorities.retrieveGroupRoles = false
		grails.plugins.springsecurity.ldap.usernameMapper.userDnDBase = false
    }
}

/* ******************************************************************************************************************
 * Log4j specific settings
 * *****************************************************************************************************************/

log4j =
{
	// sensible defaults under Tomcat 6.x and Tomcat 7.x
	def catalinaBase = System.properties.getProperty('catalina.base')
	if (!catalinaBase) catalinaBase = '.'
	def logDirectory = "${catalinaBase}/logs"
	def logFile = "${logDirectory}/esaui.log"

	appenders {
		console		name: 'stdout'
		file		name: 'file', file: logFile, append: true
	}

	environments {

		development {

			/*
			 * grails.app - most everything to do with grails
			 * grails.app.{domain,controllers,services,taglib}.org.ihc.esa - my package specific code
			 * org.hibernate.SQL - for hibernate SQL
			 * org.hibernate.type - for hibernate parameter bindings
			 * org.hibernate.type.descriptor.sql.BasicBinder - see http://burtbeckwith.com/blog/?p=1604
			 * org.codehaus.groovy.grails.web.sitemesh - for Layout debugging
			 * grails.plugins.twitterbootstrap - for the css twitter bootstrap plugin
			 * grails.plugins.reloadconfig - for external reload configuration plugin
			 * grails.app.services.grails.plugins.springsecurity.ui.SpringSecurityUiService - for output from springsecurity.ui plugin
			 * grails.app.services.org.grails.plugins.console.ConsoleService - for output from the console plugin
			 */

			// debug 	'grails.app'

			// trace	'org.hibernate.type.descriptor.sql.BasicBinder'
		}

		test {
			// set appropriate defaults for test environment
		}

		production {
			// FIXME: set appropriate production default logging
		}
	}
}

/* ******************************************************************************************************************
 * joda specific settings
 * *****************************************************************************************************************/

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

/* ******************************************************************************************************************
 * twitter bootstrap specific settings
 * *****************************************************************************************************************/

grails.plugins.twitterbootstrap.fixtaglib = true
grails.plugins.twitterbootstrap.defaultBundle = 'bundle_bootstrap'

/* ******************************************************************************************************************
 * spring-security specific settings
 * *****************************************************************************************************************/

grails.plugins.springsecurity.userLookup.userDomainClassName = 'org.ihc.esa.EsaUser'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'org.ihc.esa.EsaUserEsaRole'
grails.plugins.springsecurity.authority.className = 'org.ihc.esa.EsaRole'
grails.plugins.springsecurity.controllerAnnotations.staticRules = [
	'/user/**': ['ROLE_ESA_ADMIN'],
	'/role/**': ['ROLE_ESA_ADMIN'],
	'/registrationCode/**': ['ROLE_ESA_ADMIN'],
	'/securityInfo/**': ['ROLE_ESA_ADMIN']
]

// springsecurity Login Config Settings; authenticationProcessingFilter
grails.plugins.springsecurity.apf.allowSessionCreation = true

// successHandler
grails.plugins.springsecurity.successHandler.useReferrer = true
grails.plugins.springsecurity.successHandler.alwaysUseDefault = false

/* ******************************************************************************************************************
 * spring-security-ui specific settings
 * *****************************************************************************************************************/

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

/* ******************************************************************************************************************
 * external-config-reload settings
 * *****************************************************************************************************************/

grails.plugins.reloadConfig.files = []
grails.plugins.reloadConfig.includeConfigLocations = true
grails.plugins.reloadConfig.interval = 5000
grails.plugins.reloadConfig.enabled = true
grails.plugins.reloadConfig.notifyPlugins = ["mail", "external-config-reload", "twitter-bootstrap", "hibernate", "hibernate-search", "jquery", "fields", "resources"]
grails.plugins.reloadConfig.automerge = true

/* ******************************************************************************************************************
 * application configuration files
 * *****************************************************************************************************************/

grails.config.locations = [
	"classpath: ${appName}-config.groovy",
	"file:./${appName}-config.groovy"]

if (System.properties["${appName}.config.location"]) {
	grails.config.locations << "file:" + System.properties["${appName}.config.location"]
}