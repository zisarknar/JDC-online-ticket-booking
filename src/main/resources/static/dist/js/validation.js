$(function(){
			$("#busType_error").hide();
			var busType_error=false;
			
			$("#busType").focusout(function(){
				checkBusType();
			});
			
			function checkBusType(){
				if($("#busType").val().length>0){
					$("#busType_error").hide();
					busType_error=false;
				}
				else if($("#busType").val().length==0){
					$("#busType_error").html("Please Enter BusType");
					$("#busType_error").show();
					busType_error=true;
				}
			}
			
			$("#addBusTypeForm").submit(function(){
				busType_error=false;
				checkBusType();
				if(busType_error==false){
					return true;
					
				}
				else{
					return false;
				}
			});
			
			
		});