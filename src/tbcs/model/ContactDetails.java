package tbcs.model;

public class ContactDetails {

	private int clientID;
	private String contact_details;
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getContact_details() {
		return contact_details;
	}
	public void setContact_details(String contact_details) {
		this.contact_details = contact_details;
	}
	
	public ContactDetails(){}
	
	public ContactDetails(int ClientID , String contact_Details)
	{
		setClientID(ClientID);
		setContact_details(contact_Details);
	}
}
