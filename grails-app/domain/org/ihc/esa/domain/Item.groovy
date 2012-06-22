package org.ihc.esa.domain

import java.util.Date;

class Item {

	String external_id
	String source_system
	String standard
	Integer vendor_party_id		//TODO needs to be fixed in DB as FK
	String mmis_item_number
	String name
	String description
	String general_ledger_code
	String product_group
	String technology_group
	Date available_date
	Date ihc_actual_decomissioned
	Date ihc_proposed_decomissioned
	Date vendor_decomissioned
	Integer manufacturer_part_id
	String manufacturer_catalog_number
	String purchasing_unit_of_measure
	Float purchasing_unit_price
	String unspsc_number	
	Date dateCreated
	Date lastUpdated
	String created_by
	String updated_by
	
	static belongsTo = [contract: Contract]
	
	static hasMany = [catalog_items: Catalog_Item, item_units_conversion: Item_Units_Conversion]
	
	static mapping = {
		id generator:'sequence', params:[sequence:'ITEM_SEQ']
		dateCreated column: 'CREATION_DATE'
		lastUpdated column: 'UPDATE_DATE'
		version false
	}

    static constraints = {
		external_id nullable: true
		source_system nullable: true
		standard nullable: true
		vendor_party_id nullable: true	//TODO error? check with Stuart
		mmis_item_number nullable: true
		name nullable: true
		description nullable: true
		general_ledger_code nullable: true
		product_group nullable: true
		technology_group nullable: true
		available_date nullable: true
		ihc_actual_decomissioned nullable: true
		ihc_proposed_decomissioned nullable: true
		vendor_decomissioned nullable: true
		manufacturer_part_id nullable: true
		manufacturer_catalog_number nullable: true
		purchasing_unit_price nullable: true
		unspsc_number nullable: true
		purchasing_unit_of_measure nullable: true
		contract nullable: true
    }
}
