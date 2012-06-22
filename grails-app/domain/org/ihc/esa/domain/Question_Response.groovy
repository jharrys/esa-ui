package org.ihc.esa.domain

import java.util.Date;

class Question_Response {

	Form_Field form_field
	Document document
	String string_value
	Float float_value
	Date date_value	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
		string_value nullable: true
		float_value nullable: true
		date_value nullable: true
		document nullable: true
		form_field nullable: true
    }
}
