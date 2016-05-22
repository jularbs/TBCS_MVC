<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRAFFIC, BILLING, AND COLLECTION SYSTEM</title>
</head>
<body>
<h2>Update Client Profile</h2>
<%if(request.getParameter("action").equals("update")) {%>
	<form action="updateclientprofile.html" method="post">
			<p>Client ID: ${clientID}</p>
			<p>Name: <input type = "text" name="name" size="25" value="${client.name}"></p>
			<p>Address No: <input type = "text" name="addressNo" size="25" value="${client.addressNo}"></p>
			<p>Street: <input type = "text" name="street" size="25" value="${client.street}"></p>
			<p>City: <input type = "text" name="city" size="25" value="${client.city}"></p>
			<p>Zip Code: <input type = "text" name="zipCode" size="25" value="${client.zipCode}"></p>
			<p>Contact First Name: <input type = "text" name="contactFirstName" size="25" value="${client.contactFirstName}"></p>
			<p>Contact Middle Name: <input type = "text" name="contactMiddleName" size="25" value="${client.contactMiddleName}"></p>
			<p>Contact Last Name: <input type = "text" name="contactLastName" size="25" value="${client.contactLastName}"></p>
			<p>Agency: <input type = "text" name="agency" size="25" value="${client.agency}"></p>
			<p>Email Address: <input type = "text" name="email" size="25" value="${client.email}"></p>
			<input type="hidden" name="client_ID" value="${clientID}">
			<input type="submit" value="Update">
	</form>
	<%} %>
</body>
</html>