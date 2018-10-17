package com.bookShop.serviceObject;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bookShop.businessObject.Order;
import com.bookShop.dataLayer.OrderFileUtil;

public class ServiceOrders implements Sortable<Order>{
	
	private OrderFileUtil orderConnector;

	public ServiceOrders() { 
		orderConnector = new OrderFileUtil("res/orders.txt");
	}
	
	public List<Order> getOrders() { 
		return Arrays.asList(orderConnector.readFromFile());
	}
	
	//TODO: Repeat sorting!!!
	public List<Order> getCompleateForPeriod(Date min, Date max) { 
		List<Order> result = getOrders();
		result.removeIf((Order o) -> 
			{ 	
				if(o.getStatus() == Order.Status.COMPLEATE) { 
					return !(o.getDateRelease().before(min) || o.getDateRelease().after(max));				
				}
				return false;
			});
		return result;
	}	

	public Integer getCountCompleateForPeriod(Date min, Date max) { 
		return getCompleateForPeriod(min, max).size();
	}
	
	public List<Order> sortBy(Comparator<Order> comparator) {			
		List<Order> list = getOrders();
		list.sort(comparator);			
		return list;
	}

}