package org.ihc.esa.domain

import java.util.Date

class ConfigurationCatalog {

    BigDecimal parentItemId
    BigDecimal parentItemVersionId
    BigDecimal elementItemId
    BigDecimal elementItemVersionId
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static belongsTo = [
        parentItem: Item,
        elementItem: Item,
        elementItemVersion: ItemVersion,
        parentItemVersion: ItemVersion
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'CONFIGURATION_CATALOG_SEQ']
        table 'CONFIGURATION_CATALOG'
        version false

        parentItem joinTable: [ name:'ITEM', key: 'PARENT_ITEM_ID' ]
        elementItem joinTable: [ name:'ITEM', key: 'ELEMENT_ITEM_ID' ]
        elementItemVersion joinTable: [ name:'ITEM_VERSION', key: 'ELEMENT_ITEM_VERSION_ID' ]
        parentItemVersion joinTable: [ name:'ITEM_VERSION', key: 'PARENT_ITEM_VERSION_ID' ]

        parentItemId column: 'PARENT_ITEM_ID'
        parentItemVersionId column: 'PARENT_ITEM_VERSION_ID'
        elementItemId column: 'ELEMENT_ITEM_ID'
        elementItemVersionId column: 'ELEMENT_ITEM_VERSION_ID'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        parentItemId nullable: false
        elementItemId nullable: false
        createdBy nullable: false
        updatedBy nullable: false

    }

}