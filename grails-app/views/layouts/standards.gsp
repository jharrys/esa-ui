<%@page import="org.codehaus.groovy.grails.commons.ApplicationHolder"%>
<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title><g:layoutTitle default="EISA" /></title>
<meta name="description" content="Enterprise Information System Architecture">
<meta name="author" content="John Harris">
<meta name="viewport" content="initial-scale = 1.0">

<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<link rel="stylesheet" href="${resource(dir: 'css', file: 'datepicker.css')}" type="text/css">

<r:require modules="scaffolding" />

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">

<g:javascript library="jquery" />
<g:javascript library="jquery-ui" />
<g:javascript src="spin.js" />

<g:layoutHead />
<r:layoutResources />
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

	<!--
        bootstrap twitter doesn't include one, so I'm using this one http://www.eyecon.ro/bootstrap-datepicker/ 
        FIXME: place this resource in the appropriate configuration files 
    -->
	<g:javascript src='bootstrap-datepicker.js' />

	<%
	       def versionService = grailsApplication.mainContext.getBean("versionService")
	 %>

	<nav class="navbar navbar-fixed-top navbar-inverse">
		<div class="navbar-inner">
			<div class="container-fluid">

				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"
				></span>
				</a> <a class="brand" href="${createLink(uri: '/')}">Enterprise Architecture</a>

				<div class="nav-collapse">
					<ul class="nav">
						<li <%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/')}">Home</a></li>
						<li <%= request.forwardURI == "${createLink(uri: '/standards/index')}" ? ' class="active"' : '' %>><g:link controller="standards">Standards</g:link></li>
						<li <%= request.forwardURI == "${createLink(uri: '/exception/index')}" ? ' class="active"' : '' %>><g:link controller="exception">Exceptions</g:link></li>
						<li <%= request.forwardURI == "${createLink(uri: '/admin/admin')}" ? ' class="active"' : '' %>><g:link controller="admin">Administration</g:link></li>
						<li>
							<%
						      def aboutString = "Application: " + versionService.getApplicationVersion()
						      aboutString = aboutString + "<br /> Database: " + versionService.getDatabaseVersion()
						   %> <a href="#" id="version" rel="popover" data-content="${aboutString }" data-original-title="Version">About</a> <script>
						    $(function() {
						    	  $('#version').popover({trigger: 'hover', placement: 'bottom'})
						    });
						  </script>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</nav>

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

				<hr>

				<footer>
					<p>
						&copy; Intermountain Healthcare 2012. All Rights Reserved. <br /> <small><small>build: <g:render template="/git" /></small></small>
					</p>
				</footer>

			</div>

		</div>

	</div>

	<r:layoutResources />

</body>
</html>