package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class ReplacementItem
{
	Item item
	Item replacementItem
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = [ Item ]
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'REPLACEMENT_ITEM_SEQ']
		table 'REPLACEMENT_ITEM'
		version false
		
		item column: 'ITEM_ID'
		replacementItem column: 'REPLACEMENT_ITEM_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		item nullable: false
		replacementItem nullable: false
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}