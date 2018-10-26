package com.bookshop.core.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order implements Identified<Integer>{	
	
	public enum Status {
		AWAITING("AWAITING", 1),
		COMPLEATE("COMPLEATE", 2),
		CANCALED("CANCALED", 3);
		
		private final String name;
		private final Integer value;
		
		private Status(String str, Integer value) { 
			this.name = str;
			this.value = value;
		}
			
		public String toString() { 
			return this.name;
		}		
		
		public Integer toInt() { 
			return value;
		}
	}
	
	private Integer id;
	private Date dateOrder;
	private Date dateRelease;
	private List<Book> books;
	private Status status;
	
	public Order(Integer id, Date dateOrder, Date dateRelease, List<Book> books) { 
		this.id = id;
		this.dateOrder = dateOrder;
		this.dateRelease = dateRelease;
		this.books = books;		
	}

	public Order(Integer id, Date dateOrder, Date dateRelease, List<Book> books, Status status) {
		this(id, dateOrder, dateRelease, books);
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
	
	public int hashCode() { 
		return id.hashCode();
	}
	
	public boolean equals(Object o) { 
		boolean result = false;
		if(this.id == ((Order)o).getId()) { 
			result = true;
		}
		return result;
	}
	
	public String toString() { 
		DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");

		return "Order [ID=" + id + ", DataOrder=" + dateOrder + ", DateRelease=" + dateFormat.format(dateRelease) + 
				", Price=" + getPrice() + ", Books=" + books.toString() + ", Status=" + status.toString() + "]";
	}	
	
}
