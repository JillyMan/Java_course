package com.lineSteps;

import com.partType.Corpus;
import com.partType.IProductPart;

public class LineStepCorps implements ILineStep {
	public IProductPart buildProductPart() {
		return new Corpus();
	}	
}
