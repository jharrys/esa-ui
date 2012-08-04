package org.ihc.esa.domain

import java.util.Date

class Catalog {

    String name
    String description
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        hasCatalogItems: CatalogItem
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'CATALOG_SEQ']
        table 'CATALOG'
        version false

        hasCatalogItems joinTable: [ name: 'CATALOG_ITEM', key: 'CATALOG_ID']

        name column: 'NAME'
        description column: 'DESCRIPTION'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        name nullable: false
        description nullable: true
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}