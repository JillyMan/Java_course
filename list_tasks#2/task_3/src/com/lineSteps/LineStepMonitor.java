package com.lineSteps;
import com.partType.IProductPart;
import com.partType.Monitor;

public class LineStepMonitor implements ILineStep {
	
	public LineStepMonitor() { 
		System.out.println("Линия подачи монитора готова.");
	}
	
	public IProductPart buildProductPart() {
		System.out.println("Монитор готов к установке.");
		return new Monitor();
	}
}
