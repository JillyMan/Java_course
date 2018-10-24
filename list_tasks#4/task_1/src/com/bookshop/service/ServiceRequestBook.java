package com.bookshop.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storage;
import com.bookshop.dao.util.RequestBookFileUtil;

public class ServiceRequestBook {
	
	private final Storage<RequestsBook> storage = 
			new Storage<RequestsBook>(new RequestBookFileUtil("data/requestsBook.txt"));

	public ServiceRequestBook() { 
		
	} 
	
	public void deregister(Book book, int quantity) { 
		RequestsBook req = storage.getById(book.getId());
		req.setBooksOnStorage(req.getBooksOnStorage() - quantity);
		storage.update(req);					
	}
	
	public void makeRequest(Book book) { 
		RequestsBook req = storage.getById(book.getId());
		req.setQueryOnBook(req.getQueryOnBook() + 1);
		storage.update(req);
	}
	
	public List<RequestsBook> sortBy(Comparator<RequestsBook> comparator) { 
		List<RequestsBook> result = storage.getAll();
		result.sort(comparator);
		return result;
	}

}
