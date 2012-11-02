<!-- Modal -->
<div class="modal hide fade" id="editCategoryModal" tabindex="-1" role="dialog" aria-labelledby="editCategoryModalLabel" aria-hidden="true">

    <!-- closes modal dialog -->
    <g:formRemote name="renameCategoryForm" action="${createLink(controller: 'standards', action: 'renameCategory') }" 
        url="[action: 'renameCategory']" onSuccess="refreshCategoryList(data)">
        
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
            <br />
            <h4>Rename Category "<span id="catname"></span>"</h4>
            <h6 id="editCategoryModalLabel"><!-- Category path inserted here --></h6>
        </div>
        
        <div class="modal-body">
                <p>
                    <g:field type="hidden" name="categoryId" />
                    <g:field autofocus class="span12" type="text" name="categoryName" />
                </p>
        </div>
        
        <div class="modal-footer">
            <div class="pull-left" id="renameMessage"><!-- success message --></div>
            <button id="closeModal" class="btn" data-dismiss="modal" aria-hidden="true" onclick="closeEditCategoryModal();">Close</button>
            <button class="btn btn-primary" id="refreshme" onclick="$('#renameMessage').spin('tiny','blue');">Save</button>
        </div>
        
    </g:formRemote>
</div>