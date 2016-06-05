<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<jsp:useBean id="adMaterials" type="java.sql.ResultSet" scope="request"/>
<jsp:useBean id="radioStations" type="java.sql.ResultSet" scope="request"/>

    
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TBC System | Create Broadcast Order</title>
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  </head>
  <body class="hold-transition register-page">
  	<div class="schedule" id="schedule" hidden>
		<select name="radioStation[]" id="radiostation">
			<%do{ %>
			<option value="<%=radioStations.getInt("stationID") %>"><%=radioStations.getString("name") %></option>
			<%}while(radioStations.next()); %>
     	</select>		
			  <input type="time" name="startTime[]" value=""/>	
        	  <input type="time" name="endTime[]" value=""/>
			  <input type="number" name="spotsPerDay[]" value="" min="0"/>
	          <input type="checkbox" name="mon[]" value="true"><input type="checkbox" name="mon[]" value="false" hidden>mon
	          <input type="checkbox" name="tue[]" value="true"><input type="checkbox" name="tue[]" value="false" hidden>tue 
			  <input type="checkbox" name="wed[]" value="true"><input type="checkbox" name="wed[]" value="false" hidden>wed 
			  <input type="checkbox" name="thu[]" value="true"><input type="checkbox" name="thu[]" value="false" hidden>thu
			  <input type="checkbox" name="fri[]" value="true"><input type="checkbox" name="fri[]" value="false" hidden>fri 
			  <input type="checkbox" name="sat[]" value="true"><input type="checkbox" name="sat[]" value="false" hidden>sat
			  <input type="checkbox" name="sun[]" value="true"><input type="checkbox" name="sun[]" value="false" hidden>sun
			  <input type="text" name="buffer" hidden>
	    	  <a class="remove_button">Remove Radio Station</a></div>
	</div>
  <form method="post" action="insertBroadcastOrder" enctype="multipart/form-data">	
	<h1>CREATE BROADCAST ORDER</h1>
	<div class="advertisingmaterial_wrapper">
	 <script>
		  var showNew = function() {
			  var newAd = document.getElementById('newAdvertisement');
			  var oldAd = document.getElementById('oldAdvertisement');
				    newAd.style.display = 'block';
				    oldAd.style.display = 'none';
				    document.getElementById('amName').required = true;
				    document.getElementById('amProduct').required = true;
				    document.getElementById('amVersion').required = true;
				    document.getElementById('amTagline').required = true;
				    document.getElementById('amMaterial').required = true;
					document.getElementById('newad').disabled = true;
					document.getElementById('oldad').disabled = false;		
		  }
		  
		  var showOld = function() {
			  var newAd = document.getElementById('newAdvertisement');
			  var oldAd = document.getElementById('oldAdvertisement');
				    newAd.style.display = 'none';
				    oldAd.style.display = 'block';
				    document.getElementById('amName').required = false;
				    document.getElementById('amProduct').required = false;
				    document.getElementById('amVersion').required = false;
				    document.getElementById('amTagline').required = false;
				    document.getElementById('amMaterial').required = false;
					document.getElementById('newad').disabled = false;
					document.getElementById('oldad').disabled = true;		
			  
		  }
	</script>
	<h2>Advertisement Material</h2>
	<input type="button" value="Pick old advertisements" id="oldad" onclick="showOld();" />
	<input type="button" value="create new advertisements" id="newad" onclick="showNew();" disabled/>
		<div id="newAdvertisement">
			<input type="text" maxlength="80" name="name" placeholder="Advertisement Name" id="amName" required>
			<input type="text" maxlength="50" name="product" placeholder="Product" id="amProduct" required>
			<input type="text" maxlength="50" name="version" placeholder="Version" id="amVersion" required>
			<input type="text" maxlength="50" name="tagline" placeholder="Tagline" id="amTagline" required>
			<input type="file" name="advertisingMaterial" placeholder="Advertisement Material" id="amMaterial" accept="audio/mp3" required>		
		</div>
		<div id="oldAdvertisement" style="display: none;">
			<select name="materialID" class="form-control">
          		
          			<%if(adMaterials.next()){ %>
          		<option value="-1">-- Pick an old Advertisement Material --</option>	
          			<%do{%>							
					<option value="<%=adMaterials.getInt("materialID")%>"><%=adMaterials.getString("name") %> <%=adMaterials.getString("version") %> </option>         					
          			<%}while(adMaterials.next()); } else {%>
          			<option value="-1"> -- No Old Advertisement -- </option>
          			<%} %>
			</select>
		</div>
       
		</div>
		<h2>Broadcast Order Details</h2>
		<div class="boDetails_wrapper">
			Start Date
			<input type="date" name="startDate" required>
			End Date
			<input type="date" name="endDate" required>
			Additional Instructions
			<textarea  row="10" name="additionalInstructions"></textarea>
		</div>
		<h2>Broadcast Order Schedule</h2>
		<div class="boSchedule_wrapper">
			<!-- Broadcast Order will be appended here. See SCRIPT element below to see elements to design -->
			
			<input type ="button" class="add_sched" value="Add Radio Station" />	
		</div>
		<button type="submit">Submit Broadcast Order</button>
  </form>
  
  
	<script src="assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
	    var maxField = 10; //Number of metro manila radio stations
	    var addButton = $('.add_sched');
	    var wrapper = $('.boSchedule_wrapper');
	    var x = 0;
	    $(addButton).click(function(){ //Once add button is clicked
	        if(x < maxField){ //Check maximum number of input fields
	            x++; //Increment field counter
	            var $additional = $('#schedule').clone();
	            $additional.removeAttr('id');
	            $additional.removeAttr('hidden');
	            $additional.insertBefore('.add_sched')
	        }
	    });
	    
	    $(wrapper).on('click', '.remove_button', function(e){ //Once remove button is clicked
	        e.preventDefault();
	        $(this).parent('div').remove(); //Remove field html
	        x--; //Decrement field counter
	    });
	});
	</script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/plugins/iCheck/icheck.min.js"></script>
  	
  </body>
</html>
