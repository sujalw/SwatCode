package swat.algorithms.string;

/**
 * Problem Statement:
 *
 * Consider the following sequence
 *
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * 312211
 * 13112221
 * 1113213211
 * ...
 *
 * Given n, return the nth string in the above sequence
 *
 * @author Sujal
 */
public class Sequence1 {

	public String getNthInTheSequence(int n) {
		if(n <= 0) {
			return "";
		}

		String result = "1";
		for(int i=0 ; i<n-1 ; i++) {
			result = getProcessed(result);
		}
		return result;
	}

	private String getProcessed(String str) {
		assert str != null;

		if(str.length() == 1) {
			return "1" + str;
		}

		char prevChar = str.charAt(0);
		char currChar;
		int cnt = 1;
		String result = "";

		for(int i=1 ; i<str.length() ; i++) {
			currChar = str.charAt(i);

			if(prevChar == currChar) {
				cnt++;
			} else {
				result += String.valueOf(cnt) + prevChar;

				prevChar = currChar;
				cnt = 1;
			}
		}

		result += String.valueOf(cnt) + prevChar;

		return result;
	}
}
