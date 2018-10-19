package com.bookShop.businessObject.serviceObject;

import java.util.Comparator;
import java.util.List;

import com.bookShop.businessObject.Book;
import com.bookShop.dataLayer.Storable;
import com.bookShop.dataLayer.Storage;
import com.bookShop.dataLayer.fileUtil.BookFileUtil;

public class ServiceBook {
	
	private final Storable<Book> storage = new Storage<Book>(new BookFileUtil("res/books.txt"));
	
	public ServiceBook() { 
		
	}
	
	public List<Book> getSortBy(Comparator<Book> comparator) { 
		List<Book> result = storage.getAll();
		result.sort(comparator);
		return result;
	}

	public List<Book> ancientBooks(Comparator<Book> comparator) { 
		List<Book> result = storage.getAll();
		result.removeIf((Book b) -> { 	
			//TODO: get month between(b.getReceipt, Date.now());
			return false;
		});
		result.sort(comparator);
		return result;
	}
	
}
