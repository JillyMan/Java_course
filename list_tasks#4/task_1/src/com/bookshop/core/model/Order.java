package com.bookshop.core.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Order implements Identified<Integer>{	
	
	public enum Status {
		AWAITING("AWAITING", 1),
		COMPLETED("COMPLETED", 2),
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
	private Map<Book, Integer> booksCount;
	private Status status;
	private Integer price;
	
	private final DateFormat dateFormat = new SimpleDateFormat("d MMMM, yyyy");
	
	public Order() {}
	
	public Order(Integer id, Date dateOrder, Date dateRelease, Map<Book, Integer> books) {
		this.id = id;
		this.dateOrder = dateOrder;
		this.dateRelease = dateRelease;
		this.booksCount = books;
	}
	
	public Order(Integer id, Date dateOrder, Date dateRelease, Map<Book, Integer> books, Status status) {
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
		return booksCount.keySet().stream()
					.mapToInt(book -> book.getPrice())
					.sum();
	}
	
	public void setIdCountBooks(Map<Book, Integer> map) { 
		booksCount = map;
	}
	
	public Map<Book, Integer> getBooksCount() {
		return booksCount;
	}
	
	public Set<Book> getBooks() { 
		return booksCount.keySet();
	}
	
	public void addBooks(Book book, int count) {
		if(book == null || count <= 0) { 
			throw new IllegalArgumentException();
		}
		if(booksCount.containsKey(book)) { 
			booksCount.compute(book, (key, value) -> value + count);
		} else { 
			booksCount.put(book, 1);
		}
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOrder == null) ? 0 : dateOrder.hashCode());
		result = prime * result + ((dateRelease == null) ? 0 : dateRelease.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (dateOrder == null) {
			if (other.dateOrder != null)
				return false;
		} else if (!dateOrder.equals(other.dateOrder))
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
		if (status != other.status)
			return false;
		return true;
	}

	public String toString() {
		return "Order [ID=" + id + ", DataOrder=" + dateFormat.format(dateOrder) + ", DateRelease=" + dateFormat.format(dateRelease) + 
				", Price=" + getPrice() + ", IdCountBooks=" + booksCount.toString() + ", Status=" + status.toString() + "]";
	}	
	
}
