package com.bookshop.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.util.BookFileUtil;
import com.bookshop.dao.util.OrderFileUtil;
import com.bookshop.dao.util.RequestBookFileUtil;

public class StorageFactory {

	private Storable<Book> bookStorage;
	private Storable<Order> orderStorage; 
	private Storable<RequestsBook> requestBookStorage;

	private static StorageFactory storageFactory = null;
	private static String pathToFiles = null;
	
	private StorageFactory() {
		initStorages(pathToFiles);
	}

	public static void initFactory(String path) {
		pathToFiles = path;
	}
	
	public static StorageFactory getInstance() {
		if(storageFactory == null) { 
			storageFactory = new StorageFactory();
		}
		return storageFactory;
	}
	
	public  Storable<Book> getBookStorage() { 
		return bookStorage;
	}

	public Storable<Order> getOrderStorage() { 
		return orderStorage;
	}
	
	public Storable<RequestsBook> getRequestBookStorage() { 
		return requestBookStorage;
	}
	
	public void initStorages(String path) { 
		String booksPath = "data/books.txt";
		String ordersPath = "data/orders.txt";
		String requestsPath = "data/requestsBook.txt";
		
		if(path != null) {
			FileInputStream fis = null;
			Properties property = new Properties();
			try {
				fis = new FileInputStream(path);
				property.load(fis);
				booksPath = property.getProperty("db.books");
				ordersPath = property.getProperty("db.orders");
				requestsPath = property.getProperty("db.requestsBook");
			}catch(IOException e) { 
				 e.printStackTrace();
			}
		}
		bookStorage = new Storage<Book>(new BookFileUtil(booksPath));
		orderStorage = new Storage<Order>(new OrderFileUtil(ordersPath)); 	
		requestBookStorage = new Storage<RequestsBook>(new RequestBookFileUtil(requestsPath));
	}
		
}
