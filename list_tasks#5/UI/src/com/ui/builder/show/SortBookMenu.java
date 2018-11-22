package com.ui.builder.show;

import java.util.ArrayList;
import java.util.List;

import com.bookshop.core.comparator.BookComparators;
import com.ui.action.book.ShowSortBooksAction;
import com.ui.builder.IBuilder;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class SortBookMenu implements IBuilder{
	
	
	public SortBookMenu() {
	}
	
	public Menu getMenu() {			

		List<MenuItem> items = new ArrayList<MenuItem>();

		for (BookComparators.Type t : BookComparators.Type.values()) {
			items.add(new MenuItem(t.toString(), null, new ShowSortBooksAction(t)));
		}
		
		Menu result = new Menu("Book sort by", (MenuItem[]) items.toArray(new MenuItem[items.size()]));

		return result;
	}
}
