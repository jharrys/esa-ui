package org.ihc.esa.domain

/***************************************************************************
	Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
	Copyright 2012 by Intermountain Healthcare
***************************************************************************/

import java.util.Date
class Address {

    String externalId
    String addressLine1
    String addressLine2
    String addressLine3
    String addressLine4
    String city
    String state
    String zip
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        partyAddressAddress: PartyAddress
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'ADDRESS_SEQ']
        table 'ADDRESS'
        version false

        partyAddressAddress joinTable: [ name: 'PARTY_ADDRESS', key: 'ADDRESS_ID']

        externalId column: 'EXTERNAL_ID'
        addressLine1 column: 'ADDRESS_LINE1'
        addressLine2 column: 'ADDRESS_LINE2'
        addressLine3 column: 'ADDRESS_LINE3'
        addressLine4 column: 'ADDRESS_LINE4'
        city column: 'CITY'
        state column: 'STATE'
        zip column: 'ZIP'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        externalId nullable: true
        addressLine1 nullable: true
        addressLine2 nullable: true
        addressLine3 nullable: true
        addressLine4 nullable: true
        city nullable: true
        state nullable: true
        zip nullable: true
        createdBy nullable: false
        updatedBy nullable: false

    }

}