<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<g:javascript library="jquery" />
<g:javascript src="spin.js" />
<title>New Item</title>
</head>
<body>
<script>
/*

You can now create a spinner using any of the variants below:

$("#el").spin(); // Produces default Spinner using the text color of #el.
$("#el").spin("small"); // Produces a 'small' Spinner using the text color of #el.
$("#el").spin("large", "white"); // Produces a 'large' Spinner in white (or any valid CSS color).
$("#el").spin({ ... }); // Produces a Spinner using your custom settings.

$("#el").spin(false); // Kills the spinner.

*/
(function($) {
    $.fn.spin = function(opts, color) {
        var presets = {
        "tiny": { lines: 8, length: 2, width: 2, radius: 3 },
        "small": { lines: 8, length: 4, width: 3, radius: 5 },
        "large": { lines: 10, length: 8, width: 4, radius: 8 }
        };
        if (Spinner) {
            return this.each(function() {
                var $this = $(this),
                    data = $this.data();
            
                if (data.spinner) {
                    data.spinner.stop();
                    delete data.spinner;
                }
                
                if (opts !== false) {
                    if (typeof opts === "string") {
                        if (opts in presets) {
                            opts = presets[opts];
                        } else {
                            opts = {};
                        }
                        if (color) {
                            opts.color = color;
                        }
                    }
                    data.spinner = new Spinner($.extend({color: $this.css('color')}, opts)).spin(this);
                }
            });
        } else {
               throw "Spinner class not available.";
        }
    };
})(jQuery);


</script>
    <div class="row-fluid">

        <div class="span9">

            <div class="page-header">
                <h1>
                    Add New Item
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
                    <g:form action="addNewItem">
                        <fieldset>
							<g:field type="text" name="categoryId" type="hidden" value="${categoryId }" />

							<f:all bean="itemInstance" except="categories,catalogs,itemVersions,itemConversions,
	                        replacementForItems,replacementItems,configurationElements,partOfConfigurations,
	                        createdBy,updatedBy"/>
	                        
	                        <div class="form-actions">
                                <button type="submit" class="btn btn-primary">
                                    <i class="icon-ok icon-white"></i>
                                    <g:message code="default.button.create.label" default="Create" />
                                </button>
                                <button type="submit" class="btn" name="_action_cancel" formnovalidate>
                                    <i class="icon-remove icon-black"></i>
                                    <g:message code="default.button.cancel.label" default="Cancel" />
                                </button>
                            </div>
                        </fieldset>
                     </g:form>
                </p>

            </div>

        </div>

    </div>
</body>
</html>
                