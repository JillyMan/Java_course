package com.products;

import com.partType.IProductPart;

public interface IProduct {
	public void installFirstPart(IProductPart part);
	public void installSecondPart(IProductPart part);
	public void installThirdPart(IProductPart part);
}
