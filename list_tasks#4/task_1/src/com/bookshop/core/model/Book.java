package com.bookshop.core.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book implements Identified<Integer> {

	private Integer id;
	private Integer price;
	private Author author;
	private String title;
	private Date dateReceipt;
	private Date dateRelease;

	public Book(Integer id, Integer price, Author author, 
			String title, Date dateReceipt, Date dateRelease) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.dateReceipt = dateReceipt;
		this.dateRelease = dateRelease;
		this.price = price;
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateRelease() {
		return dateRelease;
	}

	public void setDateRelease(Date dateRelease) {
		this.dateRelease = dateRelease;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public boolean equals(Book book) { 
		boolean result = false;
		if(this.id == book.getId()) { 
			result = true;
		}
		return result;
	}
	
	public String toString() { 
		DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");

		return "Book [ID =" + id + ", Price=" + price + ", Author=" + author + ", Title=" + title 
				+ ", DataReceipt=" + dateFormat.format(dateReceipt) + ", DateRelease=" + dateFormat.format(dateRelease) + "]";
	}

}