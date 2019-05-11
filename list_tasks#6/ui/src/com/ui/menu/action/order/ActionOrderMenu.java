package com.ui.menu.action.order;

import com.ui.core.Menu;
import com.ui.core.MenuItem;

public class ActionOrderMenu {
	
	public Menu getMenu() {
		MenuItem addOrderBtn = new MenuItem("Add order", new BuildOrderMenu().getMenu(), null);		
		MenuItem equipOrder = new MenuItem("Equip order", new EquipOrderMenu().getMenu(), null);
		MenuItem cancelOrder = new MenuItem("Cancle order", new CancelOrderMenu().getMenu(), null);
		MenuItem cloneOrder = new MenuItem("Clone and replace order", new CloneOrderMenu().getMenu(), null);
					
		Menu result = new Menu("Action menu");
		result.add(addOrderBtn);
		result.add(equipOrder);		
		result.add(cancelOrder);
		result.add(cloneOrder);
			
		return result;
	}
}
