<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="radioStations" type="java.sql.ResultSet" scope="request"/>
<jsp:useBean id="positions" type="java.sql.ResultSet" scope="request"/>

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
        <p class="login-box-msg">Register a new employee</p>
        <form action="employeeregistration.html" method="post">
          <div class="form-group has-feedback">
            <label>Type</label>
			  <div class="form-group has-feedback">
				<select name="position">
					<%do{ %>
						<option value="<%=positions.getInt("positionID") %>"><%=positions.getString("name") %></option>
					<%}while(positions.next()); %>
				</select>
			  </div>
			  <div class="form-group has-feedback">
				<select name="radioStation">
					<%do{ %>
						<option value="<%=radioStations.getInt("stationID") %>"><%=radioStations.getString("name") %></option>
					<%}while(radioStations.next()); %>
				</select>
			  </div>
          </div>
          <div class="form-group has-feedback">
          <label>Full Name</label>
            <input type="text" maxlength="80" class="form-control" name="firstName" placeholder="First name" required>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <input type="text" maxlength="50" class="form-control" name="middleName" placeholder="Middle name" required>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <input type="text" maxlength="50" class="form-control" name="lastName" placeholder="Last name" required>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          
          <div class="form-group has-feedback text-center">
                <label>
                  <div class="iradio_minimal-blue checked" aria-checked="false" aria-disabled="false" style="position: relative; margin-right:20px; margin-left 10px;">
                  Male
                  <input type="radio" name="gender" value="Male" class="minimal" checked="" style="position: absolute; opacity: 0;">
                  <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
                  </div>
             
                </label>
                <label>
                  <div class="iradio_minimal-blue" aria-checked="false" aria-disabled="false" style="position: relative;margin-right:20px; margin-left 10px;">
                  Female
                  <input type="radio" name="gender" value="Female" class="minimal" style="position: absolute; opacity: 0;">
                  <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
                  </div>
                
                </label>
          </div>
          <div class="form-group has-feedback">
          	<label>Birthday</label>
            <input type="date" class="form-control" name="birthday" placeholder="mm/dd/yyyy" required>
            <span class="fa fa-calendar form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
          <label>Address</label>
            <input type="text" maxlength="10" class="form-control" name="addressNo" placeholder="Address Number" required>
            <span class="fa fa-location-arrow form-control-feedback"></span>
            <input type="text" maxlength="50" class="form-control" name="street" placeholder="Street" required>
            <span class="fa fa-location-arrow form-control-feedback"></span>
            <input type="text" maxlength="50" class="form-control" name="city" placeholder="City" required>
            <span class="fa fa-location-arrow form-control-feedback"></span>
            <input type="text" maxlength="10" class="form-control" name="zipCode" placeholder="Zip Code" required>
            <span class="fa fa-location-arrow form-control-feedback"></span>
          </div>
          
          <label>Account Details</label>
          <div class="form-group has-feedback">
           <input type="email" maxlength="50" class="form-control" name="email" placeholder="email" required>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          <div class="row">
            <div class="col-xs-12">
              <button type="submit" class="btn btn-primary btn-block btn-flat" id="register">Register</button>
            </div><!-- /.col -->
          </div>
        </form>

        <a href="index.jsp" class="text-center">I already have a membership</a>
      </div><!-- /.form-box -->
    </div><!-- /.register-box -->

    <!-- jQuery 2.1.4 -->
    <script src="assets/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- iCheck -->
    <script src="assets/plugins/iCheck/icheck.min.js"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>
</html>
