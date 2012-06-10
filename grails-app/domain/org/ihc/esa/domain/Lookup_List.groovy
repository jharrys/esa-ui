package org.ihc.esa.domain

import java.util.Date;

class Lookup_List {

	String name
	String prompt
	String description
	String list_type
	String sql_statement	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
		description nullable: true
		sql_statement nullable: true
    }
}
