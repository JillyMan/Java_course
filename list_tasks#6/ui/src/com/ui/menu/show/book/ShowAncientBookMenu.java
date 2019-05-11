package com.ui.menu.show.book;

import com.bookshop.core.comparator.BookComparators.Type;
import com.ui.action.book.ShowAncientBooksAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ShowAncientBookMenu {
	
	public Menu getMenu() {			
		Menu result = new Menu("Book sort by");
		
		MenuItem date = new MenuItem(Type.DATERECEIPT.toString(), null, new ShowAncientBooksAction(Type.DATERECEIPT));
		MenuItem price = new MenuItem(Type.PRICE.toString(), null, new ShowAncientBooksAction(Type.PRICE));			
		result.add(date);
		result.add(price);

		return result;
	}
}
