package swat.algorithms.string;

import swat.swatcodeutilities.SwatStringUtils;

/**
 * @author Sujal
 */
public class BinaryStringAddition {

	String add(String str1, String str2) {
		if(str1 == null || str2 == null) {
			return null;
		}

		if(!SwatStringUtils.isBinaryString(str1) || !SwatStringUtils.isBinaryString(str2)) {
			return null;
		}

		if(SwatStringUtils.isEmpty(str1)) {
			return str2;
		}

		if(SwatStringUtils.isEmpty(str2)) {
			return str1;
		}

		if(str1.length() < str2.length()) {
			str1 = SwatStringUtils.addLeadingZeros(str1, str2.length() - str1.length());
		} else if(str2.length() < str1.length()) {
			str2 = SwatStringUtils.addLeadingZeros(str2, str1.length() - str2.length());
		}

		return addBinaryStringsOfEqualLength(str1, str2);
	}

	private String addBinaryStringsOfEqualLength(String str1, String str2) {
		String result = "";
		int i1 = str1.length() - 1;
		int i2 = str2.length() - 1;
		char carry = '0';
		char sum;

		while(i1 >= 0) {
			sum = getSum(str1.charAt(i1), str2.charAt(i2), carry);
			carry = getCarry(str1.charAt(i1), str2.charAt(i2), carry);

			result = sum + result;
			i1--;
			i2--;
		}

		if(carry == '1') {
			return carry + result;
		}

		return result;
	}

	private char getSum(char c1, char c2, char c3) {
		int i1 = c1 - '0';
		int i2 = c2 - '0';
		int i3 = c3 - '0';

		int sum = i1 ^ i2 ^ i3;

		return sum==1 ? '1' : '0';
	}

	private char getCarry(char c1, char c2, char c3) {
		boolean atleastTwoOnes = (c1=='1' && c2=='1') || (c1=='1' && c3=='1') || (c2=='1' && c3=='1');

		return atleastTwoOnes ? '1' : '0';
	}
}
