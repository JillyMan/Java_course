package com.bookShop.businessObject;

import java.util.Date;
import java.util.List;

public class Order {	
	
	enum Status {
		AWAITING("Awaiting"),
		COMPLEATE("Compleate"), 
		CANCALED("Cancaled");
		
		private final String name;
		
		private Status(String str) { 
			this.name = str;
		}
		
		public String toString() { 
			return this.name;
		}		
	}
	
	private Date dateOrder;
	private Date dateRelease;
	private Integer price;
	private List<Book> books;
	private Status status;
	
	public Order(Date dateOrder, Date dateRelease, List<Book> books, Status status) {
		super();
		this.dateOrder = dateOrder;
		this.dateRelease = dateRelease;
		this.books = books;
		this.status = status;
	}
	
	public Date getDateOrder() {
		return dateOrder;
	}
	
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	
	public Date getDateRelease() {
		return dateRelease;
	}
	
	public void setDateRelease(Date dateRelease) {
		this.dateRelease = dateRelease;
	}
	
	//TODO: it's useless!!! 
	public Integer getPrice() {
		for(int i = 0; i < books.size(); ++i) { 
			price += books.get(i).getPrice();
		}
		
		return price;
	}
		
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}	
	
	public String toString() { 
		return "Order [DataOrder=" + dateOrder + ", DateRelease=" + dateRelease + 
				", Price=" + price + ", Books=" + books.toString() + ", Status=" + status.toString();
	}	
}
