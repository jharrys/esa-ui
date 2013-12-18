package org.ihc.esa

class EsaUser
{
	
	transient springSecurityService
	
	String username
	String password
	String emailAddress
	boolean enabled = false
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false
	BigDecimal version
	Party party
	
	static mapping =
	{
		id generator:'sequence', params:[sequence:'ESA_USER_SEQ']
		table 'ESA_USER'
		
		username column: 'USERNAME'
		password column: 'PASSWORD'
		emailAddress column: 'EMAIL_ADDRESS'
		enabled column: 'ENABLED'
		accountExpired column: 'ACCOUNT_EXPIRED'
		accountLocked column: 'ACCOUNT_LOCKED'
		passwordExpired column: 'PASSWORD_EXPIRED'
		version column: 'VERSION'
		party column: 'PARTY_ID'
	}
	
	static constraints =
	{
		username nullable: false, blank: false, unique: true
		password nullable: false, blank: false, password: true
		emailAddress nullable: true, blank: false, email: true
		enabled nullable: true
		accountExpired nullable: true
		accountLocked nullable: true
		passwordExpired nullable: true
		version nullable: true
		party nullable: true
	}
	
	Set<EsaRole> getAuthorities()
	{
		EsaUserEsaRole.findAllByEsaUser(this).collect
						{ it.esaRole } as Set
	}
	
	def beforeInsert()
	{
		encodePassword()
	}
	
	def beforeUpdate()
	{
		if (isDirty('password'))
		{
			encodePassword()
		}
	}
	
	protected void encodePassword()
	{
		password = springSecurityService.encodePassword(password)
	}
	
	public String toString()
	{
		return "${this.id} : ${this.username}"
	}
}
