package org.ihc.esa

/*--------------------------------------------------------------------------
 Created manually by JH [05-Oct-2012 13:00:00 -0600]
 Copyright 2012 by Intermountain Healthcare
 --------------------------------------------------------------------------*/

/**
 * <p>
 * ConfigurationParameter stores meta-data about the database version for this
 * application, as well as other parameters that may be needed in the future.
 * </p>
 * <p>
 * {@link #name} is required.
 * {@link #value} is required.
 * </p>
 * @author lpjharri
 * @since 1.0
 */

class ConfigurationParameter
{
	/**
	 * Name of parameter. See {@link #value}.
	 * Required.
	 */
	String name
	
	/**
	 * value of {@link #name}.
	 * Required.
	 */
	String value
	
	Date dateCreated
	String createdBy
	Date lastUpdated
	String updatedBy
	
	/**
	 * ConfigurationParameter maps to table CONFIGURATION_PARAMETER
	 */
	static mapping =
	{
		id generator:'sequence', params:[sequence:'CONFIGURATION_PARAMETER_SEQ']
		table 'CONFIGURATION_PARAMETER'
		version false
		
		name column: 'NAME'
		value column: 'VALUE'
		dateCreated column: 'DATE_CREATED'
		createdBy column: 'CREATED_BY'
		lastUpdated column: 'LAST_UPDATED'
		updatedBy column: 'UPDATED_BY'
	}
	
	static constraints =
	{
		name nullable: false, blank: false, size: 1..1024
		value nullable: false, blank: false, size: 1..4000
		dateCreated nullable: true, display: false, format: 'yyyy-MM-dd'
		createdBy nullable: false, size: 1..40
		lastUpdated nullable: true, display: false, format: 'yyyy-MM-dd'
		updatedBy nullable: false, size: 1..40
	}
}