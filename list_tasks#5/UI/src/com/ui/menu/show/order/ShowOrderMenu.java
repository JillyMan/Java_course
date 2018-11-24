package com.ui.menu.show.order;

import com.ui.action.order.CountCompletedOrderForPeriod;
import com.ui.action.order.EarnedMoneyForPeriod;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ShowOrderMenu {

	ShowSortOrderMenu showOrdersMenu = new ShowSortOrderMenu();
	CompletedOrderMenu compltedOrderMenu = new CompletedOrderMenu();
	
	public Menu getMenu() {
		MenuItem sortByBtn = new MenuItem("Show orders", showOrdersMenu.getMenu(), null);
		MenuItem completedForPeriodBtn = new MenuItem("Completed order for period", compltedOrderMenu.getMenu(), null);
		MenuItem countCompletedBtn = new MenuItem("Count completed order for period", null, new CountCompletedOrderForPeriod());
		MenuItem countEarnedForPerBtn = new MenuItem("Earned money for period", null, new EarnedMoneyForPeriod());
		
		Menu result = new Menu("Order menu");
		result.add(sortByBtn);
		result.add(completedForPeriodBtn);
		result.add(countCompletedBtn);
		result.add(countEarnedForPerBtn);

		return result;
	}	
}