package com.ui.action.book;

import java.text.ParseException;
import java.util.List;

import com.bookshop.BookShop;
import com.bookshop.core.model.Book;
import com.bookshop.dao.StorageException;
import com.ui.core.IAction;
import com.ui.core.input.InputHandler;
import com.ui.core.input.InputUtil;

public class DerigisterBookAction implements IAction {

	private final BookShop shop = BookShop.getInstance();
	private final InputHandler input = InputHandler.getInstance();
	
	private final String MESSAGE = "Derigister count ->";
	private final String ERROR_INPUT = "Incorrect input. Check sign a digit !!";
	private final String ERROR_MESSAGE = "Incorrect intput count!!!";
	private final String ERROR_ADD_BOOK = "Can't add book!!";
	private final String INPUT_FROM_LIST = "Input from list -> ";
	
	public void action() {
		List<Book> books = shop.getBooks();
		for (Book book : books) {
			System.out.println(book);
		}
		Book book = null;
		try {
			System.out.println(INPUT_FROM_LIST + " [0 ; " + (books.size() - 1) + "]");
			book = (Book) InputUtil.getFromArray(books.toArray());
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("");
		} catch (ParseException e) { }	
		
		System.out.println(MESSAGE);
		int count;
		try {
			count = input.getInt();
			shop.derigisterBook(book, count);
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR_INPUT);
		} catch (StorageException e) {
			System.out.println(ERROR_ADD_BOOK);
		} catch (ParseException e) {
			System.out.println(ERROR_MESSAGE );
		}
	}	
}
