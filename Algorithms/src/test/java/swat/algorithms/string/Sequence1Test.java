package swat.algorithms.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sujal
 */
public class Sequence1Test {

	@DataProvider(name = "sequenceData")
	public static Object[][] sequenceData() {
		return new Object[][] {
				{0, ""},
				{-4, ""},
				{1, "1"},
				{2, "11"},
				{3, "21"},
				{4, "1211"},
				{5, "111221"},
				{6, "312211"},
				{7, "13112221"},
				{8, "1113213211"}
		};
	}

	@Test(dataProvider = "sequenceData")
	public void testSequence(int n, String expectedSequence) {
		Sequence1 sequence1 = new Sequence1();
		String computedSequence = sequence1.getNthInTheSequence(n);

		System.out.println("computedSequence = " + computedSequence);

		assert expectedSequence.equals(computedSequence);
	}
}
