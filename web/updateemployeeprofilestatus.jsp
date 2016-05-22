<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRAFFIC, BILLING AND COLLECTION SYSTEM</title>
</head>
<body>
	<%
		if (request.getParameter("success").equals("true")) { %>
			<h2>Successfully Updated Record</h2>
			<br/>
			<p>First Name: ${updateEmployeeProfile.firstName}</p>
			<p>Middle Name: ${updateEmployeeProfile.middleName}</p>
			<p>Last Name: ${updateEmployeeProfile.lastName}</p>
			<p>Gender: ${updateEmployeeProfile.gender}</p>
			<p>Birthday: ${updateEmployeeProfile.birthday}</p>
			<p>Address No: ${updateEmployeeProfile.addressNo}</p>
			<p>Street: ${updateEmployeeProfile.street}</p>
			<p>City: ${updateEmployeeProfile.city}</p>
			<p>Zip Code: ${updateEmployeeProfile.zipCode}</p>
			<p>Email: ${updateEmployeeProfile.email}</p>
	<% } else { %>
	  <h1>Update Profile Failed</h1>		
	<% } %>
	
	<form action="employeeindex.jsp" method="post">
		<input type="submit" value="Go back to the main page">
	</form> 

</body>
</html>