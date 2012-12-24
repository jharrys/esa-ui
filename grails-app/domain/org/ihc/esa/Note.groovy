package org.ihc.esa

/*--------------------------------------------------------------------------
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Detailed note about {@link #item}(s) or {@link #project}(s). Different types of note can be defined.
 * A Note can belong to several Items or Projects (Many Items/Projects to One Note).
 *
 * TODO: Need to map out the Many-Many relationship when the model settles down.
 *
 * </p>
 * <p>
 * {@link #text} is required.
 * The rest are optional.
 * </p>
 * @author lpjharri
 * @since 0.5
 * @see Item
 * @see Project
 */
class Note
{
	/**
	 * Optional. The {@link Item} that owns this note.
	 */
	Item item

	/**
	 * Optional. The {@link Project} that owns this note.
	 */
	Project project

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

	/*
	 * Many Item(s) to One Note (Parent: Item, Child: Note) (Many - One relationship)
	 * Many Project(s) to One Note (Parent: Project, Child: Note) (Many - One relationship)
	 */
	static belongsTo = [Item, Project]

	/**
	 * Note maps to table NOTE
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'NOTE_SEQ']
		table 'NOTE'
		version false

		item column: 'ITEM_ID'
		project column: 'PROJECT_ID'
		noteType column: 'NOTE_TYPE'
		text column: 'TEXT'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}

	static constraints =
	{
		item nullable: true
		project nullable: true
		noteType nullable: true, size: 1..40
		text nullable: false, blank: false, size: 1..4000
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}