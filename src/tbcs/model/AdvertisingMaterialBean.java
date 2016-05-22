package tbcs.model;

public class AdvertisingMaterialBean {
	
	private int id;
	private String name;
	private String product;
	private String version;
	private String link;
	private String tagline;
	private int clientID;
	
	public String getName() {
		return name;
	}
	public String getProduct() {
		return product;
	}
	public String getVersion() {
		return version;
	}
	public String getLink() {
		return link;
	}
	public String getTagline() {
		return tagline;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	
	

}
