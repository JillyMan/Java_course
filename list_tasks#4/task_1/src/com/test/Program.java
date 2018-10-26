package com.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.bookshop.core.comparator.BookComparators;
import com.bookshop.core.comparator.OrderComparators;
import com.bookshop.core.comparator.RequestBookComparator;
import com.bookshop.core.comparator.RequestBookComparator.Type;
import com.bookshop.core.model.Author;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.util.BookFileUtil;
import com.bookshop.dao.util.OrderFileUtil;
import com.bookshop.dao.util.RequestBookFileUtil;
import com.bookshop.service.ServiceBook;
import com.bookshop.service.ServiceOrder;
import com.bookshop.service.ServiceRequestBook;

public class Program {
	public static Book book0;
	public static Book book1;
	public static Book book2;

	public static Order order0;
	public static Order order1;
	public static Order order2;
	
	public static RequestsBook req0;
	public static RequestsBook req1;
	public static RequestsBook req2;
	
	@SuppressWarnings("deprecation")
	public static void fillFileBooks() {
		
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
	public static void fillFileOrder() { 
		order0 = new Order(1, new Date(102, 2, 4), new Date(102, 2, 10), Arrays.asList(new Book[] { book0, book2}), Order.Status.COMPLEATE);
		order1 = new Order(2, new Date(), new Date(119, 1, 1), Arrays.asList(new Book[] { book0, book1, book2}), Order.Status.CANCALED);
		order2 = new Order(3, new Date(), new Date(118, 11, 1), Arrays.asList(new Book[] { book2}), Order.Status.AWAITING);
		
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

	public static void fillRequestsBook() { 
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
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws ParseException { 
		System.out.println("Fill data\n-----------");
		fillFileBooks();
		fillFileOrder();
		fillRequestsBook();
		System.out.println("---------");
				
		System.out.println("Testing ServiceBook:");
		ServiceBook serviceBook = new ServiceBook();
		System.out.println("Add book2 ");
		book0.setDateReceipt(new Date());
		serviceBook.add(book2, 10);
		System.out.println("Compare book by title: ");
		List<Book> list = serviceBook.sortBy(BookComparators.getComparator(BookComparators.Type.NAME));			
		for (Book book : list) {
			System.out.println("\t" + book);
		}
		System.out.println("Get ancient book(date recepient > 6 month)");
		list = serviceBook.ancientBooks(BookComparators.getComparator(BookComparators.Type.DATERECEIPT));
		for (Book book : list) {
			System.out.println("\tId:" + book.getId() + " " + book.getDateReceipt());
		}	
		System.out.println("---------");

		System.out.println("Testing ServiceRequestsBook:");
		ServiceRequestBook servReqBook = new ServiceRequestBook();
		System.out.println("Make request on book: ");
		
		System.out.println("Before make requests");
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println(x));
		servReqBook.makeRequest(book0);
		System.out.println("After make requests, and sort by count requests");
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println(x));		
		
		System.out.println("Derigiser book2");
		servReqBook.deregister(book2, 2);
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.ONSTORAGE))
					.forEach(x -> System.out.println(x));		
		System.out.println("---------");

		System.out.println("Testing ServiceOrder:");
		ServiceOrder serviceOrder = new ServiceOrder();
		int count = serviceOrder.getCountCompleateForPeriod(new Date(102, 2, 1), new Date(102, 3, 1));		
		System.out.println("Count compleate order for period (2002:02:01; 2002:03:01) -> " + count);
		serviceOrder.getCompleateForPeriod(new Date(102, 2, 1), new Date(102, 3, 1))
					.forEach(x -> System.out.println(x));
		int money = serviceOrder.getEarnedMoney(new Date(102, 2, 1), new Date(102, 3, 1));
		System.out.println("Wage for period (2002:02:01; 2002:03:01) -> " + money);
		System.out.println("\t---");
		System.out.println("Befor add \'order2\' and requestsBook");
		serviceOrder.sortBy(OrderComparators.getComparator(OrderComparators.Type.RELEASE))
					.forEach(x -> System.out.println(x));
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println(x));	
		System.out.println("\t---");
		System.out.println("Add order (order2 = {book: book2})");
		serviceOrder.add(order2);
		System.out.println("After order (order2 = {book: book2}), also changes requestsBook");
		System.out.println("\t---");
		serviceOrder.sortBy(OrderComparators.getComparator(OrderComparators.Type.RELEASE))
					.forEach(x -> System.out.println(x));
		servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
					.forEach(x -> System.out.println(x));	
		System.out.println("\t---");		
		System.out.println("Equip order2");

		if(serviceOrder.equip(order2)) { 
			serviceOrder.sortBy(OrderComparators.getComparator(OrderComparators.Type.STATUS))
			.forEach(x -> System.out.println(x));
			servReqBook.sortBy(RequestBookComparator.getComparator(Type.REQUESTS))
				.forEach(x -> System.out.println(x));	
		}
	}
}
