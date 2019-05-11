package com.bookshop.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageException;
import com.bookshop.dao.StorageFactory;

import config.ConfigProject;

import static com.bookshop.util.DateUtil.monthsBetween;

public class ServiceBook {
	
	private final Storable<Book> connector = StorageFactory.getInstance().getBookStorage();
	private int monthsAncient;
	
	public ServiceBook() { 
		init();
	}
	
	private void init() {
		monthsAncient = ConfigProject.getInstance().getMonthsAncient();
	}
	
	public void add(Book book, int quantity) throws StorageException {
		if(book == null || quantity < 0) {
			throw new IllegalArgumentException();
		}
				
		Storable<RequestsBook> reqStore = StorageFactory.getInstance().getRequestBookStorage();

		RequestsBook reqBook = null;
		try {
			reqBook = reqStore.getById(book.getId());
		} catch (StorageException e) { 
			reqBook = new RequestsBook(book, 0, quantity);
			reqStore.add(reqBook);
			connector.add(book);
		}
		
		if(reqBook != null) { 
			reqBook.setBooksOnStorage(reqBook.getBooksOnStorage() + quantity);
			reqStore.update(reqBook);			
		}
	}
	
	public List<Book> getBooksByOrder(Order order) throws StorageException { 
		List<Book> result = new ArrayList<Book>();
		for (Book book : order.getBooks()) {
			result.add(connector.getById(book.getId()));
		}
		return result;
	}
	
	public List<Book> sortBy(Comparator<Book> comparator) { 
		List<Book> result = new ArrayList<Book>(connector.getAll());		
		result.sort(comparator);
		return result;
	}

	public List<Book> ancientBooks(Comparator<Book> comparator) { 
		List<Book> result = sortBy(comparator);
		result.removeIf((Book b) -> { 	
			Date now = Calendar.getInstance().getTime();
			Date date = b.getDateReceipt();	
			return !(monthsBetween(date, now) > monthsAncient);			
		});
		return result;
	}	
}