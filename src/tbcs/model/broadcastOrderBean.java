package tbcs.model;

public class broadcastOrderBean {
	
	private int boID;
	private String boDate;
	private int materialID;
	private int clientID;
	private double totalCost;
	private String additionalInstruction;
	private String startDate;
	private String endDate;
	private int totalSpots;
	private String status;
	private int approvedBy;
	//foreign keys
	
	private AdvertisingMaterialBean advertisementMaterial;
	private ClientBean client;
	private EmployeeBean employee;
	
	public int getBoID() {
		return boID;
	}
	public String getBoDate() {
		return boDate;
	}
	public int getMaterialID() {
		return materialID;
	}
	public int getClientID() {
		return clientID;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public String getAdditionalInstruction() {
		return additionalInstruction;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public int getTotalSpots() {
		return totalSpots;
	}
	public String getStatus() {
		return status;
	}
	public void setBoID(int boID) {
		this.boID = boID;
	}
	public void setBoDate(String boDate) {
		this.boDate = boDate;
	}
	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public void setAdditionalInstruction(String additionalInstruction) {
		this.additionalInstruction = additionalInstruction;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setTotalSpots(int totalSpots) {
		this.totalSpots = totalSpots;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public AdvertisingMaterialBean getAdvertisementMaterial() {
		return advertisementMaterial;
	}
	public ClientBean getClient() {
		return client;
	}
	public void setAdvertisementMaterial(
			AdvertisingMaterialBean advertisementMaterial) {
		this.advertisementMaterial = advertisementMaterial;
	}
	public void setClient(ClientBean client) {
		this.client = client;
	}
	public int getApprovedBy() {
		return approvedBy;
	}
	public EmployeeBean getEmployee() {
		return employee;
	}
	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}
	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}
	
	
}
