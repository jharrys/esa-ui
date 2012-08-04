package org.ihc.esa.domain

import java.util.Date

class LookupList {

    String name
    String prompt
    String description
    String listType
    String sqlStatement
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        fieldLookup: FormField,
        lookupElementLookupList: LookupElement
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'LOOKUP_LIST_SEQ']
        table 'LOOKUP_LIST'
        version false

        fieldLookup joinTable: [ name: 'FORM_FIELD', key: 'LOOKUP_LIST_ID']
        lookupElementLookupList joinTable: [ name: 'LOOKUP_ELEMENT', key: 'LOOKUP_LIST_ID']

        name column: 'NAME'
        prompt column: 'PROMPT'
        description column: 'DESCRIPTION'
        listType column: 'LIST_TYPE'
        sqlStatement column: 'SQL_STATEMENT'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        name nullable: false
        prompt nullable: false
        description nullable: true
        listType nullable: false
        sqlStatement nullable: true
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}