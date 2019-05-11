package com.ui.menu.action.order;

import java.util.List;

import com.bookshop.BookShop;
import com.bookshop.core.model.Order;
import com.bookshop.dao.StorageException;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class CancelOrderMenu {
	
	private BookShop shop = BookShop.getInstance();
	
	public Menu getMenu() {		
		Menu result = new Menu("Cancel order");
		
		List<Order> orders = BookShop.getInstance().getAwaiting();
		
		for(int i = 0; i < orders.size(); ++i) {
			final int index = i;
			MenuItem item = new MenuItem("ID: " + orders.get(index).getId(), null, () -> {
				try {
					//TODO: closeOrder return bool
					shop.cancelOrder(orders.get(index));
					result.remove(index);
					System.out.println("Order closed");
//					} else {
//						System.out.println("Failed equip.");
//					}
				} catch (StorageException e) {
					System.out.println("Failed equip.");
				}
			});
			result.add(item);
		}
				
		return result;
	}
	
}
