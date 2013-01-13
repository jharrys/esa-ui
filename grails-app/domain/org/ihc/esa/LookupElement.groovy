package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Represents an element for a given {@link LookupList}.
 * </p>
 * <p>
 * {@link #value} a value for this element (Required).<br />
 * {@link #display} is a string to render to end-user (Required).<br />
 * </p>
 *
 * @author lpjharri
 * @since 1.0
 * @see LookupList
 */
class LookupElement
{
	/**
	 * The owning list (pick list)
	 */
	LookupList lookupList
	
	/**
	 * Required. A value for this element.
	 */
	String value
	
	/**
	 * Required. A string to render to the end-user.
	 */
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
		cache true
		
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
		lookupList nullable: false
		value nullable: false, blank: false, size: 1..40
		display nullable: false, blank: false, size: 1..256
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}