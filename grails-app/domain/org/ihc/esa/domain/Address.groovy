package org.ihc.esa.domain

import java.util.Date

class Address {

    String addressLine1
    String addressLine2
    String addressLine3
    String addressLine4
    String city
    String state
    String zip
    Date creationDate
    String createdBy
    Date updateDate
    String updatedBy

    static hasMany = [
        partyAddressAddress: PartyAddress
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'ADDRESS_SEQ']
        table 'ADDRESS'
        version false

        partyAddressAddress joinTable: [ name: 'PARTY_ADDRESS', key: 'ADDRESS_ID']

        addressLine1 column: 'ADDRESS_LINE1'
        addressLine2 column: 'ADDRESS_LINE2'
        addressLine3 column: 'ADDRESS_LINE3'
        addressLine4 column: 'ADDRESS_LINE4'
        city column: 'CITY'
        state column: 'STATE'
        zip column: 'ZIP'
        creationDate column: 'CREATION_DATE'
        createdBy column: 'CREATED_BY'
        updateDate column: 'UPDATE_DATE'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        addressLine1 nullable: true
        addressLine2 nullable: true
        addressLine3 nullable: true
        addressLine4 nullable: true
        city nullable: true
        state nullable: true
        zip nullable: true
        creationDate nullable: false
        createdBy nullable: false
        updateDate nullable: false
        updatedBy nullable: false

    }

}