package org.ihc.esa.domain

import java.util.Date;

class Address {

	String address_line1
	String address_line2
	String address_line3
	String address_line4
	String city
	String state
	String zip
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = Party
	static hasMany = [parties: Party]
	
	static mapping = {
		id generator:'sequence', params:[sequence:'ADDRESS_SEQ']
		parties column: 'Party_Id', joinTable: 'PARTY_ADDRESS'
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}
	
    static constraints = {
		address_line1 nullable: true
		address_line2 nullable: true
		address_line3 nullable: true
		address_line4 nullable: true
		city nullable: true
		state nullable: true
		zip nullable: true
    }
}
