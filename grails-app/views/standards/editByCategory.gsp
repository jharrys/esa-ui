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
					<li class="nav-header">
						${entityName}
					</li>
					<li><g:link action="list">
							<g:message code="default.list.label" args="[entityName]" />
						</g:link></li>
					<li><g:link action="create">
							<g:message code="default.create.label" args="[entityName]" />
						</g:link></li>
					<li class="active"><g:link action="editByCategory">
                                Standards By Category
                            </g:link></li>
				</ul>
			</div>
		</div>

		<div class="span10">

			<div class="page-header">
				<h1>
					<g:message code="default.list.label" args="[entityName]" />
				</h1>
			</div>

			<div id="message">
				<g:if test="${flash.message}">
					<bootstrap:alert class="alert alert-error fade in" id="alertmessage">
						${flash.message}
					</bootstrap:alert>
				</g:if>
			</div>

			<script>

		        function refreshCategoryList() {
		            var categoryElement = document.getElementById('category');
		            // FIXME: currentIndex is not correct - need to search based on name
		            var currentIndex = categoryElement.selectedIndex  
		            var allCategories = ${categories as grails.converters.JSON };

		            while (categoryElement.length) {
		                categoryElement.remove(0);
			        }

			        var x;
			        for (x=0;x<allCategories.length;x++) {
				        var displayName = (allCategories[x].parentCategoryPath == "/") ? "/" + allCategories[x].name : allCategories[x].parentCategoryPath + "/" + allCategories[x].name
			            var cat = new Option(displayName, allCategories[x].id);
			            categoryElement.options.add(cat);
				    }

				    categoryElement.selectedIndex = currentIndex
			    }
			    
	            function updateItemList(initialItemsArray, textStatus) {
	                // capture the 3 select dom elements we will be working with
	                var categoryElement = document.getElementById('category');
	                var realItemsElement = document.getElementById('itemsincategory');
	                var allItemsElement = document.getElementById('allitems');
	                
		            if (initialItemsArray) {
		            	// the selected value of the categoryElement
		                var category = categoryElement.value;
		
		                // clear out the itemsincategory select box
		                while (realItemsElement.length) {
		                    realItemsElement.remove(0);
		                }   
		
		                // clear out the allitems select box
		                while (allItemsElement.length) {
		                    allItemsElement.remove(0);
		                }

		                // get all Items sorted by name then populate allitems select box
		                var all = ${Item.list(sort: 'name') as grails.converters.JSON }
		                var y;
		                for (y=0;y<all.length;y++) {
		                    var item = new Option(all[y].name, all[y].id);
		                    allItemsElement.options.add(item);
		                }

		                // go through allitems select box and remove options whose item.name exists in the itemsincategory select box
		                var x;
		                for (x=0;x<initialItemsArray.length;x++) {
		                    var item = new Option(initialItemsArray[x].name, initialItemsArray[x].id);
		                    realItemsElement.options.add(item);
		                    var index = $("#allitems > option:contains(\""+initialItemsArray[x].name+"\")").index();
		                    allItemsElement.remove(index);
		                }

		                // set the text on the hidden #editCategoryModal form so if the 'rename' button is pushed the right text is displayed
		                var catIdElement = document.getElementById('categoryId');
		                var catNameElement = document.getElementById('categoryName');
		                var fullpath = categoryElement.options[categoryElement.selectedIndex].innerText.split("/");
		                var parentpath = fullpath.slice(0,fullpath.length-1).join("/");
		                document.getElementById('editCategoryModalLabel').innerText = parentpath;
		                var name = fullpath[fullpath.length-1];
		                catIdElement.value = categoryElement.value;
		                catNameElement.value = name;

		                // enable the allitems and itemsincategory select boxes
		                realItemsElement.disabled = false;
		                allItemsElement.disabled = false;
		            } else {
			            // if we're here it means that 'no selection' was made, clear out the itemsincategory select box
		                while (realItemsElement.length) {
		                    realItemsElement.remove(0);
			            }

		                // enable the allitems and itemsincategory select boxes
                        realItemsElement.disabled = true;
                        allItemsElement.disabled = true;
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
	                var option = sourceElement.options[index];
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
	                var option = sourceElement.options[index];
	                var value = sourceElement.options[index].value;
	                var display = sourceElement.options[index].text;
	
	                // this may become useful, but not using it now
	                var isSuccessful = ${remoteFunction(action: 'removeItemFromCategory', params: '\'fromCategory=\'+category+\'&itemId=\'+value')};

	                alert('isSuccessful= ' + isSuccessful);
	
	                // remove from itemsincategory
	                sourceElement.remove(index);
	
	                // add to allitems list
	                targetElement.add(option);
	            };
            </script>

			<div class="row-fluid">
				<div class="span8">
					<g:select id="category" name="category" style="width: 100%" from="${categories }" optionKey="id"
						optionValue="${{it.parentCategoryPath.equals("/") ? "/" + it.name : it.parentCategoryPath + "/" + it.name }}" noSelection="['':'-Select One-']"
						onchange="${remoteFunction(action: 'itemsInCategoryList', params: '\'catId=\'+value', onSuccess: 'updateItemList(data,textStatus)') }"
					/>
				</div>
			</div>

			<div class="row-fluid">

				<div class="span4">
					<g:select disabled="true" id="allitems" name="allitems" size="10" style="width: 100%" from="${Item.list(sort: 'name') }" optionKey="id" optionValue="name"
						ondblclick="addMeToCategory(this.value);"
					/>
				</div>

				<div class="span4">
					<g:select disabled="true" id="itemsincategory" name="itemsincategory" size="10" style="width: 100%" from="" ondblclick="removeMeFromCategory(this);" />
				</div>

			</div>

			<%-- FIXME: Add check that category was selected --%>
			<a href="#editCategoryModal" role="button" class="btn" data-toggle="modal"> Rename Category </a>

			<!-- Modal -->
			<div class="modal hide fade" id="editCategoryModal" tabindex="-1" role="dialog" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
			
			    <!-- closes modal dialog -->
			    <g:javascript>
			         function closeMe() {
			             document.getElementById('renameMessage').innerText = "";
			         };
			    </g:javascript>
			
			    <g:formRemote name="renameCategoryForm" action="${createLink(controller: 'standards', action: 'renameCategory') }" 
			        url="[action: 'renameCategory']" update="renameMessage">
			        
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
						<br />
						<h6 id="editCategoryModalLabel"><!-- Category path inserted here --></h6>
					</div>
					
					<div class="modal-body">
							<p>
								<g:field type="hidden" name="categoryId" />
								<g:field class="span12" type="text" name="categoryName" />
							</p>
					</div>
					
					<div class="modal-footer">
						<div class="pull-left" id="renameMessage"><!-- success message --></div>
						<button id="closeModal" class="btn" data-dismiss="modal" aria-hidden="true" onclick="closeMe();">Close</button>
						<button class="btn btn-primary" onclick="refreshCategoryList();">Save</button>
					</div>
					
				</g:formRemote>
			</div>

		</div>

	</div>
</body>
</html>
