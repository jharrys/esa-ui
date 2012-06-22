package org.ihc.esa.domain

import java.util.Date;

class Item_Version {

	String version_number
	Date ihc_actual_decomisison
	Date ihc_proposed_decomissioned
	Date vendor_decomission	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = [item: Item]

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
		ihc_actual_decomisison nullable: true
		ihc_proposed_decomissioned nullable: true
		vendor_decomission nullable: true
    }
}
