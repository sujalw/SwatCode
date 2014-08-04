package swat.swatcodeutilities;

/**
 * @author Sujal
 */
public class SwatStringUtils {
	public static boolean isNullOrEmpty(String s) {
		return s==null || s.trim().length()==0;
	}

	public static boolean isEnglishCharacter(char character) {
		return character>='a' && character<='z';
	}

	public static boolean isValidEnglishWord(String word) {
		if(isNullOrEmpty(word)) {
			return false;
		}

		for(int i=0 ; i<word.length() ; i++) {
			if(! isEnglishCharacter(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isDigit(char c) {
		return c>='0' && c<='9';
	}

	public static boolean isEmpty(String str) {
		if(str == null) {
			return false;
		}

		return str.length() == 0;
	}

	public static boolean isBinaryString(String str) {
		if(str == null || isEmpty(str)) {
			return false;
		}

		for(char c : str.toCharArray()) {
			if(c != '0' && c != '1') {
				return false;
			}
		}

		return true;
	}

	public static String addLeadingZeros(String str, int n) {
		if(str == null || n <= 0) {
			return str;
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			stringBuilder.append('0');
		}

		return stringBuilder.toString() + str;
	}
}
