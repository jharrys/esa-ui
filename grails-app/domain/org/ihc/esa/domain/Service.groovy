package org.ihc.esa.domain

import java.util.Date;

class Service {

	String service_name
	String service_type
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static hasMany = [role_services: Role_Service]

	static mapping = {
		id generator:'sequence', params:[sequence:'SERVICE_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}
	
    static constraints = {
    }
}
