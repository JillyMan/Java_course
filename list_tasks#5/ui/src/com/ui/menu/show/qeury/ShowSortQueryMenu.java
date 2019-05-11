package com.ui.menu.show.qeury;

import com.bookshop.core.comparator.RequestBookComparator.Type;
import com.ui.action.query.ShowQueryAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ShowSortQueryMenu {
	
	public Menu getMenu() { 
		Menu result = new Menu("Querys sort by");

		for (Type t : Type.values()) {
			result.add(new MenuItem(t.toString(), null, new ShowQueryAction(t)));
		}
		
		return result;
	}
}
