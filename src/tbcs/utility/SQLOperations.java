package tbcs.utility;

import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import tbcs.model.AdvertisingMaterialBean;
import tbcs.model.ClientBean;
import tbcs.model.ContactDetails;
import tbcs.model.EmployeeBean;
import tbcs.model.accountBean;
import tbcs.model.broadcastOrderBean;

public class SQLOperations implements SQLCommands {
	
	private static Connection connection;
	
	private static Connection getConnection() {
		try {
			DataSource dataSource = (DataSource)InitialContext.doLookup(DS_SOURCE);
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static Connection getConnectionInstance() {
		return (connection!=null)?connection:getConnection();
	}
	
	//LOGIN
	public static accountBean authorizeAccount(accountBean account){
		int accountType = -1;
		
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(AUTHORIZE);
			pstmt.setString(1, account.getUsername());
			pstmt.setString(2, account.getPassword());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()){
				account.setAccountType(rs.getInt(1));
				account.setAccountID(rs.getInt(2));
			}else account.setAccountType(-1);
		}catch(SQLException sqle){
			System.out.println("SQLException - getAccountType: " + sqle.getMessage());
		}
		
		return account;
	}
	
	public static ClientBean loginClient(accountBean account){
		int clientID = -1;
		ClientBean client = new ClientBean();
		
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(LOGIN_CLIENT);
			pstmt.setInt(1, account.getAccountID());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				clientID = rs.getInt(1);
			}
			
