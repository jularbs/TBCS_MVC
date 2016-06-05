<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="listbroadcastorder" type="java.sql.ResultSet" scope="request"/>
     
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Broadcast Order for Approval</title>
</head>
<body>
	<center>
	<br/>
	
	<h2>Your Broadcast Orders</h2>
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
    <thead>
	    <tr>
			<th>Advertisement Material</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>status</th>
			<th></th>
	    </tr>
     </thead>
     
     	<% 
			while(listbroadcastorder.next()) {	
		%>
		
			<tr class="active">
					<td><%=listbroadcastorder.getString("materialID")%></td>
					<td><%=listbroadcastorder.getString("startDate")%></td>
					<td><%=listbroadcastorder.getString("endDate")%></td>
					<td><%=listbroadcastorder.getString("status")%></td>
					<td><a href='viewclientbo?id=<%= listbroadcastorder.getInt("boID")%>'>VIEW</a></td>
			</tr>
		<%}%>	
		
		</table>
</body>
</html>