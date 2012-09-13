package org.ihc.esa

/***************************************************************************
	Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
	Copyright 2012 by Intermountain Healthcare
***************************************************************************/

import java.util.Date
class Role {

    String name
    Date dateCreated
    String createdBy
    Date lastUpdated
    String updatedBy

    static hasMany = [
        roleService: RoleService
    ]

    static mapping = {

        id generator:'sequence', params:[sequence:'ROLE_SEQ']
        table 'ROLE'
        version false

        roleService joinTable: [ name: 'ROLE_SERVICE', key: 'ROLE_ID']

        name column: 'NAME'
        dateCreated column: 'DATE_CREATED'
        createdBy column: 'CREATED_BY'
        lastUpdated column: 'LAST_UPDATED'
        updatedBy column: 'UPDATED_BY'

    }

    static constraints = {

        name nullable: false
        createdBy nullable: false
        updatedBy nullable: false

    }

}