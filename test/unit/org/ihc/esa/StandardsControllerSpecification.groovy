package org.ihc.esa


import static grails.test.MockUtils.mockLogging
import grails.plugins.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

import org.apache.poi.ss.formula.functions.T

import spock.lang.Specification

@TestFor(StandardsController)
@Mock(Item)
class StandardsControllerSpecification extends Specification
{
	Item item
	
	def setup()
	{
		defineBeans
		{ springSecurityService(SpringSecurityService) }
		
		item = new Item(id: 1, name: 'johnny', createdBy: 'lpjharri', updatedBy: 'lpjharri', dateCreated: new Date(), lastUpdated: new Date())
		item.save(flush: true, failOnError: true)
	}
	
	def "index should redirect to list"()
	{
		when:
		controller.index()
		
		then:
		response.redirectedUrl == "/standards/list"
	}
	
	def "list() should return Item.list() with max and sort"()
	{
		setup:
		controller.params.id = 1
		
		when:
		def model = controller.list()
		
		then:
		assert response.status == 200
		assert model.itemInstanceList != null
		assert model.itemInstanceTotal == 1
	}
	
	void "test plain show"()
	{
		setup:
		mockLogging(StandardsController, false)
		controller.params.id = 1
		
		when:
		def model = controller.show()
		
		then:
		assert response.status == 200
		assert model.itemInstance.is(item)
		assert Item.count() == 1
	}
}
