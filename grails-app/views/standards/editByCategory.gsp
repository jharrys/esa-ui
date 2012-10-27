<%@page import="grails.converters.JSON"%>
<%@ page import="org.ihc.esa.*"%>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'standard.label', default: 'Standard')}" />
		<g:javascript library="jquery" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span2">
				<div class="well">
					<ul class="nav nav-pills nav-stacked">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link action="list">
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link action="create">
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>

			<div class="span10">
				
				<div class="page-header">
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

                <div id="message">
					<g:if test="${flash.message}">
					    <bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
					</g:if>
				</div>
				
				<g:form action="editByCategory" params="${ }">
				
				
				    <script>
                        function updateItemList(initialItemsArray, textStatus) {
                        	// capture all the values we will need again
                            var categoryElement = document.getElementById('category');
                            var realItems = document.getElementById('itemsincategory');
                            var allItems = document.getElementById('allitems');
                            var category = categoryElement.value;

                            // clear out the itemsincategory select box
                            while (realItems.length) {
                                realItems.remove(0);
                            }   

                            // re-initialize the allitems select box
                            // FIXME: optimize, don't want to hit the server for every selection change
                            while (allItems.length) {
                                allItems.remove(0);
                            }
                            
                            var all = ${Item.list(sort: 'name') as grails.converters.JSON }
                            var y;
                            for (y=0;y<all.length;y++) {
                                var item = new Option(all[y].name, all[y].id);
                                allItems.options.add(item);
                            }

                            var x;
                            for (x=0;x<initialItemsArray.length;x++) {
                                var item = new Option(initialItemsArray[x].name, initialItemsArray[x].id);
                                realItems.options.add(item);
                                var index = $("#allitems > option:contains("+initialItemsArray[x].name+")").index()
                                allItems.remove(index);
                            }
                        };
                   </script>
                    
                    <script>
	                    function addMeToCategory(value) {
	                    	// capture all the values we will need again
                            var categoryElement = document.getElementById('category');
                            var sourceElement = document.getElementById('allitems');
                            var targetElement = document.getElementById('itemsincategory');
                            var category = categoryElement.value;
                            var index = sourceElement.options.selectedIndex;
                            var option = sourceElement.options[index]
                            var value = sourceElement.options[index].value;
                            var display = sourceElement.options[index].text;

		                    // not using it right now
	                        var isSuccessful = ${remoteFunction(action: 'addItemToCategory', params: '\'toCategory=\'+category+\'&itemId=\'+value')};

	                        // remove from allitems
	                        sourceElement.remove(index);

	                        // add to itemsincategory
	                        targetElement.add(option);
	                    };
	                    
	                    function removeMeFromCategory(value) {
		                    // capture all the values we will need again
	                        var categoryElement = document.getElementById('category');
	                        var sourceElement = document.getElementById('itemsincategory');
	                        var targetElement = document.getElementById('allitems');
	                        var category = categoryElement.value;
	                        var index = sourceElement.options.selectedIndex;
	                        var option = sourceElement.options[index]
	                        var value = sourceElement.options[index].value;
	                        var display = sourceElement.options[index].text;

		                    // this may become useful, but not using it now
	                        var isSuccessful = ${remoteFunction(action: 'removeItemFromCategory', params: '\'fromCategory=\'+category+\'&itemId=\'+value')};

	                        // remove from itemsincategory
	                        sourceElement.remove(index);

	                        // add to allitems list
	                        targetElement.add(option);
	                    };
                    </script>
				
				    <div class="row-fluid">
					    <div class="span8">
						    <g:select id="category" name="category" style="width: 100%"
		                            from="${categories }" optionKey="id" optionValue="${{it.parentCategoryPath.equals("/") ? "/" + it.name : it.parentCategoryPath + "/" + it.name }}" 
		                            noSelection="['':'-Select One-']" onchange="${remoteFunction(action: 'itemsInCategoryList', params: '\'catId=\'+value', onSuccess: 'updateItemList(data,textStatus)') }"/>
	                    </div>  
                    </div>
                    
                    <div class="row-fluid">
                    
	                    <div class="span4">
		                    <g:select id="allitems" name="allitems" size="10" style="width: 100%"
		                            from="${Item.list(sort: 'name') }" optionKey="id" optionValue="name" ondblclick="addMeToCategory(this.value);" />
	                    </div>
	                    
	                    <div class="span4">
	                        <g:select id="itemsincategory" name="itemsincategory" size="10" style="width: 100%" from="" ondblclick="removeMeFromCategory(this);" />
	                    </div>
                    
                    </div>
                           
                    <div class="row-fluid"> 
	                    <div class="span10">
						    <div class="form-actions">
		                        <button type="submit" class="btn btn-primary" name="save">
		                            <i class="icon-ok icon-white"></i> Save
		                        </button>
		                        <button type="submit" class="btn" name="cancel">
		                            <i class="icon-remove icon-black"></i> Cancel
		                        </button>
		                    </div>
	                    </div>
                    </div>
				
				</g:form>
				
			</div>

		</div>
	</body>
</html>
