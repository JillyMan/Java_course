package com.products;

import com.partType.IProductPart;

public class Laptop implements IProduct {
	
	private IProductPart corps;
	private IProductPart matherBoard;
	private IProductPart monitor;
	
	public void installFirstPart(IProductPart part) {		
		corps = part;
	}

	public void installSecondPart(IProductPart part) {
		matherBoard = part;
	}

	public void installThirdPart(IProductPart part) {
		monitor = part;
	}	
}
