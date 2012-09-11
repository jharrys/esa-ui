package org.ihc.esa.controllers



import org.ihc.esa.controllers.EsaRoleController;
import org.ihc.esa.domain.EsaRole;
import org.junit.*
import grails.test.mixin.*

@TestFor(EsaRoleController)
@Mock(EsaRole)
class EsaRoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/esaRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.esaRoleInstanceList.size() == 0
        assert model.esaRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.esaRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.esaRoleInstance != null
        assert view == '/esaRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/esaRole/show/1'
        assert controller.flash.message != null
        assert EsaRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/esaRole/list'

        populateValidParams(params)
        def esaRole = new EsaRole(params)

        assert esaRole.save() != null

        params.id = esaRole.id

        def model = controller.show()

        assert model.esaRoleInstance == esaRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/esaRole/list'

        populateValidParams(params)
        def esaRole = new EsaRole(params)

        assert esaRole.save() != null

        params.id = esaRole.id

        def model = controller.edit()

        assert model.esaRoleInstance == esaRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/esaRole/list'

        response.reset()

        populateValidParams(params)
        def esaRole = new EsaRole(params)

        assert esaRole.save() != null

        // test invalid parameters in update
        params.id = esaRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/esaRole/edit"
        assert model.esaRoleInstance != null

        esaRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/esaRole/show/$esaRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        esaRole.clearErrors()

        populateValidParams(params)
        params.id = esaRole.id
        params.version = -1
        controller.update()

        assert view == "/esaRole/edit"
        assert model.esaRoleInstance != null
        assert model.esaRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/esaRole/list'

        response.reset()

        populateValidParams(params)
        def esaRole = new EsaRole(params)

        assert esaRole.save() != null
        assert EsaRole.count() == 1

        params.id = esaRole.id

        controller.delete()

        assert EsaRole.count() == 0
        assert EsaRole.get(esaRole.id) == null
        assert response.redirectedUrl == '/esaRole/list'
    }
}
