package com.ui.menu.show;

import com.ui.core.Menu;
import com.ui.core.MenuItem;
import com.ui.menu.show.book.ShowBookMenu;
import com.ui.menu.show.order.ShowOrderMenu;
import com.ui.menu.show.qeury.ShowQueryMenu;

public class ShowMenu {
	
	ShowBookMenu showBooks;
	ShowOrderMenu showOrders;
	ShowQueryMenu showQuery;
		
	public ShowMenu() {
		showBooks = new ShowBookMenu();
		showOrders = new ShowOrderMenu();
 		showQuery = new ShowQueryMenu();
	}
	
	public Menu getMenu() {
		MenuItem showBookButton = new MenuItem("Books", showBooks.getMenu(), null);
		MenuItem showOrderButton= new MenuItem("Orders", showOrders.getMenu(), null);
		MenuItem showQueryButton= new MenuItem("Query", showQuery.getMenu(), null);
		
		Menu result = new Menu("Main menu");

		result.add(showBookButton);
		result.add(showOrderButton);
		result.add(showQueryButton);
		
		return result;
	}	
}
