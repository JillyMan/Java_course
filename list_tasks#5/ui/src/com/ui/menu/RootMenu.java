package com.ui.menu;

import com.ui.menu.action.ActionMenu;
//import com.ui.menu.action.ActionBookMenu;
import com.ui.menu.show.ShowMenu;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class RootMenu {

	ShowMenu showMenu;
	ActionMenu actionMenu;
		
	public RootMenu() {
		showMenu = new ShowMenu();
		actionMenu = new ActionMenu();
	}
	
	public Menu getMenu() {
		MenuItem showButton = new MenuItem("Show", showMenu.getMenu(), null);
		MenuItem actionButton = new MenuItem("Action", actionMenu.getMenu(), null);
		
		Menu result = new Menu("Main menu");
		result.add(showButton);
		result.add(actionButton);
		return result;
	}	
}