package com.bookShop.businessObject;

import java.util.Date;

public class Book {
	private String name;
	private Date dateRelease;
	private Boolean onStorage;
	private Integer price;

	public Book(String name, Date dateRelease, Boolean onStorage, Integer price) {
		super();
		this.name = name;
		this.dateRelease = dateRelease;
		this.onStorage = onStorage;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDataRelease() {
		return dateRelease;
	}

	public void setDataRelease(Date dataRelease) {
		this.dateRelease = dataRelease;
	}

	public Boolean onStorage() {
		return onStorage;
	}

	public void onStorage(Boolean onStorage) {
		this.onStorage = onStorage;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}	
		
	public String toString() { 
		return "Book [Name=" + name + ", DataRelease=" + 
				dateRelease + ", OnStorage=" + onStorage + ", Price=" + price + "]";
	}
}
