import java.util.Scanner;
public class Program {
	public static void main(String[] args) { 		
		Scanner sc = new Scanner(System.in);
		for (String string : sc.nextLine().split(" ")) {
			System.out.println(string);
		}
	}
}
