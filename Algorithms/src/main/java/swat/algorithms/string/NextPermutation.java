package swat.algorithms.string;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, print its next permutation in lexicographical order.
 * For example,
 * ab => ba
 * bdca => cabd
 *
 * If input is null or contain duplicate characters.
 *
 * @author Sujal
 */
public class NextPermutation {

	public String getNextPermutation(String input) {

		if(input == null || StringUtils.isEmpty(input.trim()) || input.length() == 1) {
			return null;
		}

		if(! isAllUniqueCharacters(input)) {
			return null;
		}

		int firstCharIndex = getFirstCharIndex(input);
		if(firstCharIndex == -1) {
			return null;
		}

		int secondCharIndex = getSecondCharIndex(input, firstCharIndex);

		String strWithSwappedChars = swapCharacters(input, firstCharIndex, secondCharIndex);

		String prefix = strWithSwappedChars.substring(0, firstCharIndex+1);
		String suffix = StringUtils.reverse(strWithSwappedChars.substring(firstCharIndex+1));

		return prefix + suffix;
	}

	private int getFirstCharIndex(String input) {
		assert input != null;

		for(int index=input.length()-2 ; index>=0 ; index--) {
			char currentChar = input.charAt(index);
			char nextChar = input.charAt(index + 1);

			if(currentChar < nextChar) {
				return index;
			}
		}

		return -1;
	}

	private int getSecondCharIndex(String input, int firstCharIndex) {
		assert input != null && firstCharIndex >= 0 && firstCharIndex < input.length();

		char firstChar = input.charAt(firstCharIndex);
		char secondChar = input.charAt(firstCharIndex+1);
		int secondCharIndex = firstCharIndex+1;
		for(int index=firstCharIndex+1 ; index<input.length() ; index++) {
			char currentChar = input.charAt(index);

			if(currentChar>firstChar && currentChar < secondChar) {
				secondChar = currentChar;
				secondCharIndex = index;
			}
		}

		return secondCharIndex;
	}

	private String swapCharacters(String input, int firstCharIndex, int secondCharIndex) {

		assert input!=null
				&& firstCharIndex>=0 && firstCharIndex<input.length()
				&& secondCharIndex<input.length() && secondCharIndex > firstCharIndex;

		String prefix = input.substring(0, firstCharIndex);
		String middle = input.substring(firstCharIndex+1, secondCharIndex);
		String suffix = input.substring(secondCharIndex+1);

		return prefix
				+ input.charAt(secondCharIndex)
				+ middle
				+ input.charAt(firstCharIndex)
				+ suffix;
	}

	private boolean isAllUniqueCharacters(String input) {
		if(input==null) {
			return true;
		}

		Set<Character> uniqueCharacters = new HashSet<Character>();
		for(int i=0 ; i<input.length() ; i++) {
			uniqueCharacters.add(input.charAt(i));
		}

		return input.length() == uniqueCharacters.size();
	}
}