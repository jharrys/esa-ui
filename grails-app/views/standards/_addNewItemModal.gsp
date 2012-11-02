<%@ page import="org.ihc.esa.*"%>
<!-- Modal -->
<div class="modal hide fade" id="addNewItemModal" tabindex="-1" role="dialog" aria-labelledby="addNewItemModalLabel" aria-hidden="true">

    <!-- closes modal dialog -->
    <g:formRemote name="addNewItemForm" action="${createLink(controller: 'standards', action: 'addNewItem') }" 
        url="[action: 'addNewItem']" onSuccess="">
        
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <br />
            <h4>Add New Item</h4>
        </div>
        
        <div class="modal-body">
                <p>
                    <%-- FIXME: this cannot be left in for production - it's purely dev --%>
                    <f:all bean="itemInstance"/>
                </p>
        </div>
        
        <div class="modal-footer">
            <div class="pull-left" id="addNewItemMessage"><!-- success message --></div>
            <button id="closeAddNewItemModal" class="btn" data-dismiss="modal" aria-hidden="true" onclick="closeAddNewItemModal();">Close</button>
            <button class="btn btn-primary" onclick="$('#addNewItemMessage').spin('tiny','blue');">Save</button>
        </div>
        
    </g:formRemote>
</div>