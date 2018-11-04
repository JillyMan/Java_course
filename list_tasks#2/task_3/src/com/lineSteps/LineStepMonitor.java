package com.lineSteps;
import com.partType.IProductPart;
import com.partType.Monitor;

public class LineStepMonitor implements ILineStep {
	
	public LineStepMonitor() { 
		System.out.println("����� ������ �������� ������.");
	}
	
	public IProductPart buildProductPart() {
		System.out.println("������� ����� � ���������.");
		return new Monitor();
	}
}
