package com.ui.menu.show.book;

import com.bookshop.core.comparator.BookComparators;

import com.ui.action.book.ShowBooksAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class SortBookMenu {
	
	public Menu getMenu() {			
		Menu result = new Menu("Book sort by");

		for (BookComparators.Type t : BookComparators.Type.values()) {
			result.add(new MenuItem(t.toString(), null, new ShowBooksAction(t)));
		}		
		
		return result;
	}
}
