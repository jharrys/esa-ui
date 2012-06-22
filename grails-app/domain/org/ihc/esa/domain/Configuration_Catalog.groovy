package org.ihc.esa.domain

import java.util.Date;

class Configuration_Catalog {

	Item parent_item
	Item element_item
	Item_Version parent_item_version
	Item_Version element_item_version	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static mapping = {
		id generator:'sequence', params:[sequence:'CONFIGURATION_CATALOG_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
		parent_item_version nullable: true
		element_item_version nullable: true
    }
}
