package org.ihc.esa.domain

import java.util.Date;

class Catalog_Item {

	Item item
	Item_Version item_version
	Catalog catalog
//	Party party			TODO Stuart forgot this one
//	Integer item_version_id	TODO Stuart forgot this one too
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by

	static mapping = {
		id generator:'sequence', params:[sequence:'CATALOG_ITEM_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
    }
}
