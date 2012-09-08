package org.ihc.esa.controllers

import grails.plugins.springsecurity.Secured

//@Secured(['ROLE_ADMIN']) - could do it this way as well
class SecureController
{
	
	@Secured(['ROLE_ADMIN'])
	def index()
	{
		render 'Secure access only'
	}
}
