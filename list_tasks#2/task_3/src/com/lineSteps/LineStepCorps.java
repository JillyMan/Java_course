package com.lineSteps;

import com.partType.Corps;
import com.partType.IProductPart;

public class LineStepCorps implements ILineStep {
	public LineStepCorps() { 
		System.out.println("����� ������ ������� ������.");
	}
	
	public IProductPart buildProductPart() {
		System.out.println("������ ����� � ���������.");
		return new Corps();
	}	
}
