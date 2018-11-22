package com.ui.core;

import java.util.ArrayDeque;
import java.util.Deque;

public class Navigator {
	
	private final int PREVIOUS_MENU = 0;
	
	private Menu currentMenu;	
	private Deque<Menu> history;
	
	public Navigator(Menu currentMenu) {
		this.currentMenu = currentMenu;
		this.history = new ArrayDeque<Menu>();
	}
	
	public void render() {
		System.out.println(currentMenu.getName());
		for(int i = 0; i < currentMenu.getMenuItems().length; ++i) {
			System.out.println((i+1) + " - " + currentMenu.getMenuItem(i).getTitle());
		}
		
		System.out.println("0 - close menu");
	}
	
	public boolean navigate(Integer index) {	
		if(index == PREVIOUS_MENU) {
			if(!history.isEmpty()) {
				currentMenu = history.pop();
				return true;
			} else {
				return false;				
			}
		}
		
		MenuItem item = currentMenu.getMenuItem(index - 1);								
		if(item.getAction() != null) {
			item.getAction().action();
		}
		else {
			history.push(currentMenu);
			currentMenu = item.getNextMenu();
		}
		
		return true;
	}	
}
