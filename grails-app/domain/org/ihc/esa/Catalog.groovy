package org.ihc.esa

/*--------------------------------------------------------------------------
 Generated code by GenGroovyObjects [09-Aug-2012 20:45:44 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Catalog represents a hierarchy of nodes much like a file systems
 * folder/sub-folder structure. Each node may contain other nodes or leaf nodes.
 * The Catalog container is used to hold object of type {@link Item} in the Supply Chain
 * Organization (SCO) data model.
 * </p>
 * <p>
 * {@link #name} is the only required field, all others are nullable.
 * </p>
 * <p>
 * Currently not used within the EISA domain model. {@link Category} is used instead.
 * </p>
 *
 * @author lpjharri
 * @since 1.0
 * @see Item
 * @see ItemVersion
 * @see Category
 */

class Catalog
{
	/**
	 * Name of Catalog node is required
	 */
	String name

	/**
	 * Description of Catalog node can be nullable
	 */
	String description

	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy

	int hashCode = 0

	static transients = ['hashCode']

	/**
	 * Catalog has many {@link Item}s
	 */
	static hasMany = [
		items: Item
	]

	/**
	 * Catalog maps to table CATALOG
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'CATALOG_SEQ']
		table 'CATALOG'
		version false
		cache true

		name column: 'NAME'
		description column: 'DESCRIPTION'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'

		items cache:true, joinTable: [name: 'CATALOG_ITEM',
							key: 'CATALOG_ID',
							column: 'ITEM_ID']
	}

	static constraints =
	{
		name nullable: false, blank: false, size: 1..128
		description nullable: true, size: 0..1024
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}

	/**
	 * Should only have one Catalog with String Abc==aBC
	 *
	 * @param object
	 * @return
	 */
	@Override public boolean equals(Object object)
	{

		if (!(object instanceof Catalog)) return false

		if (object == null) return false

		if (this.is(object)) return true

		if (this.name.equalsIgnoreCase(object.name))
		{
			return true
		}

		return false
	}

	@Override public int hashCode()
	{

		if (this.hashCode==0) {
			int result = 17
			result = (37*result) + this.name.toLowerCase().hashCode()
			this.hashCode = result
		}

		return this.hashCode
	}
}