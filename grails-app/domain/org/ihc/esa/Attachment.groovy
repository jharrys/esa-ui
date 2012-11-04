package org.ihc.esa

/*--------------------------------------------------------------------------
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * Provides representation for file attachments. 
 * </p>
 * <p>
 * {@link #document} is required.<br />
 * {@link #name} is required and is usually the file name.<br />
 * {@link #mimeType} is required for the mime type of the file.<br />
 * {@link #artifact} is required and is the blob storing the actual file.<br />
 * </p>
 * 
 * @author lpjharri
 * @since 1.0
 * @see Document
 */

class Attachment
{
	/**
	 * {@link Document} this attachment is associated with. Required.
	 */
	Document document
	
	/**
	 * Name of the attachment. Required.
	 */
	String name
	
	/**
	 * Mime type of the file. Required.
	 */
	String mimeType
	
	/**
	 * The actual attachment. Required.<br />
	 * Our constraint sets it to a 5MB maximum file size (1024 * 1024 * 5)
	 */
	byte[] artifact
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	static belongsTo = Document
	
	/**
	 * Attachment maps to table ATTACHMENT
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'ATTACHMENT_SEQ']
		table 'ATTACHMENT'
		version false
		
		document column: 'DOCUMENT_ID'
		name column: 'NAME'
		mimeType column: 'MIME_TYPE'
		artifact column: 'ARTIFACT', type: 'blob'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		document nullable: false, blank: false
		name nullable: false, blank: false, size: 1..20
		mimeType nullable: false, size: 0..40
		// artifact set to 5MB
		artifact nullable: false, maxSize: 1024*1024*5
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
	
	/**
	 * name, mimeType, document, dateCreated and createdBy participate in equals.
	 * 
	 * TODO: should I add artifact? would it be ugly?
	 *
	 * @param Attachment
	 * @return
	 */
	@Override public boolean equals(Attachment o)
	{
		
		if (o == null) return false
		
		if (this.is(o)) return true
		
		if (o.getClass() != getClass()) return false
		
		if (this.name.equalsIgnoreCase(o.name))
		{
			if (this.mimeType.equals(o.mimeType) && this.document.equals(o.document) && this.dateCreated.equals(o.dateCreated) && this.createdBy.equals(o.createdBy))
			{
				return true
			}
		}
		
		return false
	}
	
	@Override public int hashCode()
	{
		
		if (this.hashCode==0)
		{
			int result = 17
			result = (37*result) + this.name.toLowerCase().hashCode()
			result = (37*result) + this.mimeType.hashCode()
			result = (37*result) + this.document.hashCode()
			result = (37*result) + this.dateCreated.hashCode()
			result = (37*result) + this.createdBy.hashCode()
			this.hashCode = result
		}
		
		return this.hashCode
	}
}