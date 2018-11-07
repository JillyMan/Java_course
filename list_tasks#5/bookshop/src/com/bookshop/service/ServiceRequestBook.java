package com.bookshop.service;

import java.util.Comparator;
import java.util.List;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storable;
import com.bookshop.dao.StorageException;
import com.bookshop.dao.StorageFactory;

public class ServiceRequestBook {
	
	private final Storable<RequestsBook> connector = StorageFactory.getInstance().getRequestBookStorage();

	public ServiceRequestBook() { 
		
	} 
	
	public void deregisterBook(Book book, int quantity) throws StorageException {
		if(book == null || quantity <= 0) { 
			throw new IllegalArgumentException();
		}
		RequestsBook req = connector.getById(book.getId());
		req.setBooksOnStorage(req.getBooksOnStorage() - quantity);
		connector.update(req);					
	}

	public void registerBook(Book book, int quantity) throws StorageException { 
		if(book == null || quantity <= 0) { 
			throw new IllegalArgumentException();
		}
		RequestsBook req = connector.getById(book.getId());
		req.setBooksOnStorage(req.getBooksOnStorage() + quantity);
		connector.update(req);					
	}
	
	public void makeRequest(Book book, int quantity) throws StorageException { 
		if(book == null || quantity <= 0) { 
			throw new IllegalArgumentException();
		}
		RequestsBook req = connector.getById(book.getId());			
		req.setQueryOnBook(req.getQueryOnBook() + quantity);
		connector.update(req);
	}

	public void removeRequest(Book book, int quantity) throws StorageException { 
		if(book == null || quantity <= 0) { 
			throw new IllegalArgumentException();
		}
		
		RequestsBook req = connector.getById(book.getId());			
		req.setQueryOnBook(req.getQueryOnBook() - quantity);
		connector.update(req);
	}	

	public List<RequestsBook> sortBy(Comparator<RequestsBook> comparator) { 
		List<RequestsBook> result = connector.getAll();
		result.sort(comparator);
		return result;
	}
}
