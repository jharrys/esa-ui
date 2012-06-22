package org.ihc.esa.domain

import java.util.Date;

class Item_Version {

	String version_number
	Date ihc_actual_decomission
	Date ihc_proposed_decomissioned //TODO need to change this to decommission verify all tables
	Date vendor_decomission	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = [item: Item]

	static mapping = {
		id generator:'sequence', params:[sequence:'ITEM_VERSION_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
		ihc_actual_decomission nullable: true
		ihc_proposed_decomissioned nullable: true
		vendor_decomission nullable: true
    }
}
