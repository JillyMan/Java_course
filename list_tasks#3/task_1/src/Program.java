import java.util.Scanner;
public class Program {
	public static void main(String[] args) { 		
		FormatText ft = new FormatText();
		Scanner sc = new Scanner(System.in);
		for (String string : ft.getWords(sc.nextLine())) {
			System.out.println(string);			
		}
	}
}
