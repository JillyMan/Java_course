import java.util.Random;

public class Program {	
	public static void main(String[] args) { 
		int number = GenaratorNumbers().getRandom(100, 999);
		System.out.println(number);
		int sum = Math.getDigitSum(number);
		System.out.println("Sum: " + sum);
	}
}