import com.products.Milk;
import com.products.Monitor;
import com.storage.Storage;

public class Program {
	
	public static void main(String[] args) { 

		Storage storage = new Storage(4);
		storage.add(new Milk(400));
		storage.add(new Monitor(200));
		storage.add(new Milk(400));
		storage.add(new Monitor(200));
		
		System.out.println("Weight: " + storage.getWeight());
	}
}
