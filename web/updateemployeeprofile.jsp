<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRAFFIC, BILLING, AND COLLECTION SYSTEM</title>
</head>
<body>
<h2>Update Employee Profile</h2>
<%if(request.getParameter("action").equals("update")) {%>
	<form action="updateemployeeprofile.html" method="post">
			<p>First Name: <input type = "text" name="firstName" size="25" value="${userLoggedin.firstName}"></p>
			<p>Middle Name: <input type = "text" name="middleName" size="25" value="${userLoggedin.middleName}"></p>
			<p>Last Name: <input type = "text" name="lastName" size="25" value="${userLoggedin.lastName}"></p>	
			<p>Gender: <input type = "text" name="gender" size="25" value="${userLoggedin.gender}"></p>
			<p>Birthday: <input type = "text" name="birthday" size="25" value="${userLoggedin.birthday}"></p>
			<p>Address No: <input type = "text" name="addressNo" size="25" value="${userLoggedin.addressNo}"></p>
			<p>Street: <input type = "text" name="street" size="25" value="${userLoggedin.street}"></p>
			<p>City: <input type = "text" name="city" size="25" value="${userLoggedin.city}"></p>
			<p>Zip Code: <input type = "text" name="zipCode" size="25" value="${userLoggedin.zipCode}"></p>
			<p>Email Address: <input type = "text" name="email" size="25" value="${userLoggedin.email}"></p>
			<input type="hidden" name="employee_ID" value="${employeeID}">
			<input type="submit" value="Update">
	</form>
	<%} %>
</body>
</html>