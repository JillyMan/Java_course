package com.ui.menu.action.book;

import com.ui.action.book.AddCountBooksAction;
import com.ui.action.book.AddNewBookAction;
import com.ui.action.book.DerigisterBookAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ActionBookMenu {

	public Menu getMenu() {
		Menu result = new Menu("Action menu");
		
		MenuItem addNewBookBtn = new MenuItem("Add new book", null, new AddNewBookAction());
		MenuItem addBookBtn = new MenuItem("Add books", null, new AddCountBooksAction());
		MenuItem derigisterBtn = new MenuItem("Derigister book", null, new DerigisterBookAction());
		result.add(addNewBookBtn);
		result.add(addBookBtn);
		result.add(derigisterBtn);
		
		return result;
	}
}
