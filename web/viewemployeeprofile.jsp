<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
     <jsp:useBean id="viewEmployeeProfile" type="java.sql.ResultSet" scope="request"/>
     
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
			<td>Employee ID</td>
			<td>Name</td>
			<td>Gender</td>
			<td>Birthday</td>
			<td>Address</td>
			<td>Email</td>
		</tr>
			<% 
				while(viewEmployeeProfile.next()) {
			%>
				<tr>
					<td><%=viewEmployeeProfile.getInt("employeeID")%></td>
					<td><%=viewEmployeeProfile.getString("lastName")%>, <%=viewEmployeeProfile.getString("firstName")%> <%=viewEmployeeProfile.getString("middleName")%></td>
					<td><%=viewEmployeeProfile.getString("gender")%></td>
					<td><%=viewEmployeeProfile.getString("birthday")%></td>
					<td><%=viewEmployeeProfile.getString("addressNo")%> <%=viewEmployeeProfile.getString("street")%> <%=viewEmployeeProfile.getString("city")%> <%=viewEmployeeProfile.getString("zipCode")%></td>
					<td><%=viewEmployeeProfile.getString("email")%></td>
					<td align="center">
					  <a href="employeeprofilemaintenance.html?employeeID=<%=viewEmployeeProfile.getInt("employeeID")%>&action=update">Update Profile
					  </a>  
					</td>
					<td align="center">
					  <a href="employeeprofilemaintenance.html?employeeID=<%=viewEmployeeProfile.getInt("employeeID")%>&action=update">Change Password
					  </a>  
					</td>
					</tr>
<% } %>
</table>

</body>
</html>