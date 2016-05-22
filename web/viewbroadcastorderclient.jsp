<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <jsp:useBean id="viewbroadcastorderclient" type="java.sql.ResultSet" scope="request"/>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TRAFFIC, BILLING, AND COLLECTION SYSTEM</title>
</head>
<body>
<h1>BROADCAST ORDER</h1>
<table border="1" width="100%" cellpadding="3" cellspacing="3">
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
			<% 
				while(viewbroadcastorderclient.next()) {
			%>
				<tr>
					<td><%=viewbroadcastorderclient.getInt("boID")%></td>
					<td><%=viewbroadcastorderclient.getString("boDate")%></td>
					<td><%=viewbroadcastorderclient.getInt("stationID")%></td>
					<td><%=viewbroadcastorderclient.getInt("clientID")%></td>
					<td><%=viewbroadcastorderclient.getInt("spotsPerDay")%></td>
					<td><%=viewbroadcastorderclient.getDate("startDate")%></td>
					<td><%=viewbroadcastorderclient.getDate("endDate")%></td>
					<td><%=viewbroadcastorderclient.getTime("startTime")%></td>
					<td><%=viewbroadcastorderclient.getTime("endTime")%></td>
					<td><%=viewbroadcastorderclient.getTime("startTime")%></td>
					<td><%=viewbroadcastorderclient.getBoolean("mon")%>, <%=viewbroadcastorderclient.getBoolean("tue")%>, <%=viewbroadcastorderclient.getBoolean("wed")%>, <%=viewbroadcastorderclient.getBoolean("thu")%>, <%=viewbroadcastorderclient.getBoolean("fri")%>, <%=viewbroadcastorderclient.getBoolean("sat")%>, <%=viewbroadcastorderclient.getBoolean("sun")%></td>
					<td><%=viewbroadcastorderclient.getDouble("totalCost")%></td>
					</tr>
<% } %>
</table>

</body>
</html>