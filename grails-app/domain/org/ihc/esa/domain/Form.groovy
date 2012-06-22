package org.ihc.esa.domain

import java.util.Date;

class Form {

	String name
	String description	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static hasMany = [documents: Document]

	static mapping = {
		id generator:'sequence', params:[sequence:'FORM_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
		description nullable: true
    }
}
