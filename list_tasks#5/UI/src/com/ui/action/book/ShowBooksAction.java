package com.ui.action.book;

import java.util.List;
import com.bookshop.BookShop;
import com.bookshop.core.comparator.BookComparators;
import com.bookshop.core.model.Book;
import com.ui.core.IAction;
import com.ui.core.input.InputUtil;

public class ShowBooksAction implements IAction {
	
	private BookShop shop = BookShop.getInstance();
	
	public ShowBooksAction() {
	}
	
	public void action() {
		System.out.println("Select type sort");
		System.out.println(BookComparators.Type.values());
		List<Book> books = shop.getBooks(InputUtil.getFromArray(BookComparators.Type.values()));

		for (Book book : books) {
			System.out.println(book);
		}
	}
}