package org.ihc.esa.domain

import java.util.Date;

class Contract {

	String contract_number	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by

	static mapping = {
		id generator:'sequence', params:[sequence:'CONTRACT_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
    }
}
