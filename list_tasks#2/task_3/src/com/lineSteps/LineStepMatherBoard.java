package com.lineSteps;

import com.partType.IProductPart;
import com.partType.MatherBoard;

public class LineStepMatherBoard implements ILineStep {
	
	public LineStepMatherBoard () { 
		System.out.println("����� ������ ����������� ����� ������.");
	}
	
	public IProductPart buildProductPart() {
		System.out.println("����������� ����� ������ � ���������.");
		return new MatherBoard();
	}
}
