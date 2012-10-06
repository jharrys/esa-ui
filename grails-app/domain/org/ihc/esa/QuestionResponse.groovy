package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class QuestionResponse
{
	FormField formField
	Document document
	String stringValue
	BigDecimal floatValue
	Date dateValue
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	def value = null
	
	def getValue() {
		if (this.value == null) {
			if (stringValue != null) {
				this.value = stringValue
			} else if (dateValue != null) {
				this.value = dateValue
			} else if (floatValue != null) {
				this.value = floatValue
			} else {
				this.errors.reject("${id} does not have a value set")
			}
		}
		
		return this.value
	}
	
	static transients = ['value']
	
	static belongsTo = [ Document, FormField ]
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'QUESTION_RESPONSE_SEQ']
		table 'QUESTION_RESPONSE'
		version false
		
		formField column: 'FORM_FIELD_ID'
		document column: 'DOCUMENT_ID'
		stringValue column: 'STRING_VALUE'
		floatValue column: 'FLOAT_VALUE'
		dateValue column: 'DATE_VALUE'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		formField nullable: true, unique: 'document'
		document nullable: true
		stringValue nullable: true, blank: false, size: 1..4000
		floatValue nullable: true
		dateValue nullable: true, display: true, format: 'yyyy-MM-dd'
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}