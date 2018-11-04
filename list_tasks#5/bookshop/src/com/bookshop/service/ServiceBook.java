package com.bookshop.service;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.bookshop.util.DateUtil.monthsBetween;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageException;
import com.bookshop.dao.StorageFactory;
import com.bookshop.service.exception.ServiceBookException;
import com.bookshop.service.exception.ServiceRequestsBookException;

public class ServiceBook {
	
	private final static Logger log = Logger.getLogger(ServiceBook.class.getName());
	private final Storable<Book> connector = StorageFactory.getInstance().getBookStorage();
	
	public ServiceBook() { 
		
	}
	
	public void add(Book book, int quantity) throws ServiceBookException, StorageException, ServiceRequestsBookException {
		if(book == null) {
			throw new IllegalArgumentException();
		}
		
		if(quantity < 0) {
			throw new IllegalArgumentException();			
		}
		
		Storable<RequestsBook> reqStore = StorageFactory.getInstance().getRequestBookStorage();
		if(reqStore == null) { 
			throw new ServiceRequestsBookException("ReqestStorage is null");
		}
			
		RequestsBook reqBook = null;
		try {
			reqBook = reqStore.getById(book.getId());
			reqBook.setBooksOnStorage(reqBook.getBooksOnStorage() + quantity);			
			try {
				reqStore.update(reqBook);
			} catch (StorageException e) {
				log.log(Level.INFO, "Crash update requestsBook", e);
				throw new ServiceBookException("Can't add book" + book);
			}
		} catch (StorageException e) { 
			reqBook = new RequestsBook(book, 0, quantity);
			reqStore.add(reqBook);			
		}

		try {
			connector.add(book);
		} catch (StorageException e) {
			log.info(e.getMessage());
		}
	}
	
	public List<Book> getBooksByOrder(Order order) { 
		List<Book> result = new ArrayList<Book>();
		for (Book book : order.getBooks()) {
			try {
				result.add(connector.getById(book.getId()));
			} catch (StorageException e) {
				log.info(e.getMessage());
			}
		}
		return result;
	}
	
	public List<Book> sortBy(Comparator<Book> comparator) { 
		List<Book> result = connector.getAll();
		result.sort(comparator);
		return result;
	}

	public List<Book> ancientBooks(Comparator<Book> comparator) { 
		List<Book> result = sortBy(comparator);
		result.removeIf((Book b) -> { 	
			Date now = Calendar.getInstance().getTime();
			Date date = b.getDateReceipt();	
			return !(monthsBetween(date, now) > 6);			
		});
		return result;
	}
	
}
