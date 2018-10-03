package com.products;

import com.type_prod.IEatable;

public class Milk implements IEatable {
	private int weight;
	
	public Milk(int weight ) { 
		this.weight = weight;
	}
	
	public int getWeight () {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
