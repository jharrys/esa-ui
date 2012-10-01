package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class QuestionResponse
{
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	String stringValue
	BigDecimal floatValue
	Date dateValue
	
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
	
	static belongsTo = [
		document: Document,
		formField: FormField
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'QUESTION_RESPONSE_SEQ']
		table 'QUESTION_RESPONSE'
		version false
		
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
		formField column: 'FORM_FIELD_ID'
		document column: 'DOCUMENT_ID'
		stringValue column: 'STRING_VALUE'
		floatValue column: 'FLOAT_VALUE'
		dateValue column: 'DATE_VALUE'
	}
	
	static constraints =
	{
		
		createdBy nullable: true
		updatedBy nullable: true
		stringValue nullable: true
		floatValue nullable: true
		dateValue nullable: true
	}
}