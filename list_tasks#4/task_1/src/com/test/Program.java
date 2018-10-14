package com.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.bookShop.businessObject.Book;
import com.bookShop.serviceObject.ServiceBook;

public class Program {
	public static void main(String[] args) { 		
		ServiceBook serviceBook = new ServiceBook(
			new Comparator<Book>() {
				public int compare(Book a, Book b) {
					return Integer.compare(a.getPrice(), b.getPrice());
				}
			}
		);
		
		serviceBook.setComparator((Book a, Book b) -> a.getName().compareTo(b.getName()));
		serviceBook.setComparator((Book a, Book c) -> Boolean.compare(c.getOnStorage(), a.getOnStorage()));
		
		serviceBook.setComparator(new Comparator<Book>() {
			@Override
			public int compare(Book a, Book b) { 
				return a.getDataRelease().compareTo(b.getDataRelease());
			}			
		});
		
		List<Boolean> b = Arrays.asList(new Boolean[] { false, false, true, false, true, false} );
		b.sort();
		System.out.println(b.toString());
		
		
		//MyAction((Integer a, Integer b) -> Integer.compare(a, b));
	}
	
	public static void MyAction(Comparator<Integer> c) { 
		System.out.println(c.compare(10, 20));
	}
	
}
