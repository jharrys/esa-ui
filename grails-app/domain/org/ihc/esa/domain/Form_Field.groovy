package org.ihc.esa.domain

import java.util.Date;

class Form_Field {

	Integer section_number
	String question
	String data_type
	String multi_select
	Lookup_List lookup_list
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = [form: Form]
	
	static hasMany = [question_response: Question_Response]

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
    }
}
