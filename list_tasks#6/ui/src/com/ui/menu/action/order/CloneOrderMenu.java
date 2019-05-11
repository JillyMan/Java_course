package com.ui.menu.action.order;

import com.bookshop.BookShop;
import com.bookshop.core.model.Order;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class CloneOrderMenu {
	
	private BookShop shop = BookShop.getInstance();
	
	public Menu getMenu() {
		Menu result = new Menu("Clone order");
		
		for(Order order : shop.getOrders()) {
			result.add(new MenuItem(
					order.getId().toString(),
					new ConfigurateOrderMenu(order).getMenu(),
					null));
		}
				
		return result;
	}
}
