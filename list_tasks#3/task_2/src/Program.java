import java.util.Random;

public class Program {
	public static final Random random = new Random();
	
	public static void main(String[] args) { 
		int number = new GenaratorNumbers().getRandom(100, 999);
		System.out.println(number);
		int sum = Math.getDigitSum(number);
		System.out.println("Sum: " + sum);
	}
}