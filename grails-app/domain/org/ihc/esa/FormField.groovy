package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * FormField represents a discrete element to be rendered as part of a {@link Document}
 * instance. Each FormField can be associated with a response by the end-user and this response
 * would be stored in the {@link QuestionResponse} class which ties it back to the
 * {@link Document} instance as well as this FormField.
 * </p>
 * <p>
 * {@link #form} is required to link back to it's type.
 * {@link #sectionNumber} is required, probably doesn't need to be.
 * {@link #question} is required as it is the rendered representation of this FormField.
 * </p>
 * <p>
 * The rest of the properties are nullable.
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Form
 * @see LookupList
 * @see LookupElement
 */
class FormField
{
	/**
	 * The type of {@link Form} this question is associated with.
	 * Required.
	 */
	Form form
	
	/**
	 * Optional field for rendering presentation.
	 */
	BigDecimal pageNumber
	
	/**
	 * A required field for rendering presentation.
	 */
	BigDecimal sectionNumber
	
	/**
	 * An optional field for rendering presentation.
	 */
	BigDecimal orderNumber
	
	/**
	 * Tells rendering system whether this FormField requires a response.
	 * Default "N".
	 */
	String required = "N"
	
	/**
	 * Tells rendering system whether this FormField should be rendered to basic end-user.
	 * Default "N".
	 */
	String internalOnly = "N"
	
	/**
	 * Required FormField. The question to render.
	 */
	String question
	
	/**
	 * Optional. Not sure what it's use is for.
	 */
	String searchListing
	
	/**
	 * Optional. Expresses the type of data for response.
	 */
	String dataType
	
	/**
	 * Tells rendering system whether {@link #lookupList} is multi-select or not.
	 * Default "N".
	 */
	String multiSelect
	
	/**
	 * The default value for a String type.
	 * Optional.
	 */
	String defaultValueString
	
	/**
	 * The default value for a Float type.
	 * Optional.
	 */
	BigDecimal defaultValueFloat
	
	/**
	 * The default value for a Date type.
	 * Optional.
	 */
	Date defaultValueDate
	
	/**
	 * Optional {@link LookupList} to render to the end-user.
	 */
	LookupList lookupList
	
	/**
	 * Optional input type to render to the end-user.
	 */
	String formInputType
	
	/**
	 * Optional css class for web rendering systems.
	 */
	String cssClass
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	/**
	 * A derived field to ease computation of value, since value
	 * can be of three different types.
	 */
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
	
	static belongsTo = Form
	
	/**
	 * FormField maps to table FORM_FIELD
	 */
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
		multiSelect column: 'MULTI_SELECT'
		defaultValueString column: 'DEFAULT_VALUE_STRING'
		defaultValueFloat column: 'DEFAULT_VALUE_FLOAT'
		defaultValueDate column: 'DEFAULT_VALUE_DATE'
		lookupList column: 'LOOKUP_LIST_ID'
		formInputType column: 'FORM_INPUT_TYPE'
		cssClass column: 'CSS_CLASS'
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
		required nullable: true, inList: ["Y","N"], blank: false, size: 1..1
		internalOnly nullable: true, inList: ["Y","N"], blank: false, size: 1..1
		question nullable: false, blank: false, size: 1..4000
		searchListing nullable: true, inList:["Y","N"], blank: false, size: 1..1 
		dataType nullable: true, blank: false, size: 1..20
		multiSelect nullable: true, inList: ["Y","N"], blank: false, size: 1..1
		defaultValueString nullable: true, blank: false, size: 1..4000
		defaultValueFloat nullable: true
		defaultValueDate nullable: true
		lookupList nullable: true
		formInputType nullable: true, blank: false, size: 1..40
		cssClass nullable: true, blank: false, size: 1..128
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}