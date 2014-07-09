package swat.algorithms.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sujal
 */
public class NextPermutationTest {

	@DataProvider(name = "nextPermutationData")
	public static Object[][] nextPermutationData() {
		// arg1 = input string
		// arg2 = expected next permutation
		return new Object[][]{
				{null, null},
				{"", null},
				{"   ", null},
				{"a", null},
				{"ab", "ba"},
				{"badc", "bcad"},
				{"dcba", null},
				{"aaa", null},
				{"bdca", "cabd"},
				{"baac", null}
		};
	}

	@Test(dataProvider = "nextPermutationData")
	public void testNextPermutation(String inputString, String expectedNextPermutation) {
		NextPermutation nextPermutation = new NextPermutation();
		String computedNextPermutation = nextPermutation.getNextPermutation(inputString);

		if(expectedNextPermutation == null) {
			assert expectedNextPermutation == computedNextPermutation;
		} else {
			assert expectedNextPermutation.equals(computedNextPermutation);
		}
	}
}
