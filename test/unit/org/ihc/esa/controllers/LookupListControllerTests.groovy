package org.ihc.esa.controllers



import org.ihc.esa.controllers.LookupListController;
import org.ihc.esa.domain.LookupList;
import org.junit.*
import grails.test.mixin.*

@TestFor(LookupListController)
@Mock(LookupList)
class LookupListControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/lookupList/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.lookupListInstanceList.size() == 0
        assert model.lookupListInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.lookupListInstance != null
    }

    void testSave() {
        controller.save()

        assert model.lookupListInstance != null
        assert view == '/lookupList/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lookupList/show/1'
        assert controller.flash.message != null
        assert LookupList.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lookupList/list'

        populateValidParams(params)
        def lookupList = new LookupList(params)

        assert lookupList.save() != null

        params.id = lookupList.id

        def model = controller.show()

        assert model.lookupListInstance == lookupList
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lookupList/list'

        populateValidParams(params)
        def lookupList = new LookupList(params)

        assert lookupList.save() != null

        params.id = lookupList.id

        def model = controller.edit()

        assert model.lookupListInstance == lookupList
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lookupList/list'

        response.reset()

        populateValidParams(params)
        def lookupList = new LookupList(params)

        assert lookupList.save() != null

        // test invalid parameters in update
        params.id = lookupList.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/lookupList/edit"
        assert model.lookupListInstance != null

        lookupList.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lookupList/show/$lookupList.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lookupList.clearErrors()

        populateValidParams(params)
        params.id = lookupList.id
        params.version = -1
        controller.update()

        assert view == "/lookupList/edit"
        assert model.lookupListInstance != null
        assert model.lookupListInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lookupList/list'

        response.reset()

        populateValidParams(params)
        def lookupList = new LookupList(params)

        assert lookupList.save() != null
        assert LookupList.count() == 1

        params.id = lookupList.id

        controller.delete()

        assert LookupList.count() == 0
        assert LookupList.get(lookupList.id) == null
        assert response.redirectedUrl == '/lookupList/list'
    }
}
