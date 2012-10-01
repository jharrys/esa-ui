package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class Document
{
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	String title = null
	FormField titleFormField = null
	
	String getTitle() {
		if (this.titleFormField == null) {
			this.titleFormField = FormField.findByDataType("Title")
		}
		
		if (this.title == null) {
			this.title = QuestionResponse.findByDocumentAndFormField(this, this.titleFormField)?.stringValue
		}
		
		return this.title
	}
	
	FormField getTitleFormField() {
		if (this.titleFormField == null) {
			this.titleFormField = FormField.findByDataType("Title")
		}
		
		return this.titleFormField
	}
	
	// part of searchable plugin
	static searchable = true
	
	static transients = ['title', 'titleFormField']
	
	static hasMany = [
		items: Item,
		responses: QuestionResponse
	]
	
	static belongsTo = [
		form: Form
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'DOCUMENT_SEQ']
		table 'DOCUMENT'
		version false
		
		form column: 'FORM_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		
		form nullable: false
		createdBy nullable: false
		updatedBy nullable: false
	}
}