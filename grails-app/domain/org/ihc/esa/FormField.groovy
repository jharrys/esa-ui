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
	String question
	String dataType
	String formInputType
	String cssClass
	String multiSelect
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	LookupList lookupList
	
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
		question column: 'QUESTION'
		dataType column: 'DATA_TYPE'
		formInputType column: 'FORM_INPUT_TYPE'
		cssClass column: 'CSS_CLASS'
		multiSelect column: 'MULTI_SELECT'
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
		question nullable: false
		dataType nullable: false
		formInputType nullable: true
		cssClass nullable: true
		multiSelect nullable: false
		createdBy nullable: false
		updatedBy nullable: false
	}
}