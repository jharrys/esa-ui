package org.ihc.esa

/*--------------------------------------------------------------------------
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/


/**
 * <p>
 * Represents a many-many relationship between Party (Architect) and Project.
 * </p>
 * <p>
 * {@link #party} is required.<br />
 * {@link #project} is required.<br />
 * </p>
 *
 * @author lpjharri
 * @since 1.1
 * @see Party
 * @see Project
 */
class ProjectArchitect
{
	/**
	 * Required. The owning party.
	 */
	Party party

	/**
	 * Required. The owning project.
	 */
	Project project

	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy

	int hashCode = 0

	static transients = ['hashCode']

	static mapping =
	{
		id generator:'sequence', params:[sequence:'PROJECT_ARCHITECT_SEQ']
		table 'PROJECT_ARCHITECT'
		version false
		cache true

		party column: 'PARTY_ID'
		project column: 'PROJECT_ID'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}

	static constraints =
	{
		party nullable: false, unique: 'project'
		project nullable: false
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}

	/**
	 * party, project, dateCreated and createdBy
	 *
	 * @param object to compare to
	 * @return boolean
	 */
	@Override public boolean equals(Object object)
	{

		if (!(object instanceof ProjectArchitect)) return false

		if (object == null) return false

		if (this.is(object)) return true

		if (object.party.equals(this.party) && object.project(this.project) && object.dateCreated.equals(this.dateCreated) && object.createdBy.equals(this.createdBy))
		{
			return true
		}

		return false
	}

	@Override public int hashCode()
	{

		if (this.hashCode==0)
		{
			int result = 17
			result = (37*result) + this.party.hashCode()
			result = (37*result) + this.project.hashCode()
			result = (37*result) + this.dateCreated.hashCode()
			result = (37*result) + this.createdBy.toLowerCase().hashCode()
			this.hashCode = result
		}

		return this.hashCode
	}

	static ProjectArchitect get(long partyId, long projectId	 ) {
		find 'from ProjectArchitect where party.id=:partyId and project.id=:projectId',
			[partyId: partyId, projectId: projectId]
	}



	static ProjectArchitect create(Party party, Project project, boolean flush = false) {
		new ProjectArchitect(party: party, project: project).save(flush: flush, insert: true)
	}

	static boolean remove(Party party, Project project, boolean flush = false) {
		ProjectArchitect instance = ProjectArchitect.findByPartyAndProject(party, project) {
			if (!instance) {
				return false
			}

			instance.delete(flush: flush)
			true
		}
	}

	static void removeAll(Party party) {
		executeUpdate 'DELETE FROM ProjectArchitect WHERE party=:party', [party: party]
	}

	static void removeAll(Project project) {
		executeUpdate 'DELETE FROM ProjectArchitect WHERE project=:project', [project: project]
	}
}