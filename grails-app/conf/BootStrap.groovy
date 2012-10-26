import java.sql.Statement
import org.ihc.esa.*
import org.h2.tools.*

class BootStrap
{
	private final String databaseVersion = "1.1"
	private final String applicationVersion = "0.6"
	
	def grailsApplication
	def sessionFactory
	def init =
	{ servletContext ->
		environments
		{
			development
			{
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
				/*-----------------------------------------------------------*/
				def session = null
				def connection = null
				if (grailsApplication.getFlatConfig().get("dataSource.url").startsWith("jdbc:h2"))
				{
					//if we are running with the internal H2 database, then execute scripts with H2 specific queries.
					session = sessionFactory.getCurrentSession()
					connection = session.connection()
				}
				
				if (connection != null)
				{
					Statement s = connection.createStatement()
					s.execute("CREATE ALIAS TO_TIMESTAMP AS \$\$ " +
									"java.sql.Date toTimestamp(String one, String two) " +
									"{ return new java.sql.Date((new java.util.Date()).getTime()); } \$\$")
					
					Reader reader = null
					
					def map = [:]
					
					map['form'] = 4
					map['party'] = 42
					map['configuration_parameter'] = 2
					map['lookup_list'] = 14
					map['lookup_element'] = 69
					map['form_field'] = 44
					map['category'] = 265
					map['item'] = 63
					map['document'] = 3
					map['question_response'] = 85
					
					map.each
					{ table,expectedCount ->
						reader = new BufferedReader(new FileReader("esa-content/" + table + "_seed_data.sql"))
						RunScript.execute(connection, reader)
						reader.close()
						def realCount = Class.forName("org.ihc.esa." + table.tokenize("_").collect
										{ it.capitalize() }.join(''), false, this.class.getClassLoader()).count()
						assert realCount == expectedCount
						s.execute("ALTER SEQUENCE " + table + "_seq RESTART WITH " + (expectedCount + 1))
					}
				}
				
				String dbVersion = ConfigurationParameter.findByName('database.version').value
				String appVersion = ConfigurationParameter.findByName('esaui.version').value
				
				assert dbVersion.equals(databaseVersion)
				assert appVersion.equals(applicationVersion)
			}	// end-development
			test {
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
			} // end-test
			production {
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
			} // end-production
		}
	}
	
	def destroy =
	{
	}
}
