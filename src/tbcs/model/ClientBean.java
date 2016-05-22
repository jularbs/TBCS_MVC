package tbcs.model;

public class ClientBean {
	
	private int id;
	private String name;
	private String addressNo;
	private String street;
	private String city;
	private String zipCode;
	private String contactFirstName;
	private String contactMiddleName;
	private String contactLastName;
	private boolean agency;
	private String email;
	private String status;
	
	
	public ClientBean () {}
	
	public ClientBean (String name, String addressNo, String street, String city, String zipCode, String contactFirstName, String contactMiddleName, String contactLastName, boolean agency, String email, String status) {
		setName(name);
		setAddressNo(addressNo);
		setStreet(street);
		setCity(city);
		setZipCode(zipCode);
		setContactFirstName(contactFirstName);
		setContactMiddleName(contactMiddleName);
		setContactLastName(contactLastName);
		setAgency(agency);
		setEmail(email);
		setStatus(status);
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressNo() {
		return addressNo;
	}

	public void setAddressNo(String addressNo) {
		this.addressNo = addressNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getContactMiddleName() {
		return contactMiddleName;
	}

	public void setContactMiddleName(String contactMiddleName) {
		this.contactMiddleName = contactMiddleName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public boolean isAgency() {
		return agency;
	}

	public void setAgency(boolean agency) {
		this.agency = agency;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
