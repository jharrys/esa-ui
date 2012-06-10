package org.ihc.esa.domain

import java.util.Date;

class Lookup_Element {

	Lookup_List lookup_list
	String value
	String display	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
		lookup_list nullable: true
    }
}
