package org.ihc.esa.domain

import java.util.Date;

class Application_Role {

	String name
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static hasMany = [role_privleges: Role_Privliges]

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}
		
    static constraints = {
    }
}
