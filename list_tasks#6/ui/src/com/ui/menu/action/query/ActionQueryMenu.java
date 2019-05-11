package com.ui.menu.action.query;

import com.ui.action.query.AddQueryOnBookAction;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ActionQueryMenu {
	
	public Menu getMenu() {
		//TODO: Make select book menu
		Menu result = new Menu("Action menu"); 
		result.add(new MenuItem("Add query on book", null, new AddQueryOnBookAction()));
		
		return result;
	}
}
