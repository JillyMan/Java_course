package com.bookshop.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bookshop.core.model.Order;
import com.bookshop.dao.Storage;
import com.bookshop.dao.util.OrderFileUtil;

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
