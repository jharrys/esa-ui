package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class Document
{
	
	String sirpId
	String requestor
	String requestorEmail
	String owner
	String ownerEmail
	String justification
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	String getTitle() {
		owner + " - " + requestor
	}
	
	static transients = ['title']
	
	static hasMany = [
		items: Item,
		responses: QuestionResponse
	]
	
	static belongsTo = [
		form: Form,
		vendorRepresentativeParty: Party
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'DOCUMENT_SEQ']
		table 'DOCUMENT'
		version false
		
		form column: 'FORM_ID'
		sirpId column: 'SIRP_ID'
		requestor column: 'REQUESTOR'
		requestorEmail column: 'REQUESTOR_EMAIL'
		owner column: 'OWNER'
		ownerEmail column: 'OWNER_EMAIL'
		justification column: 'JUSTIFICATION'
		vendorRepresentativeParty column: 'VENDOR_REPRESENTATIVE_PARTY_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		
		form nullable: false
		sirpId nullable: true
		requestor nullable: false
		requestorEmail nullable: false
		owner nullable: false
		ownerEmail nullable: false
		justification nullable: true
		vendorRepresentativeParty nullable: true
		createdBy nullable: true
		updatedBy nullable: true
	}
	
	String toString() {
		this.getTitle()
	}
}