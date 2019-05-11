package com.ui.menu.action.order;

import java.text.ParseException;

import com.bookshop.BookShop;
import com.bookshop.core.model.Book;
import com.bookshop.core.model.Order;
import com.ui.action.order.AddOrderAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;
import com.ui.core.input.InputHandler;

public class ConfigurateOrderMenu {

	private Order order;
	private final String TITLE = "Change order:\n";
	private BookShop shop = BookShop.getInstance();
	private AddOrderAction action;
	
	public ConfigurateOrderMenu (Order order) {
		this.order = order;
		this.action = new AddOrderAction(order);  
	}
	
	public Menu getMenu() {
		
		Menu result = new Menu(TITLE + order);

		for(Book book : shop.getBooks()) {
			result.add(new MenuItem(book.getTitle(), null, () -> {
				System.out.println("Count -> ");
				try {
					int count = InputHandler.getInstance().getInt();
					action.setBook(book, count);
					result.setName(TITLE + order);
				} catch (ParseException e) {
					System.out.println("Input error!");
				}
			}));
		}
		
		result.add(new MenuItem("Save changes", null,() -> {
			action.action();
			result.getMenuItems().clear();
		}));
		
		return result;
	}
}