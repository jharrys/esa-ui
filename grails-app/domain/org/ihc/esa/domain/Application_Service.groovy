package org.ihc.esa.domain

import java.util.Date;

class Application_Service {

	String service_name
	String service_type
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static hasMany = [role_privileges: Role_Privileges]

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}
	
    static constraints = {
    }
}
