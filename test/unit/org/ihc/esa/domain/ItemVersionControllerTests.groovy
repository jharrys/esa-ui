package org.ihc.esa.domain



import org.ihc.esa.controllers.ItemVersionController;
import org.junit.*
import grails.test.mixin.*

@TestFor(ItemVersionController)
@Mock(ItemVersion)
class ItemVersionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/itemVersion/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.itemVersionInstanceList.size() == 0
        assert model.itemVersionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.itemVersionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.itemVersionInstance != null
        assert view == '/itemVersion/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/itemVersion/show/1'
        assert controller.flash.message != null
        assert ItemVersion.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/itemVersion/list'

        populateValidParams(params)
        def itemVersion = new ItemVersion(params)

        assert itemVersion.save() != null

        params.id = itemVersion.id

        def model = controller.show()

        assert model.itemVersionInstance == itemVersion
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/itemVersion/list'

        populateValidParams(params)
        def itemVersion = new ItemVersion(params)

        assert itemVersion.save() != null

        params.id = itemVersion.id

        def model = controller.edit()

        assert model.itemVersionInstance == itemVersion
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/itemVersion/list'

        response.reset()

        populateValidParams(params)
        def itemVersion = new ItemVersion(params)

        assert itemVersion.save() != null

        // test invalid parameters in update
        params.id = itemVersion.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/itemVersion/edit"
        assert model.itemVersionInstance != null

        itemVersion.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/itemVersion/show/$itemVersion.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        itemVersion.clearErrors()

        populateValidParams(params)
        params.id = itemVersion.id
        params.version = -1
        controller.update()

        assert view == "/itemVersion/edit"
        assert model.itemVersionInstance != null
        assert model.itemVersionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/itemVersion/list'

        response.reset()

        populateValidParams(params)
        def itemVersion = new ItemVersion(params)

        assert itemVersion.save() != null
        assert ItemVersion.count() == 1

        params.id = itemVersion.id

        controller.delete()

        assert ItemVersion.count() == 0
        assert ItemVersion.get(itemVersion.id) == null
        assert response.redirectedUrl == '/itemVersion/list'
    }
}
