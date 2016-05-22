<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="approvedclient" type="java.sql.ResultSet" scope="request"/>
     
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Approved Client</title>
</head>
<body>
	<center>
	<br/>
	
	<h2>Approved Client</h2>
	
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
	      <th>Status</th>
	      <th>Account</th>
	     </tr>
     </thead>
     
     	<% 
			while(approvedclient.next()) {	
		%>
		
			<tr class="active">
					<td><%=approvedclient.getInt("clientID")%></td>
					<td><%=approvedclient.getString("name")%></td>
					<td><%=approvedclient.getString("addressNo")%> <%=approvedclient.getString("street")%> <%=approvedclient.getString("city")%> <%=approvedclient.getString("zipCode")%></td>
					<td><%=approvedclient.getString("contactFirstName")%></td>
					<td><%=approvedclient.getString("contactMiddleName")%></td>
					<td><%=approvedclient.getString("contactLastName")%></td>
					<td><%=approvedclient.getString("agency")%></td>
					<td><%=approvedclient.getString("email")%></td>
					<td><%=approvedclient.getString("status")%></td>
					<%
						if(approvedclient.getString("status").equals("NO ACCOUNT")){
					%>
					<td>
						<form action="createaccount.html" method="post">
							<input type="hidden" class="form-control" name="ID" value="<%=approvedclient.getInt("clientID")%>" >
							<input type="hidden" class="form-control" name="email" value="<%=approvedclient.getString("email")%>" >
							<input type="hidden" class="form-control" name="type" value="2" >
							<button type="submit" class="btn btn-default" style="width: 180px; margin-left: 40px;">Create Account</button>
						</form>
					</td>
					<%}else { %>
					<td>
						<p>Account Created</p>
					</td>
					<%} %>
				</tr>
			<% } %>	
		
		</table>
				
	
</body>
</html>