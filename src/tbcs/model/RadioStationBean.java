package tbcs.model;

public class RadioStationBean {
	
	private int id;
	private String name;
	private String frequency;
	private String area;
	private Double rate;
	
	public String getName() {
		return name;
	}
	public String getFrequency() {
		return frequency;
	}
	public String getArea() {
		return area;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getId() {
		return id;
	}
	public Double getRate() {
		return rate;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	

}
