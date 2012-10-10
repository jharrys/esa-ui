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
 * {@link #name} is required.
 * {@link #parentCategory} is required. For root nodes parentCategory == this.
 * {@link #parentCategoryPath} is required and is the string representation of the full hierarchy, to prevent recursive queries.
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Item
 */

class Category
{
	/**
	 * Required. The owning node of this node.
	 * Note parentCategory == this for root nodes.
	 */
	Category parentCategory
	
	/**
	 * Required. The full string path including the root node and delimiter '/'.
	 * This is done to avoid a recursive query.
	 */
	String parentCategoryPath
	
	/**
	 * Name for this node or container.
	 * Required.
	 */
	String name
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static hasMany = [ Category, Item ]
	
	static belongsTo = Category
	
	/**
	 * Category maps to table CATEGORY
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'CATEGORY_SEQ']
		table 'CATEGORY'
		version false
		
		parentCategory column: 'PARENT_CATEGORY_ID'
		parentCategoryPath column: 'PARENT_CATEGORY_PATH'
		name column: 'NAME'
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
		parentCategory nullable: false
		parentCategoryPath nullable: false, blank: false, size: 1..4000
		name nullable: false, blank: false, size: 1..128
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}