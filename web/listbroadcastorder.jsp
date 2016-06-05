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
	
	<h2>Broadcast Order record for Approval</h2>
	
	<form action="ae.jsp">
		<button type="submit" class="btn btn-default" style="width: 180px; margin-left: 40px;">Go back</button>
	</form>
	
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
    <thead>
	    <tr>
			<th>Client</th>
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
					<td><%=listbroadcastorder.getString("clientName")%></td>
					<td><%=listbroadcastorder.getString("materialName")%></td>
					<td><%=listbroadcastorder.getString("startDate")%></td>
					<td><%=listbroadcastorder.getString("endDate")%></td>
					<td><%=listbroadcastorder.getString("status")%></td>
					<td><a href='boview?id=<%= listbroadcastorder.getInt("boID")%>'>VIEW</a></td>
			</tr>
		<%}%>	
		
		</table>
</body>
</html>