import org.ihc.esa.domain.EsaRole
import org.ihc.esa.domain.EsaUser
import org.ihc.esa.domain.EsaUserEsaRole

class BootStrap
{
	
	def init =
	{ servletContext ->
		def adminRole = new EsaRole(authority: 'ROLE_ADMIN').save(flush: true)
		def userRole = new EsaRole(authority: 'ROLE_USER').save(flush: true)
		
		def testUser = new EsaUser(username: 'me', enabled: true, password: 'password')
		testUser.save(flush: true)
		
		EsaUserEsaRole.create testUser, adminRole, true
		
		assert EsaUser.count() == 1
		assert EsaRole.count() == 2
		assert EsaUserEsaRole.count() == 1
	}
	def destroy =
	{
	}
}
