package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 ---------------------------------------------------------------------------*/

/**
 * <p>
 * Party represents a vendor or partner with which we do business.
 * </p>
 * <p>
 * {@link #type} is the only required field, all others are nullable.
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Address
 * @see PartyRelationship
 */
class Party
{
	/**
	 * Identifier from the external source from which this address was acquired.
	 * Can be nullable.
	 */
	String externalId

	/**
	 * Describes the type of relationship we have with the vendor.
	 * Required field.
	 */
	String type

	/**
	 * Vendor name.
	 * Can be nullable.
	 */
	String name

	/**
	 * Valid email address.
	 * Can be nullable.
	 */
	String emailAddress

	String mobilePhoneNumber
	String workPhoneNumber
	String homePhoneNumber

	/**
	 * Valid URL for vendor's website.
	 * Can be nullable.
	 */
	String webSiteUrl

	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy

	int hashCode = 0

	static transients = ['hashCode']

	/**
	 * Return list of architects
	 */
	static listArchitects = where
	{ type == 'architect' }

	/**
	 * Return list of persons
	 */
	static listPersons = where
	{ type == 'person' || type == 'architect'}

	/**
	 * Party can have many of type {@link Item}
	 * Party can have many of type {@link Address}; join table is PARTY_ADDRESS
	 * Party can also have relationships with multiple other parties.
	 */
	static hasMany = [
		addresses: Address,							//validated
		items: Item,										//validated
		partyRelationshipParty: PartyRelationship,
		partyRelationshipParty1: PartyRelationship,
		esaUsers: EsaUser
	]

	static mappedBy = [
		partyRelationshipParty:"parentParty",
		partyRelationshipParty1:"childParty"
	]

	/**
	 * Party maps to table PARTY
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'PARTY_SEQ']
		table 'PARTY'
		version false
		cache true

		externalId column: 'EXTERNAL_ID'
		type column: 'TYPE'
		name column: 'NAME'
		emailAddress column: 'EMAIL_ADDRESS'
		mobilePhoneNumber column: 'MOBILE_PHONE_NUMBER'
		workPhoneNumber column: 'WORK_PHONE_NUMBER'
		homePhoneNumber column: 'HOME_PHONE_NUMBER'
		webSiteUrl column: 'WEB_SITE_URL'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'

		//validated: addresses, items
		addresses cache:true, joinTable: [ name: 'PARTY_ADDRESS', key: 'PARTY_ID', column: 'ADDRESS_ID']
		items cache: true, joinTable: [ name: 'ITEM', key: 'VENDOR_PARTY_ID' ]

		partyRelationshipParty joinTable: [ name: 'PARTY_RELATIONSHIP', key: 'PARENT_PARTY_ID']
		partyRelationshipParty1 joinTable: [ name: 'PARTY_RELATIONSHIP', key: 'CHILD_PARTY_ID']
	}

	static constraints =
	{
		externalId nullable: true, blank: false, size: 1..128
		type nullable: false, blank: false, size: 1..40
		name nullable: true, blank: false, size: 1..128
		emailAddress nullable: true, email: true
		mobilePhoneNumber nullable: true, size: 0..15
		workPhoneNumber nullable: true, size: 0..15
		homePhoneNumber nullable: true, size: 0..15
		webSiteUrl nullable: true, url: true, size: 0..4000
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}

	/**
	 * id - because name can be null
	 *
	 * @param object to compare to
	 * @return boolean
	 */
	@Override public boolean equals(Object object)
	{

		if (!(object instanceof Party)) return false

		if (object == null) return false

		if (this.is(object)) return true

		if (object.id.equals(this.id))	return true

		return false
	}

	@Override public int hashCode()
	{

		if (this.hashCode==0)
		{
			int result = 17
			result = (37*result) + this.id.hashCode()
			this.hashCode = result
		}

		return this.hashCode
	}
}