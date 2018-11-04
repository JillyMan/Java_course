package com.lineSteps;

import com.partType.IProductPart;
import com.partType.MatherBoard;

public class LineStepMatherBoard implements ILineStep {
	
	public LineStepMatherBoard () { 
		System.out.println("Ћини€ подачи материнской платы готова.");
	}
	
	public IProductPart buildProductPart() {
		System.out.println("ћатеринска€ плата готова к установке.");
		return new MatherBoard();
	}
}
