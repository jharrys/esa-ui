package org.ihc.esa.domain

import java.util.Date;

class Role_Privileges {

	Application_Role granted_application_role
	Application_Service granted_application_service	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
    }
}
