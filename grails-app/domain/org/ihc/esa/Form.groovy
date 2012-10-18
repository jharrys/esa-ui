package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * A form is an abstract type that may be rendered in a web application to
 * provide feedback and data entry fields in response to questions. Different
 * types of forms that are represented are Exceptions and Recommendations for
 * standards.
 * </p>
 * <p>
 * A {@link Document} is an instance of a Form. See the documentation for Document
 * for detailed implementation notes.
 * </p>
 * <p>
 * A Form will have multiple {@link FormField} elements that represent questions
 * or feedback to be rendered to the screen and used by an end-user to supply
 * responses. The responses are stored in the {@link QuestionResponse} class and associated
 * with a specific {@link Document} instance.
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see FormField
 * @see Document
 * @see QuestionResponse
 */
class Form
{
	/**
	 * The name of this form type expressing its use within EISA.
	 * Required.
	 */
	String name
	
	/**
	 * Description of this form type.
	 * Not required.
	 */
	String description
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	int hashCode = 0
	
	static transients = ['hashCode']
	
	static hasMany = [
		anInstanceOfForm: Document,
		formFields: FormField
	]
	
	/**
	 * Form maps to table FORM
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'FORM_SEQ']
		table 'FORM'
		version false
		autoTimestamp true
		
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
		dateCreated nullable: true
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true
		updatedBy nullable: false, size: 1..40
	}
	
	/**
	 * equality based on id and name (case ignored)
	 *
	 * @param c
	 * @return
	 */
	@Override public boolean equals(Form o)
	{
		
		if (this.is(o)) return true

		if (o == null) return false
		
		if (o.getClass() != getClass()) return false
		
		if (o.dateCreated.equals(this.dateCreated) && o.name.equalsIgnoreCase(this.name)) return true
		
		return false
	}
	
	@Override public int hashCode()
	{

		if (this.hashCode==0) {
			int result = 17
			result = (37*result) + this.name.toLowerCase().hashCode()
			result = (37*result) + this.dateCreated.hashCode()
			this.hashCode = result
		}
		
		return this.hashCode
	}
}