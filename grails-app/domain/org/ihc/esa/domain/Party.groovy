package org.ihc.esa.domain

import java.util.Date;


class Party {

	String external_id
	String type
	String name
	String email_address
	String mobile_phone_number
	String work_phone_number
	String home_phone_number
	String web_site_url
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static hasMany = [addresses: Address]
	
	static mapping = {
		addresses column: 'Address_Id', joinTable: 'PARTY_ADDRESS'
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
	}
	
    static constraints = {
		external_id nullable: true
		type nullable: true
		email_address nullable: true
		mobile_phone_number nullable: true
		work_phone_number nullable: true
		home_phone_number nullable: true
		web_site_url nullable: true
    }
}
