package swat.algorithms.recursion;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sujal
 */
public class RegexMatchingTest {

	@DataProvider(name = "regexMatchingData")
	public static Object[][] regexMatchingData() {
		// arg1 = input string
		// arg2 = pattern
		// arg3 = result
		return new Object[][]{
				{"aa", "a", false},
				{"aa", "aa", true},
				{"aaa", "aa", false},
				{"aa", "a*", true},
				{"aa", ".*", true},
				{"ab", ".*", true},
				{"aab", "c*a*b*", true},
				{"a", "a*a", true}
		};
	}

	@Test(dataProvider = "regexMatchingData")
	public void testIsMatchUsingIteration(String str, String pattern, boolean expectedIsMatch) {
		RegexMatching regexMatching = new RegexMatching();

		boolean computedIsMatch = regexMatching.isMatch(str, pattern);
		assert expectedIsMatch == computedIsMatch;
	}
}
