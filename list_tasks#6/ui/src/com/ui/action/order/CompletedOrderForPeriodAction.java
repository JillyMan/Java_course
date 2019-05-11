package com.ui.action.order;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.bookshop.BookShop;
import com.bookshop.core.comparator.OrderComparators.Type;
import com.bookshop.core.model.Order;
import com.ui.core.IAction;
import com.ui.core.input.InputHandler;

public class CompletedOrderForPeriodAction implements IAction {

	private BookShop shop = BookShop.getInstance();
	private InputHandler input = InputHandler.getInstance();
	private Type typeComp;
	
	private final String FIRST_DATE = "Input min date " + InputHandler.DATE_PATTERN; 
	private final String SECOND_DATE = "Input max date " + InputHandler.DATE_PATTERN;
	private final String ERROR_MESSAGE = "Incorrect input date. Use pattern" + InputHandler.DATE_PATTERN;
	private final String NO_ORDERS = "No orders!!";
	
	public CompletedOrderForPeriodAction (Type typeComp ) { 
		this.typeComp = typeComp;
	}
	
	public void action() {
		Date min = null; 
		Date max = null;
		List<Order> orders = null;
		try {
			System.out.println(FIRST_DATE);
			min = input.getDate();
			System.out.println(SECOND_DATE);
			max = input.getDate();						
		} catch (ParseException e) {
			System.out.println(ERROR_MESSAGE);
			return;
		}
		
		orders = shop.getCompletedOrder(min, max, typeComp);
		if(orders == null || orders.isEmpty()) {
			System.out.println(NO_ORDERS);
		} else {
			for (Order order : orders) {
				System.out.println(order);
			}
		}	
	}
}