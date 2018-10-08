ackage com.storage;

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

	public int getProductsWeight() { 
		int result = 0;
		for (IProduct iProduct : products) {
			result += iProduct.getWeight();
		}
		return result;
	}
		
	public boolean add(IProduct product) { 
		if(products.size() < getCapacity()) { 
			return products.add(product);
		}
		return false;
	}
		
	public int getCapacity() { 
		return capacity;
	}
}