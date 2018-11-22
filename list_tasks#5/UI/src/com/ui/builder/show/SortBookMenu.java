package com.ui.builder.show;

import java.util.ArrayList;
import java.util.List;

import com.bookshop.core.comparator.BookComparators;
import com.ui.action.book.ShowSortBooks;
import com.ui.builder.IBuilder;
import com.ui.core.IAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class SortBookMenu implements IBuilder{
	
	private IAction action;
	
	public SortBookMenu(IAction action) {
		this.action = action;
	}
	
	public Menu getMenu() {			

		List<MenuItem> items = new ArrayList<MenuItem>();

		for (BookComparators.Type t : BookComparators.Type.values()) {
			items.add(new MenuItem(t.toString(), null, action));
		}
		
		Menu result = new Menu("Sort by", (MenuItem[]) items.toArray(new MenuItem[items.size()]));

		return result;
	}
}
