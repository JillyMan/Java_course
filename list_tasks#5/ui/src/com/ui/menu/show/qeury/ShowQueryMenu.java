package com.ui.menu.show.qeury;

import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ShowQueryMenu {

	private ShowSortQueryMenu sortMenu;
	
	public ShowQueryMenu() { 
		sortMenu = new ShowSortQueryMenu();
	}
	
	public Menu getMenu() { 
		
		MenuItem showBtn = new MenuItem("Show querys", sortMenu.getMenu(), null);
		
		Menu result = new Menu("Query menu");
		result.add(showBtn);
		return result;
	}
	
}
