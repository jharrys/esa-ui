package org.ihc.esa



import org.ihc.esa.LookupElementController;
import org.ihc.esa.LookupElement;
import org.junit.*
import grails.test.mixin.*

@TestFor(LookupElementController)
@Mock(LookupElement)
class LookupElementControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/lookupElement/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.lookupElementInstanceList.size() == 0
        assert model.lookupElementInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.lookupElementInstance != null
    }

    void testSave() {
        controller.save()

        assert model.lookupElementInstance != null
        assert view == '/lookupElement/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lookupElement/show/1'
        assert controller.flash.message != null
        assert LookupElement.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lookupElement/list'

        populateValidParams(params)
        def lookupElement = new LookupElement(params)

        assert lookupElement.save() != null

        params.id = lookupElement.id

        def model = controller.show()

        assert model.lookupElementInstance == lookupElement
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lookupElement/list'

        populateValidParams(params)
        def lookupElement = new LookupElement(params)

        assert lookupElement.save() != null

        params.id = lookupElement.id

        def model = controller.edit()

        assert model.lookupElementInstance == lookupElement
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lookupElement/list'

        response.reset()

        populateValidParams(params)
        def lookupElement = new LookupElement(params)

        assert lookupElement.save() != null

        // test invalid parameters in update
        params.id = lookupElement.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/lookupElement/edit"
        assert model.lookupElementInstance != null

        lookupElement.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lookupElement/show/$lookupElement.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lookupElement.clearErrors()

        populateValidParams(params)
        params.id = lookupElement.id
        params.version = -1
        controller.update()

        assert view == "/lookupElement/edit"
        assert model.lookupElementInstance != null
        assert model.lookupElementInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lookupElement/list'

        response.reset()

        populateValidParams(params)
        def lookupElement = new LookupElement(params)

        assert lookupElement.save() != null
        assert LookupElement.count() == 1

        params.id = lookupElement.id

        controller.delete()

        assert LookupElement.count() == 0
        assert LookupElement.get(lookupElement.id) == null
        assert response.redirectedUrl == '/lookupElement/list'
    }
}
