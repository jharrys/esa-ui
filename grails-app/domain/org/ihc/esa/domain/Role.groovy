package org.ihc.esa.domain

import java.util.Date;

class Role {

	String name
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static hasMany = [role_services: Role_Service]

	static mapping = {
		id generator:'sequence', params:[sequence:'ROLE_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}
		
    static constraints = {
    }
}
