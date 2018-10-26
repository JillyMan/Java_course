package com.bookshop.service;

import java.util.Comparator;
import java.util.List;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.FactoryStorage;
import com.bookshop.dao.Storable;

public class ServiceRequestBook {
	
	private final Storable<RequestsBook> connector = FactoryStorage.getRequestBookStorage();

	public ServiceRequestBook() { 
		
	} 
	
	public void deregister(Book book, int quantity) { 
		RequestsBook req = connector.getById(book.getId());
		req.setBooksOnStorage(req.getBooksOnStorage() - quantity);
		connector.update(req);					
	}
	
	public void makeRequest(Book book) { 
		RequestsBook req = connector.getById(book.getId());
		req.setQueryOnBook(req.getQueryOnBook() + 1);
		connector.update(req);
	}
	
	public List<RequestsBook> sortBy(Comparator<RequestsBook> comparator) { 
		List<RequestsBook> result = connector.getAll();
		result.sort(comparator);
		return result;
	}

}
