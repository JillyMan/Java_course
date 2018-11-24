package com.ui.core;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private String name;
	private List<MenuItem> menuItems;
	
	public Menu(String name) {
		this.name = name;
		this.menuItems = new ArrayList<MenuItem>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void add(MenuItem item) {
		if(item != null) {
			menuItems.add(item);	
		}
	}

	public void remove(int index) {
		menuItems.remove(index);
	}
	
	public boolean remove(MenuItem item) {
		return menuItems.remove(item);
	}
	
	public MenuItem getMenuItem(int index) {
		return menuItems.get(index);
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}	
}