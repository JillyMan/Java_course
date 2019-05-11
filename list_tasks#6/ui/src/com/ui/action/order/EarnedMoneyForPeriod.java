package com.ui.action.order;

import java.text.ParseException;
import java.util.Date;

import com.bookshop.BookShop;
import com.ui.core.IAction;
import com.ui.core.input.InputHandler;

public class EarnedMoneyForPeriod implements IAction {
	private BookShop shop = BookShop.getInstance();
	private InputHandler input = InputHandler.getInstance();

	private final String FIRST_DATE = "Input min date " + InputHandler.DATE_PATTERN; 
	private final String SECOND_DATE = "Input max date " + InputHandler.DATE_PATTERN;
	private final String ERROR_MESSAGE = "Incorrect input date. Use pattern" + InputHandler.DATE_PATTERN;
	
	private final String MESSAGE = "Earned money: "; 

	public void action() {
		Date min = null;
		Date max = null;
		try {
			System.out.println(FIRST_DATE);
			min = input.getDate();
			System.out.println(SECOND_DATE);
			max = input.getDate();						
		} catch (ParseException e) {
			System.out.println(ERROR_MESSAGE);
			return;
		}
		int wage = shop.getEarnedMoney(min, max);
		System.out.println(MESSAGE + wage);
	}}
