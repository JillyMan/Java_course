package com.ui.menu.show.book;

import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ShowBookMenu {
	
	private SortBookMenu bookMenu = new SortBookMenu();
	private ShowAncientBookMenu ancientBookMenu = new ShowAncientBookMenu();
	
	public Menu getMenu() {
		MenuItem sortBy = new MenuItem("List books", bookMenu.getMenu(), null);
		MenuItem ancientBook = new MenuItem("Ancients book", ancientBookMenu.getMenu(), null);
				
		Menu result = new Menu("Show books");		
		result.add(sortBy);
		result.add(ancientBook);
				
		return result;
	}
}