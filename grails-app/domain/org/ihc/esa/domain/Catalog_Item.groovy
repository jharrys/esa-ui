package org.ihc.esa.domain

import java.util.Date;

class Catalog_Item {

	Item item
	Item_Version item_version
	Catalog catalog
	//TODO: Need to modify DB with the correct FK constraints below for Party and Item_Version
//	Party party
//	Integer item_version_id
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
