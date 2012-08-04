package org.ihc.esa.domain

import java.util.Date
import java.math.BigDecimal

class Service {

    String serviceName
    String serviceType
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        partOfRole: RoleService
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'SERVICE_SEQ']
        table 'SERVICE'
        version false

        partOfRole joinTable: [ name: 'ROLE_SERVICE', key: 'SERVICE_ID']

        serviceName column: 'SERVICE_NAME'
        serviceType column: 'SERVICE_TYPE'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        serviceName nullable: false
        serviceType nullable: false
        dateCreated nullable: false
        createdBy nullable: false
        lastUpdated nullable: false
        updatedBy nullable: false

    }

}