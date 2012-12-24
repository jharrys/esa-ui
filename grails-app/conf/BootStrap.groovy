import java.sql.Statement

import org.h2.tools.*
import org.ihc.esa.*

class BootStrap
{
	// TODO: compatibility matrix: [app 0.5.0-0.8.1 with db 1.1], [app 0.9.0 with db 1.2], [app 1.0 with db 1.3]
	// FIXME: need to fix how i calculate versions - split into major version & minor version
	private final double minimumDatabaseVersion = 1.3
	private final double minimumApplicationVersion = 1.0
	
	def grailsApplication
	def sessionFactory
	def init =
	{ servletContext ->
		environments
		{
			development
			{
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
									"java.sql.Date toTimestamp(String one, String two) throws Exception " +
									"{ " +
									"java.text.SimpleDateFormat sdf1 = new java.text.SimpleDateFormat(\"dd-MMM-yy hh.mm.ss.S a\");" +
									"java.text.SimpleDateFormat sdf2 = new java.text.SimpleDateFormat(\"dd-MMM-yy\");" +
									"sdf1.setLenient(true);" +
									"sdf2.setLenient(true);" +
									"java.util.Date date = null;" +
									"try { date = sdf1.parse(one); } catch (java.text.ParseException pe) { date = sdf2.parse(one); }" +
									"return new java.sql.Date(date.getTime()); } \$\$")
					
					Reader reader = null
					
					def map = [:]
					
					map['form'] = 4
					map['party'] = 92
					map['configuration_parameter'] = 2
					map['lookup_list'] = 14
					map['lookup_element'] = 69
					map['form_field'] = 44
					map['category'] = 265
					map['item'] = 63
					map['document'] = 3
					map['question_response'] = 85
					map['project'] = 108
					
					map.each
					{ table,expectedCount ->
						reader = new BufferedReader(new FileReader("esa-content/" + table + "_seed_data.sql"))
						
						if (table.equals("project")) {
							String line = ""
							while ((line = reader.readLine()) != null) {
								log.debug("line: " + line)
								println ("line: " + line)
								if (!(line =~ /^--/)) {
									def match = line =~ /values \([^,]*,[^,]*,[^,]*,[^,]*,\'([^,]*)\',/
									Party party = null
									String name = "null"
									
									if (match.size() > 0) {
										name = match[0][1]
										party = Party.findByName(name)
									}
									
									// if a ${name} was found in the party table, then use its ID. If not use ID 1 which is 'unknown'
									def newId = party ? party.id : 1
									
									def newSQL = (line =~ /\'${name}\'/).replaceAll(newId.toString())
									log.debug("newSQL: " + newSQL)
									println ("newSQL: " + newSQL)
									
									s.execute(newSQL)
								}
							}
						} else {
							RunScript.execute(connection, reader)
						}
						
						reader.close()
						def realCount = Class.forName("org.ihc.esa." + table.tokenize("_").collect
										{ it.capitalize() }.join(''), false, this.class.getClassLoader()).count()
						assert realCount == expectedCount
						s.execute("ALTER SEQUENCE " + table + "_seq RESTART WITH " + (expectedCount + 1))
					}
				}
				
				/*-----------------------------------------------------------*/
				
				// real roles
				def architectRole = new EsaRole(authority: 'ROLE_ESA_ARCHITECT').save(flush: true)
				def adminRole = new EsaRole(authority: 'ROLE_ESA_ADMIN').save(flush: true)
				def userRole = new EsaRole(authority: 'ROLE_ESA_USER').save(flush: true)
				def earbContributorRole = new EsaRole(authority: 'ROLE_ESA_EARB_CONTRIBUTOR').save(flush: true)
				def earbMemberRole = new EsaRole(authority: 'ROLE_ESA_EARB_MEMBER').save(flush: true)
				def earbAdminRole = new EsaRole(authority: 'ROLE_ESA_EARB_ADMIN').save(flush: true)
				def exceptionContributorRole = new EsaRole(authority: 'ROLE_ESA_EXCEPTION_CONTRIBUTOR').save(flush: true)
				def exceptionAdminRole = new EsaRole(authority: 'ROLE_ESA_EXCEPTION_ADMIN').save(flush: true)
				def contentContributorRole = new EsaRole(authority: 'ROLE_ESA_CONTENT_CONTRIBUTOR').save(flush: true)
				def contentAdminRole = new EsaRole(authority: 'ROLE_ESA_CONTENT_ADMIN').save(flush: true)
				
				// role for testing
				def bogusRole = new EsaRole(authority: 'ROLE_ESA_BOGUS').save(flush: true)
				
				Party party = Party.get(1)
				def manager = new EsaUser(username: 'manager', email_address: 'john.harris@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(5)
				def user = new EsaUser(username: 'lpjharri', email_address: 'john.harris@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(8)
				def user1 = new EsaUser(username: 'tssimpso', email_address: 'stuart.simpson@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(7)
				def user2 = new EsaUser(username: 'gknarra', email_address: 'gopal.narra@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(6)
				def user3 = new EsaUser(username: 'ldsgrove', email_address: 'sara.curry@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(9)
				def user4 = new EsaUser(username: 'tshipley', email_address: 'tim.shipley@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(4)
				def user5 = new EsaUser(username: 'lbuchanan', email_address: 'lonnie.buchanan@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(10)
				def user6 = new EsaUser(username: 'eisa', email_address: 'eisa-repository-notify@imail.org', enabled: true, password: 'esa', party: party)
				party = Party.get(11)
				def user7 = new EsaUser(username: 'donottrust', email_address: 'john.harris@ihc.com', enabled: false, password: 'esa', party: party)
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
				EsaUserEsaRole.create user, architectRole, true
				EsaUserEsaRole.create user1, userRole, true
				EsaUserEsaRole.create user2, userRole, true
				EsaUserEsaRole.create user3, userRole, true
				EsaUserEsaRole.create user4, userRole, true
				EsaUserEsaRole.create user5, userRole, true
				EsaUserEsaRole.create user6, bogusRole, true
				EsaUserEsaRole.create user7, bogusRole, true
				
				assert EsaUser.count() == 9
				assert EsaRole.count() == 11
				assert EsaUserEsaRole.count() == 10
				
				/*-----------------------------------------------------------*/
				
				String dbVersion = ConfigurationParameter.findByName('database.version').value
				String appVersion = ConfigurationParameter.findByName('esaui.version').value
				
				assert dbVersion.isDouble() && ((dbVersion as double) >= minimumDatabaseVersion)
				assert appVersion.isDouble() && ((appVersion as double) >= minimumApplicationVersion)
				
			}	// end-development
			test {
				// real roles
				def adminRole = new EsaRole(authority: 'ROLE_ESA_ADMIN').save(flush: true)
				def userRole = new EsaRole(authority: 'ROLE_ESA_USER').save(flush: true)
				def earbContributorRole = new EsaRole(authority: 'ROLE_ESA_EARB_CONTRIBUTOR').save(flush: true)
				def earbMemberRole = new EsaRole(authority: 'ROLE_ESA_EARB_MEMBER').save(flush: true)
				def earbAdminRole = new EsaRole(authority: 'ROLE_ESA_EARB_ADMIN').save(flush: true)
				def exceptionContributorRole = new EsaRole(authority: 'ROLE_ESA_EXCEPTION_CONTRIBUTOR').save(flush: true)
				def exceptionAdminRole = new EsaRole(authority: 'ROLE_ESA_EXCEPTION_ADMIN').save(flush: true)
				def contentContributorRole = new EsaRole(authority: 'ROLE_ESA_CONTENT_CONTRIBUTOR').save(flush: true)
				def contentAdminRole = new EsaRole(authority: 'ROLE_ESA_CONTENT_ADMIN').save(flush: true)
				
				// role for testing
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
				
				assert EsaUser.count() >= 9
				assert EsaRole.count() >= 10
				assert EsaUserEsaRole.count() >= 9
			} // end-test
			production {
				def adminRole = new EsaRole(authority: 'ROLE_ESA_ADMIN').save(flush: true)
				def userRole = new EsaRole(authority: 'ROLE_ESA_USER').save(flush: true)
				def earbContributorRole = new EsaRole(authority: 'ROLE_ESA_EARB_CONTRIBUTOR').save(flush: true)
				def earbMemberRole = new EsaRole(authority: 'ROLE_ESA_EARB_MEMBER').save(flush: true)
				def earbAdminRole = new EsaRole(authority: 'ROLE_ESA_EARB_ADMIN').save(flush: true)
				def exceptionContributorRole = new EsaRole(authority: 'ROLE_ESA_EXCEPTION_CONTRIBUTOR').save(flush: true)
				def exceptionAdminRole = new EsaRole(authority: 'ROLE_ESA_EXCEPTION_ADMIN').save(flush: true)
				def contentContributorRole = new EsaRole(authority: 'ROLE_ESA_CONTENT_CONTRIBUTOR').save(flush: true)
				def contentAdminRole = new EsaRole(authority: 'ROLE_ESA_CONTENT_ADMIN').save(flush: true)
				
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
				
				assert EsaUser.count() >= 9
				assert EsaRole.count() >= 9
				assert EsaUserEsaRole.count() >= 7
			} // end-production
		}
	}
	
	def destroy =
	{
	}
}
