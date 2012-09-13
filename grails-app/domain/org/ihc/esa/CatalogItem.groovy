package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class CatalogItem
{
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = [
		catalog: Catalog,
		item: Item,
		itemVersion: ItemVersion
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'CATALOG_ITEM_SEQ']
		table 'CATALOG_ITEM'
		version false
		
		item column: 'ITEM_ID'
		catalog column: 'CATALOG_ID'
		itemVersion column: 'ITEM_VERSION_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		
		item nullable: false
		catalog nullable: false
		itemVersion nullable: true
		createdBy nullable: false
		updatedBy nullable: false
	}
}