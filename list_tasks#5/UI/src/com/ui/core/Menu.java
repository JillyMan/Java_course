package com.ui.core;

public class Menu {
	private String name;
	private MenuItem[] menuItems;
	
	public Menu(String name, MenuItem[] items) {
		this.name = name;
		this.menuItems = items;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public MenuItem getMenuItem(int index) {
		return menuItems[index];
	}
	
	public MenuItem[] getMenuItems() {
		return menuItems;
	}
}