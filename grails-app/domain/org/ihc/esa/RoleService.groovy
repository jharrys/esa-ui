package org.ihc.esa

/***************************************************************************
	Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
	Copyright 2012 by Intermountain Healthcare
***************************************************************************/

import java.util.Date
class RoleService {

    BigDecimal roleId
    BigDecimal serviceId
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static belongsTo = [
        role: Role,
        service: Service
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'ROLE_SERVICE_SEQ']
        table 'ROLE_SERVICE'
        version false

        role joinTable: [ name:'ROLE', key: 'ROLE_ID' ]
        service joinTable: [ name:'SERVICE', key: 'SERVICE_ID' ]

        roleId column: 'ROLE_ID'
        serviceId column: 'SERVICE_ID'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        roleId nullable: false
        serviceId nullable: false
        createdBy nullable: false
        updatedBy nullable: false

    }

}