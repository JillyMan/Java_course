package com.bookShop.serviceObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.bookShop.businessObject.Book;
import com.bookShop.dataAccessLayer.BookFileUtil;

public class ServiceBook {

	private Comparator<Book> comparator;
	private BookFileUtil bookConnector;
	
	public ServiceBook(Comparator<Book> comparator) { 
		this.comparator = comparator;
	}
	
	public void setComparator(Comparator<Book> comparator) { 
		this.comparator = comparator;
	}
	
	public List<Book> getBooks() { 
		return Arrays.asList(bookConnector.readFromFile());
	}
	
	public List<Book> getSort() {			
		List<Book> list = null;
		if(comparator == null) { 
			list = getBooks();
			list.sort(comparator);			
		}
		return list;
	}

}
