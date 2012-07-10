<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Log levels</title>
	
	
	<c:url var="jquery" value="/static/js/jquery-1.7.2.min.js" />
	<c:url var="core" value="/static/js/ui/jquery.ui.core.js" />
	<c:url var="widget" value="/static/js/ui/jquery.ui.widget.js" />
	<c:url var="position" value="/static/js/ui/jquery.ui.position.js" />
	<c:url var="autocomplete" value="/static/js/ui/jquery.ui.autocomplete.js" />
    <c:url var="all" value="/static/css/themes/base/jquery.ui.all.css" />
	
	<script src="${jquery}"></script>
	<script src="${core}"></script>
	<script src="${widget}"></script>
	<script src="${position}"></script>
	<script src="${autocomplete}"></script>

	<link rel="stylesheet" href="${all}" />
		<style>
		.ui-autocomplete-loading {
			background: white url('static/images/ui-anim_basic_16x16.gif') right
				center no-repeat;
		}
		
		#packagefinder {
			width: 460px;
		}
		
		.container {
			margin: 0 auto;
		}
		
		.container>div {
			margin-top: 30px;
		}
		
		body {
			margin: 0;
			text-align: center;
		}
		
		#log {
			height: 200px;
			width: 1024px;
			margin: 0 auto;
			text-align: left;
		}
		
		.ui-menu-item {
			text-align: left;
		}
		
	   .packagelabel, .radio, .radioGroup {
			float: left; 
		}
		.packagelabel {
		 	width: 640px;
		
		}
		.packagelabel, .radioGroup {
		 	border-bottom: dashed 1px #444;
		}
		label {
		 margin-left: 10px;
		}
		
	</style>
	<script>
	$(function() {
		$(".radio").live('change', function(button) {
			$.ajax({
				url: "./setLogLevel",
				data: {
					pack: button.target.name,
					level: button.target.value
				}
			});
		    
		});

		function getRadioButton( pack, level ) {
			var id = level + '.' + pack;
			return $( '<div class="radio"/>' ).html('<label for="' + id + '">' + level + '</label><input type="radio" name="' + pack + '" id="' + id + '" value="'+ level + '">');
		}
		
		function log( pack ) {
			var group = $( '<div class="radioGroup" />' );
			getRadioButton( pack, "trace" ).prependTo( group );
			getRadioButton( pack, "debug" ).prependTo( group );
			getRadioButton( pack, "info" ).prependTo( group );
			getRadioButton( pack, "warn" ).prependTo( group );
			getRadioButton( pack, "error" ).prependTo( group );
			
			group.prependTo( "#log");
			
			
			
			
			var row = $( "<div />" );
			$( '<div class="packagelabel" />' ).text( pack ).appendTo( row );
			group.appendTo( row );
			
				
			row.prependTo( "#log" );
			
			$( "#log" ).scrollTop( 0 );
		}

		$( "#packagefinder" ).autocomplete({
			source: function( request, response ) {
				$.ajax({
					url: "./package",
					dataType: "json",
					data: {
						featureClass: "P",
						style: "full",
						maxRows: 12,
						input: request.term
					},
					success: function( data ) {
						response( $.map( data, function( pack ) {
							return {
								label: pack,
								value: pack
							}
						}));
					}
				});
			},
			minLength: 2,
			select: function( event, selected ) {
				log( selected.item ?
					selected.item.label :
					"Nothing selected" + this.value);
			},
			open: function() {
				$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
			},
			close: function() {
				$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
			}
		});
		
		$( "button" ).click(function() {
			$.ajax({
				url: "./doLog",
				data: {
					level: this.value
				}
			});
		});	

	});
	</script>

</head>
<body>

	<div class="container">
		
		<div class="ui-widget">
			<button id="error" value="error" class="ui-button"><span class="ui-button-text logbutton">log.error</span></button>
			<button id="warn"  value="warn"  class="ui-button"><span class="ui-button-text logbutton">log.warn </span></button>
			<button id="info"  value="info"  class="ui-button"><span class="ui-button-text logbutton">log.info </span></button>
			<button id="debug" value="debug" class="ui-button"><span class="ui-button-text logbutton">log.debug</span></button>
			<button id="trace" value="trace" class="ui-button"><span class="ui-button-text logbutton">log.trace</span></button>
		</div>
		<div class="ui-widget">
			<label for="packagefinder">enter package</label> <input id="packagefinder" />
		</div>

		<div class="ui-widget">		
			<div id="log" class="ui-widget-content"></div>
		</div>

	</div>

</body>
</html>
	