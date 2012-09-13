import org.ihc.esa.EsaRole
import org.ihc.esa.EsaUser
import org.ihc.esa.EsaUserEsaRole

class BootStrap
{
	
	def init =
	{ servletContext ->
		def adminRole = new EsaRole(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new EsaRole(authority: 'ROLE_USER').save(flush: true)
		
		def manager = new EsaUser(username: 'john', enabled: true, password: 'esa')
		manager.save(flush: true)
		
		EsaUserEsaRole.create manager, adminRole, true
		
		assert EsaUser.count() == 1
		assert EsaRole.count() == 2
		assert EsaUserEsaRole.count() == 1
	}
	
	def destroy =
	{
	}
}
