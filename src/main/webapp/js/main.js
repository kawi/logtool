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
	
	$(document).ready(function() {
		
	$( "#packages" ).on("change", 
		function () {
			$.ajax({
				url: "./package",
				dataType: "json",
				data: {
					featureClass: "P",
					style: "full",
					maxRows: 12,
					input: $(this).attr('value')
				},
				success: function( data ) {
					alert(data)
//					response( $.map( data, function( pack ) {
//						return {
//							label: pack,
//							value: pack
//						}
//					}));
				}
			});
		}
	);
	});
	
//	minLength: 2,
//	select: function( event, selected ) {
//		log( selected.item ?
//			selected.item.label :
//			"Nothing selected" + this.value);
//	},
//	open: function() {
//		$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
//	},
//	close: function() {
//		$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
//	}
	
	$( "button" ).click(function() {
		$.ajax({
			url: "./doLog",
			data: {
				level: this.value
			}
		});
	});	

});
