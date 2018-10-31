package com.bookshop.service;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.bookshop.util.DateUtil.monthsBetween;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageFactory;

public class ServiceBook {
	
	private final Storable<Book> connector = StorageFactory.getInstance().getBookStorage();
	
	public ServiceBook() { 
		
	}
	
	public void add(Book book, int quantity) { 
		Storable<RequestsBook> reqStore = StorageFactory.getInstance().getRequestBookStorage();
		RequestsBook reqBook = reqStore.getById(book.getId());
		if(reqBook == null) { 
			reqBook = new RequestsBook(book, 0, quantity);
			reqStore.add(reqBook);
		}
		else { 
			reqBook.setBooksOnStorage(reqBook.getBooksOnStorage() + quantity);
			reqStore.update(reqBook);
		}
		connector.add(book);
	}
	
	public List<Book> getBooksByOrder(Order order) { 
		List<Book> result = new ArrayList<Book>();
		for (Book book : order.getBooks()) {
			result.add(connector.getById(book.getId()));
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
