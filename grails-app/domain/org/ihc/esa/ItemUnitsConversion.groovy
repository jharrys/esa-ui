package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * Units conversion for parent {@link Item}
 * 
 * @author lpjharri
 * @since 1.0
 * @see Item
 */
class ItemUnitsConversion
{
	/**
	 * Back reference to owning {@link Item}
	 */
	Item item
	
	/**
	 * Optional. Price per unit.
	 * FIXME: Correct number type
	 */
	BigDecimal value
	
	/**
	 * Optional.
	 */
	String units
	
	/**
	 * Optional.
	 */
	String perUnits
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = Item
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'ITEM_UNITS_CONVERSION_SEQ']
		table 'ITEM_UNITS_CONVERSION'
		version false
		
		item column: 'ITEM_ID'
		value column: 'VALUE'
		units column: 'UNITS'
		perUnits column: 'PER_UNITS'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		item nullable: false
		value nullable: true
		units nullable: true, blank: false, size: 1..64
		perUnits nullable: true, blank: false, size: 1..64
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}