package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
--------------------------------------------------------------------------*/

/**
 * <p>
 * This domain class represents a complex join table, and therefore we need to
 * define it as a domain class. It describes relationship between Catalog, Item 
 * and ItemVersion. Because ItemVersion is tied to both a Catalog and Item we 
 * need this entity class. If ItemVersion could be tied to just one of the classes
 * then we could skip this join table.
 * </p>
 * <p>
 * {@link #catalog} is required.
 * {@link #item} is required.
 * {@link #itemVersion} can be nullable.
 * </p>
 * @see Catalog
 * @see Item
 * @see ItemVersion
 * 
 * @author lpjharri
 */

class CatalogItem
{
	/**
	 * Catalog the Item is stored against.
	 * Required.
	 */
	Catalog catalog
	
	/**
	 * Item to store in Catalog.
	 * Required.
	 */
	Item item
	
	/**
	 * ItemVersion for Catalog and Item combination.
	 * Can be nullable.
	 */
	ItemVersion itemVersion
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = [ Catalog, Item, ItemVersion ]
	
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
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}