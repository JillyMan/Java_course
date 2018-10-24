package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bookshop.core.model.Author;
import com.bookshop.core.model.Book;

public class Program {
	
	public static void main(String[] args) throws ParseException { 			

		Book book = new Book(10, 100, new Author("man", "it's man"), "title", 
				new Date(1, 1, 1), new Date(2, 2, 2));
		
		final String[] array = new String[] { 
				String.valueOf(book.getId()), 
				String.valueOf(book.getPrice()),
				String.valueOf(book.getAuthor()),
				book.getTitle(),
				String.valueOf(book.getDateReceipt()),
				String.valueOf(book.getDateRelease())
		};
		
	//	System.out.println(String.join(";", array));
		
		DateFormat date = new SimpleDateFormat("MMMM d, yyyy");
		System.out.println(date.format(new Date(1, 1, 1)));
		System.out.println(date.parse("февраля 1, 1901"));
		
//		Date d = date.parse(array[4]);
//		System.out.println(d);
		
	}
}
