import org.ihc.esa.EsaRole
import org.ihc.esa.EsaUser
import org.ihc.esa.EsaUserEsaRole

class BootStrap
{
	
	def init =
	{ servletContext ->
		def adminRole = new EsaRole(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new EsaRole(authority: 'ROLE_USER').save(flush: true)
		
		def manager = new EsaUser(username: 'manager', enabled: true, password: 'esa')
		def user = new EsaUser(username: 'user', enabled: true, password: 'esa')
		manager.save(flush: true)
		user.save(flush: true)
		
		EsaUserEsaRole.create manager, adminRole, true
		EsaUserEsaRole.create user, userRole, true
		
		assert EsaUser.count() == 2
		assert EsaRole.count() == 2
		assert EsaUserEsaRole.count() == 2
	}
	
	def destroy =
	{
	}
}
