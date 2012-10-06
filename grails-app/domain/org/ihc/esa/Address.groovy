package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

import java.util.Date

/**
 * Simple address domain class.
 * All fields are nullable, including {@link Address}.
 * 
 * @author lpjharri
 * @since 1.0
 * @see Address
 * @see Party
 */

class Address
{
	/**
	 * Identifier from the external source from which this address was acquired.
	 * This can be nullable.
	 */
	String externalId
	
	String addressLine1
	String addressLine2
	String addressLine3
	String addressLine4
	String city
	String state
	String zip
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = Party
	
	/**
	 * Address can have many of type {@link Party} through join table PARTY_ADDRESS
	 */
	static hasMany = [
		parties: Party		//validated
	]
	
	/**
	 * Address maps to table ADDRESS
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'ADDRESS_SEQ']
		table 'ADDRESS'
		version false
		
		externalId column: 'EXTERNAL_ID'
		addressLine1 column: 'ADDRESS_LINE1'
		addressLine2 column: 'ADDRESS_LINE2'
		addressLine3 column: 'ADDRESS_LINE3'
		addressLine4 column: 'ADDRESS_LINE4'
		city column: 'CITY'
		state column: 'STATE'
		zip column: 'ZIP'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
		
		parties joinTable: [ name: 'PARTY_ADDRESS', key: 'ADDRESS_ID', column: 'PARTY_ID']
	}
	
	static constraints =
	{
		externalId nullable: true, size: 0..128
		addressLine1 nullable: true, size: 0..256
		addressLine2 nullable: true, size: 0..256
		addressLine3 nullable: true, size: 0..256
		addressLine4 nullable: true, size: 0..256
		city nullable: true, size: 0..256
		state nullable: true, size: 0..3
		zip nullable: true, size: 0..10
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}