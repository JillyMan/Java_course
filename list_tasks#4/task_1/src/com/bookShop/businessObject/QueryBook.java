package com.bookShop.businessObject;

public class QueryBook {			
	
	private final Integer idBook;
	private Integer count;

	public QueryBook(Integer idBook, Integer count) {
		this.idBook = idBook;
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}
	
	public Integer getIdBook() { 
		return idBook;
	}
	
	public void addQuantity(int value) { 
		this.count = value;
	}
	
	public void delQuantity(int value) { 
		if(value <= count) { 
			count -= value;
		}
	}
	
	public String toString() {
		return "QueryOnBook [Count=" + count + ", IdBook=" + idBook + "]";
	}
	
}