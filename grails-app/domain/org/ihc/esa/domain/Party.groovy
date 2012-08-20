package org.ihc.esa.domain

/***************************************************************************
	Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
	Copyright 2012 by Intermountain Healthcare
***************************************************************************/

import java.util.Date
class Party {

    String externalId
    String type
    String name
    String emailAddress
    String mobilePhoneNumber
    String workPhoneNumber
    String homePhoneNumber
    String webSiteUrl
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        vendorRepresentative: Document,
        vendorItem: Item,
        partyAddressParty: PartyAddress,
        partyRelationshipParty: PartyRelationship,
        partyRelationshipParty1: PartyRelationship
    ]
	
	static mappedBy = [
		partyRelationshipParty:"parentParty",
		partyRelationshipParty1:"childParty"
	]

    static mapping = {

        id generator:'sequence', params:[sequence:'PARTY_SEQ']
        table 'PARTY'
        version false

        vendorRepresentative joinTable: [ name: 'DOCUMENT', key: 'VENDOR_REPRESENTATIVE_PARTY_ID']
        vendorItem joinTable: [ name: 'ITEM', key: 'VENDOR_PARTY_ID']
        partyAddressParty joinTable: [ name: 'PARTY_ADDRESS', key: 'PARTY_ID']
        partyRelationshipParty joinTable: [ name: 'PARTY_RELATIONSHIP', key: 'PARENT_PARTY_ID']
        partyRelationshipParty1 joinTable: [ name: 'PARTY_RELATIONSHIP', key: 'CHILD_PARTY_ID']

        externalId column: 'EXTERNAL_ID'
        type column: 'TYPE'
        name column: 'NAME'
        emailAddress column: 'EMAIL_ADDRESS'
        mobilePhoneNumber column: 'MOBILE_PHONE_NUMBER'
        workPhoneNumber column: 'WORK_PHONE_NUMBER'
        homePhoneNumber column: 'HOME_PHONE_NUMBER'
        webSiteUrl column: 'WEB_SITE_URL'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        externalId nullable: true
        type nullable: false
        name nullable: true
        emailAddress nullable: true
        mobilePhoneNumber nullable: true
        workPhoneNumber nullable: true
        homePhoneNumber nullable: true
        webSiteUrl nullable: true
        createdBy nullable: false
        updatedBy nullable: false

    }

}