package org.ihc.esa.domain

import java.util.Date

class ItemVersion {

    BigDecimal itemId
    String versionNumber
    Date ihcActualDecomission
    Date ihcProposedDecomissioned
    Date vendorDecomission
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        usingVersion: CatalogItem,
        configurationVersionIs: ConfigurationCatalog,
        elementVersionIs: ConfigurationCatalog
    ]
	
	static mappedBy = [
		configurationVersionIs:"parentItemVersion",
		elementVersionIs:"elementItemVersion"
	]

    static belongsTo = [
        item: Item
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'ITEM_VERSION_SEQ']
        table 'ITEM_VERSION'
        version false

        usingVersion joinTable: [ name: 'CATALOG_ITEM', key: 'ITEM_VERSION_ID']
        configurationVersionIs joinTable: [ name: 'CONFIGURATION_CATALOG', key: 'PARENT_ITEM_VERSION_ID']
        elementVersionIs joinTable: [ name: 'CONFIGURATION_CATALOG', key: 'ELEMENT_ITEM_VERSION_ID']

        item joinTable: [ name:'ITEM', key: 'ITEM_ID' ]

        itemId column: 'ITEM_ID'
        versionNumber column: 'VERSION_NUMBER'
        ihcActualDecomission column: 'IHC_ACTUAL_DECOMISSION'
        ihcProposedDecomissioned column: 'IHC_PROPOSED_DECOMISSIONED'
        vendorDecomission column: 'VENDOR_DECOMISSION'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        itemId nullable: false
        versionNumber nullable: false
        ihcActualDecomission nullable: true
        ihcProposedDecomissioned nullable: true
        vendorDecomission nullable: true
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}