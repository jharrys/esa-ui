package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

import java.util.Date

/**
 * <p>
 * Represents relationships between different vendors.
 * </p>
 * <p>
 * {@link #parentParty} is required.<br />
 * {@link #childParty} is required.<br />
 * {@link #relationship} is required.<br />
 * </p>
 * 
 * @author lpjharri
 * @since 1.0
 * @see Party
 */
class PartyRelationship
{
	/**
	 * Required. The owning party.
	 */
	Party parentParty
	
	/**
	 * Required. The owned party.
	 */
	Party childParty
	
	/**
	 * Required. The type of relationship.
	 */
	String relationshipType
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = [
		childParty: Party,
		parentParty: Party
	]
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'PARTY_RELATIONSHIP_SEQ']
		table 'PARTY_RELATIONSHIP'
		version false
		
		childParty joinTable: [ name:'PARTY', key: 'CHILD_PARTY_ID' ]
		parentParty joinTable: [ name:'PARTY', key: 'PARENT_PARTY_ID' ]
		
		parentParty column: 'PARENT_PARTY_ID'
		childParty column: 'CHILD_PARTY_ID'
		relationshipType column: 'RELATIONSHIP_TYPE'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		parentParty nullable: false
		childParty nullable: false
		relationshipType nullable: false, blank: false, size: 1..40
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}