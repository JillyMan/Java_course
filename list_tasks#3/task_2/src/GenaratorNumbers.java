import java.util.Random;

public class GenaratorNumbers {
	private Random random = new Random();
	
	public int getRandom(int left, int right) { 		
		return left + random.nextInt(right - left);		
	}	
}
