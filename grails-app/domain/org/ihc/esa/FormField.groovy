package org.ihc.esa

/***************************************************************************
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 ***************************************************************************/

class FormField
{
	
	BigDecimal pageNumber
	BigDecimal sectionNumber
	BigDecimal orderNumber
	String required
	String internalOnly
	String question
	String searchListing
	String dataType
	String formInputType
	String cssClass
	String multiSelect
	String defaultValueString
	BigDecimal defaultValueFloat
	Date defaultValueDate
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	LookupList lookupList
	
	def defaultValue = null
	
	def getDefaultValue() {
		if (this.defaultValue == null) {
			if (defaultValueString != null) {
				this.defaultValue = defaultValueString
			} else if (defaultValueDate != null) {
				this.defaultValue = defaultValueDate
			} else if (defaultValueFloat != null) {
				this.defaultValue = defaultValueFloat
			}
		}
		
		return this.defaultValue
	}
	
	static transients = ['defaultValue']
	
	static hasMany = [
		responseToQuestion: QuestionResponse
	]
	
	static belongsTo = [
		form: Form
	]
	
	static mapping =
	{
		
		id generator:'sequence', params:[sequence:'FORM_FIELD_SEQ']
		table 'FORM_FIELD'
		version false
		
		form column: 'FORM_ID'
		pageNumber column: 'PAGE_NUMBER'
		sectionNumber column: 'SECTION_NUMBER'
		orderNumber column: 'ORDER_NUMBER'
		required column: 'REQUIRED'
		internalOnly column: 'INTERNAL_ONLY'
		question column: 'QUESTION'
		searchListing column: 'SEARCH_LISTING'
		dataType column: 'DATA_TYPE'
		formInputType column: 'FORM_INPUT_TYPE'
		cssClass column: 'CSS_CLASS'
		multiSelect column: 'MULTI_SELECT'
		defaultValueString column: 'DEFAULT_VALUE_STRING'
		defaultValueFloat column: 'DEFAULT_VALUE_FLOAT'
		defaultValueDate column: 'DEFAULT_VALUE_DATE'
		lookupList column: 'LOOKUP_LIST_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		
		form nullable: false
		pageNumber nullable: true
		sectionNumber nullable: false
		orderNumber nullable: true
		required nullable: false
		internalOnly nullable: false
		question nullable: false
		searchListing inList:["Y","N"], size: 1..1
		dataType nullable: false
		formInputType nullable: true
		cssClass nullable: true
		multiSelect nullable: false
		defaultValueString nullable: true
		defaultValueFloat nullable: true
		defaultValueDate nullable: true
		lookupList nullable: true
		createdBy nullable: false
		updatedBy nullable: false
	}
}