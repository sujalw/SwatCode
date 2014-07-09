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
}
