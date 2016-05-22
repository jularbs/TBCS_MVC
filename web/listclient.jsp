<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="listclient" type="java.sql.ResultSet" scope="request"/>
     
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Client for Approval</title>
</head>
<body>
	<center>
	<br/>
	
	<h2>Client record for Approval</h2>
	
	<form action="admin.jsp">
		<button type="submit" class="btn btn-default" style="width: 180px; margin-left: 40px;">Go back to profile</button>
	</form>
	
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
    <thead>
	    <tr>
	      <th>ID</th>
	      <th>Company Name</th>
	      <th>Address</th>
	      <th>Firstname</th>
	      <th>Middlename</th>
	      <th>Lastname</th>
	      <th>Agency</th>
	      <th>Email</th>
	      <th>STATUS</th>
	     </tr>
     </thead>
     
     	<% 
			while(listclient.next()) {	
		%>
		
			<tr class="active">
					<td><%=listclient.getInt("clientID")%></td>
					<td><%=listclient.getString("name")%></td>
					<td><%=listclient.getString("addressNo")%> <%=listclient.getString("street")%> <%=listclient.getString("city")%> <%=listclient.getString("zipCode")%></td>
					<td><%=listclient.getString("contactFirstName")%></td>
					<td><%=listclient.getString("contactMiddleName")%></td>
					<td><%=listclient.getString("contactLastName")%></td>
					<td><%=listclient.getString("agency")%></td>
					<td><%=listclient.getString("email")%></td>
					<td align="center">
					  <a href="updateclient.html?clientID=<%=listclient.getInt("clientID")%>&action=update">
					  APPROVE
					  </a>
					</td>						
					<td align="center">
					  <a href="clientmaintenance.html?clientID=<%=listclient.getInt("clientID")%>&action=delete" 
					  	onclick="if(confirm('Do you want to continue disapprove this record?')){ return true;} else{return false;}">
				   		DISAPPROVE
				   </a>
					</td>
				</tr>
			<% } %>	
		
		</table>
</body>
</html>