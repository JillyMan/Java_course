package com.bookshop.dao;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.util.BookFileUtil;
import com.bookshop.dao.util.OrderFileUtil;
import com.bookshop.dao.util.RequestBookFileUtil;

public class FactoryStorage {

	private static final Storable<Book> bookStorage = new Storage<Book>(new BookFileUtil("data/books.txt"));
	private static final Storage<Order> orderStorage = new Storage<Order>(new OrderFileUtil("data/orders.txt"));
	private static final Storage<RequestsBook> requestBookStorage = 
			new Storage<RequestsBook>(new RequestBookFileUtil("data/requestsBook.txt"));

	public static Storable<Book> getBookStorage() { 
		return bookStorage;
	}

	public static Storable<Order> getOrderStorage() { 
		return orderStorage;
	}
	
	public static Storable<RequestsBook> getRequestBookStorage() { 
		return requestBookStorage;
	}
	
}
