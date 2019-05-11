package com.bookshop.core.model;

import java.io.Serializable;

public class RequestsBook implements Identified<Integer>, Serializable {				
	private static final long serialVersionUID = -1641531324126202949L;
	
	private Book book;
	private Integer booksOnStorage;
	private Integer queryOnBook;

	public RequestsBook() { }
	
	public RequestsBook(Book book, Integer queryOnBook, Integer booksOnStorage) {
		this.book = book;
		this.queryOnBook = queryOnBook;
		this.booksOnStorage = booksOnStorage;
	}
	
	public Book getBook() { 
		return book;
	}
	
	public void setBook(Book value) {
		book = value;
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
		return book.getId();
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((booksOnStorage == null) ? 0 : booksOnStorage.hashCode());
		result = prime * result + ((queryOnBook == null) ? 0 : queryOnBook.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestsBook other = (RequestsBook) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (booksOnStorage == null) {
			if (other.booksOnStorage != null)
				return false;
		} else if (!booksOnStorage.equals(other.booksOnStorage))
			return false;
		if (queryOnBook == null) {
			if (other.queryOnBook != null)
				return false;
		} else if (!queryOnBook.equals(other.queryOnBook))
			return false;
		return true;
	}

	public String toString() {
		return "QueryOnBook [Book=" + book + ", QueryOnBook=" + queryOnBook +
				", BookOnStorage=" + booksOnStorage + "]";
	}
	
}