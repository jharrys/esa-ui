import org.ihc.esa.*

class BootStrap
{
	
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
				
				/***********************************************************/
				Form exception = new Form(id:1, name:"exception", description:"exception form", createdBy:"lpjharri", updatedBy:"lpjharri")
				exception.save(flush: true, failOnError: true)
				
				assert Form.count() == 1
				
				/***********************************************************/
				FormField ff1 = new FormField(id: 1,form: exception,searchListing:"Y",pageNumber: 1,sectionNumber: 1,orderNumber: 1,required: 'N',internalOnly: 'N',question: 'Title Of Exception',dataType: 'Title',multiSelect: 'N',cssClass: 'titleHeader',createdBy: 'bootstrap',updatedBy: 'bootstrap')
				FormField ff2 = new FormField(id: 2,form: exception,searchListing:"Y",pageNumber: 1,sectionNumber: 1,orderNumber: 2,required: 'N',internalOnly: 'N',question: 'Introduction',dataType: 'SectionHeader',multiSelect: 'N',cssClass: 'sectionHeader',createdBy: 'bootstrap',updatedBy: 'bootstrap')
				FormField ff3 = new FormField(id: 3,form: exception,searchListing:"Y",pageNumber: 1,sectionNumber: 1,orderNumber: 3,required: 'N',internalOnly: 'N',question: '<p>EISA APPLICATION FOR EXCEPTION: CLINICAL AND BUSINESS APPLICATIONS</p> <p>This exception is a technical approval from Enterprise IS Architecture and is not an indication that resources have been committed and approved to implement, install, or support the described application or system.</p><p>Instructions:</p><p>This exception process is required to purchase and/or install a Business Application or Technology for which there is already an established standard. If a standard does not already exist, do not use this process unless directed by Enterprise IS Architecture. Contact Enterprise IS Architecture (mail: Enterprise IS Architecture) for help.  This exception process requires business level approvals before purchase or installation can begin. Because an enterprise wide standard already exists, you and information Services will likely incur higher support costs for duplicate or competing applications.  The form must be completed and returned to the EISA Team prior to the purchase or implementation of this exception.</p>',dataType: 'Title',multiSelect: 'N',cssClass: 'titleHeader',createdBy: 'bootstrap',updatedBy: 'bootstrap')
				FormField ff4 = new FormField(id: 4,form: exception,searchListing:"Y",pageNumber: 1,sectionNumber: 1,orderNumber: 4,required: 'Y',internalOnly: 'N',question: 'Submission Date',dataType: 'DATE_VALUE',multiSelect: 'N',cssClass: 'textField',createdBy: 'bootstrap',updatedBy: 'bootstrap')
				FormField ff5 = new FormField(id: 5,form: exception,searchListing:"Y",pageNumber: 1,sectionNumber: 1,orderNumber: 5,required: 'Y',internalOnly: 'N',question: 'Please provide the justification for the exemption from the standard: (Document attachments will be made available after this wizard completes)',dataType: 'STRING_VALUE',multiSelect: 'N',cssClass: 'textArea',formInputType: 'textArea',createdBy: 'bootstrap',updatedBy: 'bootstrap')
				
				ff1.save(flush: true, failOnError: true)
				ff2.save(flush: true, failOnError: true)
				ff3.save(flush: true, failOnError: true)
				ff4.save(flush: true, failOnError: true)
				ff5.save(flush: true, failOnError: true)
				
				assert FormField.count() == 5
			}
		}
	}
	
	def destroy =
	{
	}
}
