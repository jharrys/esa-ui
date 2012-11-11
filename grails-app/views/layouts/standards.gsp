<g:applyLayout name="general">
	<!doctype html>
	<html>
		<head>
			<title><g:layoutTitle/></title>
		</head>
		
		<body>
			<script>
				/*
				 * Popup Window
				 */
				 var windowSizeArray = ["width=500,height=800", "width=300,height=400,scrollbars=yes"];
				 $(document).ready(function() {
				     $('.actionRef').click(function(event) {
				           var url = $(this).attr('href');
				           var windowName = 'popUp';
				           var windowSize = windowSizeArray[$(this).attr('rel')];
				
				           window.open(url, windowName, windowSize);
				
				           event.preventDefault();
				     });
				 });
				 
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
				
				function setEditItemLink(itemId) {
				       var editItemButtonUrlCategory = $('#editItemLink').attr('href').split("?")[0] + "?itemId=" + itemId;
				       $('#editItemLink').attr('href', editItemButtonUrlCategory);
				       $('#editItemLi').removeClass('disabled');
				   }
				   
			    function setDeleteItemLink(itemId) {
				       var deleteItemButtonUrlCategory = $('#deleteItemLink').attr('href').split("?")[0] + "?itemId=" + itemId;
				       $('#deleteItemLink').attr('href', deleteItemButtonUrlCategory);
				       $('#deleteItemLi').removeClass('disabled');
				   }
			</script>
		
			<div class="container-fluid">
		
				<div class="row-fluid">
		
					<div class="span12">
		
						<div class="span2">
							<div class="well">
								<ul class="nav nav-pills nav-stacked">
									<li class="nav-header">
										${entityName}
									</li>
		
									<li <%= request.forwardURI == "${createLink(uri: '/standards/editByCategory')}" ? ' class="active"' : '' %>>
		                                <g:link action="editByCategory">
		                                    Standards By Category
		                                </g:link>
		                            </li>
		
									<li <%= request.forwardURI == "${createLink(uri: '/standards/list')}" ? ' class="active"' : '' %>>
										<g:link action="list">
										    List of Standard Items
										</g:link>
								    </li>
		
								</ul>
							</div>
		
							<div class="well">
								<ul class="nav nav-pills nav-stacked">
									<li class="nav-header">Actions</li>
									<li id="editCategoryLi" class="disabled"><a href="#" id="editCategoryButton" data-target="#editCategoryModal"> Rename Category </a></li>
									<li id="addNewItemLi" class="disabled"><g:link elementId="addNewItemLink" action="addNewItem" rel="0" class="actionRef">New Item</g:link></li>
									<li id="editItemLi" class="disabled"><g:link elementId="editItemLink" action="editItem" rel="0" class="actionRef">Edit Item</g:link></li>
									<li id="deleteItemLi" class="disabled"><a id="deleteItemLink" href="/esa-ui/standards/deleteItem">Delete Item</a></li>
								</ul>
							</div>
		
						</div>
		
						<g:layoutBody />
		
					</div>
		
				</div>
		
			</div>
		
		</body>
	</html>
</g:applyLayout>