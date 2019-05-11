package com.ui.menu.action.order;

import com.bookshop.BookShop;
import com.bookshop.core.model.Book;
import com.ui.action.order.AddOrderAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class BuildOrderMenu {

	private AddOrderAction action = new AddOrderAction();
	
	public Menu getMenu() {
		Menu result = new Menu("Add book in order");
	
		for(Book book : BookShop.getInstance().getBooks()) { 
			result.add(new MenuItem(book.getTitle(), null, () -> action.addBook(book)));
		}
		
		result.add(new MenuItem("Make order", null, action));
		
		return result;
	}
	
}
