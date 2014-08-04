package swat.algorithms.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Sujal
 */
public class BinaryStringAdditionTest {

	@DataProvider(name = "binaryStringsSuccessData")
	public static Object[][] binaryStringsSuccessData() {
		return new Object[][] {
				{"0", "0", "0"},
				{"0", "1", "1"},
				{"1", "0", "1"},
				{"1", "1", "10"},
				{"1100011", "0000010", "1100101"},
				{"1100011", "10", "1100101"},
				{"1001", "10", "1011"},
				{"1010", "10", "1100"}
		};
	}

	@DataProvider(name = "binaryStringsInvalidData")
	public static Object[][] binaryStringsInvalidData() {
		return new Object[][] {
				{null, "", null},
				{"", null, null},
				{null, null, null},
				{"1234", "0", null},
				{"0", "2312", null},
				{"", "", null},
				{"", "10", null},
				{"01", "", null},
		};
	}

	@Test(dataProvider = "binaryStringsSuccessData")
	public void addTestSuccess(String inputString1, String inputString2, String expectedResult) {
		BinaryStringAddition binaryStringAddition = new BinaryStringAddition();
		String computedResult = binaryStringAddition.add(inputString1, inputString2);

		assert expectedResult.equals(computedResult);
	}

	@Test(dataProvider = "binaryStringsInvalidData")
	public void addtestInvalidData(String inputString1, String inputString2, String expectedResult) {
		BinaryStringAddition binaryStringAddition = new BinaryStringAddition();
		String computedResult = binaryStringAddition.add(inputString1, inputString2);

		assert expectedResult == computedResult;
	}
}
