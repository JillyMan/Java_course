package com.assembly;

import com.lineSteps.ILineStep;
import com.products.IProduct;

public class Assembly implements IAssemblyLine {

	private ILineStep first;
	private ILineStep second; 
	private ILineStep third;
	
	public Assembly(ILineStep first, ILineStep second, ILineStep third) { 
		this.first = first;
		this.second = second;
		this.third = third;
	}
	
	public IProduct assemblyProduct(IProduct product) {
		product.installFirstPart(first.buildProductPart());
		product.installSecondPart(second.buildProductPart());
		product.installFirstPart(third.buildProductPart());
		return product;
	}
}
