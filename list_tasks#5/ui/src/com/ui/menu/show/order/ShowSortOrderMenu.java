package com.ui.menu.show.order;

import com.ui.action.order.ShowOrdersAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

import com.bookshop.core.comparator.OrderComparators.Type;

public class ShowSortOrderMenu {
	
	public Menu getMenu() { 
		Menu result = new Menu("Orders sort by");

		for (Type t : Type.values()) {
			result.add(new MenuItem(t.toString(), null, new ShowOrdersAction(t)));
		}
		
		return result;
	}	
}
