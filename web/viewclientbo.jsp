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
	
	<h2>Broadcast Order</h2>
	
	<form action="clientindex.jsp">
		<button type="submit" class="btn btn-default" style="width: 180px; margin-left: 40px;">Go back</button>
	</form>
	
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
    <thead>
	    <tr>
	      <th>Broadcast Order ID</th>
	      <th>Broadcast Order Date</th>
	      <th>MaterialID</th>
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
			while(listbroadcastorder.next()) {	
				//github testing
		%>
		
			<tr class="active">
					<td><%=listbroadcastorder.getInt("boID")%></td>
					<td><%=listbroadcastorder.getString("boDate")%></td>
					<td><%=listbroadcastorder.getInt("materialID") %>
					<td><%=listbroadcastorder.getInt("stationID")%></td>
					<td><%=listbroadcastorder.getInt("clientID")%></td>
					<td><%=listbroadcastorder.getInt("spotsPerDay")%></td>
					<td><%=listbroadcastorder.getString("startDate")%></td>
					<td><%=listbroadcastorder.getString("endDate")%></td>
					<td><%=listbroadcastorder.getString("startTime")%></td>
					<td><%=listbroadcastorder.getString("endTime")%></td>
					<td><%=listbroadcastorder.getBoolean("mon")%>, <%=listbroadcastorder.getBoolean("tue")%>, <%=listbroadcastorder.getBoolean("wed")%>, <%=listbroadcastorder.getBoolean("thu")%>, <%=listbroadcastorder.getBoolean("fri")%>, <%=listbroadcastorder.getBoolean("sat")%>, <%=listbroadcastorder.getBoolean("sun")%></td>
					<td><%=listbroadcastorder.getDouble("totalCost")%></td>
					<td>${listbroadcastorder.getString("status")}</td>
				</tr>
			<% } %>	
		
		</table>
</body>
</html>