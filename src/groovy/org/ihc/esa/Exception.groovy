package org.ihc.esa

class Exception
{
	Document document
	
	Exception(Document doc)
	{
		this.document = doc
	}
	
	Exception()
	{
		this(new Document())
	}
	
	public String getSirpId()
	{
		return this.document.getSirpId()
	}
	
	public void setSirpId(String sirpId)
	{
		this.document.setSirpId(sirpId)
	}
	
	public String getRequestor()
	{
		return this.document.getRequestor()
	}
	
	public void setRequestor(String requestor)
	{
		this.document.setRequestor(requestor)
	}
	
	public String getRequestorEmail()
	{
		return this.document.getRequestorEmail()
	}
	
	public void setRequestorEmail(String requestorEmail)
	{
		this.document.setRequestorEmail(requestorEmail)
	}
	
	public String getOwner()
	{
		return this.document.getOwner()
	}
	
	public void setOwner(String owner)
	{
		this.document.setOwner(owner)
	}
	
	public String getOwnerEmail()
	{
		return this.document.getOwnerEmail()
	}
	
	public void setOwnerEmail(String ownerEmail)
	{
		this.document.setOwnerEmail(ownerEmail)
	}
	
	public String getJustification()
	{
		return this.document.getJustification()
	}
	
	public void setJustification(String justification)
	{
		this.document.setJustification(justification)
	}
	
	public Date getDateCreated()
	{
		return this.document.getDateCreated()
	}
	
	public void setDateCreated(Date dateCreated)
	{
		this.document.setDateCreated(dateCreated)
	}
	
	public Date getLastUpdated()
	{
		return this.document.getLastUpdated()
	}
	
	public void setLastUpdated(Date lastUpdated)
	{
		this.document.setLastUpdated(lastUpdated)
	}
	
	public String getCreatedBy()
	{
		return this.document.getCreatedBy()
	}
	
	public void setCreatedBy(String createdBy)
	{
		this.document.setCreatedBy(createdBy)
	}
	
	public String getUpdatedBy()
	{
		return this.document.getUpdatedBy()
	}
	
	public void setUpdatedBy(String updatedBy)
	{
		this.document.setUpdatedBy(updatedBy)
	}
	
	public Form getForm()
	{
		return this.document.form
	}
	
	public void setForm(Form form)
	{
		this.document.form = form
	}
	
	public Party getVendorRepresentativeParty()
	{
		return this.document.vendorRepresentativeParty
	}
	
	public void setVendorRepresentativePary(Party vendorRepresentativeParty)
	{
		this.document.vendorRepresentativeParty = vendorRepresentativeParty
	}
}
