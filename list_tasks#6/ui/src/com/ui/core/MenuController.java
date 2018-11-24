package com.ui.core;

import java.io.IOException;

import com.ui.core.input.InputHandler;

public class MenuController {
	private Navigator navigator;

	private boolean running;
	
	public MenuController(Navigator nav) {
		this.navigator = nav;
	}
	
	public void run() {
		running = true;
		int input;
		while(running) {
			navigator.render();
			
			try {
				input = Integer.valueOf(InputHandler.getInstance().getString());		
				running = navigator.navigate(input);	
			}catch (ArrayIndexOutOfBoundsException e) {
				System.err.println("Out of range, try again!");
			} catch (NumberFormatException e) {
			//	e.printStackTrace();
				System.err.println("Input integer !!");
			}
		}
	}
}
