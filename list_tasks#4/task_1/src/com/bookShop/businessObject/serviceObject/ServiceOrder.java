package com.bookShop.businessObject.serviceObject;

import java.util.Date;
import java.util.List;

import com.bookShop.businessObject.Order;
import com.bookShop.dataLayer.Storage;
import com.bookShop.dataLayer.fileUtil.OrderFileUtil;

public class ServiceOrder {
	
	private Storage<Order> storage = new Storage<Order>(new OrderFileUtil("res/orders.txt"));
	
	public List<Order> getCompleateForPeriod(Date min, Date max) { 
		List<Order> result = storage.getAll();
		result.removeIf((Order o) -> { 	
			if(o.getStatus() == Order.Status.COMPLEATE) { 
				return !(o.getDateRelease().before(min) || o.getDateRelease().after(max));				
			}
			return false;
		});
		return result;
	}	

}
