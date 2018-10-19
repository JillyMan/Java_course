package com.bookShop.businessObject.serviceObject;

import java.util.Comparator;
import java.util.List;

import com.bookShop.businessObject.QueryBook;
import com.bookShop.dataLayer.Storage;
import com.bookShop.dataLayer.fileUtil.QueryBookFileUtil;

public class ServiceQueryBook {
	
	private final Storage<QueryBook> storage = new Storage<QueryBook>(new QueryBookFileUtil("res/queryBooks.txt"));

	public ServiceQueryBook() { 
		
	}
	
	public List<QueryBook> sortBy(Comparator<QueryBook> comparator) { 
		List<QueryBook> result = storage.getAll();
		result.sort(comparator);
		return result;
	}

}
