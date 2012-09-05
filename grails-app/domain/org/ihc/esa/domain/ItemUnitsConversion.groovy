package org.ihc.esa.domain

/***************************************************************************
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class ItemUnitsConversion
{
	
	BigDecimal value
	String units
	String perUnits
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = [
		item: Item
	]
	
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
		
		item nullable: true
		value nullable: true
		units nullable: true
		perUnits nullable: true
		createdBy nullable: false
		updatedBy nullable: false
	}
}