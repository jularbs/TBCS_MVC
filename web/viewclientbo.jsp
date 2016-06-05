<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id="broadcastorder" type="tbcs.model.broadcastOrderBean" scope="request"/>
<jsp:useBean id="advertisingmaterial" type="tbcs.model.AdvertisingMaterialBean" scope="request"/>
<jsp:useBean id="schedules" type="java.sql.ResultSet" scope="request"/>
     
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Broadcast Order for Approval</title>
</head>
<body>
	<h2>Broadcast Order</h2>
	
	
	<form action="viewclientbo">
		<button type="submit" class="btn btn-default" style="width: 180px; margin-left: 40px;">Go back</button>
	</form>
	
	<p>Broadcast Order ID: ${broadcastorder.getBoID()} Broadcast Order Date: ${broadcastorder.getBoDate()}</p>
	<p>Advertisement Material: ${advertisingmaterial.getName()}</p>
	<p>Start Date: ${broadcastorder.getStartDate() } End Date: ${broadcastorder.getEndDate() }</p>
	<p>Status: ${broadcastorder.getStatus() }</p>
	<table border="1" width="100%" cellpadding="3" cellspacing="3">
    <thead>
	    <tr>
    		<th>Radio Station</th>
    		<th>Start Time</th>
    		<th>End Time</th>
    		<th>Spots per day</th>
    		<th>Days</th>
    		<th>Cost</th>
        </tr>
    </thead>
    <tbody>
    	<%while(schedules.next()){ %>
    	<tr>
    		<td>${schedules.getString("name")}</td>
    		<td>${schedules.getString("startTime")}</td>
    		<td>${schedules.getString("endTime")}</td>
    		<td>${schedules.getInt("spotsPerDay")}</td>
    		<td>${schedules.getString("mon")}${schedules.getString("tue")}${schedules.getString("wed")}${schedules.getString("thu")}${schedules.getString("fri")}${schedules.getString("sat")}${schedules.getString("sun")}</td>
    		<td>${schedules.getDouble("cost")}</td>
    		
    	</tr>
    	<%} %>
    </tbody>
    </table>
    
    <p>Additional Instructions: ${broadcastorder.getAdditionalInstruction() }</p>
    <p>Total Spots: ${broadcastorder.getTotalSpots() }</p>
    <p>Total Cost: ${broadcastorder.getTotalCost() }</p>
    <%if(broadcastorder.getStatus() == "Pending"){ %>
	<p>Approved By: ${broadcastorder.getApprovedBy() }</p>
	<%} %>
</body>
</html>