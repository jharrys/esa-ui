package org.ihc.esa

class EsaUser
{
	
	transient springSecurityService
	
	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	static constraints =
	{
		username blank: false, unique: true
		password blank: false
	}
	
	static mapping =
	{ password column: 'password' }
	
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
}
