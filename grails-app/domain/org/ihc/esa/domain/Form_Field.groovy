package org.ihc.esa.domain

import java.util.Date;

class Form_Field {

	Integer section_number
	String question
	String data_type
	String multi_select
	Integer lookup_list_id	//TODO error here should be FK
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = [form: Form]
	
	static hasMany = [question_response: Question_Response]

	static mapping = {
		id generator:'sequence', params:[sequence:'FORM_FIELD_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
		version false
	}

    static constraints = {
    }
}
