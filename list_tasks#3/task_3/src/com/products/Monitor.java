package com.products;

import com.typeProd.IElectronic;

public class Monitor implements IElectronic {
	private int weight;
	
	public Monitor(int weight ) { 
		this.weight = weight ;
	}
	
	public int getWeight() {
		return weight ;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}