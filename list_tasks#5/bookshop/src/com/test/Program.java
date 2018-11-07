 package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bookshop.core.comparator.BookComparators;
import com.bookshop.core.comparator.OrderComparators;
import com.bookshop.core.comparator.RequestBookComparator;
import com.bookshop.core.comparator.RequestBookComparator.Type;
import com.bookshop.core.model.Author;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.StorageException;
import com.bookshop.dao.StorageFactory;
import com.bookshop.dao.util.BookFileUtil;
import com.bookshop.dao.util.OrderFileUtil;
import com.bookshop.dao.util.RequestBookFileUtil;
import com.bookshop.service.ServiceBook;
import com.bookshop.service.ServiceOrder;
import com.bookshop.service.ServiceRequestBook;

public class Program {
	private Book book0;
	private Book book1;
	private Book book2;
	
	private Order order0;
	private Order order1;
	private Order order2;
	
	private RequestsBook req0;
	private RequestsBook req1;
	private RequestsBook req2;
	
	private ServiceOrder serviceOrder = new ServiceOrder();
	private ServiceBook serviceBook = new ServiceBook();
	private ServiceRequestBook servReqBook = new ServiceRequestBook();

	@SuppressWarnings("deprecation")
	private void fillFileBooks() {
		
		book0 = new Book(1, 100, new Author("man1", "man1"), "BBB", 
				new Date(101, 2, 1), new Date(100, 5, 2));	
		book1 = new Book(2, 100, new Author("man2", "man2"), "CCC", 
				new Date(112, 3, 3), new Date(110, 1, 2));	
		book2 = new Book(3, 100, new Author("man3", "man3"), "AAA", 
				new Date(118, 7, 3), new Date(111, 4, 2));
				
		BookFileUtil bookUtil = new BookFileUtil("data/books.txt"); 
		
		List<Book> books = new ArrayList<Book>();
		books.add(book0);
		books.add(book1);
		books.add(book2);
			
		bookUtil.writeToFile(books);		
		books = bookUtil.readFromFile();
		System.out.println("DataBase store books: ");
		for(Book b : books) { 
			System.out.println("\t" + b);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void fillFileOrder() { 
		Map<Book, Integer> map0 = new HashMap<Book, Integer>();
		map0.put(book0, 1);
		map0.put(book2, 2);
		order0 = new Order(1, new Date(102, 2, 4), new Date(102, 2, 10), map0, Order.Status.COMPLETED);
		
		Map<Book, Integer> map1 = new HashMap<Book, Integer>();		
		map1.put(book0, 1);
		map1.put(book1, 3);
		map1.put(book2, 2);
		order1 = new Order(2, new Date(), new Date(119, 1, 1), map1, Order.Status.CANCALED);

		Map<Book, Integer> map2 = new HashMap<Book, Integer>();
		map2.put(book2, 2);
		order2 = new Order(3, new Date(), new Date(118, 11, 1), map2, Order.Status.AWAITING);
		
		OrderFileUtil fileUtil = new OrderFileUtil("data/orders.txt");
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(order0);
		orders.add(order1);
		//orders.add(order2);
				
		fileUtil.writeToFile(orders);
		orders = fileUtil.readFromFile();
		System.out.println("DataBase store orders: ");
		for (Order order : orders) {
			System.out.println("\t" + order);
		}
	}

	private void fillRequestsBook() { 
		req0 = new RequestsBook(book0, 1, 0);
		req1 = new RequestsBook(book1, 1, 0);
		req2 = new RequestsBook(book2, 1, 2);
	
		RequestBookFileUtil reqUtil = new RequestBookFileUtil("data/requestsBook.txt");
		
		List<RequestsBook> reqs = new ArrayList<RequestsBook>();
		reqs.add(req0);
		reqs.add(req1);
		reqs.add(req2);
				
		reqUtil.writeToFile(reqs);
		
		reqs = reqUtil.readFromFile();
		
		System.out.println("DataBase store reqBook: ");
		for (RequestsBook r: reqs) {
			System.out.println("\t" + r);
		}		
	}	
	
	private void fillData() { 
		
		System.out.println("Fill test data\n-----------");
		fillFileBooks();
		fillFileOrder();
		fillRequestsBook();
		System.out.println("---------");
	}

	private void testServiceBook() { 
		System.out.println("Testing ServiceBook:");
		System.out.println("Add book2 ");
		book2.setDateReceipt(new Date());
		
		try {
			serviceBook.add(book2, 10);
		} catch (StorageException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Compare book by title: ");
		List<Book> list = serviceBook.sortBy(BookComparators.getComparator(BookComparators.Type.NAME));			
		for (Book book : list) {
			System.out.println("\t" + book);
		}
		System.out.println("Get ancient book(date recepient > 6 month)");
		list = serviceBook.ancientBooks(BookComparators.getComparator(BookComparators.Type.DATERECEIPT));
		for (Book book : list) {
			System.out.println("\tId = " + book.getId() + " Date = " + book.getDateReceipt());
		}	
		System.out.println("---------");
	}
	
	private void testServiceRequests() throws StorageException { 
		System.out.println("Testing ServiceRequestsBook:");
		System.out.println("Make request on book: ");
		
		System.out.println("Before make 2 requests on book0");
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println("\t" + x));
		servReqBook.makeRequest(book0, 2);
		System.out.println("After make requests, and sort by count requests");
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println("\t" + x));		
		
		System.out.println("Derigiser two -> book2 ");
		servReqBook.deregisterBook(book2, 2);
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.ONSTORAGE))
					.forEach(x -> System.out.println("\t" + x));		
		System.out.println("---------");
	}
	
	@SuppressWarnings("deprecation")
	private void testServiceOrder() throws StorageException { 
		System.out.println("Testing ServiceOrder:");
		int count = serviceOrder.getCountCompleateForPeriod(new Date(102, 2, 1), new Date(102, 3, 1));		
		System.out.println("Count compleate order for period (2002:02:01; 2002:03:01) -> " + count);
		serviceOrder.getCompleateForPeriod(new Date(102, 2, 1), new Date(102, 3, 1), OrderComparators.Type.STATUS)
					.forEach(x -> System.out.println("\t" + x));
		int money = serviceOrder.getEarnedMoney(new Date(102, 2, 1), new Date(102, 3, 1));
		System.out.println("Wage for period (2002:02:01; 2002:03:01) -> " + money);
		System.out.println("\t---");
		System.out.println("Befor add \'order2\' and requestsBook");
		serviceOrder.sortBy(OrderComparators.getComparator(OrderComparators.Type.RELEASE))
					.forEach(x -> System.out.println("\t" + x));
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println("\t" + x));	
		System.out.println("\t---");
		System.out.println("Add order (order2 = {book: book2})");
		serviceOrder.add(order2);
		System.out.println("After order (order2 = {book: book2, 2 count}), also changes requestsBook");
		System.out.println("\t---");
		serviceOrder.sortBy(OrderComparators.getComparator(OrderComparators.Type.RELEASE))
					.forEach(x -> System.out.println("\t" + x));
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println("\t" + x));	
		System.out.println("\t---");		
		System.out.println("Equip order2");

		if(serviceOrder.equip(order2)) { 
			serviceOrder.sortBy(OrderComparators.getComparator(OrderComparators.Type.STATUS))
			.forEach(x -> System.out.println("\t" + x));
			servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
				.forEach(x -> System.out.println("\t" + x));	
		}
	}
	
	public void run() { 
		fillData();
		try {
			testServiceRequests();
			testServiceBook();
			testServiceOrder();
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length == 1) { 
//			StorageFactory.initFactory(args[0]);
		} else {
		}
		StorageFactory.initFactory(null);			
	
		new Program().run();
	}
}
