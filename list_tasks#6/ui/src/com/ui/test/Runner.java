package com.ui.test;

import com.bookshop.BookShop;
import com.ui.core.MenuController;
import com.ui.core.Navigator;
import com.ui.menu.RootMenu;;

public class Runner {
	
	public void run() { 	
		Navigator nav = new Navigator(new RootMenu().getMenu());
		new MenuController(nav).run();
		System.out.println("Good bye");
	}
	
	public static void main(String[] args) {			
		BookShop.init("filePath.properties");
		new Runner().run();			
	}
}




