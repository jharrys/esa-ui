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
 * Required fields are: {@link #name}, {@link #standard}, {@link #exception}, {@link #deviation}, {@link #inservice}, {@link #exceptionRequired}.
 * These along with the creation dates and creation by make up equals and hash code.
 * </p>
 * <p>
 * Item can have multiple categories ({@link Category}).<br />
 * Item can have multiple catalogs ({@link Catalog}).<br />
 * Item can have multiple itemVersions ({@link ItemVersion}).<br />
 * Item can have multiple notes ({@link Note}).<br />
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
	 * Such as Enterprise, Intermountain, SelectHealth, Financial, Clinical, Regional, Department.
	 * Can be nullable.
	 */
	StandardType standardType = StandardType.ENTERPRISE

	enum StandardType {
		ENTERPRISE("Enterprise"),
		INTERMOUNTAIN("Intermountain"),
		SELECTHEALTH("SelectHealth"),
		FINANCIAL("Financial"),
		CLINICAL("Clinical"),
		REGIONAL("Regional"),
		DEPARTMENT("Department")

		private String value

		StandardType(String value) { this.value = value }

		public String value() { return this.value }
	}

	/**
	 * Required. 'Y' or 'N' whether this Item is an approved exception. Defaults to 'N'.
	 */
	String exception = "N"

	/**
	 * Required. 'Y' or 'N' whether this Item is a deviation. Defaults to 'N'.
	 */
	String deviation = "N"

	/**
	 * Required. 'Y' or 'N' whether this Item is current in service. Defaults to 'Y'.
	 */
	String inService = "N"

	/**
	 * Required. 'Y' or 'N' whether this Item requires an exception to acquire. Defaults to 'N'.
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
	 * FIXME: Correct number type
	 */
	BigDecimal intermountainItemNumber

	/**
	 * Required. Intermountain name for this item.
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
	Party manufacturerParty

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
	 * FIXME: Correct number type
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

	int hashCode = 0

	static transients = ['hashCode']

	static hasMany = [
		catalogs: Catalog,							//validated
		itemVersions: ItemVersion,					//validated
		notes: Note,								//validated
		categories: Category,
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

	/*
	 * Documenting some decisions here.
	 * Removed Document: an Item's life cycle will not be tied to a Document
	 * Category: because this is a many-many relationship, gorm requires an owner
	 * Catalog: because this is a many-many relationship, gorm requires an owner
	 * FIXME: Do I have to manage the bi-directional relationship between the two Party elements (manufacturerParty & party)?
	 */
	static belongsTo = [ Contract, Party, Catalog, Category ]

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
		manufacturerParty column: 'MANUFACTURER_PARTY_ID'
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
		sourceSystem nullable: true, blank: false, size: 1..256
		name nullable: false, blank: false, size: 1..128
		description nullable: true, size: 0..512
		comments nullable: true, blank: false, size: 1..4000
		document nullable: true
		usefulLife nullable: true, blank: false, size: 1..128
		party nullable: true
		productGroup nullable: true, size: 0..64
		technologyGroup nullable: true, size: 0..64
		standard nullable: false, inList: ["Y", "N", "A"], size: 1..1
		standardType nullable: true, blank: false
		exceptionRequired nullable: false, blank: false, inList: ["Y", "N"], size: 1..1
		exception nullable: false, blank: false, inList: ["Y", "N"], size: 1..1
		exceptionCriteria nullable: true, size: 0..2048
		deviation nullable: false, blank: false, inList: ["Y", "N"], size: 1..1
		inService nullable: false, blank: false, inList: ["Y", "N"], size: 1..1
		intermountainItemNumber nullable: true
		generalLedgerCode nullable: true, size: 0..256
		externalId nullable: true, blank: false, size: 1..256
		vendorCatalogNumber nullable: true, blank: false, size: 1..256
		purchasingUnitOfMeasure nullable: true, blank: false, size: 1..64
		purchasingUnitPrice nullable: true
		manufacturerCatalogNumber nullable: true, blank: false, size: 1..256
		manufacturerParty nullable: true
		unspscNumber nullable: true, size: 0..20
		availableDate nullable: true, display: true, format: 'yyyy-MM-dd'
		ihcActualDecomissioned nullable: true, display: true, format: 'yyyy-MM-dd'
		ihcProposedDecomissioned nullable: true, display: true, format: 'yyyy-MM-dd'
		vendorDecomissioned nullable: true, display: true, format: 'yyyy-MM-dd'
		contract nullable: true
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}

	/**
	 * name, standard, exception, deviation, inService, exceptionRequired, dateCreated and createdBy
	 *
	 * @param
	 * @return boolean
	 */
	@Override public boolean equals(Item item)
	{

		if (this.is(item)) return true

		if (item == null) return false

		if (item.getClass() != getClass()) return false

		if (item.name.equalsIgnoreCase(this.name) && item.standard.equalsIgnoreCase(this.standard) && item.standardType.equals(this.standardType) && item.exception.equalsIgnoreCase(this.exception)) {
			if (item.deviation.equalsIgnoreCase(this.deviation) && item.inService.equalsIgnoreCase(this.inService)) {
				if (item.exceptionRequired.equalsIgnoreCase(this.exceptionRequired)) {
					if (item.createdBy.equalsIgnoreCase(this.createdBy)) {
						return true
					}
				}
			}
		}

		return false
	}

	@Override public int hashCode()
	{

		if (this.hashCode==0) {
			int result = 17
			result = (37*result) + this.name.toLowerCase().hashCode()
			result = (37*result) + this.standard.toUpperCase().hashCode()
			result = (37*result) + (this.standardType ? this.standardType.hashCode() : 0)
			result = (37*result) + this.exception.toUpperCase().hashCode()
			result = (37*result) + this.deviation.toUpperCase().hashCode()
			result = (37*result) + this.inService.toUpperCase().hashCode()
			result = (37*result) + this.exceptionRequired.toUpperCase().hashCode()
			result = (37*result) + this.createdBy.toLowerCase().hashCode()
			this.hashCode = result
		}

		return this.hashCode
	}
}