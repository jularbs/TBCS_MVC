package tbcs.model;

public class accountBean {
	private int accountID;
	private String username;
	private String password;
	private int accountType;
	private String status;

	public int getAccountID() {
		return accountID;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getAccountType() {
		return accountType;
	}
	public String getStatus() {
		return status;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
