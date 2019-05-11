package com.ui.core.input;

import java.text.ParseException;

public class InputUtil {	
	public static <T> T getFromArray(T[] array) throws ParseException, ArrayIndexOutOfBoundsException {
		int i = InputHandler.getInstance().getInt();
		return array[i];
	}	
}
