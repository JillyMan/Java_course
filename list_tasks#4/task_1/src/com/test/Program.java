package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.bookshop.core.model.Author;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.RequestsBook;
import com.bookshop.dao.util.BookFileUtil;
import com.bookshop.dao.util.OrderFileUtil;
import com.bookshop.dao.util.RequestBookFileUtil;
import com.bookshop.service.ServiceBook;

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
		
		book0 = new Book(1, 100, new Author("man1", "man1"), "title0", 
				new Date(100, 2, 1), new Date(1999, 5, 2));	
		book1 = new Book(2, 100, new Author("man2", "man2"), "title1", 
				new Date(103, 3, 3), new Date(102, 1, 2));	
		book2 = new Book(3, 100, new Author("man3", "man3"), "title2", 
				new Date(106, 1, 3), new Date(101, 4, 2));
		
		BookFileUtil bookUtil = new BookFileUtil("data/books.txt"); 
			
		bookUtil.writeToFile(new Book[] { book0, book1, book2 });
		
		Book[] books = bookUtil.readFromFile();
		System.out.println("DataBase store books: ");
		for(Book b : books) { 
			System.out.println("\t" + b);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void fillFileOrder() { 
		order0 = new Order(1, new Date(2,2,4), new Date(100, 2, 4), Arrays.asList(new Book[] { book0, book2}), Order.Status.COMPLEATE);
		order1 = new Order(2, new Date(3,3,5), new Date(120, 3, 4), Arrays.asList(new Book[] { book0, book1 ,book2}), Order.Status.AWAITING);
		order2 = new Order(3, new Date(4,4,6), new Date(130, 5, 4), Arrays.asList(new Book[] { book2}), Order.Status.CANCALED);
	
		OrderFileUtil fileUtil = new OrderFileUtil("data/orders.txt");
		
		fileUtil.writeToFile(new Order[] { order0, order1, order2});
		Order[] orders = fileUtil.readFromFile();
		System.out.println("DataBase store orders: ");
		for (Order order : orders) {
			System.out.println("\t" + order);
		}
	}

	public static void fillRequestsBook() { 
		req0 = new RequestsBook(book0.getId(), 1, 0);
		req1 = new RequestsBook(book1.getId(), 1, 0);
		req2 = new RequestsBook(book2.getId(), 1, 2);
	
		RequestBookFileUtil reqUtil = new RequestBookFileUtil("data/requestsBook.txt");
		
		reqUtil.writeToFile(new RequestsBook[] {req0, req1, req2 });
		
		RequestsBook[] reqs = reqUtil.readFromFile();
		
		System.out.println("DataBase store reqBook: ");
		for (RequestsBook r: reqs) {
			System.out.println("\t" + r);
		}
	}
	
	public static void main(String[] args) throws ParseException { 			
/*		fillFileBooks();
		fillFileOrder();
		fillRequestsBook();
*/
		
		ServiceBook serviceBook = new ServiceBook();
		ServiceBook.
		
		
//		Date d = new Date(1000, 10, 1);
//		System.out.println(d);
// 		DateFormat date = new SimpleDateFormat("MMMM d, yyyy");
//		System.out.println(date.format(new Date(2, 1, 2).));
//		System.out.println(date.parse("февраля 1, 1901"));	
		
	}
}
