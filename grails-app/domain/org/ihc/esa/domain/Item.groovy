package org.ihc.esa.domain

import java.util.Date

class Item {

    String externalId
    String sourceSystem
    String standard
    BigDecimal vendorPartyId
    BigDecimal intermountainItemNumber
    String name
    String description
    String generalLedgerCode
    String productGroup
    String technologyGroup
    BigDecimal contractId
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

    static hasMany = [
        belongsToCatalogs: CatalogItem,
        configurationElements: ConfigurationCatalog,
        partOfConfigurations: ConfigurationCatalog,
        itemConversions: ItemUnitsConversion,
        itemVersions: ItemVersion,
        replacementForItems: ReplacementItem,
        replacementItems: ReplacementItem
    ]
	
	static mappedBy = [
		configurationElements:"parentItem",
		partOfConfigurations:"elementItem",
		replacementForItems:"replacementItem",
		replacementItems:"item"
	]

    static belongsTo = [
        contract: Contract,
        vendorParty: Party
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'ITEM_SEQ']
        table 'ITEM'
        version false

        belongsToCatalogs joinTable: [ name: 'CATALOG_ITEM', key: 'ITEM_ID']
        configurationElements joinTable: [ name: 'CONFIGURATION_CATALOG', key: 'ELEMENT_ITEM_ID']
        partOfConfigurations joinTable: [ name: 'CONFIGURATION_CATALOG', key: 'PARENT_ITEM_ID']
        itemConversions joinTable: [ name: 'ITEM_UNITS_CONVERSION', key: 'ITEM_ID']
        itemVersions joinTable: [ name: 'ITEM_VERSION', key: 'ITEM_ID']
        replacementForItems joinTable: [ name: 'REPLACEMENT_ITEM', key: 'ITEM_ID']
        replacementItems joinTable: [ name: 'REPLACEMENT_ITEM', key: 'REPLACEMENT_ITEM_ID']

        contract joinTable: [ name:'CONTRACT', key: 'CONTRACT_ID' ]
        vendorParty joinTable: [ name:'PARTY', key: 'VENDOR_PARTY_ID' ]

        externalId column: 'EXTERNAL_ID'
        sourceSystem column: 'SOURCE_SYSTEM'
        standard column: 'STANDARD'
        vendorPartyId column: 'VENDOR_PARTY_ID'
        intermountainItemNumber column: 'INTERMOUNTAIN_ITEM_NUMBER'
        name column: 'NAME'
        description column: 'DESCRIPTION'
        generalLedgerCode column: 'GENERAL_LEDGER_CODE'
        productGroup column: 'PRODUCT_GROUP'
        technologyGroup column: 'TECHNOLOGY_GROUP'
        contractId column: 'CONTRACT_ID'
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

    static constraints = {

        externalId nullable: true
        sourceSystem nullable: true
        standard nullable: true
        intermountainItemNumber nullable: true
        name nullable: true
        description nullable: true
        generalLedgerCode nullable: true
        productGroup nullable: true
        technologyGroup nullable: true
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

    }

}