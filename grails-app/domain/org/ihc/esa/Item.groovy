package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class Item
{
	
	String externalId
	String sourceSystem
	String standard
	Document document
	Party vendorParty
	BigDecimal intermountainItemNumber
	String name
	String description
	String generalLedgerCode
	String productGroup
	String technologyGroup
	Contract contract
	Date availableDate
	Date ihcActualDecomissioned
	Date ihcProposedDecomissioned
	Date vendorDecomissioned
	String vendorCatalogNumber
	BigDecimal manufacturerPartId
	String manufacturerCatalogNumber
	String purchasingUnitOfMeasure
	BigDecimal purchasingUnitPrice
	String unspscNumber
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	// part of searchable plugin
	static searchable = true
	
	static hasMany = [
		itemConversions: ItemUnitsConversion,
		itemVersions: ItemVersion,
		replacementForItems: ReplacementItem,
		replacementItems: ReplacementItem,
		belongsToCatalogs: CatalogItem,
		configurationElements: ConfigurationCatalog,
		partOfConfigurations: ConfigurationCatalog
	]
	
	static mappedBy = [
		configurationElements:"parentItem",
		partOfConfigurations:"elementItem",
		replacementForItems:"replacementItem",
		replacementItems:"item"
	]
	
	static belongsTo = [
		contract: Contract,
		document: Document,
		vendorParty: Party
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'ITEM_SEQ']
		table 'ITEM'
		version false
		
		externalId column: 'EXTERNAL_ID'
		sourceSystem column: 'SOURCE_SYSTEM'
		standard column: 'STANDARD'
		document column: 'DOCUMENT_ID'
		vendorParty column: 'VENDOR_PARTY_ID'
		intermountainItemNumber column: 'INTERMOUNTAIN_ITEM_NUMBER'
		name column: 'NAME'
		description column: 'DESCRIPTION'
		generalLedgerCode column: 'GENERAL_LEDGER_CODE'
		productGroup column: 'PRODUCT_GROUP'
		technologyGroup column: 'TECHNOLOGY_GROUP'
		contract column: 'CONTRACT_ID'
		availableDate column: 'AVAILABLE_DATE'
		ihcActualDecomissioned column: 'IHC_ACTUAL_DECOMISSIONED'
		ihcProposedDecomissioned column: 'IHC_PROPOSED_DECOMISSIONED'
		vendorDecomissioned column: 'VENDOR_DECOMISSIONED'
		vendorCatalogNumber column: 'VENDOR_CATALOG_NUMBER'
		manufacturerPartId column: 'MANUFACTURER_PART_ID'
		manufacturerCatalogNumber column: 'MANUFACTURER_CATALOG_NUMBER'
		purchasingUnitOfMeasure column: 'PURCHASING_UNIT_OF_MEASURE'
		purchasingUnitPrice column: 'PURCHASING_UNIT_PRICE'
		unspscNumber column: 'UNSPSC_NUMBER'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		
		externalId nullable: true
		sourceSystem nullable: true
		standard (inList: ["Y", "N"], nullable: true)
		document nullable: true
		intermountainItemNumber nullable: true
		name nullable: true
		description nullable: true
		generalLedgerCode nullable: true
		productGroup nullable: true
		technologyGroup nullable: true
		contract nullable: true
		availableDate nullable: true
		ihcActualDecomissioned nullable: true
		ihcProposedDecomissioned nullable: true
		vendorDecomissioned nullable: true
		vendorCatalogNumber nullable: true
		manufacturerPartId nullable: true
		manufacturerCatalogNumber nullable: true
		purchasingUnitOfMeasure nullable: true
		purchasingUnitPrice nullable: true
		unspscNumber nullable: true
		createdBy nullable: false
		updatedBy nullable: false
		vendorParty nullable: true
	}
}