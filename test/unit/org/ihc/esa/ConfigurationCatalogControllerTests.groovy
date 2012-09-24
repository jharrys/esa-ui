package org.ihc.esa



import org.junit.*
import grails.test.mixin.*

@TestFor(ConfigurationCatalogController)
@Mock(ConfigurationCatalog)
class ConfigurationCatalogControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/configurationCatalog/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.configurationCatalogInstanceList.size() == 0
        assert model.configurationCatalogInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.configurationCatalogInstance != null
    }

    void testSave() {
        controller.save()

        assert model.configurationCatalogInstance != null
        assert view == '/configurationCatalog/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/configurationCatalog/show/1'
        assert controller.flash.message != null
        assert ConfigurationCatalog.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/configurationCatalog/list'

        populateValidParams(params)
        def configurationCatalog = new ConfigurationCatalog(params)

        assert configurationCatalog.save() != null

        params.id = configurationCatalog.id

        def model = controller.show()

        assert model.configurationCatalogInstance == configurationCatalog
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/configurationCatalog/list'

        populateValidParams(params)
        def configurationCatalog = new ConfigurationCatalog(params)

        assert configurationCatalog.save() != null

        params.id = configurationCatalog.id

        def model = controller.edit()

        assert model.configurationCatalogInstance == configurationCatalog
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/configurationCatalog/list'

        response.reset()

        populateValidParams(params)
        def configurationCatalog = new ConfigurationCatalog(params)

        assert configurationCatalog.save() != null

        // test invalid parameters in update
        params.id = configurationCatalog.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/configurationCatalog/edit"
        assert model.configurationCatalogInstance != null

        configurationCatalog.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/configurationCatalog/show/$configurationCatalog.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        configurationCatalog.clearErrors()

        populateValidParams(params)
        params.id = configurationCatalog.id
        params.version = -1
        controller.update()

        assert view == "/configurationCatalog/edit"
        assert model.configurationCatalogInstance != null
        assert model.configurationCatalogInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/configurationCatalog/list'

        response.reset()

        populateValidParams(params)
        def configurationCatalog = new ConfigurationCatalog(params)

        assert configurationCatalog.save() != null
        assert ConfigurationCatalog.count() == 1

        params.id = configurationCatalog.id

        controller.delete()

        assert ConfigurationCatalog.count() == 0
        assert ConfigurationCatalog.get(configurationCatalog.id) == null
        assert response.redirectedUrl == '/configurationCatalog/list'
    }
}
