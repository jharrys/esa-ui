package org.ihc.esa

/*--------------------------------------------------------------------------
 Created manually by JH [05-Oct-2012 13:00:00 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Category represents a hierarchy of nodes much like a file systems
 * folder/sub-folder structure. Each node may contain other nodes or leaf nodes.
 * The Category container is used to hold object of type {@link Item}. 
 * </p>
 * <p>
 * The relationship between Category and Item is straightforward. The join table
 * does not need to be defined as it has with the {@link Catalog} relationship.
 * </p>
 * <p>
 * {@link #taxonomy} is the only required field.
 * </p>
 * @author lpjharri
 * @see Item
 */

class Category
{
	/**
	 * Name for this node or container.
	 * Required.
	 */
	String taxonomy
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static hasMany = [
		items: Item
	]
	
	/**
	 * Category maps to table CATEGORY
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'CATEGORY_SEQ']
		table 'CATEGORY'
		version false
		
		taxonomy column: 'TAXONOMY'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
		
		items joinTable: [name: 'ITEM_CATEGORY',
							key: 'CATEGORY_ID',
							column: 'ITEM_ID']
	}
	
	static constraints =
	{
		taxonomy nullable: false, blank: false, size: 1..4000
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}