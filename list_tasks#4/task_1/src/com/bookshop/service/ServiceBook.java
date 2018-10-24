package com.bookshop.service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static com.bookshop.util.DateUtil.monthsBetween;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.FactoryStorage;
import com.bookshop.dao.Storable;

public class ServiceBook {
	
	private final Storable<Book> storage = FactoryStorage.getBookStorage();
	
	public ServiceBook() { 
		
	}
	
	public void add(Book book, int quantity) { 
		Storable<RequestsBook> reqStore = FactoryStorage.getRequestBookStorage();
		RequestsBook reqBook = reqStore.getById(book.getId());
		reqBook.setBooksOnStorage(reqBook.getBooksOnStorage() + quantity);
		storage.add(book);
		reqStore.update(reqBook);
	}
	
	public List<Book> getSortBy(Comparator<Book> comparator) { 
		List<Book> result = storage.getAll();
		result.sort(comparator);
		return result;
	}

	public List<Book> ancientBooks(Comparator<Book> comparator) { 
		List<Book> result = getSortBy(comparator);
		result.removeIf((Book b) -> { 	
			Date now = Calendar.getInstance().getTime();
			Date date = b.getDateReceipt();			
			return (monthsBetween(date, now) > 6);			
		});
		return result;
	}
	
}
