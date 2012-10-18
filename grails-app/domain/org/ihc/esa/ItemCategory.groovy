package org.ihc.esa

/*--------------------------------------------------------------------------
 Created manually by JH [05-Oct-2012 13:00:00 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Represents the join table between {@link Item} and {@link Category} 
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Item
 * @see Category
 */

class ItemCategory
{
	/**
	 * Required. The Item of this relationship.
	 */
	Item item
	
	/**
	 * Required. The category that item falls into.
	 */
	Category category
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	/**
	 * Category maps to table ITEM_CATEGORY
	 */
	static mapping =
	{
		cache true
		
		id generator:'sequence', params:[sequence:'ITEM_CATEGORY_SEQ']
		table 'ITEM_CATEGORY'
		version false
		
		item column: 'ITEM_ID'
		category column: 'CATEGORY_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		item nullable: false
		category nullable: false
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}
