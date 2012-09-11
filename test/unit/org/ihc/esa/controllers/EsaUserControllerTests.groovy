package org.ihc.esa.controllers



import org.ihc.esa.controllers.EsaUserController;
import org.ihc.esa.domain.EsaUser;
import org.junit.*
import grails.test.mixin.*

@TestFor(EsaUserController)
@Mock(EsaUser)
class EsaUserControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/esaUser/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.esaUserInstanceList.size() == 0
        assert model.esaUserInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.esaUserInstance != null
    }

    void testSave() {
        controller.save()

        assert model.esaUserInstance != null
        assert view == '/esaUser/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/esaUser/show/1'
        assert controller.flash.message != null
        assert EsaUser.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/esaUser/list'

        populateValidParams(params)
        def esaUser = new EsaUser(params)

        assert esaUser.save() != null

        params.id = esaUser.id

        def model = controller.show()

        assert model.esaUserInstance == esaUser
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/esaUser/list'

        populateValidParams(params)
        def esaUser = new EsaUser(params)

        assert esaUser.save() != null

        params.id = esaUser.id

        def model = controller.edit()

        assert model.esaUserInstance == esaUser
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/esaUser/list'

        response.reset()

        populateValidParams(params)
        def esaUser = new EsaUser(params)

        assert esaUser.save() != null

        // test invalid parameters in update
        params.id = esaUser.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/esaUser/edit"
        assert model.esaUserInstance != null

        esaUser.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/esaUser/show/$esaUser.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        esaUser.clearErrors()

        populateValidParams(params)
        params.id = esaUser.id
        params.version = -1
        controller.update()

        assert view == "/esaUser/edit"
        assert model.esaUserInstance != null
        assert model.esaUserInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/esaUser/list'

        response.reset()

        populateValidParams(params)
        def esaUser = new EsaUser(params)

        assert esaUser.save() != null
        assert EsaUser.count() == 1

        params.id = esaUser.id

        controller.delete()

        assert EsaUser.count() == 0
        assert EsaUser.get(esaUser.id) == null
        assert response.redirectedUrl == '/esaUser/list'
    }
}
