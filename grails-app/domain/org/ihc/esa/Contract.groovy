package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Contract represents the contract we have with the vendor for a specific
 * {@link Item}.
 * </p>
 * <p>
 * A contract can have many of type {@link Item}.
 * </p>
 * <p>
 * {@link #contractNumber} is required and represents the identifier for the
 * contract on file.
 * </p>
 * @author lpjharri
 *
 */
class Contract
{
	/**
	 * String to identify contract.
	 * Required.
	 */
	String contractNumber
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static hasMany = [
		items: Item
	]
	
	/**
	 * Contract maps to table CONTRACT
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'CONTRACT_SEQ']
		table 'CONTRACT'
		version false
		cache true
		
		items cache:true
		
		contractNumber column: 'CONTRACT_NUMBER'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		contractNumber nullable: false, blank: false, size: 1..256
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}