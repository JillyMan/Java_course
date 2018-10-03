package com.storage;

import java.util.ArrayList;
import java.util.List;
import com.IProduct;

public class Storage {
	private List<IProduct> products;
	private int capacity;
	
	public Storage(int capacity) {
		this.capacity = capacity;
		this.products = new ArrayList<IProduct>();
	}

	public int getWeight() { 
		int result = 0;
		for (IProduct iProduct : products) {
			result += iProduct.getWeight();
		}
		return result;
	}
	
	public int size() { 
		return products.size();
	}
	
	public boolean add(IProduct product) { 
		if(products.size() < capacity) { 
			products.add(product);
			return true;
		}
		return false;
	}
		
	public int getCapacity() { 
		return capacity;
	}
}