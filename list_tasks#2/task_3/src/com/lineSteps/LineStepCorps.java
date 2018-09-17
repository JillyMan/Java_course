package com.lineSteps;

import com.partType.Corps;
import com.partType.IProductPart;

public class LineStepCorps implements ILineStep {
	public LineStepCorps() { 
		System.out.println("Линия подачи корпуса готова.");
	}
	
	public IProductPart buildProductPart() {
		System.out.println("Корпус готов к установке.");
		return new Corps();
	}	
}
