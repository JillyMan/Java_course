import com.assembly.AssemblyLaptop;
import com.lineSteps.LineStepCorps;
import com.lineSteps.LineStepMatherBoard;
import com.lineSteps.LineStepMonitor;
import com.products.IProduct;
import com.products.Laptop;

public class Main {
	public static void main(String[] args) {
		LineStepCorps corps = new LineStepCorps();
		LineStepMatherBoard mboard = new LineStepMatherBoard();
		LineStepMonitor monitor = new LineStepMonitor();
		
		AssemblyLaptop assm = new AssemblyLaptop(corps, mboard, monitor);
		IProduct laptop = new Laptop();
		laptop = assm.assemblyProduct(laptop);
		System.out.println("complete");
	}
}
