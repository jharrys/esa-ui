package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated manually by JH [05-Oct-2012 15:00:00 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Detailed note about {@link #item}. Different types of note can be defined.
 * Items can have multiple notes.
 * </p>
 * <p>
 * {@link #item} is required.
 * {@link #text} is required.
 * The rest are optional.
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Item
 */
class Note
{
	/**
	 * The {@link Item} that owns this note.
	 * Required.
	 */
	Item item
	
	/**
	 * Optional. Type of note this is.
	 */
	String noteType
	
	/**
	 * Required. Actual note.
	 */
	String text
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = Item
	
	/**
	 * Note maps to table NOTE
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'NOTE_SEQ']
		table 'NOTE'
		version false
		
		item column: 'ITEM_ID'
		noteType column: 'NOTE_TYPE'
		text column: 'TEXT'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		item nullable: false
		noteType nullable: true, size: 1..40
		text nullable: false, blank: false, size: 1..4000
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}