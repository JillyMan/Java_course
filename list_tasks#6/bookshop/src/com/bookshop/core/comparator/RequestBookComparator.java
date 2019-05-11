package com.bookshop.core.comparator;

import java.util.Comparator;

import com.bookshop.core.model.RequestsBook;

public class RequestBookComparator {
	
	public enum Type {
		ALPHABET,
		ONSTORAGE, 
		REQUESTS
	}
	
	public static Comparator<RequestsBook> getComparator(Type type) { 
		Comparator<RequestsBook> result = null;
		
		switch(type) { 
		case ALPHABET: 
			result = (RequestsBook req1, RequestsBook req2) -> req1.getBook().getTitle().compareTo(req2.getBook().getTitle());
			break;
		case ONSTORAGE: 
			result = (RequestsBook req1, RequestsBook req2) -> req1.getBooksOnStorage().compareTo(req2.getBooksOnStorage()); 
			break;
		case REQUESTS: 
			result = (RequestsBook req1, RequestsBook req2) -> req1.getQueryOnBook().compareTo(req2.getQueryOnBook());
			break;
		default: 
			break;
		}
		
		return result;
	}
	
}
