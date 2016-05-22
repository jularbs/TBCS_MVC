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
        <p class="login-box-msg">Register a new membership</p>
        <form action="clientregistration.html" method="post">
        
          <div class="form-group has-feedback">
          	<label>Company Name</label>
            <input type="text" maxlength="150" class="form-control" name="name" placeholder="Company Name" required>
            <span class="fa fa-institution form-control-feedback"></span>
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
          
          <div class="form-group has-feedback">
          <label>Contact Person</label>
            <input type="text" maxlength="80" class="form-control" name="contactFirstName" placeholder="First name" required>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <input type="text" maxlength="50" class="form-control" name="contactMiddleName" placeholder="Middle name" required>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
            <input type="text" maxlength="50" class="form-control" name="contactLastName" placeholder="Last name" required>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          
          <div class="form-group has-feedback">
          <label>Contact Number</label>
            <input type="text" maxlength="15" class="form-control" name="contactno" placeholder="Contact Number" required>
            <span class="fa fa-phone form-control-feedback"></span>
          </div>
          
          <label>Email Address</label>
          <div class="form-group has-feedback">
           <input type="email" maxlength="50" class="form-control" name="email" placeholder="Email" required="required" required>
            <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
          </div>
          
          <label>Type</label>
		  <div class="form-group has-feedback">
			<select name="agency">
				<option>Agency</option>
				<option>Advertiser</option>
			</select>
		  </div>
          <div class="row">
            <div class="col-xs-8">
              <div class="checkbox icheck">
                <label>
                  <input type="checkbox"> I agree to the <a href="#">terms</a>
                </label>
              </div>
            </div><!-- /.col -->
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat">Register</button>
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
