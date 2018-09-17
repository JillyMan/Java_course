import com.assembly.Assembly;
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
	
		Assembly assm = new Assembly(corps, mboard, monitor);
		IProduct laptop = new Laptop();
		laptop = assm.assemblyProduct(laptop);
		System.out.println("complete");
	}
}
