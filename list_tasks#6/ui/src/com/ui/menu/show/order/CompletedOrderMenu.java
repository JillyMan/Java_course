package com.ui.menu.show.order;

import com.bookshop.core.comparator.OrderComparators.Type;
import com.ui.action.order.CompletedOrderForPeriodAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class CompletedOrderMenu {

	public Menu getMenu() {

		MenuItem priceBtn = new MenuItem(Type.PRICE.toString(), null, new CompletedOrderForPeriodAction(Type.PRICE));
		MenuItem dateBtn = new MenuItem(Type.RELEASE.toString().toString(), null, new CompletedOrderForPeriodAction(Type.RELEASE));		

		Menu result = new Menu("Sort by");
		result.add(priceBtn);
		result.add(dateBtn);	
		
		return result;
	}	
}
