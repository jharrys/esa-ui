package org.ihc.esa.domain

import java.util.Date;

class Document {

	String requestor
	String requestor_email
	String owner
	String owner_email
	String justification
	Integer vendor_representative_party_id	// TODO Stuart needs to fix should be FK
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = [form: Form]
	static hasMany = [question_response: Question_Response]

	static mapping = {
		id generator:'sequence', params:[sequence:'DOCUMENT_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
    }
}
