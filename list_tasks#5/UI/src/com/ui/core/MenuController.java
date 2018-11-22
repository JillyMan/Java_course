package com.ui.core;

import com.ui.core.input.InputHandler;

public class MenuController {
	private Navigator navigator;
	private boolean running;
	
	public MenuController(Navigator navigator) {
		this.navigator = navigator;
	}
	
	public void run() {
		running = true;
		int input;
		while(running) {
			navigator.render();
			input = InputHandler.getInstance().getInt();		
			running = navigator.navigate(input);		
		}
	}
}
