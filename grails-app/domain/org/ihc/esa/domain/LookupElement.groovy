package org.ihc.esa.domain

/***************************************************************************
	Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
	Copyright 2012 by Intermountain Healthcare
***************************************************************************/

import java.util.Date
class LookupElement {

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

        lookupList column: 'LOOKUP_LIST_ID'
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
        createdBy nullable: false
        updatedBy nullable: false

    }

}