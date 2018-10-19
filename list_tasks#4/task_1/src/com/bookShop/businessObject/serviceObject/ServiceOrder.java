package com.bookShop.businessObject.serviceObject;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bookShop.businessObject.Order;
import com.bookShop.dataLayer.Storage;
import com.bookShop.dataLayer.fileUtil.OrderFileUtil;

public class ServiceOrder {
	
	private final Storage<Order> storage = new Storage<Order>(new OrderFileUtil("res/orders.txt"));
	
	public ServiceOrder() { 
		
	}
	
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
	
	public int getEarnedMoney(Date min, Date max) { 
		List<Order> result = getCompleateForPeriod(min, max);
		return result.stream().mapToInt(o -> o.getPrice()).sum();
	}

	public int getCountCompleateForPeriod(Date min, Date max) { 
		return getCompleateForPeriod(min, max).size();
	}
	
	public List<Order> sortBy(Comparator<Order> comparator) { 
		List<Order> result = storage.getAll();
		result.sort(comparator);
		return result;
	}

}
