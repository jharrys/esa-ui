package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class ConfigurationCatalog
{
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = [
		parentItem: Item,
		parentItemVersion: ItemVersion,
		elementItem: Item,		// TODO should this be a hasMany relationship?
		elementItemVersion: ItemVersion
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'CONFIGURATION_CATALOG_SEQ']
		table 'CONFIGURATION_CATALOG'
		version false
		
		parentItem column: 'PARENT_ITEM_ID'
		parentItemVersion column: 'PARENT_ITEM_VERSION_ID'
		elementItem column: 'ELEMENT_ITEM_ID'
		elementItemVersion column: 'ELEMENT_ITEM_VERSION_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		
		parentItem nullable: false
		parentItemVersion nullable: true
		elementItem nullable: false
		elementItemVersion nullable: true
		createdBy nullable: false
		updatedBy nullable: false
	}
}