package com.bookshop.core.comparator;

import java.util.Comparator;

import com.bookshop.core.model.Book;

public class BookComparators {
	
	public enum Type {
		NAME,
		DATERELEASE,
		DATERECEIPT,
		PRICE
	}
	
	public static Comparator<Book> getComparator(Type type) { 
		Comparator<Book> result = null;

		switch (type) { 
		case NAME:  
			result = (Book a, Book b) -> a.getTitle().compareTo(b.getTitle());
			break;
		case DATERELEASE:
			result = (Book a, Book b) -> a.getDataRelease().compareTo(b.getDateRelease());
			break;
		case DATERECEIPT:
			result = (Book a, Book b) -> a.getDateReceipt().compareTo(b.getDataRelease());
		case PRICE:
			result = (Book a, Book b) -> a.getPrice().compareTo(b.getPrice());
			break;
		default: 
			break;
		}
		
		return result;
	}
	
}
