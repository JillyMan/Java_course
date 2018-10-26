package com.bookshop.core.model;

public class RequestsBook implements Identified<Integer>{			
	
	private final Book book;
	private Integer booksOnStorage;
	private Integer queryOnBook;

	public RequestsBook(Book book, Integer queryOnBook, Integer booksOnStorage) {
		this.book = book;
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

	public Book getBook() { 
		return book;
	}
	
	public int hashCode() { 
		return book.hashCode();
	}
	
	public Integer getId() {
		return book.getId();
	}

	public boolean equals(Object req) { 
		boolean result = false;		
		if(book.getId() == ((RequestsBook)req).getId()) { 
			result = true;
		}
		return result;
	}
	
	public String toString() {
		return "QueryOnBook [Book=" + book + ", QueryOnBook=" + queryOnBook + 
				", BookOnStorage=" + booksOnStorage + "]";
	}
	
}