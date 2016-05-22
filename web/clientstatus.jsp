<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Status</title>
</head>
<body>
	<% if (request.getParameter("success").equals("true")) { %>
	
	<legend>Your registration has been successful. We will keep in touch with you once your registration has been approved.</legend>
		
		<% } else { %>
	  <h1>Registration Add Failed</h1>		
	<% } %>
	<a href="index.jsp" class="btn btn-success">GO BACK</a>
		
</body>
</html>