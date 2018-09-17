package com.lineSteps;

import com.partType.IProductPart;
import com.partType.MatherBoard;

public class LineStepMatherBoard implements ILineStep {
	public IProductPart buildProductPart() {		
		return new MatherBoard();
	}
}
