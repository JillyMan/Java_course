package com.ui.core.input;

public class InputUtil {	
	public static <T> T getFromArray(T[] array){
		int i = InputHandler.getInstance().getInt();
		return array[i];
	}	
}
