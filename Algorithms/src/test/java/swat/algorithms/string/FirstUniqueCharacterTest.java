package swat.algorithms.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sujal
 */
public class FirstUniqueCharacterTest {

	@DataProvider(name = "uniqueCharacterData")
	private static Object[][] uniqueCharacterUsecases() {
		// arg1 = source string
		// arg2 = expected first unique character
		return new Object[][]{
				{null, null},
				{"", null},
				{"a", 'a'},
				{"abc", 'a'},
				{"abaabcgfgw", 'c'},
				{"abaabcbddc", null},
				{"!@#$#@%!^", '$'}
		};
	}

	@Test(dataProvider = "uniqueCharacterData")
	public void testFirstUniqueCharacter(String sourceString, Character expectedFirstUniqueCharacter) {
		FirstUniqueCharacter firstUniqueCharacter = new FirstUniqueCharacter(sourceString);
		Character computedFirstUniqueCharacter = firstUniqueCharacter.getFirstUniqueCharacter();
		assert expectedFirstUniqueCharacter == computedFirstUniqueCharacter;
	}
}
