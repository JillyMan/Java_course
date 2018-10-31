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

	private DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
	
	public Book() {}
	
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
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((dateRelease == null) ? 0 : dateRelease.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj) 
			return true;			
		if (obj == null) 
			return false;			
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (dateRelease == null) {
			if (other.dateRelease != null)
				return false;
		} else if (!dateRelease.equals(other.dateRelease))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public String toString() { 
		return "Book [ID =" + id + ", Price=" + price + ", Author=" + author + ", Title=" + title 
				+ ", DataReceipt=" + dateFormat.format(dateReceipt) + ", DateRelease=" + dateFormat.format(dateRelease) + "]";
	}

}