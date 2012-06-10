package org.ihc.esa.domain

import java.util.Date;

class Catalog {
	
	String name
	String description
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static hasMany = [catalog_items: Catalog_Item]

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
		description nullable: true
    }
}
