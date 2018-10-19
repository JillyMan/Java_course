package com.bookShop.businessObject.serviceObject;

import com.bookShop.businessObject.Order;
import com.bookShop.dataLayer.Storage;
import com.bookShop.dataLayer.fileUtil.OrderFileUtil;

public class ServiceQueryBook {
	
	private Storage<Order> storage = new Storage<Order>(new OrderFileUtil("res/queryBooks.txt"));
	
	public ServiceQueryBook() { 
		
	}

}
