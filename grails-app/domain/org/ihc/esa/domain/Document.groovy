package org.ihc.esa.domain

import java.util.Date

class Document {

    BigDecimal formId
    String requestor
    String requestorEmail
    String owner
    String ownerEmail
    String justification
    BigDecimal vendorRepresentativePartyId
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        partOfDocument: QuestionResponse
    ]

    static belongsTo = [
        form: Form,
        vendorRepresentativeParty: Party
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'DOCUMENT_SEQ']
        table 'DOCUMENT'
        version false

        partOfDocument joinTable: [ name: 'QUESTION_RESPONSE', key: 'DOCUMENT_ID']

        form joinTable: [ name:'FORM', key: 'FORM_ID' ]
        vendorRepresentativeParty joinTable: [ name:'PARTY', key: 'VENDOR_REPRESENTATIVE_PARTY_ID' ]

        formId column: 'FORM_ID'
        requestor column: 'REQUESTOR'
        requestorEmail column: 'REQUESTOR_EMAIL'
        owner column: 'OWNER'
        ownerEmail column: 'OWNER_EMAIL'
        justification column: 'JUSTIFICATION'
        vendorRepresentativePartyId column: 'VENDOR_REPRESENTATIVE_PARTY_ID'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        formId nullable: false
        requestor nullable: false
        requestorEmail nullable: false
        owner nullable: false
        ownerEmail nullable: false
        justification nullable: false
        vendorRepresentativePartyId nullable: false
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}