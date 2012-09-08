package org.ihc.esa.domain

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class Form
{
	
	String name
	String description
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static hasMany = [
		anInstanceOfForm: Document,
		formFields: FormField
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'FORM_SEQ']
		table 'FORM'
		version false
		
		anInstanceOfForm joinTable: [ name: 'DOCUMENT', key: 'FORM_ID']
		formFields joinTable: [ name: 'FORM_FIELD', key: 'FORM_ID']
		
		name column: 'NAME'
		description column: 'DESCRIPTION'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		
		name nullable: false
		description nullable: true
		createdBy nullable: false
		updatedBy nullable: false
	}
	
	String toString()
	{
		name
	}
}