<%@page import="grails.converters.JSON"%>
<%@ page import="org.ihc.esa.Item"%>
<!doctype html>
<html>
	<head>
		<g:set var="entityName" value="${message(code: 'standard.label', default: 'Standard')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<script>
			function closeEditCategoryModal() {
			    document.getElementById('renameMessage').innerText = "";
			};
			
			$(function() {
			    $( "#delete-confirm" ).dialog({
			        autoOpen: false,
			        resizable: false,
			        height:140,
			        modal: true,
			        buttons: {
			            "Delete": function() {
			                //FIXME: need to refresh allitems and realitems
			                var value = $('#deleteItemLink').prop('href').split("?")[1].split("itemId=")[1].split("&")[0];
			            	${remoteFunction(action: 'deleteItem', params: '\'itemId=\'+value', update: [success: 'alertmessage', failure: 'alertmessage'])};
			                $( this ).dialog( "close" );
			            },
			            Cancel: function() {
			                $( this ).dialog( "close" );
			            }
			        }
			    });
			
			    //this.search.split("itemId=")[1].split("&")[0]
			    $( "#deleteItemLink" ).click(function(event) {
			        event.preventDefault();
			        $( "#delete-confirm" ).dialog( "open" );
			    });
			});
			
			function refreshCategoryList(allCategories) {
			    var categoryElement = document.getElementById('category');
			    // FIXME: currentIndex is not correct - need to search based on name
			    var currentValue = categoryElement.value  
			
			    while (categoryElement.length) {
			        categoryElement.remove(0);
			    }
			
			    var x;
			    for (x=0;x<allCategories.length;x++) {
			        var displayName = (allCategories[x].parentCategoryPath == "/") ? "/" + allCategories[x].name : allCategories[x].parentCategoryPath + "/" + allCategories[x].name
			        var cat = new Option(displayName, allCategories[x].id);
			        categoryElement.options.add(cat);
			    }
			
			    var index = $("#category > option[value='" + currentValue + "']").index();
			    categoryElement.selectedIndex = index
			    $('#renameMessage').spin(false);
			    $('#renameMessage').html("Done!");
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
			        document.getElementById('editCategoryModalLabel').innerText = (parentpath ? parentpath : "/");
			        var name = fullpath[fullpath.length-1];
			        catIdElement.value = categoryElement.value;
			        catNameElement.value = name;
			        $('#catname').html(name);
			
			        // enable the allitems and itemsincategory select boxes
			        realItemsElement.disabled = false;
			        allItemsElement.disabled = false;
			        $('#editCategoryLi').removeClass('disabled');
			        $('#editCategoryButton').attr('data-toggle', 'modal');
			
			        // toggle the addNewItem*
			        $('#addNewItemLi').removeClass('disabled');
			        $('#addNewItemButton').attr('data-toggle', 'modal');
			        var addNewItemButtonUrlCategory = $('#addNewItemLink').attr('href').split("?")[0] + "?categoryId=" + catIdElement.value;
			        $('#addNewItemLink').attr('href', addNewItemButtonUrlCategory);
			    } else {
			        // if we're here it means that 'no selection' was made, clear out the itemsincategory select box
			        while (realItemsElement.length) {
			            realItemsElement.remove(0);
			        }
			
			        // enable the allitems and itemsincategory select boxes
			        realItemsElement.disabled = true;
			        allItemsElement.disabled = true;
			        $('#editCategoryLi').addClass('disabled');
			        $('#editCategoryButton').removeAttr('data-toggle');
			        $('#addNewItemLi').addClass('disabled');
			        $('#addNewItemButton').removeAttr('data-toggle');
			        $('#editItemLi').addClass('disabled');
			        $('#editItemButton').removeAttr('data-toggle');
			    }
			};
			
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
			
			    var isSuccessful = "";
			    
			    ${remoteFunction(action: 'addItemToCategory', params: '\'toCategoryId=\'+category+\'&itemId=\'+value', update: [success: 'alertmessage', failure: 'alertmessage'])};
			
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
			    // FIXME: saw condition where user session times but additions or removals don't work - but it still acts as if it works
			    var isSuccessful = ${remoteFunction(action: 'removeItemFromCategory', params: '\'fromCategoryId=\'+category+\'&itemId=\'+value')};
			    
			    // remove from itemsincategory
			    sourceElement.remove(index);
			
			    // add to allitems list
			    targetElement.add(option);
			};
		</script>
		<div class="span10">
	
			<div class="page-header">
				<h1>
					<g:message code="default.list.label" args="[entityName]" />
				</h1>
			</div>
	
			<div id="message">
				<bootstrap:alert class="alert alert-error fade in" id="alertmessage">
					${flash.message}
				</bootstrap:alert>
			</div>
	
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
				    <span class="label label-info">Items To Choose From:</span>
					<g:select disabled="true" id="allitems" name="allitems" size="10" style="width: 100%" from="${Item.list(sort: 'name') }" optionKey="id" optionValue="name"
						onclick="setEditItemLink(this.value);setDeleteItemLink(this.value);" ondblclick="addMeToCategory(this.value);"
					/>
				</div>
	
				<div class="span4">
				    <span class="label label-info">Items Already Part of Category:</span>
					<g:select disabled="true" id="itemsincategory" name="itemsincategory" size="10" style="width: 100%" from="" 
					   onclick="setEditItemLink(this.value);setDeleteItemLink(this.value);" ondblclick="removeMeFromCategory(this);" />
				</div>
	
			</div>
	
			<%-- Modal box for renaming selected Category --%>
			<g:render template="editCategoryModal" />
			
			<div id="delete-confirm" title="Delete?">
			     <p><span class="ui-icon ui-icon-alert" style="float:left; margin: 0 7px 20px 0;"></span>This will be deleted
			</div>
			
		</div>
			
	</body>
</html>
