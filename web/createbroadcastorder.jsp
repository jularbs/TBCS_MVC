<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="radioStations" type="java.sql.ResultSet" scope="request"/>
<jsp:useBean id="adMaterials" type="java.sql.ResultSet" scope="request"/>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TBC System | Registration Page</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.5 -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="assets/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="assets/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="hold-transition register-page">
    <div class="register-box">
      <div class="register-logo">
        <a href="login.jsp"><b>TBC</b>SYSTEM</a>
      </div>

      <div class="register-box-body">
        <p class="login-box-msg">Create new broadcast order</p>
        <script>
		  var toggle = function() {
		  var newAd = document.getElementById('newAd');
		  var oldAd = document.getElementById('oldAd');
		  
		  var theButton = document.getElementById('toggle');
		  
		  if (oldAd.style.display === 'none'){
			    newAd.style.display = 'none';
			    oldAd.style.display = 'block';
			    document.getElementById('amName').required = false;
			    document.getElementById('amProduct').required = false;
			    document.getElementById('amVersion').required = false;
			    document.getElementById('amTagline').required = false;
			    document.getElementById('amMaterial').required = false;
			  	theButton.value = "New Advertising Material";
		  }
		  else{
				oldAd.style.display = 'none';
			    newAd.style.display = 'block';
			    document.getElementById('amName').required = true;
			    document.getElementById('amProduct').required = true;
			    document.getElementById('amVersion').required = true;
			    document.getElementById('amTagline').required = true;
			    document.getElementById('amMaterial').required = true;
			    theButton.value = "Old Advertising Material";
		  }
		  }
		</script>
		<input type="button" class="btn btn-primary btn-block btn-flat" value="Old Advertising Material" id="toggle" onclick="toggle();">
        <form action="insertbroadcastorder" method="post" enctype="multipart/form-data">  
        	<div class="form-group has-feedback" id="newAd">
         		<label>Advertisement Material</label>
	            <input type="text" maxlength="80" class="form-control" name="name" placeholder="Name" id="amName" required>
	            <span class="glyphicon glyphicon-user form-control-feedback"></span>
	            <input type="text" maxlength="50" class="form-control" name="product" placeholder="Product" id="amProduct" required>
	            <span class="glyphicon glyphicon-user form-control-feedback"></span>
	            <input type="text" maxlength="50" class="form-control" name="version" placeholder="Version" id="amVersion" required>
	            <span class="glyphicon glyphicon-user form-control-feedback"></span>
	            <input type="text" maxlength="50" class="form-control" name="tagline" placeholder="Tagline" id="amTagline" required>
	            <span class="glyphicon glyphicon-user form-control-feedback"></span>
	            <input type="file" class="form-control" name="advertisingMaterial" placeholder="Advertisement Material" id="amMaterial" required>
	            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          	</div>
          	<div class="form-group has-feedback" id="oldAd" hidden>
          		<label>Advertisement Material</label>
          		<select name="materialID" class="form-control">
          			<option value="-1">-- Pick an old Advertisement Material --</option>
          			<%
 //         			if(adMaterials.next()){
          				do{%>				
					<option value="<%=adMaterials.getInt("materialID")%>"><%=adMaterials.getString("name") %> <%=adMaterials.getString("version") %> </option>         					
          				<%}while(adMaterials.next());
          			
          	//		  } else { %>
          			<%//} %>
				</select>
          		
          	</div>
          	<div class="form-group has-feedback">
            <label>Broadcast Order Details</label>
            	<p>Radio Station</p>
				<select name="radioStation" >
					<%do{ %>
						<option value="<%=radioStations.getInt("stationID") %>"><%=radioStations.getString("name") %></option>
					<%}while(radioStations.next()); %>
				</select>

            	<p>Spots per day</p>
	            <input type="number" min="0" class="form-control" name="spotsPerDay">
	            Start Date
	            <input type="date" class="form-control" name="startDate" required>
	            End Date
	            <input type="date" class="form-control" name="endDate" required>
	            Start Time
	            <input type="time" class="form-control" name="startTime" required>
				End Time
				<input type="time" class="form-control" name="endTime" required>
            </div>
		    
		    <div class="form-group has-feedback">
	          <div class="row">
		          <input type="checkbox" name="mon" value="true"><input type="checkbox" name="mon" value="false" hidden>mon
		          <input type="checkbox" name="tue" value="true"><input type="checkbox" name="tue" value="false" hidden>tue
		          <input type="checkbox" name="wed" value="true"><input type="checkbox" name="wed" value="false" hidden>wed
		          <input type="checkbox" name="thu" value="true"><input type="checkbox" name="thu" value="false" hidden>thu
		          <input type="checkbox" name="fri" value="true"><input type="checkbox" name="fri" value="false" hidden>fri
		          <input type="checkbox" name="sat" value="true"><input type="checkbox" name="sat" value="false" hidden>sat
		          <input type="checkbox" name="sun" value="true"><input type="checkbox" name="sun" value="false" hidden>sun
	          </div>
            </div>
            
            <div class="form-group has-feedback">
              <label>Additional Instructions</label>
	          <textarea row="10" class="form-control" name="additionalInstructions"></textarea>
            </div>
            <div class="row">
	            <div class="col-xs-12">
	              <button type="submit" class="btn btn-primary btn-block btn-flat">Create Broadcast Order</button>
	            </div><!-- /.col --> 
            </div>
        </form>
      </div><!-- /.form-box -->
    </div><!-- /.register-box -->

    <!-- jQuery 2.1.4 -->
    <script src="assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="assets/plugins/iCheck/icheck.min.js"></script>
  </body>
</html>
