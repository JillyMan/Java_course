package com.bookshop.core.model;

public class RequestsBook implements Identified<Integer>{			
	
	private final Integer idBook;
	private Integer booksOnStorage;
	private Integer queryOnBook;

	public RequestsBook(Integer idBook, Integer queryOnBook, Integer booksOnStorage) {
		this.idBook = idBook;
		this.queryOnBook = queryOnBook;
		this.booksOnStorage = booksOnStorage;
	}
	
	public Integer getBooksOnStorage() {
		return booksOnStorage;
	}

	public void setBooksOnStorage(Integer booksOnStorage) {
		this.booksOnStorage = booksOnStorage;
	}

	public Integer getQueryOnBook() {
		return queryOnBook;
	}

	public void setQueryOnBook(Integer queryOnBook) {
		this.queryOnBook = queryOnBook;
	}

	public Integer getId() {
		return idBook;
	}

	public boolean equals(RequestsBook req) { 
		boolean result = false;
		if(getId() == req.getId()) { 
			result = true;
		}
		return result;
	}
	
	public String toString() {
		return "QueryOnBook [IdBook=" + idBook + ", BookOnStorage=" + booksOnStorage + 
				", QueryOnBook=" + queryOnBook + "]";
	}
	
}