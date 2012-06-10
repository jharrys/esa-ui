package org.ihc.esa.domain

import java.util.Date;

class Replacement_Item {

	Item item
	Item replacement_item
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
