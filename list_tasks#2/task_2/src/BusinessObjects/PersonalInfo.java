package BusinessObjects;

import java.util.Date;

public class PersonalInfo {
	private String name;
	private Address address;
	private Date birthData;
	private ContactInfo contactInfo;
	
	public PersonalInfo(String name, Address address, Date birthData, ContactInfo contactInfo) {
		super();
		this.name = name;
		this.address = address;
		this.birthData = birthData;
		this.contactInfo = contactInfo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Date getBirthData() {
		return birthData;
	}
	
	public void setBirthData(Date birthData) {
		this.birthData = birthData;
	}
	
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}	
}
