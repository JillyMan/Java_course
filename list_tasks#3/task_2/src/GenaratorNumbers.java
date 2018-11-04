import java.util.Random;

public class GenaratorNumbers {
	private static Random random = new Random();
	
	public static int getRandom(int left, int right) { 		
		return left + random.nextInt(right - left);		
	}	
}
