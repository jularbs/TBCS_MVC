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
			<p>Name: ${updateClientProfile.name}</p>
			<p>Address No: ${updateClientProfile.addressNo}</p>
			<p>Street: ${updateClientProfile.street}</p>
			<p>City: ${updateClientProfile.city}</p>
			<p>Zip Code: ${updateClientProfile.zipCode}</p>
			<p>Contact Person First Name: ${updateClientProfile.contactFirstName}</p>
			<p>Contact Person Middle Name: ${updateClientProfile.contactMiddleName}</p>
			<p>Contact Person Last Name: ${updateClientProfile.contactLastName}</p>
			<p>Agency: ${updateClientProfile.agency}</p> 
			<p>Email: ${updateClientProfile.email}</p>
			<p>Status: ${updateClientProfile.status}</p>
	<% } else { %>
	  <h1>Update Profile Failed</h1>		
	<% } %>
	
	<form action="searchclient.jsp" method="post">
		<input type="submit" value="SEARCH CLIENT">
	</form> 

</body>
</html>