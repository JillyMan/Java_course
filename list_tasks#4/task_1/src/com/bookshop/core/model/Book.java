package com.bookshop.core.model;

import java.util.Date;

public class Book {

	private Integer id;
	private Integer price;
	private Author author;
	private String title;
	private Date dateReceipt;
	private Date dateRelease;

	public Book(Integer id, Integer price, Author author, String title, Date dateReceipt, Date dateRelease) {
		this.id = id;
		this.title = title;
		this.dateReceipt = dateReceipt;
		this.dateRelease = dateRelease;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}	

	public Date getDateReceipt() {
		return dateReceipt;
	}

	public void setDateReceipt(Date dateReceipt) {
		this.dateReceipt = dateReceipt;
	}
		
	public String toString() { 
		return "Book [Name=" + name + ", DataRelease=" + 
				dateRelease + ", Price=" + price + "]";
	}

}
