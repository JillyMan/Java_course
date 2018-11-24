package com.ui.action.book;

import java.util.List;
import com.bookshop.BookShop;
import com.bookshop.core.comparator.BookComparators.Type;
import com.bookshop.core.model.Book;
import com.ui.core.IAction;

public class ShowBooksAction implements IAction {
	
	private Type typeComp;
	private BookShop shop = BookShop.getInstance();

	private final String EMPTY_LIST_BOOK = "List of books empty!";

	public ShowBooksAction(Type typeComp) {
		this.typeComp = typeComp;
	}
	
	public void action() {
		List<Book> books = shop.getBooks(typeComp);
		
		if(books == null || books.isEmpty()) {
			System.out.println(EMPTY_LIST_BOOK);
		} else {
			for (Book book : books) {
				System.out.println(book.toString());
			}			
		}
	}
}