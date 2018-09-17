package com.lineSteps;
import com.partType.IProductPart;
import com.partType.Monitor;

public class LineStepMonitor implements ILineStep {
	public IProductPart buildProductPart() {
		return new Monitor();
	}
}
