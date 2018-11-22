package com.ui.action.book;

import java.util.List;

import com.bookshop.BookShop;
import com.bookshop.core.comparator.BookComparators.Type;
import com.bookshop.core.model.Book;
import com.ui.core.IAction;

public class ShowSortBooksAction implements IAction {

	private Type type;
	private BookShop shop = BookShop.getInstance();

	public ShowSortBooksAction(Type type) {
		this.type = type;
	}
	
	public void action() {		
		List<Book> books = shop.getBooks(type);

		for (Book book : books) {
			System.out.println(book);
		}
	}
}
