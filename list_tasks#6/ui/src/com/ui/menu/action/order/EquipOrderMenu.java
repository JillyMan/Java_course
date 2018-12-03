package com.ui.menu.action.order;

import java.util.List;

import com.bookshop.BookShop;
import com.bookshop.core.model.Order;
import com.bookshop.dao.StorageException;
import com.bookshop.service.exception.ServiceOrderException;
import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class EquipOrderMenu {
	
	private BookShop shop = BookShop.getInstance();
	
	//TODO: Add method rebild button, it's very important!
	public Menu getMenu() {
		Menu result = new Menu("Equip order");

		List<Order> orders = BookShop.getInstance().getAwaiting();
		
		for(int i = 0; i < orders.size(); ++i) {
			final int index = i;
			MenuItem item = new MenuItem("ID: " + orders.get(i).getId(), null, () -> {
				try {
					shop.equipOrder(orders.get(index));
					result.remove(index);
					System.out.println("Order equip");
				} catch (StorageException e) {
					System.out.println("Failed equip.");
				} catch (ServiceOrderException e) {
					System.out.println(e.getMessage());
				}

			});
			result.add(item);
		}
		
		return result;
	}
}
