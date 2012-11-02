package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [20-Aug-2012 15:47:33 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Document object represents any document type artifact produced by EISA. These
 * include but are not limited to: Exceptions, EARB Recommendations, EARB Minutes
 * EARB Agendas, Contracts and other objects.
 * </p>
 * <p>
 * This domain class and the database table it represents are intentionally generic
 * to allow any type of document object to be represented.
 * </p>
 * <p>
 * A document is an instance of the {@link Form} object. Each discrete data element is 
 * defined by a question and a response. The question is defined for a given {@link Form} 
 * in the {@link FormField} class. The responses entered by an end-user as answers to 
 * the questions are stored in the {@link QuestionResponse} class.
 * </p>
 * <p>
 * <b>Implementation Note:</b><br>
 * The derived field {@link #title} depends on a {@link #titleFormField} existing in the
 * FORM_FIELD database with the DATA_TYPE column set to "Title". The response to this
 * question (as noted above would be stored in the QUESTION_RESPONSE table) will serve
 * as the <code>Title</code> of this document instance. 
 * </p>
 * <p>
 * A document instance may also be associated with multiple items ({@link Item}).
 * </p>
 * <p>
 * {@link #form} is required as it expresses an instance of type {@link Form}.
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Form
 * @see FormField
 * @see QuestionResponse
 * @see Item
 */

class Document
{
	/**
	 * Expresses an instance of type {@link Form}.
	 * Required.
	 */
	Form form
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	int hashCode = 0
	
	/**
	 * title is derived from hydrated object.
	 */
	private String title = null
	
	/**
	 * The {@link FormField} element for this {@link Form}
	 * that maps to the <code>Title</code> of the document instance.
	 * By convention it the DataType column is named "Title"
	 */
	private FormField titleFormField = null
	
	String getTitle() {
		
		if (this.title == null) {
			this.getTitleFormField()
			this.title = QuestionResponse.findByDocumentAndFormField(this, this.titleFormField)?.stringValue
		}
		
		return this.title
	}
	
	FormField getTitleFormField() {
		if (this.titleFormField == null) {
			this.titleFormField = FormField.findByDataType("Title")
			this.getTitle()
		}
		
		return this.titleFormField
	}
	
	// part of searchable plugin
	static searchable = true
	
	static transients = ['title', 'titleFormField', 'hashCode']
	
	static hasMany = [
		items: Item,
		responses: QuestionResponse
	]
	
	static belongsTo = Form
	
	/**
	 * Document maps to table DOCUMENT
	 */
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
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
	
	/**
	 * equality based on id and form
	 *
	 * @param c
	 * @return
	 */
	@Override public boolean equals(Document o)
	{
		
		if (this.is(o)) return true
		
		if (o == null) return false
						
		if (o.getClass() != getClass()) return false
		
		if (o.form.equals(this.form) && o.dateCreated.equals(this.dateCreated) && o.createdBy.equals(this.createdBy)) return true
		
		return false
	}
	
	@Override public int hashCode()
	{

		if (this.hashCode==0) {
			int result = 17
			result = (37*result) + this.form.hashCode()
			result = (37*result) + this.dateCreated.hashCode()
			result = (37*result) + this.createdBy.hashCode()
			this.hashCode = result
		}
		
		return this.hashCode
	}
	
	/**
	 * Natural order based strictly off of name
	 * TODO: does compareTo have to satisfy the equals and hashCode? verify ...
	 */
	public int compareTo(Document d)
	{
		int EQUAL = 0
		
		if (this.is(d)) return EQUAL
		
		if (this.equals(d)) return EQUAL
		
		return this.getTitle().compareToIgnoreCase(d.getTitle())
	}
}