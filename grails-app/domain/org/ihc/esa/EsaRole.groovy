package org.ihc.esa

class EsaRole
{
	String authority
	BigDecimal version
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'ESA_ROLE_SEQ']
		table 'ESA_ROLE'
		
		cache true
		authority column: 'AUTHORITY'
		version column: 'VERSION'
	}
	
	static constraints =
	{
		authority blank: false, unique: true
		version nullable: true
	}
}
