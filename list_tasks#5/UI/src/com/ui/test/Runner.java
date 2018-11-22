package com.ui.test;

import com.ui.core.MenuController;
import com.ui.core.Navigator;
import com.ui.builder.RootBuilder;

public class Runner {

	public void run() { 			
		Navigator nav = new Navigator(new RootBuilder().getMenu());
		MenuController menu = new MenuController(nav);
		menu.run();
		System.out.println("Good bye");
	}
	
	public static void main(String[] args) {		
		new Runner().run();			
	}
}




