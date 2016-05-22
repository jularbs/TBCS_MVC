package tbcs.model;

public class broadcastOrderBean {
	private int boID;
	private String boDate; //change to string
	private int stationID;
	private int clientID;
	private int spotsPerDay;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private Double totalCost;
	private int approvedBy;
	private String remarks;
	private int materialID;
	private String status;
	private boolean mon;
	private boolean tue;
	private boolean wed;
	private boolean thu;
	private boolean fri;
	private boolean sat;
	private boolean sun;
	private String additionalInstructions;
	
	public int getBoID() {
		return boID;
	}
	public String getBoDate() {
		return boDate;
	}
	public int getStationID() {
		return stationID;
	}

	public int getSpotsPerDay() {
		return spotsPerDay;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public int getApprovedBy() {
		return approvedBy;
	}
	public String getRemarks() {
		return remarks;
	}
	public boolean isMon() {
		return mon;
	}
	public boolean isTue() {
		return tue;
	}
	public boolean isWed() {
		return wed;
	}
	public boolean isThu() {
		return thu;
	}
	public boolean isFri() {
		return fri;
	}
	public boolean isSat() {
		return sat;
	}
	public boolean isSun() {
		return sun;
	}
	public void setBoID(int boID) {
		this.boID = boID;
	}
	public void setBoDate(String boDate) {
		this.boDate = boDate;
	}
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	public void setSpotsPerDay(int spotsPerDay) {
		this.spotsPerDay = spotsPerDay;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public void setApprovedBy(int approvedBy) {
		this.approvedBy = approvedBy;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setMon(boolean mon) {
		this.mon = mon;
	}
	public void setTue(boolean tue) {
		this.tue = tue;
	}
	public void setWed(boolean wed) {
		this.wed = wed;
	}
	public void setThu(boolean thu) {
		this.thu = thu;
	}
	public void setFri(boolean fri) {
		this.fri = fri;
	}
	public void setSat(boolean sat) {
		this.sat = sat;
	}
	public void setSun(boolean sun) {
		this.sun = sun;
	}
	public int getMaterialID() {
		return materialID;
	}
	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getAdditionalInstructions() {
		return additionalInstructions;
	}
	public void setAdditionalInstructions(String additionalInstructions) {
		this.additionalInstructions = additionalInstructions;
	}
}
