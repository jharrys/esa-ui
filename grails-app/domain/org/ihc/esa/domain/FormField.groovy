package org.ihc.esa.domain

/***************************************************************************
	Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
	Copyright 2012 by Intermountain Healthcare
***************************************************************************/

import java.util.Date
class FormField {

    BigDecimal formId
    BigDecimal sectionNumber
    String question
    String dataType
    String multiSelect
    BigDecimal lookupListId
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        responseToQuestion: QuestionResponse
    ]

    static belongsTo = [
        form: Form,
        lookupList: LookupList
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'FORM_FIELD_SEQ']
        table 'FORM_FIELD'
        version false

        responseToQuestion joinTable: [ name: 'QUESTION_RESPONSE', key: 'FORM_FIELD_ID']

        form joinTable: [ name:'FORM', key: 'FORM_ID' ]
        lookupList joinTable: [ name:'LOOKUP_LIST', key: 'LOOKUP_LIST_ID' ]

        formId column: 'FORM_ID'
        sectionNumber column: 'SECTION_NUMBER'
        question column: 'QUESTION'
        dataType column: 'DATA_TYPE'
        multiSelect column: 'MULTI_SELECT'
        lookupListId column: 'LOOKUP_LIST_ID'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        formId nullable: false
        sectionNumber nullable: true
        question nullable: false
        dataType nullable: false
        multiSelect nullable: false
        createdBy nullable: false
        updatedBy nullable: false

    }

}