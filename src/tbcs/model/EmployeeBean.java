package tbcs.model;

public class EmployeeBean {
	
	private int id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String birthday;
	private String addressNo;
	private String street;
	private String city;
	private String zipCode;
	private String email;
	private int positionID;
	private int stationID;
	
	//foreign keys
	private String positionName;
	private String stationName;
	
	public EmployeeBean() {}
	
	public EmployeeBean(String firstName, String middleName, String lastName, String gender, String birthday, String addressNo, String street, String city, String zipCode, String email) {
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setGender(gender);
		setBirthday(birthday);
		setAddressNo(addressNo);
		setStreet(street);
		setCity(city);
		setZipCode(zipCode);
		setEmail(email);
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	//foreign keys
	public String getPositionName() {
		return positionName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}
