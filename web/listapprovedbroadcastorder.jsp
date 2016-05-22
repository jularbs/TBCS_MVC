<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="approvedbroadcastorder" type="java.sql.ResultSet" scope="request"/>
     
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Approved Broadcast Order</title>
</head>
<body>
	<center>
	<br/>
	
	<h2>Approved Broadcast Order</h2>
	
	<form action="ae.jsp">
		<button type="submit" class="btn btn-default" style="width: 180px; margin-left: 40px;">Go back</button>
	</form>
	
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
    <thead>
	    <tr>
	      <th>Broadcast Order ID</th>
	      <th>Broadcast Order Date</th>
	      <th>Station</th>
	      <th>Advertiser</th> <!-- Client -->
	      <th>Spots per Day</th>
	      <th>Start Date</th>
	      <th>End Date</th>
	      <th>Start Time</th>
	      <th>End Time</th>
	      <th>Air Days</th>
	      <th>Total Cost</th>
	      <th>Status</th>
	     </tr>
     </thead>
     
     	<% 
			while(approvedbroadcastorder.next()) {	
		%>
		
			<tr class="active">
					<td><%=approvedbroadcastorder.getInt("boID")%></td>
					<td><%=approvedbroadcastorder.getString("boDate")%></td>
					<td><%=approvedbroadcastorder.getInt("stationID")%></td>
					<td><%=approvedbroadcastorder.getInt("clientID")%></td>
					<td><%=approvedbroadcastorder.getInt("spotsPerDay")%></td>
					<td><%=approvedbroadcastorder.getString("startDate")%></td>
					<td><%=approvedbroadcastorder.getString("endDate")%></td>
					<td><%=approvedbroadcastorder.getString("startTime")%></td>
					<td><%=approvedbroadcastorder.getString("endTime")%></td>
					<td><%=approvedbroadcastorder.getBoolean("mon")%>, <%=approvedbroadcastorder.getBoolean("tue")%>, <%=approvedbroadcastorder.getBoolean("wed")%>, <%=approvedbroadcastorder.getBoolean("thu")%>, <%=approvedbroadcastorder.getBoolean("fri")%>, <%=approvedbroadcastorder.getBoolean("sat")%>, <%=approvedbroadcastorder.getBoolean("sun")%></td>
					<td><%=approvedbroadcastorder.getDouble("totalCost")%></td>
			</tr>
		<% } %>	
		</table>
				
	
</body>
</html>