<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <jsp:useBean id="viewClientProfile" type="java.sql.ResultSet" scope="request"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRAFFIC, BILLING, AND COLLECTION SYSTEM</title>
</head>
<body>
<h1>MANAGE PROFILE</h1>
<table border="1" width="100%" cellpadding="3" cellspacing="3">
		<tr>
			<td>Client ID</td>
			<td>Name</td>
			<td>Address</td>
			<td>Contact Person</td>
			<td>Agency</td>
			<td>Email</td>
		</tr>
			<% 
				while(viewClientProfile.next()) {
			%>
				<tr>
					<td><%=viewClientProfile.getInt("clientID")%></td>
					<td><%=viewClientProfile.getString("name")%></td>
					<td><%=viewClientProfile.getString("addressNo")%> <%=viewClientProfile.getString("street")%> <%=viewClientProfile.getString("city")%> <%=viewClientProfile.getString("zipCode")%></td>
					<td><%=viewClientProfile.getString("contactFirstName")%> <%=viewClientProfile.getString("contactMiddleName")%> <%=viewClientProfile.getString("contactLastName")%></td>
					<td><%=viewClientProfile.getString("agency")%></td>
					<td><%=viewClientProfile.getString("email")%></td>
					<td align="center">
					  <a href="clientprofilemaintenance.html?clientID=<%=viewClientProfile.getInt("clientID")%>&action=update">Update Profile
					  </a>  
					</td>
					<td align="center">
					  <a href="clientprofilemaintenance.html?clientID=<%=viewClientProfile.getInt("clientID")%>&action=update">Change Password
					  </a>  
					</td>
					</tr>
<% } %>
</table>

</body>
</html>