package com.bookshop.service;

import java.util.Comparator;

import java.util.List;

import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.Storage;
import com.bookshop.dao.util.RequestBookFileUtil;


public class ServiceRequestBook {
	
	private final Storage<RequestsBook> storage = new Storage<RequestsBook>(new RequestBookFileUtil("res/queryBooks.txt"));

	public ServiceRequestBook() { 
		
	}
	
	public List<RequestsBook> sortBy(Comparator<RequestsBook> comparator) { 
		List<RequestsBook> result = storage.getAll();
		result.sort(comparator);
		return result;
	}

}
