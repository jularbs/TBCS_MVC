package tbcs.model;

public class boScheduleBean {
	
	private int detailsID;
	private int boID;
	private int stationID;
	private String startTime;
	private String endTime;
	private int spotsPerDay;
	private boolean mon;
	private boolean tue;
	private boolean wed;
	private boolean thu;
	private boolean fri;
	private boolean sat;
	private boolean sun;
	private double cost;
	private String status;
	
	//foreign keys
	
	private broadcastOrderBean bo;
	private RadioStationBean radioStation;

	public int getDetailsID() {
		return detailsID;
	}
	public int getBoID() {
		return boID;
	}
	public int getStationID() {
		return stationID;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public int getSpotsPerDay() {
		return spotsPerDay;
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
	public double getCost() {
		return cost;
	}
	public String getStatus() {
		return status;
	}
	public broadcastOrderBean getBo() {
		return bo;
	}
	public RadioStationBean getRadioStation() {
		return radioStation;
	}
	public void setDetailsID(int detailsID) {
		this.detailsID = detailsID;
	}
	public void setBoID(int boID) {
		this.boID = boID;
	}
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setSpotsPerDay(int spotsPerDay) {
		this.spotsPerDay = spotsPerDay;
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
	public void setCost(double cost) {
		this.cost = cost;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setBo(broadcastOrderBean bo) {
		this.bo = bo;
	}
	public void setRadioStation(RadioStationBean radioStation) {
		this.radioStation = radioStation;
	}

}
