package tbcs.model;

public class implementationMemoBean {
	private int imID;
	private String imDate;
	private int boID;
	private int employeeID;
	private String status;
	
	//foreign key
	private broadcastOrderBean bo;
	private EmployeeBean employee;

	public int getImID() {
		return imID;
	}

	public String getImDate() {
		return imDate;
	}

	public int getBoID() {
		return boID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public EmployeeBean getEmployee() {
		return employee;
	}

	public void setImID(int imID) {
		this.imID = imID;
	}

	public void setImDate(String imDate) {
		this.imDate = imDate;
	}

	public void setBoID(int boID) {
		this.boID = boID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public void setEmployee(EmployeeBean employee) {
		this.employee = employee;
	}

	public broadcastOrderBean getBo() {
		return bo;
	}

	public void setBo(broadcastOrderBean bo) {
		this.bo = bo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
