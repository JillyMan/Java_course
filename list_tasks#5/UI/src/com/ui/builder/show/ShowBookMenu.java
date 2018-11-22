package com.ui.builder.show;

import com.ui.core.Menu;
import com.ui.core.MenuItem;
import com.ui.action.book.ShowAncientBooks;
import com.ui.action.book.ShowBooksAction;
import com.ui.action.book.ShowSortBooks;
import com.ui.builder.IBuilder;

public class ShowBookMenu implements IBuilder {
	
	private SortBookMenu bookMenu = new SortBookMenu();
	private SortBookMenu anctinnBookMenu = new SortBookMenu(new ShowAncientBooks());
	
	public Menu getMenu() {
		MenuItem sortBy = new MenuItem("List books", bookMenu.getMenu(), null);
		MenuItem ancientBook = new MenuItem("Ancients book", sortMenu.getMenu(), null);
				
		Menu result = new Menu("Show books", new MenuItem[] { sortBy, ancientBook });			
		return result;
	}	
	
	
}