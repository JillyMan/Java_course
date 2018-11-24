package com.ui.action.query;

import java.util.List;

import com.bookshop.BookShop;
import com.bookshop.core.comparator.RequestBookComparator.Type;
import com.bookshop.core.model.RequestsBook;
import com.ui.core.IAction;

public class ShowQueryAction implements IAction {

	private Type typeComp;
	private BookShop shop = BookShop.getInstance();

	private final String EMPTY_LIST_QUERY = "List of query empty!";

	public ShowQueryAction(Type typeComp) {
		this.typeComp = typeComp;
	}
	
	public void action() {
		List<RequestsBook> req = shop.getRequest(typeComp);
		
		if(req == null || req .isEmpty()) {
			System.out.println(EMPTY_LIST_QUERY);
		} else {
			for (RequestsBook r: req ) {
				System.out.println(r.toString());
			}			
		}
	}	
}
