package tbcs.model;

public class imDetailsBean {
	
	private int detailsID;
	private int boDetailsID;
	private String status;
	
	private boScheduleBean boDetails;

	public int getDetailsID() {
		return detailsID;
	}

	public int getBoDetailsID() {
		return boDetailsID;
	}

	public String getStatus() {
		return status;
	}

	public boScheduleBean getBoDetails() {
		return boDetails;
	}

	public void setDetailsID(int detailsID) {
		this.detailsID = detailsID;
	}

	public void setBoDetailsID(int boDetailsID) {
		this.boDetailsID = boDetailsID;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setBoDetails(boScheduleBean boDetails) {
		this.boDetails = boDetails;
	}
	
	
}
