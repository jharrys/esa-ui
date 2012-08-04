package org.ihc.esa.domain

import java.util.Date

class LookupElement {

    BigDecimal lookupListId
    String value
    String display
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static belongsTo = [
        lookupList: LookupList
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'LOOKUP_ELEMENT_SEQ']
        table 'LOOKUP_ELEMENT'
        version false

        lookupList joinTable: [ name:'LOOKUP_LIST', key: 'LOOKUP_LIST_ID' ]

        lookupListId column: 'LOOKUP_LIST_ID'
        value column: 'VALUE'
        display column: 'DISPLAY'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        value nullable: false
        display nullable: false
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}