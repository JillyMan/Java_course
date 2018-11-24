package com.ui.menu.action.order;

import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ActionOrderMenu {
	
	public Menu getMenu() {
		MenuItem addOrderBtn = new MenuItem("Add order", new BuildOrderMenu().getMenu(), null);		
		MenuItem equipOrder = new MenuItem("Equip order", new EquipOrderMenu().getMenu(), null);
		MenuItem cancelOrder = new MenuItem("Cancle order", new CancelOrderMenu().getMenu(), null);
	
		Menu result = new Menu("Action menu");
		result.add(addOrderBtn);
		result.add(equipOrder);		
		result.add(cancelOrder);
		
		return result;
	}
}
