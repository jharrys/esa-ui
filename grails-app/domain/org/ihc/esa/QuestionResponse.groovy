package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * This QuestionResponse is a response to a question displayed by {@link FormField}.
 * </p>
 * <p>
 * Note the derived field {@link #value}
 * </p>
 * 
 * @author lpjharri
 * @since 1.0
 * @see FormField
 * @see Document
 */
class QuestionResponse
{
	/**
	 * Required. The owning {@link FormField} or question for this specific answer.
	 */
	FormField formField
	
	/**
	 * Required. The owning {@link Document} that displays this particular answer.
	 */
	Document document
	
	/**
	 * Optional value of type String.
	 * Note that one of stringValue, {@link #floatValue} and {@link #dateValue} is required.
	 */
	String stringValue
	
	/**
	 * FIXME: Choose better type
	 * Optional value of type {@link BigDecimal}.
	 * Note that one of {@link #stringValue}, floatValue and {@link #dateValue} is required.
	 */
	BigDecimal floatValue
	
	/**
	 * Optional value of type Date.
	 * Note that one of {@link #stringValue}, {@link #floatValue} and dateValue is required.
	 */
	Date dateValue
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	/**
	 * This value is derived from the getter {@link #getValue()}
	 */
	def value = null
	
	/**
	 * getter method for derived field {@link #value}
	 * 
	 * @return the string, float or date value
	 */
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
	
	private Form form = null
	
	/**
	 * cache the form for easy access
	 * 
	 * @return the {@link Form} tied to this QuestionResponse
	 */
	public Form getForm() {
		if (this.form == null) {
			this.form = document.form
		}
		
		return this.form
	}
	
	static private Map<Form,List<FormField>> formFieldCacheMap = new HashMap<Form,List<FormField>>()
	
	/**
	 * Retrieve the list of {@link FormField}'s for a give {@link Form}. Cache them statically when found.
	 * 
	 * FIXME: Need to handle changes to the FormField list without having to bounce the webapp
	 * 
	 * @param the Form the requested FormFields are linked to
	 * @return a {@link java.util.List} of {@link FormField} used by this {@link Form}
	 */
	static public List<FormField> getAllFormFieldsOfForm(Form form) {
		List<FormField> list = formFieldCacheMap.get(form)
		
		if (list == null) {
			list = FormField.findAllByForm(form)
			if (list) formFieldCacheMap.put(form, list)
		}
		
		return list
	}
	
	static transients = ['value', 'form']
	
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
	
	// FIXME: add my own validator to make sure one of stringValue, floatValue and dateValue is specified.
	static constraints =
	{
		formField nullable: false, unique: 'document'
		document nullable: false
		stringValue nullable: true, blank: false, size: 1..4000
		floatValue nullable: true
		dateValue nullable: true, display: true, format: 'yyyy-MM-dd'
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
	
	
}