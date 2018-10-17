package com.bookShop.serviceObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.bookShop.businessObject.Book;
import com.bookShop.dataLayer.BookFileUtil;

public class ServiceBooks implements Sortable<Book> {

	private BookFileUtil bookConnector;
	
	public ServiceBooks() { 
		bookConnector = new BookFileUtil("res/books.txt");
	}
		
	public List<Book> getBooks() { 
		return Arrays.asList(bookConnector.readFromFile());
	}
	
	public List<Book> sortBy(Comparator<Book> comparator) {			
		List<Book> list = getBooks();
		list.sort(comparator);			
		return list;
	}
}
