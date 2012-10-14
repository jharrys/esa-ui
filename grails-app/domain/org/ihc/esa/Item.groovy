package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Item represents some thing that needs to be tracked by Information Systems.
 * All parameters can be nullable.
 * </p>
 * <p>
 * Item can have multiple categories ({@link Category}).
 * Item can have multiple catalogs ({@link Catalog}).
 * Item can have multiple itemVersions ({@link ItemVersion}).
 * Item can have multiple notes ({@link Note}).
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Catalog
 * @see Contract
 * @see Document
 * @see ItemVersion
 * @see Note
 * @see Party
 */
class Item
{
	/**
	 * Identifier from the external source from which this date was acquired.
	 * This can be nullable.
	 */
	String externalId
	
	/**
	 * Source of this data.
	 * May be nullable.
	 */
	String sourceSystem
	
	/**
	 * 'Y' or 'N' or 'A' whether this Item is part of Intermountain standards ('Y'), an alternate standard ('A') or not a standard ('N'). Defaults to 'N'.
	 */
	String standard = "N"
	
	/**
	 * Describes type of standard.
	 * Can be nullable.
	 */
	String standardType
	
	/**
	 * 'Y' or 'N' whether this Item is an approved exception. Defaults to 'N'.
	 */
	String exception = "N"
	
	/**
	 * 'Y' or 'N' whether this Item is a deviation. Defaults to 'N'.
	 */
	String deviation = "N"
	
	/**
	 * 'Y' or 'N' whether this Item is current in service. Defaults to 'Y'.
	 */
	String inService = "N"
	
	/**
	 * 'Y' or 'N' whether this Item requires an exception to acquire. Defaults to 'N'.
	 */
	String exceptionRequired = "N"
	
	/**
	 * String describing the criteria required to approve.
	 * Can be nullable.
	 */
	String exceptionCriteria
	
	/**
	 * document meant to link to the {@link Document}
	 * that is the standard or the exception for allowing this
	 * Item at Intermountain. Can be nullable.
	 */
	Document document
	
	/**
	 * The vendor ({@link Party}) that this Item is related to.
	 * Can be nullable.
	 */
	Party party
	
	/**
	 * Intermountain specific identifier.
	 * Can be nullable.
	 */
	BigDecimal intermountainItemNumber
	
	/**
	 * Intermountain name for this item.
	 * Can be nullable.
	 */
	String name
	
	/**
	 * Description for this item.
	 * Can be nullable.
	 */
	String description
	
	/**
	 * Billing code for this item.
	 * Can be nullable.
	 */
	String generalLedgerCode
	
	/**
	 * Group that has stewardship over this item.
	 * Can be nullable.
	 */
	String productGroup
	
	/**
	 * Technical team that has stewardship over this item.
	 * Can be nullable.
	 */
	String technologyGroup
	
	/**
	 * {@link Contract} tied to this Item.
	 * Can be nullable.
	 */
	Contract contract
	
	/**
	 * Date this item is available.
	 * Can be nullable.
	 */
	Date availableDate
	
	/**
	 * Date this item is set for decommission.
	 * Can be nullable.
	 */
	Date ihcActualDecomissioned
	
	/**
	 * Date this item is proposed to decommission.
	 * Can be nullable.
	 */
	Date ihcProposedDecomissioned
	
	/**
	 * Date vendor has set to decommission Item.
	 */
	Date vendorDecomissioned
	
	/**
	 * Vendor's catalog identifier.
	 * Can be nullable.
	 */
	String vendorCatalogNumber
	
	/**
	 * Manufacturer's part number.
	 * Can be nullable.
	 */
	BigDecimal manufacturerPartId
	
	/**
	 * Manufacturer's catalog number.
	 * Can be nullable.
	 */
	String manufacturerCatalogNumber
	
	/**
	 * Measuring unit such as box or each.
	 * Can be nullable.
	 */
	String purchasingUnitOfMeasure
	
	/**
	 * Price per unit.
	 * Can be nullable.
	 */
	BigDecimal purchasingUnitPrice
	
