package com.ui.action.book;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.bookshop.core.model.Book;
import com.bookshop.dao.StorageException;
import com.bookshop.BookShop;
import com.bookshop.core.model.Author;
import com.ui.core.IAction;
import com.ui.core.input.InputHandler;

public class AddNewBookAction implements IAction {
	
	private final String DROW = " -> ";
	private final String MESSAGE_TITLE_BOOK   = "Title" + DROW;
	private final String MESSAGE_PRICE_BOOK	  = "Price" + DROW;
	private final String MESSAGE_COUNT_BOOK	  = "Count" + DROW;
	private final String MESSAGE_LNAME_AUTHOR = "Last name author" + DROW;
	private final String MESSAGE_FNAME_AUTHOR = "First name author" + DROW;
	private final String MESSAGE_DATE_RELEASE = "Date release" + DROW;
	
	private BookShop shop = BookShop.getInstance();
	private InputHandler inputHanlder = InputHandler.getInstance();
	
	public void action() {

		System.out.println(MESSAGE_LNAME_AUTHOR);
		String lNameAuthor = inputHanlder.getString();
		
		System.out.print(MESSAGE_FNAME_AUTHOR);
		String fNameAuthor = inputHanlder.getString();

		System.out.print(MESSAGE_TITLE_BOOK);
		String title = inputHanlder.getString();
		
		System.out.print(MESSAGE_DATE_RELEASE + " ( " + InputHandler.DATE_PATTERN + " )");
		Date dateRelease = null;
		Date dateReceipt = new Date();
		
		try {
			dateRelease = inputHanlder.getDate();
		} catch (ParseException e) { 
			System.out.println("Fail in input date");
			return;
		}

		try {		
			System.out.print(MESSAGE_PRICE_BOOK);
			int priceBook = InputHandler.getInstance().getInt();

			System.out.print(MESSAGE_COUNT_BOOK);
			int count = InputHandler.getInstance().getInt();

			Book book = new Book();
			int id = new Random().nextInt(Integer.MAX_VALUE);
			System.out.println("id: " + id);
			book.setId(id);
			book.setTitle(title);
			book.setAuthor(new Author(lNameAuthor, fNameAuthor));
			book.setDateRelease(dateRelease);
			book.setDateReceipt(dateReceipt);
			book.setPrice(priceBook);
			shop.addBook(book, count);
		} catch (StorageException e) {
			System.out.println("Incorrect input book");			
		} catch (ParseException e) {
			System.out.println("Fail in last input digits");
			return;
		}
	}
}