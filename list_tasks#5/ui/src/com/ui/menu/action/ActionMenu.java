package com.ui.menu.action;

import com.ui.core.Menu;
import com.ui.core.MenuItem;
import com.ui.menu.action.book.ActionBookMenu;
import com.ui.menu.action.order.ActionOrderMenu;
import com.ui.menu.action.query.ActionQueryMenu;

public class ActionMenu {

	private ActionBookMenu bookMenu;
	private ActionOrderMenu orderMenu;
	private ActionQueryMenu queryMenu;
	
	public ActionMenu() {
		this.bookMenu = new ActionBookMenu();
		this.orderMenu = new ActionOrderMenu();
		this.queryMenu = new ActionQueryMenu();
	}
	
	public Menu getMenu() { 
		
		MenuItem bookBtn = new MenuItem("Book action", bookMenu.getMenu(), null);
		MenuItem orderBtn = new MenuItem("Order action", orderMenu.getMenu(), null);
		MenuItem queryBtn = new MenuItem("Qeury action", queryMenu.getMenu(), null);
		
		Menu result = new Menu("Actin menu");
		result.add(bookBtn);
		result.add(orderBtn);
		result.add(queryBtn);

		return result;		
	}	
}
