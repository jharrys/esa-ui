<!doctype html>
<html>
	<head>
	   <meta name="layout" content="general" />
       <title>Edit Item</title>
	</head>
	<body>
		
        <div class="span10">

            <div class="page-header">
                <h1>
                    Edit Item "${itemInstance.name }"
                </h1>
            </div>

            <div id="message">
                <g:if test="${flash.message}">
                    <bootstrap:alert class="alert alert-error fade in" id="alertmessage">
                        ${flash.message}
                    </bootstrap:alert>
                </g:if>
            </div>

            <div class="row-fluid">

                <p>
                    <g:form action="editItem">
                        <fieldset>
							<g:field type="text" name="categoryId" type="hidden" value="${categoryId }" />

							<f:all bean="itemInstance" except="categories,catalogs,itemVersions,itemConversions,
	                        replacementForItems,replacementItems,configurationElements,partOfConfigurations,
	                        createdBy,updatedBy"/>
	                        
	                        <div class="form-actions">
                                <button type="submit" class="btn btn-primary">
                                    <i class="icon-ok icon-white"></i>
                                    <g:message code="default.button.update.label" default="Update" />
                                </button>
                                <button type="submit" class="btn" name="_action_cancel" formnovalidate onclick="window.close()">
                                    <i class="icon-remove icon-black"></i>
                                    <g:message code="default.button.cancel.label" default="Cancel" />
                                </button>
                            </div>
                        </fieldset>
                     </g:form>
                </p>

            </div>

        </div>
        
	</body>
</html>
                