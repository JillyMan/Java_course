package com.ui.builder;

import com.bookshop.BookShop;
import com.ui.builder.show.ShowBookMenu;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class RootBuilder implements IBuilder{

	ShowBookMenu showMenu;

	public RootBuilder() {
	}
	
	private void build() {
		showMenu = new ShowBookMenu();
	}
	
	public Menu getMenu() {
		build();
		MenuItem showButton = new MenuItem("Show", showMenu.getMenu(), null);
		
		Menu result = new Menu("Main menu", new MenuItem[] { showButton });
		return result;
	}	
}