package org.ihc.esa.domain

import java.util.Date

class Contract {

    String contractNumber
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        itemContract: Item
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'CONTRACT_SEQ']
        table 'CONTRACT'
        version false

        itemContract joinTable: [ name: 'ITEM', key: 'CONTRACT_ID']

        contractNumber column: 'CONTRACT_NUMBER'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        contractNumber nullable: false
        createdBy nullable: false
        updatedBy nullable: false

    }

}