	/**
	 * Some number.
	 * Can be nullable.
	 */
	String unspscNumber
	
	/**
	 * Notes about the length of useful life for this Item.
	 * Can be nullable.
	 */
	String usefulLife
	
	/**
	 * Comments about this Item.
	 * Can be nullable.
	 */
	String comments
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	// part of searchable plugin
	static searchable = true
	
	static hasMany = [
		categories: Category,						//validated
		catalogs: Catalog,							//validated
		itemVersions: ItemVersion,					//validated
		notes: Note,								//validated
		itemConversions: ItemUnitsConversion,
		replacementForItems: ReplacementItem,
		replacementItems: ReplacementItem,
		configurationElements: ConfigurationCatalog,
		partOfConfigurations: ConfigurationCatalog
	]
	
	static mappedBy = [
		configurationElements:"parentItem",
		partOfConfigurations:"elementItem",
		replacementForItems:"replacementItem",
		replacementItems:"item"
	]
	
	static belongsTo = [ Contract, Document, Party,	Category, Catalog ]
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'ITEM_SEQ']
		table 'ITEM'
		version false
		
		externalId column: 'EXTERNAL_ID'
		sourceSystem column: 'SOURCE_SYSTEM'
		standard column: 'STANDARD'
		standardType column: 'STANDARD_TYPE'
		exception column: 'EXCEPTION'
		deviation column: 'DEVIATION'
		inService column: 'IN_SERVICE'
		exceptionRequired column: 'EXCEPTION_REQUIRED'
		exceptionCriteria column: 'EXCEPTION_CRITERIA'
		document column: 'DOCUMENT_ID'
		party column: 'VENDOR_PARTY_ID'
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
		usefulLife column: 'USEFUL_LIFE'
		comments column: 'COMMENTS'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
		
		categories joinTable: [name: 'ITEM_CATEGORY',
			key: 'ITEM_ID',
			column: 'CATEGORY_ID']
		
		catalogs joinTable: [name: 'CATALOG_ITEM',
			key: 'ITEM_ID',
			column: 'CATALOG_ID']
	}
	
	static constraints =
	{
		externalId nullable: true, blank: false, size: 1..256
		sourceSystem nullable: true, blank: false, size: 1..256
		standard nullable: true, inList: ["Y", "N", "A"], size: 1..1
		standardType nullable: true, blank: false, size: 1..40
		exception nullable: true, blank: false, inList: ["Y", "N"], size: 1..1
		deviation nullable: true, blank: false, inList: ["Y", "N"], size: 1..1
		inService nullable: true, blank: false, inList: ["Y", "N"], size: 1..1
		exceptionRequired nullable: true, blank: false, inList: ["Y", "N"], size: 1..1
		exceptionCriteria nullable: true, size: 0..2048
		document nullable: true
		intermountainItemNumber nullable: true
		name nullable: true, blank: false, size: 1..128
		description nullable: true, size: 0..512
		generalLedgerCode nullable: true, size: 0..256
		productGroup nullable: true, size: 0..64
		technologyGroup nullable: true, size: 0..64
		contract nullable: true
		availableDate nullable: true, display: true, format: 'yyyy-MM-dd'
		ihcActualDecomissioned nullable: true, display: true, format: 'yyyy-MM-dd'
		ihcProposedDecomissioned nullable: true, display: true, format: 'yyyy-MM-dd'
		vendorDecomissioned nullable: true, display: true, format: 'yyyy-MM-dd'
		vendorCatalogNumber nullable: true, blank: false, size: 1..20
		manufacturerPartId nullable: true
		manufacturerCatalogNumber nullable: true, blank: false, size: 1..256
		purchasingUnitOfMeasure nullable: true, blank: false, size: 1..64
		purchasingUnitPrice nullable: true
		unspscNumber nullable: true, size: 0..20
		usefulLife nullable: true, blank: false, size: 1..128
		comments nullable: true, blank: false, size: 1..4000
		party nullable: true
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}