package tbcs.utility;

import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import tbcs.model.AdvertisingMaterialBean;
import tbcs.model.ClientBean;
import tbcs.model.ContactDetails;
import tbcs.model.EmployeeBean;
import tbcs.model.accountBean;
import tbcs.model.boScheduleBean;
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
	        PreparedStatement pstmt = connection.prepareStatement(VIEW_CLIENTPROFILE);
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
	        	connection.prepareStatement(UPDATE_CLIENTPROFILE);
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
	        pstmt.setInt(11, clientID); 
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
	        	connection.prepareStatement(SEARCH_CLIENTPROFILE);
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
	public static ResultSet viewEmployeeProfile(int employeeID) throws SQLException{
		ResultSet rs = null;
		Connection connection = getConnectionInstance();
		try {
	        PreparedStatement pstmt = connection.prepareStatement(VIEW_EMPLOYEEPROFILE);
	        pstmt.setInt(1, employeeID);
	        rs  = pstmt.executeQuery();	      
		} catch (SQLException sqle) {
			System.out.println("SQLException - viewEmployeeProfile: " + sqle.getMessage());
				throw new SQLException ("SQLException - viewEmployeeProfile: " + sqle.getMessage());
		}	
			return rs;        
	}
	
	
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
	        	employee.setFirstName(rs.getString("firstName"));
	        	employee.setMiddleName(rs.getString("middleName"));
	        	employee.setLastName(rs.getString("lastName"));
	        	employee.setGender(rs.getString("gender"));
	        	employee.setBirthday(rs.getString("birthday"));
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
	
	public static int updateEmployeeProfile(EmployeeBean employee, int employeeID) throws SQLException{
		int updated = 0;
		Connection connection = getConnectionInstance();
		
		try {
			connection.setAutoCommit(false);
	        PreparedStatement pstmt = 
	        	connection.prepareStatement(UPDATE_EMPLOYEEPROFILE);
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
	        pstmt.setInt(11, employeeID); 
	        updated = pstmt.executeUpdate();   
	        connection.commit();
		} catch (SQLException sqle) {
			String message = "";
			message = sqle.getMessage();
			System.out.println("SQLException - updateEmployeeProfile: " 
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
	
	public static EmployeeBean searchEmployeeProfile(int employeeID) throws SQLException{
		EmployeeBean employee = new EmployeeBean();
		Connection connection = getConnectionInstance();
		try {
	        PreparedStatement pstmt = 
	        	connection.prepareStatement(SEARCH_EMPLOYEEPROFILE);
	        pstmt.setInt(1, employeeID);             
	        ResultSet rs  = pstmt.executeQuery();
	        
	        while(rs.next()) {	
	        	employee.setFirstName(rs.getString("firstName"));
	        	employee.setMiddleName(rs.getString("middleName"));
	        	employee.setLastName(rs.getString("lastName"));
	        	employee.setGender(rs.getString("gender"));
	        	employee.setBirthday(rs.getString("birthday"));
	        	employee.setAddressNo(rs.getString("addressNo"));
	        	employee.setStreet(rs.getString("street"));
	        	employee.setCity(rs.getString("city"));
	        	employee.setZipCode(rs.getString("zipCode"));
	        	employee.setEmail(rs.getString("email"));
	        	
	        	
	      }
		} catch (SQLException sqle) {
			System.out.println("SQLException - searchEmployeeProfile: " 
					+ sqle.getMessage());
			throw new SQLException("SQLException - searchEmployeeProfile: " 
					+ sqle.getMessage());
		}	
		return employee;
	}
	
	
	//BROADCASTORDER
	public static ResultSet getClientBroadcastOrder(ClientBean cl){
		ResultSet rsResultSet = null;
		
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(VIEW_CLIENTBO);
			pstmt.setInt(1, cl.getId());
			
			rsResultSet = pstmt.executeQuery();
			
		}catch(SQLException sqle){
			System.out.println("error on getClientBroadcastOrder: " + sqle.getMessage());
		}
		
		return rsResultSet;
	}
	public static ResultSet getAllBroadcastOrder() throws SQLException {
		ResultSet rs = null;
		
		Statement stmt = getConnectionInstance().createStatement();
		rs = stmt.executeQuery(VIEW_BROADCAST_ORDER);
		
		return rs;
	}
	
	public static ResultSet getAllApprovedBroadcastOrder() throws SQLException{
		ResultSet rs = null;
		
			Statement stmt = getConnectionInstance().createStatement();
			rs = stmt.executeQuery(VIEW_APPROVED_BROADCAST_ORDER);  
		
		return rs;
	}
	
	public static int updateBroadcastOrder(int boID) throws SQLException{
		int updated = 0;
		Connection connection = getConnectionInstance();
		
		try {
			connection.setAutoCommit(false);
	        PreparedStatement pstmt = connection.prepareStatement(UPDATE_BROADCAST_ORDER);
	        
	        pstmt.setInt(1, boID);
	        updated = pstmt.executeUpdate();   
	        connection.commit();
		} catch (SQLException sqle) {
			String message = "";
			message = sqle.getMessage();
			System.out.println("SQLException - updateBroadcastOrder: " + sqle.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException sql) {
				System.err.println("Error on Update Connection Rollback - " + sql.getMessage());
			}
			throw new SQLException("Error on Update Connection Rollback - " + message);
			
		}	
		return updated;
	} 
		
	public static synchronized int deleteBroadcastOrder(int boID) throws SQLException {
		int updated = 0;

		try {
			getConnectionInstance().setAutoCommit(false);
	        PreparedStatement pstmt = getConnectionInstance().prepareStatement(DELETE_BROADCAST_ORDER);
	        pstmt.setInt(1, boID);             
	        updated  = pstmt.executeUpdate();
	        getConnectionInstance().commit();
		} catch (SQLException sqle) {
			System.out.println("SQLException - deleteBroadcastOrder: " + sqle.getMessage());
			
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
	
	public static broadcastOrderBean viewBroadcastOrder(int boID){
		ResultSet rs = null;
		Connection connection = getConnectionInstance();
		broadcastOrderBean bo = new broadcastOrderBean();
		
		try {
	        PreparedStatement pstmt = connection.prepareStatement(VIEW_BROADCAST_ORDER_CLIENT);
	        pstmt.setInt(1, boID);
	        System.out.println("boID: " + boID);
	        rs  = pstmt.executeQuery();	      
	        
	        if(rs.next()){
	        	bo.setBoID(boID);
		        bo.setBoDate(rs.getString("boDate"));
		        bo.setMaterialID(rs.getInt("materialID"));
		        bo.setClientID(rs.getInt("clientID"));
		        bo.setTotalCost(rs.getDouble("totalCost"));
		        bo.setAdditionalInstruction(rs.getString("additionalInstructions"));
		        bo.setStartDate(rs.getString("startDate"));
		        bo.setEndDate(rs.getString("endDate"));
		        bo.setTotalSpots(rs.getInt("totalSpots"));
		        bo.setStatus(rs.getString("status"));	
	        }
	    
		} catch (SQLException sqle) {
			System.out.println("SQLException - viewBroadcastOrder: " + sqle.getMessage());
		}
		
		return bo;
	}	
	
	public static int createBroadcastOrder(broadcastOrderBean bo){
		int id = -1;
		try{
			PreparedStatement ps = getConnectionInstance().prepareStatement(CREATE_BO, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bo.getMaterialID());
			ps.setInt(2, bo.getClientID());
			ps.setDouble(3, bo.getTotalCost());
			ps.setString(4, bo.getAdditionalInstruction());
			ps.setString(5, bo.getStartDate());
			ps.setString(6, bo.getEndDate());
			ps.setInt(7, bo.getTotalSpots());
			ps.setString(8, "Pending");
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next())
				id = rs.getInt(1);
			else return -1;
		}catch(SQLException sqle){
			System.out.println("Error on createBroadcastOrder: " + sqle.getMessage());
		}
		
		return id;
	}
	
	public static void insertBroadcastSchedule(boScheduleBean bd){
		try{
			PreparedStatement ps = getConnectionInstance().prepareStatement(INSERT_BOSCHEDULE);
			ps.setInt(1, bd.getStationID());
			ps.setString(2, bd.getStartTime());
			ps.setString(3, bd.getEndTime());
			ps.setInt(4, bd.getSpotsPerDay());
			ps.setBoolean(5, bd.isMon());
			ps.setBoolean(6, bd.isTue());
			ps.setBoolean(7, bd.isWed());
			ps.setBoolean(8, bd.isThu());
			ps.setBoolean(9, bd.isFri());
			ps.setBoolean(10, bd.isSat());
			ps.setBoolean(11, bd.isSun());
			ps.setDouble(12, bd.getCost());
			ps.setString(13, "Pending");
			ps.setInt(14, bd.getBoID());
			
			ps.executeUpdate();
		}catch(SQLException sqle){
			System.out.println("Error on insertBroadcastSchedule" + sqle.getMessage());
		}
	}
	
	public static ResultSet listClientBo(int clientID){
		ResultSet rs = null;
		
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(LIST_CLIENTBO);
			pstmt.setInt(1, clientID);
			
			rs = pstmt.executeQuery();
		}catch(SQLException e){
			System.out.println("Error on listClientBo: " + e.getMessage());
		}
		
		return rs;
	}
	//SCHEDULE
	
	public static ResultSet getSchedule(int boID){
		ResultSet rs = null;
		
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(GET_BOSCHEDULE);
			pstmt.setInt(1, boID);
			
			rs = pstmt.executeQuery();
			
		}catch(SQLException sqle){
			System.out.println("Error on getSchedule: " + sqle.getMessage());
		}
		
		return rs;
	}
	
	//ADVERTISEMENT MATERIAL
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
	
	public static AdvertisingMaterialBean getAdvertisingMaterial(int materialID){
		ResultSet rs = null;
		AdvertisingMaterialBean am = new AdvertisingMaterialBean();
		try{
			PreparedStatement pstmt = getConnectionInstance().prepareStatement(VIEW_ADVERTISINGMATERIAL);
			pstmt.setInt(1, materialID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				am.setId(materialID);
				am.setName(rs.getString("name"));
				am.setProduct(rs.getString("product"));
				am.setVersion(rs.getString("version"));
				am.setLink(rs.getString("link"));
				am.setTagline(rs.getString("tagline"));
				am.setClientID(rs.getInt("clientID"));	
			}
			
		}catch(SQLException sqle){
			System.out.println("Error on getAdvertisingMaterial: " + sqle.getMessage());
		}
		
		return am;
		
		
	}
	
	//DROPDOWNS
	public static ResultSet ddAdvertisingMaterial(int clientID){
		ResultSet rSet= null;
		
		try{
			PreparedStatement ps = getConnectionInstance().prepareStatement(DD_ADMATERIAL);
			ps.setInt(1, clientID);
			
			rSet = ps.executeQuery();
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