			client = SQLOperations.searchClient(clientID);
			
		}catch(SQLException sqle){
			System.out.println("SQLException - getAccountType: " + sqle.getMessage());
		}
		
		return client;
	}
	
	public static EmployeeBean loginEmployee(accountBean account){
		int employeeID = -1;
		EmployeeBean employee = new EmployeeBean();
		
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(LOGIN_EMPLOYEE);
			pstmt.setInt(1, account.getAccountID());
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				employeeID = rs.getInt(1);
				System.out.println(employeeID);
			}
			
			employee = SQLOperations.searchEmployee(employeeID);
			
		}catch(SQLException sqle){
			System.out.println("SQLException - getAccountType: " + sqle.getMessage());
		}
		
		return employee;
	}
	
	//REGISTER
	public static Boolean checkIfClientExists(ClientBean client){
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(CHECK_CLIENTEXIST);
			
			pstmt.setString(1, client.getEmail());
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return false;
			else return true;
			
		}catch(SQLException slqe){
			System.out.println("SQLException - checkIfClientExists: " + slqe.getMessage());
		}
		
		return true;
	}
	
	public static String forgotPassword(int accountID){
		String newPassword = functions.createPassword(10);
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(FORGOT_PASSWORD);
			pstmt.setString(1, newPassword);
			pstmt.setInt(2, accountID);
			
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("SQLException - forgotPassword: " + e.getMessage());
		}
		
		return newPassword;
	}
	
	public static int getAccountID(String email){
		int accountID = -1;
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(GET_ID_WITH_EMAIL);
			pstmt.setString(1, email);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs != null && rs.next()){
				accountID = rs.getInt(1);
			}else return accountID;
		}catch(SQLException e){
			System.out.println("Exception on getAccountID: " + e.getMessage());
		}
		
		return accountID;
	}
	
	//CLIENT
	
	public static Boolean createAccount(accountBean account, int id, int type){
		try{
			
			int accountID = -1;
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(ADD_ACCOUNT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, account.getUsername());
			pstmt.setString(2, account.getPassword());
			pstmt.setInt(3, type);
			pstmt.setString(4, "Activated");
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
	        
			if(type == 1){
				//set employee accountID
				if(rs != null && rs.next()){
			        accountID = rs.getInt(1);
			        System.out.println("accountID: " + accountID + " EMPLOYEEID: " + id);
			        pstmt = getConnectionInstance().prepareStatement(SET_EMPLOYEEACCOUNT);
			        pstmt.setInt(1, accountID);
			        pstmt.setInt(2, id);
			        pstmt.executeUpdate();
		        }	
			}else if(type == 2){
				//set client accountID
		        if(rs != null && rs.next()){
			        accountID = rs.getInt(1);
			        System.out.println("accountID: " + accountID + " clientID: " + id);
			        pstmt = getConnectionInstance().prepareStatement(SET_CLIENTACCOUNT);
			        pstmt.setInt(1, accountID);
			        pstmt.setInt(2, id);
			        pstmt.executeUpdate();
		        }	
			}

	        
	        
			
		}catch(SQLException e){
			System.out.println("SQLException - createClientAccount: " + e.getMessage());
			return false;
		}
		
		return true;
	}
	
	
	public static boolean addClient(ClientBean client) {
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(REGISTER_CLIENT);
			pstmt.setString(1, client.getName());
			pstmt.setString(2, client.getAddressNo());
			pstmt.setString(3, client.getStreet());
			pstmt.setString(4, client.getCity());
			pstmt.setString(5, client.getZipCode());
			pstmt.setString(6, client.getContactFirstName());
			pstmt.setString(7, client.getContactMiddleName());
			pstmt.setString(8, client.getContactLastName());
			pstmt.setBoolean(9, client.isAgency());
			pstmt.setString(10, client.getEmail());
			pstmt.executeUpdate();
		}catch(SQLException sqle) {
			System.out.println("SQLException - addClient: " + sqle.getMessage());
			return false;
		}
		return true;
	}
	
	public static void addContact (ContactDetails contact)
	{
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(REGISTER_CONTACT);
			pstmt.setString(1, contact.getContact_details());
			pstmt.setInt(2, contact.getClientID());
			pstmt.executeUpdate();
		}catch(SQLException sqle) {
			System.out.println("SQLException - addContact: " + sqle.getMessage());
			
		}
		
	}
	
	public static int searchClientByEmail (String email)
	{
		int id = 0;
		ResultSet rs = null;
		try
		{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(SEARCH_CLIENTBYEMAIL);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				id = rs.getInt("clientID");
			}
		}
		catch(SQLException sqle) {
			System.out.println("SQLException - addContact: " + sqle.getMessage());
			
		}
		
		return id;
		
	}
	
	public static ResultSet getAllClient() throws SQLException {
		ResultSet rs = null;
		
		Statement stmt = getConnectionInstance().createStatement();
		rs = stmt.executeQuery(VIEW_CLIENT);
		
		return rs;
	}
	
	public static synchronized int deleteClient(int clientID) throws SQLException {
		int updated = 0;

		try {
			getConnectionInstance().setAutoCommit(false);
	        PreparedStatement pstmt = getConnectionInstance().prepareStatement(DELETE_CLIENT);
	        pstmt.setInt(1, clientID);             
	        updated  = pstmt.executeUpdate();
	        getConnectionInstance().commit();
		} catch (SQLException sqle) {
			System.out.println("SQLException - deleteClient: " + sqle.getMessage());
			
			try {
				getConnectionInstance().rollback();
			} catch (SQLException sql) {
				System.err.println("Error on Delete Connection Rollback - " + sql.getMessage());
				throw new SQLException("Error on Delete Connection Rollback - " + sql.getMessage());
			}
			return updated; 
		}	
		return updated;
	}
	
	public static ClientBean searchClient(int clientID) throws SQLException{
		ClientBean client = new ClientBean();
		Connection connection = getConnectionInstance();
		
		try {
	        PreparedStatement pstmt = connection.prepareStatement(SEARCH_CLIENT);
	        pstmt.setInt(1, clientID);  
	       
	        ResultSet rs  = pstmt.executeQuery();
	        
	        while(rs.next()) {
	        	client.setId(rs.getInt("clientID"));
	        	client.setName(rs.getString("name"));
	        	client.setAddressNo(rs.getString("addressNo"));
	        	client.setStreet(rs.getString("street"));
	        	client.setCity(rs.getString("city"));
	        	client.setZipCode(rs.getString("zipCode"));
	        	client.setContactFirstName(rs.getString("contactFirstName"));
	        	client.setContactMiddleName(rs.getString("contactMiddleName"));
	        	client.setContactLastName(rs.getString("contactLastName"));
	        	client.setAgency(rs.getBoolean("agency"));
	        	client.setEmail(rs.getString("email"));
	        	client.setStatus(rs.getString("status"));
	      }
		} catch (SQLException sqle) {
			System.out.println("SQLException - searchClient: " + sqle.getMessage());
			throw new SQLException("SQLException - searchClient: " + sqle.getMessage());
		}	
		return client;
	}
	
	public static int updateClient(int clientID) throws SQLException{
		int updated = 0;
		Connection connection = getConnectionInstance();
		
		try {
			connection.setAutoCommit(false);
	        PreparedStatement pstmt = connection.prepareStatement(UPDATE_CLIENT);
	        
	        pstmt.setInt(1, clientID);
	        updated = pstmt.executeUpdate();   
	        connection.commit();
		} catch (SQLException sqle) {
			String message = "";
			message = sqle.getMessage();
			System.out.println("SQLException - updateClient: " + sqle.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException sql) {
				System.err.println("Error on Update Connection Rollback - " + sql.getMessage());
			}
			throw new SQLException("Error on Update Connection Rollback - " + message);
			
		}	
		return updated;
	} 
	
	public static ResultSet getAllApprovedClient() throws SQLException{
		ResultSet rs = null;
		
			Statement stmt = getConnectionInstance().createStatement();
			rs = stmt.executeQuery(VIEW_APPROVED_CLIENT);  
		
		return rs;
	}
	
	//new
	//View Client Profile
	public static ResultSet viewClientProfile(int clientID) throws SQLException{
		ResultSet rs = null;
		Connection connection = getConnectionInstance();
		try {
	        PreparedStatement pstmt = connection.prepareStatement(VIEW_CLIENT_PROFILE);
	        pstmt.setInt(1, clientID);
	        rs  = pstmt.executeQuery();	      
		} catch (SQLException sqle) {
			System.out.println("SQLException - viewClientProfile: " + sqle.getMessage());
				throw new SQLException ("SQLException - viewClientProfile: " + sqle.getMessage());
		}	
			return rs;        
	}
	
	//Update Client Profile Update
	public static int updateClientProfile(ClientBean client, int clientID) throws SQLException{
		int updated = 0;
		Connection connection = getConnectionInstance();
		
		try {
			connection.setAutoCommit(false);
	        PreparedStatement pstmt = 
	        	connection.prepareStatement(UPDATE_CLIENT_PROFILE);
	        pstmt.setString(1, client.getName()); 
	        pstmt.setString(2, client.getAddressNo());
	        pstmt.setString(3, client.getStreet());
	        pstmt.setString(4, client.getCity()); 
	        pstmt.setString(5, client.getZipCode()); 
	        pstmt.setString(6, client.getContactFirstName());
	        pstmt.setString(7, client.getContactMiddleName());
	        pstmt.setString(8, client.getContactLastName());
	        pstmt.setBoolean(9, client.isAgency());
	        pstmt.setString(10, client.getEmail());
	        pstmt.setInt(12, clientID); 
	        updated = pstmt.executeUpdate();   
	        connection.commit();
		} catch (SQLException sqle) {
			String message = "";
			message = sqle.getMessage();
			System.out.println("SQLException - updateClientProfile: " 
				+ sqle.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException sql) {
				System.err.println("Error on Update Connection Rollback - " 
					+ sql.getMessage());
			}
			throw new SQLException("Error on Update Connection Rollback - " + message);
			
		}	
		return updated;
	}
	
	//Search Client Profile
	public static ClientBean searchClientProfile(int clientID) throws SQLException{
		ClientBean client = new ClientBean();
		Connection connection = getConnectionInstance();
		try {
	        PreparedStatement pstmt = 
	        	connection.prepareStatement(SEARCH_CLIENT_PROFILE);
	        pstmt.setInt(1, clientID);             
	        ResultSet rs  = pstmt.executeQuery();
	        
	        while(rs.next()) {	
	        	client.setName(rs.getString("name"));
	        	client.setAddressNo(rs.getString("addressNo"));
	        	client.setStreet(rs.getString("street"));
	        	client.setCity(rs.getString("city"));
	        	client.setZipCode(rs.getString("zipCode"));
	        	client.setContactFirstName(rs.getString("contactFirstName"));
	        	client.setContactMiddleName(rs.getString("contactMiddleName"));
	        	client.setContactLastName(rs.getString("contactLastName"));
	        	client.setAgency(rs.getBoolean("agency"));
	        	client.setEmail(rs.getString("email"));
	        	
	        	
	      }
		} catch (SQLException sqle) {
			System.out.println("SQLException - searchClientProfile: " 
					+ sqle.getMessage());
			throw new SQLException("SQLException - searchClientProfile: " 
					+ sqle.getMessage());
		}	
		return client;
	}
	
	//EMPLOYEE
	public static int addEmployee(EmployeeBean employee) {
		int employeeID = -1;
		try{
			
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(REGISTER_EMPLOYEE, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getMiddleName());
			pstmt.setString(3, employee.getLastName());
			pstmt.setString(4, employee.getGender());
			pstmt.setString(5, employee.getBirthday());
			pstmt.setString(6, employee.getAddressNo());
			pstmt.setString(7, employee.getStreet());
			pstmt.setString(8, employee.getCity());
			pstmt.setString(9, employee.getZipCode());
			pstmt.setString(10, employee.getEmail());
			//stationid
			pstmt.setInt(11, employee.getStationID());
			//positionid
			pstmt.setInt(12, employee.getPositionID());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs != null && rs.next()){
				employeeID = rs.getInt(1);
			}
			
		}catch(SQLException sqle) {
			System.out.println("SQLException - addEmployee: " + sqle.getMessage());
			return -1;
		}
		return employeeID;
	}
	
	public static EmployeeBean searchEmployee(int employeeID){
		EmployeeBean employee = new EmployeeBean();
		Connection connection = getConnectionInstance();
		
		try {
	        PreparedStatement pstmt = connection.prepareStatement(SEARCH_EMPLOYEE);
	        pstmt.setInt(1, employeeID);  
	       
	        ResultSet rs  = pstmt.executeQuery();
	        
	        while(rs.next()) {	
	        	employee.setId(Integer.parseInt(rs.getString("employeeID")));
	        	employee.setFirstName("firstName");
	        	employee.setMiddleName("middleName");
	        	employee.setLastName("lastName");
	        	employee.setGender("gender");
	        	employee.setBirthday("birthday");
	        	employee.setAddressNo(rs.getString("addressNo"));
	        	employee.setStreet(rs.getString("street"));
	        	employee.setCity(rs.getString("city"));
	        	employee.setZipCode(rs.getString("zipCode"));
	        	employee.setEmail(rs.getString("email")); 	
	        	employee.setPositionName(rs.getString("position"));
	        	employee.setStationName(rs.getString("station"));
	      }
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("SQLException - searchemployee: " + sqle.getMessage());
		}	
		return employee;
	}
	
	//BROADCASTORDER
	public static Boolean createBroadcastOrder(broadcastOrderBean boBean){ //test and not yet final
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(CREATE_BO);
			pstmt.setInt(1, boBean.getClientID());
			pstmt.setInt(2, boBean.getSpotsPerDay());
			pstmt.setString(3, boBean.getStartDate());
			pstmt.setString(4, boBean.getEndDate());
			pstmt.setString(5, boBean.getStartTime());
			pstmt.setString(6, boBean.getEndTime());
			pstmt.setBoolean(7, boBean.isMon());
			pstmt.setBoolean(8, boBean.isTue());
			pstmt.setBoolean(9, boBean.isWed());
			pstmt.setBoolean(10, boBean.isThu());
			pstmt.setBoolean(11, boBean.isFri());
			pstmt.setBoolean(12, boBean.isSat());
			pstmt.setBoolean(13, boBean.isSun());
			pstmt.setString(14, "Pending");
			pstmt.setString(15, boBean.getAdditionalInstructions());
			pstmt.setInt(16, boBean.getStationID());
			pstmt.setInt(17, boBean.getMaterialID());
			pstmt.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("SQLException on createBroadcastOrder: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	//INSERT ROW FOR ADVERTISEMENT MATERIAL TABLE
	public static int uploadAdvertisingMaterial(AdvertisingMaterialBean amAdvertisingMaterialBean){
		int id = -1;
		
		try{
			PreparedStatement ps = getConnectionInstance().prepareStatement(INSERT_ADVERTISEMENTMATERIAL, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, amAdvertisingMaterialBean.getName());
			ps.setString(2, amAdvertisingMaterialBean.getProduct());
			ps.setString(3, amAdvertisingMaterialBean.getVersion());
			ps.setString(4, amAdvertisingMaterialBean.getLink());
			ps.setString(5, amAdvertisingMaterialBean.getTagline());
			ps.setInt(6, amAdvertisingMaterialBean.getClientID());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				id = rs.getInt(1);
			}
			
		}catch(SQLException sqlException){
			System.out.println("SQLException on uploadAdvertisingMaterialBean(): " + sqlException.getMessage());
		}
		
		return id;
	}
	
	//DROPDOWNS
	public static ResultSet ddAdvertisingMaterial(int clientID){
		ResultSet rSet= null;
		
		try{
			PreparedStatement ps = getConnectionInstance().prepareStatement(DD_ADMATERIAL);
			ps.setInt(1, clientID);
			
			rSet = ps.executeQuery();
			if(rSet.next()){
				System.out.println("ad materials from clientID: " + clientID + " retrieved");
			}else System.out.println("no Ad material for clientID " + clientID);
		}catch(SQLException sqle){
			System.out.println("SQLException on ddAdvertisingMaterial(): " + sqle.getMessage());
		}
		
		return rSet;
	}
	public static ResultSet ddRadioStations(){
		ResultSet rs = null;
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(DD_RADIOSTATIONS);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				System.out.println("Radio stations retrieved");
			}else System.out.println("No radio stations found.");
			
			}catch(SQLException sql){
				System.out.println("SQLException - ddRadioStations(): " + sql.getMessage());
			}
		
		return rs;
	}
	
	public static ResultSet ddPositions(){
		ResultSet rs = null;
		
		try{
			PreparedStatement ps = getConnectionInstance().prepareStatement(DD_POSITIONS);
			
			rs = ps.executeQuery();
			
			if(rs.next()){
					System.out.println("Positions retrieved");
			}else System.out.println("No Positions found");
			
		}catch(SQLException sqlException){
			System.out.println("SQLException - ddPositions(): " + sqlException.getMessage());
		}
		
		return rs;
	}
	
}
