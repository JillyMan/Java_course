package com.assembly;

import com.lineSteps.ILineStep;
import com.lineSteps.LineStepCorps;
import com.lineSteps.LineStepMatherBoard;
import com.lineSteps.LineStepMonitor;
import com.products.IProduct;

public class AssemblyLaptop implements IAssemblyLine {

	private ILineStep corps;
	private ILineStep matherBoard; 
	private ILineStep monitor;
	
	public AssemblyLaptop(LineStepCorps corps, LineStepMatherBoard matherBoard, LineStepMonitor monitor) { 
		this.corps = corps;
		this.matherBoard = matherBoard;
		this.monitor = monitor;
		
		System.out.println("—борщик готов к работе.");		
	}
	
	public IProduct assemblyProduct(IProduct product) {
		product.installFirstPart(corps.buildProductPart());
		product.installSecondPart(matherBoard.buildProductPart());
		product.installThirdPart(monitor.buildProductPart());
		System.out.println("Laptop собран.");
		return product;
	}
}