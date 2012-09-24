package org.ihc.esa



import org.junit.*
import grails.test.mixin.*

@TestFor(PartyAddressController)
@Mock(PartyAddress)
class PartyAddressControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/partyAddress/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.partyAddressInstanceList.size() == 0
        assert model.partyAddressInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.partyAddressInstance != null
    }

    void testSave() {
        controller.save()

        assert model.partyAddressInstance != null
        assert view == '/partyAddress/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/partyAddress/show/1'
        assert controller.flash.message != null
        assert PartyAddress.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/partyAddress/list'

        populateValidParams(params)
        def partyAddress = new PartyAddress(params)

        assert partyAddress.save() != null

        params.id = partyAddress.id

        def model = controller.show()

        assert model.partyAddressInstance == partyAddress
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/partyAddress/list'

        populateValidParams(params)
        def partyAddress = new PartyAddress(params)

        assert partyAddress.save() != null

        params.id = partyAddress.id

        def model = controller.edit()

        assert model.partyAddressInstance == partyAddress
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/partyAddress/list'

        response.reset()

        populateValidParams(params)
        def partyAddress = new PartyAddress(params)

        assert partyAddress.save() != null

        // test invalid parameters in update
        params.id = partyAddress.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/partyAddress/edit"
        assert model.partyAddressInstance != null

        partyAddress.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/partyAddress/show/$partyAddress.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        partyAddress.clearErrors()

        populateValidParams(params)
        params.id = partyAddress.id
        params.version = -1
        controller.update()

        assert view == "/partyAddress/edit"
        assert model.partyAddressInstance != null
        assert model.partyAddressInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/partyAddress/list'

        response.reset()

        populateValidParams(params)
        def partyAddress = new PartyAddress(params)

        assert partyAddress.save() != null
        assert PartyAddress.count() == 1

        params.id = partyAddress.id

        controller.delete()

        assert PartyAddress.count() == 0
        assert PartyAddress.get(partyAddress.id) == null
        assert response.redirectedUrl == '/partyAddress/list'
    }
}
