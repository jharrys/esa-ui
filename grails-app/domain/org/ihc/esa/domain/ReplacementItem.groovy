package org.ihc.esa.domain

import java.util.Date

class ReplacementItem {

    BigDecimal itemId
    BigDecimal replacementItemId
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static belongsTo = [
        replacementItem: Item,
        item: Item
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'REPLACEMENT_ITEM_SEQ']
        table 'REPLACEMENT_ITEM'
        version false

        replacementItem joinTable: [ name:'ITEM', key: 'REPLACEMENT_ITEM_ID' ]
        item joinTable: [ name:'ITEM', key: 'ITEM_ID' ]

        itemId column: 'ITEM_ID'
        replacementItemId column: 'REPLACEMENT_ITEM_ID'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        itemId nullable: false
        replacementItemId nullable: false
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}