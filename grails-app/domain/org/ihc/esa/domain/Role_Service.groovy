package org.ihc.esa.domain

import java.util.Date;

class Role_Service {

	Role role
	Service service	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static mapping = {
		id generator:'sequence', params:[sequence:'ROLE_SERVICE_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
    }
}
