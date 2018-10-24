package com.bookshop.core.comparator;

import java.util.Comparator;

import com.bookshop.core.model.Book;

public class BookComparators {
	
	public enum Type {
		NAME,
		DATE, 
		PRICE, 
		ONSTORAGE
	}
	
	public static Comparator<Book> getComparator(Type type) { 
		Comparator<Book> result = null;

		switch (type) { 
		case NAME:  
			result = (Book a, Book b) -> a.getTitle().compareTo(b.getTitle());
			break;
		case DATE:
			result = (Book a, Book b) -> a.getDataRelease().compareTo(b.getDateRelease());
			break;
		case PRICE:
			result = (Book a, Book b) -> a.getPrice().compareTo(b.getPrice());
			break;
		case ONSTORAGE: 
			//TODO: ???
			result = null;
			break;
		default: 
			break;
		}
		
		return result;
	}
	
}
