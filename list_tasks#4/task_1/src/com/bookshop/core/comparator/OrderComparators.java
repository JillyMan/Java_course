package com.bookshop.core.comparator;

import java.util.Comparator;

import com.bookshop.core.comparator.BookComparators.Type;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;

public class OrderComparators {
	
	public enum Type {
		RELEASE,
		PRICE, 
		STATUS
	}
	
	public static Comparator<Order> getComparator(Type type) { 
		Comparator<Order> result = null;
		
		switch(type) { 
		case RELEASE: 
			result = (Order a, Order b) -> a.getDateRelease().compareTo(b.getDateRelease());
			break;
		case PRICE: 
			result = (Order a, Order b) -> a.getPrice().compareTo(b.getPrice());
			break;
		case STATUS: 
			result = (Order a, Order b) -> a.getStatus().compareTo(b.getStatus());
			break;
		default: 
			break;
		}
		
		return result;
	}
	
}
