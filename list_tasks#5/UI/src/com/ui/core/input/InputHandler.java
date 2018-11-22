package com.ui.core.input;

import java.util.Scanner;

public class InputHandler {
	
	private static InputHandler inputHandler;	
	private Scanner sc;
	
	private InputHandler() {
		sc = new Scanner(System.in);
	}
	
	public int getInt() {		
		return sc.nextInt();
	}
	
	public String getString() {
		return sc.nextLine();
	}
	
	public static InputHandler getInstance() {
		if(inputHandler == null) {
			inputHandler = new InputHandler();
		}		
		return inputHandler;		
	}
		
}
