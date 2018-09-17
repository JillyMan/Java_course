package BusinessObjects;

public class Address {
	private int idHome;
	private int appartment;
	private String street;
	
	public Address(int idHome, int appartment, String street) {
		super();
		this.idHome = idHome;
		this.appartment = appartment;
		this.street = street;
	}
	public int getIdHome() {
		return idHome;
	}
	public void setIdHome(int idHome) {
		this.idHome = idHome;
	}
	public int getAppartment() {
		return appartment;
	}
	public void setAppartment(int appartment) {
		this.appartment = appartment;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
}
