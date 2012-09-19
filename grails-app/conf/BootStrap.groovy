import org.ihc.esa.EsaRole
import org.ihc.esa.EsaUser
import org.ihc.esa.EsaUserEsaRole

class BootStrap
{
	
	def init =
	{ servletContext ->
		def adminRole = new EsaRole(authority: 'ROLE_ESA_ADMIN').save(flush: true)
		def userRole = new EsaRole(authority: 'ROLE_ESA_USER').save(flush: true)
		def bogusRole = new EsaRole(authority: 'ROLE_ESA_BOGUS').save(flush: true)
		
		def manager = new EsaUser(username: 'manager', email_address: 'john.harris@imail.org', enabled: true, password: 'esa')
		def user = new EsaUser(username: 'lpjharri', email_address: 'john.harris@imail.org', enabled: true, password: 'esa')
		def user1 = new EsaUser(username: 'tssimpso', email_address: 'stuart.simpson@imail.org', enabled: true, password: 'esa')
		def user2 = new EsaUser(username: 'gknarra', email_address: 'gopal.narra@imail.org', enabled: true, password: 'esa')
		def user3 = new EsaUser(username: 'ldsgrove', email_address: 'sara.curry@imail.org', enabled: true, password: 'esa')
		def user4 = new EsaUser(username: 'tshipley', email_address: 'tim.shipley@imail.org', enabled: true, password: 'esa')
		def user5 = new EsaUser(username: 'lbuchanan', email_address: 'lonnie.buchanan@imail.org', enabled: true, password: 'esa')
		def user6 = new EsaUser(username: 'eisa', email_address: 'eisa-repository-notify@imail.org', enabled: true, password: 'esa')
		def user7 = new EsaUser(username: 'donottrust', email_address: 'john.harris@ihc.com', enabled: false, password: 'esa')
		manager.save(flush: true)
		user.save(flush: true)
		user1.save(flush: true)
		user2.save(flush: true)
		user3.save(flush: true)
		user4.save(flush: true)
		user5.save(flush: true)
		user6.save(flush: true)
		user7.save(flush: true)
		
		EsaUserEsaRole.create manager, adminRole, true
		EsaUserEsaRole.create user, userRole, true
		EsaUserEsaRole.create user1, userRole, true
		EsaUserEsaRole.create user2, userRole, true
		EsaUserEsaRole.create user3, userRole, true
		EsaUserEsaRole.create user4, userRole, true
		EsaUserEsaRole.create user5, userRole, true
		EsaUserEsaRole.create user6, bogusRole, true
		EsaUserEsaRole.create user7, bogusRole, true
		
		assert EsaUser.count() == 9
		assert EsaRole.count() == 3
		assert EsaUserEsaRole.count() == 9
	}
	
	def destroy =
	{
	}
}
