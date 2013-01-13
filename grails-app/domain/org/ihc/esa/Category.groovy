package org.ihc.esa

/*--------------------------------------------------------------------------
 Created manually by JH [05-Oct-2012 13:00:00 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Category represents a hierarchy of nodes much like a file systems
 * folder/sub-folder structure. Each node may contain other nodes or leaf nodes.
 * The Category container is used to hold object of type {@link Item}.
 * </p>
 * Implements Comparable
 * </p>
 * <p>
 * {@link #name} is required.
 * {@link #parentCategory} is required. For root nodes parentCategory == this.
 * {@link #parentCategoryPath} is required and is the string representation of the full hierarchy, to prevent recursive queries.
 * </p>
 * @author lpjharri
 * @since 1.0
 * @see Item
 * @see java.lang.Comparable
 */

class Category implements Comparable<Category>
{
	/**
	 * Required. The owning node of this node.
	 * Note parentCategory == this for root nodes.
	 */
	Category parentCategory

	/**
	 * Required. The full string path including the root node and delimiter '/'.
	 * This is done to avoid a recursive query.
	 */
	String parentCategoryPath

	/**
	 * Name for this node or container.
	 * Required.
	 */
	String name

	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy

	int hashCode = 0

	static transients = ['hashCode']

	static hasMany = [
		categories: Category,
		items: Item
	]

	/**
	 * Category maps to table CATEGORY
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'CATEGORY_SEQ']
		table 'CATEGORY'
		version false
		cache true

		categories cache:true, sort: 'name'

		parentCategory column: 'PARENT_CATEGORY_ID'
		parentCategoryPath column: 'PARENT_CATEGORY_PATH'
		name column: 'NAME'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'

		items cache:true, joinTable: [name: 'ITEM_CATEGORY',
							key: 'CATEGORY_ID',
							column: 'ITEM_ID']
	}

	static constraints =
	{
		parentCategory nullable: false
		parentCategoryPath nullable: false, blank: false, size: 1..4000
		name nullable: false, blank: false, size: 1..128
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}

	/**
	 * Natural order based strictly off of name
	 */
	public int compareTo(Category c)
	{
		int EQUAL = 0

		if (this.is(c)) return EQUAL

		if (this.equals(c)) return EQUAL

		int r1 = this.parentCategoryPath.compareToIgnoreCase(c.parentCategoryPath)

		if (r1 == 0)
		{
			return this.name.compareToIgnoreCase(c.name)
		}

		return r1
	}

	/**
	 * Should only have one Category with String Abc==aBC
	 *
	 * @param object
	 * @return
	 */
	@Override public boolean equals(Object object)
	{

		if (!(object instanceof Category)) return false

		if (object == null) return false

		if (this.is(object)) return true

		if (this.name.equalsIgnoreCase(object.name))
		{
			if (this.parentCategory.id.equals(object.parentCategory.id))
			{
				return true
			}
		}

		return false
	}

	@Override public int hashCode()
	{

		if (this.hashCode==0) {
			int result = 17
			result = (37*result) + this.name.toLowerCase().hashCode()
			result = (37*result) + this.parentCategory.id.hashCode()
			this.hashCode = result
		}

		return this.hashCode
	}
}
