package com.products;

import com.partType.Corps;
import com.partType.IProductPart;
import com.partType.MatherBoard;
import com.partType.Monitor;

public class Laptop implements IProduct {
	
	private Corps corps;
	private MatherBoard matherBoard;
	private Monitor monitor;
	
	public Corps getCorps() {
		return corps;
	}

	public MatherBoard getMatherBoard() {
		return matherBoard;
	}

	public Monitor getMonitor() {
		return monitor;
	}

	public void installFirstPart(IProductPart part) {		
		System.out.println("Корпус установлен.");
		corps = (Corps) part;
	}

	public void installSecondPart(IProductPart part) {
		System.out.println("Материнская плата установлена.");
		matherBoard = (MatherBoard)part;
	}

	public void installThirdPart(IProductPart part) {
		System.out.println("Монитор установлен.");
		monitor = (Monitor)part;
	}	
}
