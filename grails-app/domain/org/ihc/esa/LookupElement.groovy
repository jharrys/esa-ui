package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class LookupElement
{
	LookupList lookupList
	String value
	String display
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = LookupList
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'LOOKUP_ELEMENT_SEQ']
		table 'LOOKUP_ELEMENT'
		version false
		
		lookupList column: 'LOOKUP_LIST_ID'
		value column: 'VALUE'
		display column: 'DISPLAY'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		lookupList nullable: true
		value nullable: false, blank: false, size: 1..40
		display nullable: false, blank: false, size: 1..256
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}