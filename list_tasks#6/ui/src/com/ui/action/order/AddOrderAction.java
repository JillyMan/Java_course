package com.ui.action.order;

import java.util.Date;
import java.util.Random;

import com.bookshop.BookShop;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.bookshop.core.model.Order.Status;
import com.bookshop.dao.StorageException;
import com.ui.core.IAction;

public class AddOrderAction implements IAction {	
	private Order order;
	private BookShop shop = BookShop.getInstance();
	
	private final String MESSAGE = "Your order: ";
	
	public AddOrderAction(Order order) {
		this.order = order;
		init();
	}
	
	public AddOrderAction() {
		init();
	}

	@SuppressWarnings("deprecation")
	private void init() {
		if(order == null) {
			order = new Order();			
		}
		Date date = new Date();		
		order.setId(new Random().nextInt(Integer.MAX_VALUE));
		order.setDateOrder(date);
		order.setDateRelease(new Date(date.getYear(), date.getMonth(), date.getDay() + 7));		
		order.setStatus(Status.AWAITING);
	}
	
	public void addBook(Book book) {
		order.addBooks(book, 1);
	}
	
	public void setBook(Book book, int count) {
		if(order == null) {
			init();
		}
		
		if(book != null) {
			order.setBook(book, count);
		}
	}
	
	public void action() {
		try {
			shop.addOrder(order);
			System.out.println(MESSAGE + "\n" + order);
			order = null;
		} catch (StorageException e) {
			System.out.println("Incorrect input book");			
		} 
	}
}
