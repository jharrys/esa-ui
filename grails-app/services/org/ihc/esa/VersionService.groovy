package org.ihc.esa

class VersionService
{
	static scope = "singleton"
	static transactional = false
	
	private final String databaseVersion
	private final String applicationVersion
	
	// new Object() is not serializable, but an array of 0 is
	private final lock = new Object[0]
	
	VersionService() {
		synchronized(lock) {
			this.databaseVersion = ConfigurationParameter.findByName('database.version').value
			this.applicationVersion = ConfigurationParameter.findByName('esaui.version').value
		}
	}
	
	String getDatabaseVersion()
	{
		return this.databaseVersion
	}
	
	String getApplicationVersion()
	{
		return this.applicationVersion
	}
	
	Boolean isCompatibleWithDatabaseVersion(String databaseVersion)
	{
		return getDatabaseVersion.equalsIgnoreCase(databaseVersion)
	}
	
	Boolean isCompatibleWithApplicationVersion(String applicationVersion)
	{
		return getApplicationVersion.equalsIgnoreCase(applicationVersion)
	}
}
