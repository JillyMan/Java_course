package com.ui.core.input;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InputHandler {
	
	private static InputHandler inputHandler;	
	public static final String DATE_PATTERN = "MM dd yyyy";
	private SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
	private Scanner sc;
	
	private InputHandler() {
		sc = new Scanner(System.in);
	}
	
	public int getInt() throws ParseException {
		return Integer.valueOf(sc.nextLine());
	}
	
	public Date getDate() throws ParseException {
		String date = sc.nextLine();
		return dateFormat.parse(date);
	}
	
	public String getString() {
		String str = sc.nextLine();
		return str;
	}
	
	public static InputHandler getInstance() {
		if(inputHandler == null) {
			inputHandler = new InputHandler();
		}
		return inputHandler;		
	}	
}
