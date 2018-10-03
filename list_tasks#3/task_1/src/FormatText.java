import java.util.StringTokenizer;

public class FormatText {
	
	public String[] getWords(String string) { 
		StringTokenizer tokenizer = new StringTokenizer(string, " ");
		String[] result = new String[tokenizer.countTokens()];
		int i = 0;
		while(tokenizer.hasMoreTokens()) { 
			result[i++] = tokenizer.nextToken();
		}
		return result;
	}
}
