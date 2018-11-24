package com.ui.action.order;

import java.util.List;

import com.bookshop.BookShop;
import com.bookshop.core.comparator.OrderComparators.Type;
import com.bookshop.core.model.Order;
import com.ui.core.IAction;

public class ShowOrdersAction implements IAction {
	
	private Type typeComp;
	private BookShop shop = BookShop.getInstance();

	private final String EMPTY_LIST_ORDER = "List of orders empty!";

	public ShowOrdersAction(Type typeComp) {
		this.typeComp = typeComp;
	}
	
	public void action() {
		List<Order> orders = shop.getOrders(typeComp);
		
		if(orders == null || orders.isEmpty()) {
			System.out.println(EMPTY_LIST_ORDER);
		} else {
			for (Order order : orders) {
				System.out.println(order.toString());
			}			
		}
	}
}
