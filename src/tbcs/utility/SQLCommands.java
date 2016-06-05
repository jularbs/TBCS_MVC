package tbcs.utility;

public interface SQLCommands {
	
	String DS_SOURCE = "java:comp/env/jdbc/MBC_TBCSYSTEM";
	
	//LOGIN AND ACCOUNT
	String AUTHORIZE = "SELECT accountType, accountID FROM account WHERE username=? and password=?";
	String LOGIN_CLIENT = "SELECT clientID FROM client WHERE accountID = ?";
	String LOGIN_EMPLOYEE = "SELECT employeeID FROM employee WHERE accountID = ?";
	String ADD_ACCOUNT = "INSERT INTO account VALUES (?,?,?,?)";
	String SET_CLIENTACCOUNT = "UPDATE client SET accountID = ?, status = 'Approved' WHERE clientID = ?";
	String SET_EMPLOYEEACCOUNT = "UPDATE employee SET accountID = ? WHERE employeeID = ?";
	String FORGOT_PASSWORD = "UPDATE account SET password = ? WHERE accountID = ?";
	String GET_ID_WITH_EMAIL = "SELECT accountID FROM account WHERE username = ?";
	
	//REGISTER CLIENT
	String REGISTER_CLIENT = "INSERT INTO client(name, addressNo, street, city, zipCode, contactFirstName, contactMiddleName, contactLastName, agency, email, status) values(?,?,?,?,?,?,?,?,?,?,'Pending')";
	String REGISTER_CONTACT = "INSERT INTO CONTACT_DETAILS(contactDetails,ClientID) VALUES(?,?)";
	String VIEW_CLIENT = "SELECT * FROM client where status='Pending'";
	String VIEW_APPROVED_CLIENT = "SELECT * FROM client where status='NO ACCOUNT' OR status='Approved'";
	String DELETE_CLIENT = "DELETE FROM client where clientID=?";
	String SEARCH_CLIENT = "SELECT * FROM client where clientID=?";
	String SEARCH_CLIENTBYEMAIL = "SELECT clientID FROM CLIENT WHERE EMAIL= ?";
	String UPDATE_CLIENT = "UPDATE client set status='NO ACCOUNT' where clientID=?";
	String CHECK_CLIENTEXIST = "SELECT * FROM client WHERE email = ?";
	
	//REGISTER EMPLOYEE
	String REGISTER_EMPLOYEE = "INSERT INTO employee(firstName, middleName, lastName, gender, birthday, addressNo, street, city, zipCode, email, stationID, positionID) values(?,?,?,?,?,?,?,?,?,?,?,?)"; //add positionID and stationID
	String SEARCH_EMPLOYEE = "SELECT employeeID, firstName, middleName, lastName, gender, birthday, addressNo, street, city, zipCode, email, accountID, p.name AS position, r.name AS station " +
							 "FROM employee " +
							 "LEFT JOIN list_position p ON employee.positionID = p.positionID "+
							 "LEFT JOIN radio_station r ON employee.stationID = r.stationID " +
							 "WHERE employeeID = ?";
	
	//CLIENT MANAGE PROFILE
	String VIEW_CLIENTPROFILE = "SELECT * FROM client WHERE clientID=?";
	String UPDATE_CLIENTPROFILE = "UPDATE client SET name = ?, addressNo=?, street=?, city=?, zipCode=?, contactFirstName=?, contactMiddleName=?, contactLastName=?, agency=?, email=? where clientID=?";
	String SEARCH_CLIENTPROFILE = "SELECT * FROM client WHERE clientID=?";
	
	//EMPLOYEE MANAGE PROFILE
	String VIEW_EMPLOYEEPROFILE = "SELECT * FROM employee WHERE employeeID=?";
	String UPDATE_EMPLOYEEPROFILE = "UPDATE employee SET firstName = ?, middleName = ?, lastName = ?,gender = ?,birthday = ?, addressNo=?, street=?, city=?, zipCode=?, email=? where employeeID=?";
	String SEARCH_EMPLOYEEPROFILE = "SELECT * FROM employee WHERE employeeID=?";
	
	//BROADCST ORDER
	String CREATE_BO = "INSERT INTO broadcast_order VALUES (GETDATE(),?,?,?,?,?,?,?,?)";
	String INSERT_BOSCHEDULE = "INSERT INTO bo_schedule VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	String INSERT_ADVERTISEMENTMATERIAL = "INSERT INTO advertising_material VALUES (?,?,?,?,?,?)";
	String VIEW_BROADCAST_ORDER = "SELECT boID, c.name as clientName, am.name as materialName, bo.startDate, bo.endDate, bo.status" +
								  "	FROM broadcast_order bo"+
								  "	LEFT JOIN client c ON bo.clientID = c.clientID"+
								  "	LEFT JOIN advertising_material am ON bo.materialID = am.materialID" +
								  "	WHERE bo.status = 'Pending'";
	String VIEW_APPROVED_BROADCAST_ORDER  = "SELECT * FROM broadcast_order where status='Approved'";
	String SEARCH_BROADCAST_ORDER = "SELECT * FROM client where clientID=?";
	String UPDATE_BROADCAST_ORDER = "UPDATE broadcast_order set status='Approved' where boID=?";
	String DELETE_BROADCAST_ORDER = "DELETE FROM broadcast_order where boID=?";
	String VIEW_BROADCAST_ORDER_CLIENT= "SELECT * FROM broadcast_order WHERE boID=?";
	String VIEW_CLIENTBO = "SELECT * FROM broadcast_order WHERE clientID = ?";
	String VIEW_ADVERTISINGMATERIAL = "SELECT * FROM advertising_material WHERE materialID = ?";
	String LIST_CLIENTBO = "SELECT * FROM broadcast_order WHERE clientID = ?";	
	
	//SCHEDULES
	String GET_BOSCHEDULE = "SELECT rs.name, bs.startTime, bs.endTime, bs.spotsPerDay, bs.mon, bs.tue, bs.wed, bs.thu, bs.fri, bs.sat, bs.sun, bs.cost " +
							"FROM bo_schedule bs " +
							"LEFT JOIN radio_station rs ON bs.stationID = rs.stationID " +
							"WHERE boID = ?";
	
	//DROPDOWNS
	String DD_RADIOSTATIONS = "SELECT stationID, name FROM radio_station";
	String DD_POSITIONS = "SELECT positionID, name FROM list_position";
	String DD_ADMATERIAL  = "SELECT materialID, name, version FROM advertising_material WHERE clientID = ?";
}
