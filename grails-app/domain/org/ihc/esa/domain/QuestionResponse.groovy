package org.ihc.esa.domain

import java.util.Date
import java.math.BigDecimal

class QuestionResponse {

    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy
    BigDecimal formFieldId
    BigDecimal documentId
    String stringValue
    BigDecimal floatValue
    Date dateValue

    static belongsTo = [
        document: Document,
        formField: FormField
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'QUESTION_RESPONSE_SEQ']
        table 'QUESTION_RESPONSE'
        version false

        document joinTable: [ name:'DOCUMENT', key: 'DOCUMENT_ID' ]
        formField joinTable: [ name:'FORM_FIELD', key: 'FORM_FIELD_ID' ]

        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'
        formFieldId column: 'FORM_FIELD_ID'
        documentId column: 'DOCUMENT_ID'
        stringValue column: 'STRING_VALUE'
        floatValue column: 'FLOAT_VALUE'
        dateValue column: 'DATE_VALUE'

    }

    static constraints = {

        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false
        stringValue nullable: true
        floatValue nullable: true
        dateValue nullable: true

    }

}