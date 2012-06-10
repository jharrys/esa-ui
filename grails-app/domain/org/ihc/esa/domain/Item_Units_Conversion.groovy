package org.ihc.esa.domain

import java.util.Date;

class Item_Units_Conversion {

	Integer value
	String units
	String per_units	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = [item: Item]

	static mapping = {
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}

    static constraints = {
		item nullable: true
		value nullable: true
		units nullable: true
		per_units nullable: true
    }
}
