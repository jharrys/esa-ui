package org.ihc.esa

import org.codehaus.groovy.grails.commons.ApplicationHolder;
import org.codehaus.groovy.grails.commons.GrailsApplication;

class StandardsController
{
	static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']
	
	def springSecurityService

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [itemInstanceList: Item.findAllByStandard('Y'), itemInstanceTotal: Item.count()]
    }
	
	def error() {
		
		def user = null
		user = springSecurityService.currentUser

		log.error("====================================================================================")
		log.error("error() in standards controller called with params: " + params)
		log.error("username == " + user?.username)
		log.error("roles == " + user?.authorities)
		log.error("====================================================================================")
		
		def subjectString = "error with esa-ui version " + grailsApplication.metadata['app.version'] 
		def htmlBodyString = "<p>params this error page was called with: " + params + "</p>"
		htmlBodyString += "<p> username currently logged in: " + user?.username
		htmlBodyString += "<br> roles are: " + user?.authorities + "</p>"
		htmlBodyString += "<p> exception: " + params.exception + "</p>"
		
		//TODO enable this before sending to production
		log.debug("sending email")
		sendMail {
			from "john.harris@imail.org"
			to "eisa-repository-notify@imail.org"
			subject subjectString
			html htmlBodyString
		}
		
		flash.message = "ESA Team notified. We will try to respond within the next few hours. If it's urgent please contact (801) 442-5527 directly."
		
		log.debug("pass rendering to /confirmation.gsp")
		render(view:"/confirmation")
	}
}
