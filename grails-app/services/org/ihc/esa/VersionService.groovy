package org.ihc.esa

/**
 * The ESA version is xxx.yyy.zzz
 *
 * xxx is the major version, which are breaking changes and major upgrades; builds between major releases will usually not be compatible
 * yyy is the minor version, which are bug fixes and minor feature releases; builds between minor releases are usually compatible; yyy restarts the counter after each major release increment
 * zzz is the build version and represents a build by the CI server; zzz restarts the counter after each major release increment, but not minor releases
 *
 * 	for example: if we go from major release 1 to major release 2, then yyy is set to 0 and zzz is set to 0. If we go from minor release 3 to minor release 4, zzz does not restart at 0
 *
 * NOTE: databaseVersion may not, and probably will not, follow above. It may only have major and minor version.
 *
 * @author lpjharri
 * @since 1.0
 */

class VersionService
{
	static scope = "singleton"
	static transactional = false
	
	private final String databaseVersion
	private final String applicationVersion
	
	private final int dbMajor
	private final int dbMinor
	private final int dbBuild
	
	private final int appMajor
	private final int appMinor
	private final int appBuild
	
	// new Object() is not serializable, but an array of 0 is
	private final lock = new Object[0]
	
	VersionService() {
		synchronized(lock) {
			this.databaseVersion = ConfigurationParameter.findByName('database.version').value
			this.applicationVersion = ConfigurationParameter.findByName('esaui.version').value
		}
		
		List<String> dbTokenized = databaseVersion.tokenize('.')
		assert dbTokenized.size() >= 2
		
		try {
			dbMajor = Integer.parseInt(dbTokenized[0])
			dbMinor = Integer.parseInt(dbTokenized[1])
			
			if (dbTokenized.size() > 2) {
				dbBuild = Integer.parseInt(dbTokenized[2])
			}
		} catch (NumberFormatException nfe) {
			log.error("The database version is expected to be made up of 3 parts tokenized by a '.', each part being of type integer. Please check the version in the Configuration_Parameters table")
		}
		
		List<String> appTokenized = applicationVersion.tokenize('.')
		assert appTokenized.size() == 3
		
		try {
			appMajor = Integer.parseInt(appTokenized[0])
			appMinor = Integer.parseInt(appTokenized[1])
			appBuild = Integer.parseInt(appTokenized[2])
		} catch (NumberFormatException nfe) {
			log.error("The database version is expected to be made up of 3 parts tokenized by a '.', each part being of type integer. Please check the version in the Configuration_Parameters table")
		}
	}
	
	String getDatabaseVersion()
	{
		return this.databaseVersion
	}
	
	Integer getDatabaseMajorVersion() {
		return this.dbMajor
	}
	
	Integer getDatabaseMinorVersion() {
		return this.dbMinor
	}
	
	Integer getDatabaseBuild() {
		return this.dbBuild
	}
	
	String getApplicationVersion()
	{
		return this.applicationVersion
	}
	
	Integer getApplicationMajorVersion() {
		return this.appMajor
	}
	
	Integer getApplicationMinorVersion() {
		return this.appMinor
	}
	
	Integer getApplicationBuild() {
		return this.appBuild
	}
	
	/**
	 * For the database version, both major and minor need to match
	 *
	 * @param databaseVersion
	 * @return if major and minor versions match then returns true, otherwise false
	 * @exception NumberFormatException
	 */
	Boolean isCompatibleWithDatabaseVersion(String databaseVersion)
	{
		boolean result = false
		
		List<String> tokenizedDatabaseVersion = databaseVersion.tokenize('.')
		int computedDbMajor = 0
		int computedDbMinor = 0
		
		if (tokenizedDatabaseVersion.size() < 2) {
			log.error("databaseVersion parameter: " + databaseVersion + " is invalid.")
		} else {
			try {
				computedDbMajor = Integer.parseInt(tokenizedDatabaseVersion[0])
				computedDbMinor = Integer.parseInt(tokenizedDatabaseVersion[1])
				
				if (this.dbMajor == computedDbMajor && this.dbMinor == computedDbMinor) {
					result = true
				}
				
			} catch (NumberFormatException nfe) {
				log.error("databaseVersion parameter: " + databaseVersion + " could not be tokenized into a valid integer.")
			}
		}
		
		return result
	}
	
	Boolean isCompatibleWithApplicationVersion(String applicationVersion)
	{
		boolean result = false
		
		List<String> tokenizedApplicationVersion = applicationVersion.tokenize('.')
		int computedAppMajor = 0
		
		try {
			computedAppMajor = Integer.parseInt(tokenizedApplicationVersion[0])
			
			if (this.appMajor == computedAppMajor) {
				result = true
			}
			
		} catch (NumberFormatException nfe) {
			log.error("applicationVersion parameter: " + applicationVersion + " could not be tokenized into a valid integer.")
		}
		
		return result
	}
}
