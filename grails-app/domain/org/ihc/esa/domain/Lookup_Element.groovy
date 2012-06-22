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
		id generator:'sequence', params:[sequence:'LOOKUP_ELEMENT_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
		lookup_list nullable: true
    }
}
