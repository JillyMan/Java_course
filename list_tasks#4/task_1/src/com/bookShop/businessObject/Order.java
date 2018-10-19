package com.bookShop.businessObject;

import java.util.Date;
import java.util.List;

public class Order {	
	
	public enum Status {
		AWAITING("Awaiting", 1),
		COMPLEATE("Compleate", 2),
		CANCALED("Cancaled", 3);
		
		private final String name;
		private final int value;
		
		private Status(String str, int value) { 
			this.name = str;
			this.value = value;
		}
		
		public String toString() { 
			return this.name;
		}		
		
		public int toInt() { 
			return value;
		}
	}
	
	private Integer id;
	private Date dateOrder;
	private Date dateRelease;
	private List<Book> books;
	private Status status;
	
	public Order(Integer id, Date dateOrder, Date dateRelease, List<Book> books, Status status) {
		super();
		this.id = id;
		this.dateOrder = dateOrder;
		this.dateRelease = dateRelease;
		this.books = books;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getPrice() {
		Integer price = 0; //books.stream().mapToInt(a -> a.getPrice()).sum();
		for(Book b : books) { 
			price += b.getPrice();
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
				", Price=" + getPrice() + ", Books=" + books.toString() + ", Status=" + status.toString();
	}	
	
}
