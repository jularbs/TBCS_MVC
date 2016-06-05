package tbcs.model;

public class auditTrail {

	private int id;
	private int accountID;
	private int accountType;
	private String event;
	private String timestamp;
	
	public int getId() {
		return id;
	}
	public int getAccountID() {
		return accountID;
	}
	public int getAccountType() {
		return accountType;
	}
	public String getEvent() {
		return event;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
