package swat.t9dictionary;

import java.util.List;
import java.util.Scanner;

import swat.swatcodeutilities.SwatPrintUtils;
import swat.swatcodeutilities.SwatStringUtils;

public class T9DictionaryTest {
	T9Dictionary dict = new T9Dictionary();

	public T9DictionaryTest() {

		String[] words = new String[]{"bat", "cat", "cot", "ball", "banana"};
		dict.addWords(words);
		
		String numberStr = "226";
		List<String> wordsForNumber = dict.getWords(numberStr);
		SwatPrintUtils.print(wordsForNumber, "Words for the number : " + numberStr);
		
		testEditor();
	}

	private void testEditor() {
		Scanner scanner = new Scanner(System.in);
		
		String inputLine = "";
		StringBuilder inputNumber = new StringBuilder();
		while(!(inputLine = scanner.nextLine()).equals("x")) {
			if(inputLine.equals(" ")) {				
				if(inputNumber.length() != 0) {
					List<String> wordsForNumber = dict.getWords(inputNumber.toString());
					if(wordsForNumber!=null && wordsForNumber.size()>0) {
						dict.incrementWordFrequency(wordsForNumber.iterator().next());						
					}
				}
				resetInputNumber(inputNumber);
			} else {
				if(SwatStringUtils.isDigit(inputLine.charAt(0))) {
					inputNumber.append(inputLine);
				} else {
					resetInputNumber(inputNumber);
				}
			}

			if(inputNumber != null && inputNumber.length() != 0) {
				List<String> wordsForNumber = dict.getWords(inputNumber.toString());
				SwatPrintUtils.print(wordsForNumber, "");
			}
		}
		scanner.close();
	}

	private void resetInputNumber(StringBuilder inputNumber) {
		if(inputNumber != null) {
			inputNumber.setLength(0);
		}
	}

	public static void main(String[] args) {
		new T9DictionaryTest();
	}
}
