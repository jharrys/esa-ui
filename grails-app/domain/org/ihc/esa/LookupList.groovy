package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * This represents a pick list used to render choices to the end-user. LookupLists
 * are made up of {@link LookupElement}s (in which case this list type is 'fixed') or made
 * up dynamically through the use of a sql statement (in which case this list type is 'sql').
 * </p>
 * <p>
 * {@link #name} given to this LookupList. Required.<br />
 * {@link #prompt} the question to render to end-user. Required.<br />
 * {@link #listType} the type of list it is. Required.<br />
 * </p>
 * 
 * @author lpjharri
 * @since 1.0
 * @see LookupElement
 */

class LookupList
{
	/**
	 * Required. Name of the list.
	 */
	String name
	
	/**
	 * Required. What to render to end-user.
	 */
	String prompt
	
	/**
	 * Optional. Description of this list.
	 */
	String description
	
	/**
	 * Required. Currently supported types are 'fixed' and 'sql'.
	 * 'fixed' type is straight forward and means the elements making up this list are entities in the {@link LookupElement} class.
	 * 'sql' means the elements making up this list is generated from a sql statement ({@link #sqlStatement})
	 */
	String listType
	
	/**
	 * Required if listType == 'sql'. The sql statement to execute.
	 */
	String sqlStatement
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static hasMany = [
		fieldLookup: FormField,
		elements: LookupElement
	]
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'LOOKUP_LIST_SEQ']
		table 'LOOKUP_LIST'
		version false
		
		name column: 'NAME'
		prompt column: 'PROMPT'
		description column: 'DESCRIPTION'
		listType column: 'LIST_TYPE'
		sqlStatement column: 'SQL_STATEMENT'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		name nullable: false, blank: false, size: 1..40
		prompt nullable: false, blank: false, size: 1..256
		description nullable: true, size: 0..1024
		listType nullable: false, blank: false, inList: ["fixed","sql"], size: 1..40
		sqlStatement nullable: true, blank: false, size: 1..4000
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}