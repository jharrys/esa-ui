package org.ihc.esa.domain

import java.util.Date

class PartyRelationship {

    BigDecimal parentPartyId
    BigDecimal childPartyId
    String relationshipType
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static belongsTo = [
        childParty: Party,
        parentParty: Party
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'PARTY_RELATIONSHIP_SEQ']
        table 'PARTY_RELATIONSHIP'
        version false

        childParty joinTable: [ name:'PARTY', key: 'CHILD_PARTY_ID' ]
        parentParty joinTable: [ name:'PARTY', key: 'PARENT_PARTY_ID' ]

        parentPartyId column: 'PARENT_PARTY_ID'
        childPartyId column: 'CHILD_PARTY_ID'
        relationshipType column: 'RELATIONSHIP_TYPE'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        parentPartyId nullable: false
        childPartyId nullable: false
        relationshipType nullable: false
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}