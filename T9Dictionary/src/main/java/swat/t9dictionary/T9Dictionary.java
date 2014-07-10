package swat.t9dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import swat.swatcodeutilities.SwatStringUtils;

public class T9Dictionary {
	
	private T9Node root;
	private Map<String, T9Word> wordStatistics;
	private final int noOfTopFrequentWords = 2;
	
	public T9Dictionary() {
		root = new T9Node();
		wordStatistics = new HashMap<String, T9Word>();
	}
	
	public void addWords(String[] words) {
		for(String word : words) {
			String lowerCaseWord = word.toLowerCase();
			if(SwatStringUtils.isValidEnglishWord(lowerCaseWord)) {
				addWordToDictionary(lowerCaseWord);
				incrementWordFrequency(lowerCaseWord);
			} else {
				System.out.println("Invalid word : " + word);
			}
		}
	}

	public void incrementWordFrequency(String lowerCaseWord) {
		if(wordStatistics.containsKey(lowerCaseWord)) {
			wordStatistics.get(lowerCaseWord).incrementFrequency();
		} else {
			wordStatistics.put(lowerCaseWord, new T9Word(lowerCaseWord));
		}
	}

	private void addWordToDictionary(String word) {
		root.addWord(word);
	}
	
	public List<String> getWords(String numberStr) {
		if(! isValidPositiveInteger(numberStr)) {
			System.out.println("Invalid input number : " + numberStr);
			return new ArrayList<String>();
		}
		
		T9Node node = root.getNodeByPath(numberStr);
		if(node == null) {
			System.out.println("Dictionary does not have the words for number : " + numberStr);
			return new ArrayList<String>();
		}
		
		List<String> allValidWords = node.getWordsWithThisNodeAsPrefix();		
		//List<String> topFrequentWords = getTopFrequestWords(allValidWords, noOfTopFrequestWords);
		List<String> sortedWords = getSortedWords(allValidWords, noOfTopFrequentWords);
		
		return sortedWords;
	}

	private List<String> getSortedWords(List<String> words, int noOfTopFrequentWords) {
		String[] wordArray = new String[words.size()];
		words.toArray(wordArray);
		
		Arrays.sort(wordArray, new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				int str1Frequency = wordStatistics.get(str1).getFrequency();
				int str2Frequency = wordStatistics.get(str2).getFrequency();
				
				if(str1Frequency != str2Frequency) {
					return str2Frequency - str1Frequency; 
				} else {
					long str1LastAccessedTime = wordStatistics.get(str1).getLastAccessedTimeInMilis();
					long str2LastAccessedTime = wordStatistics.get(str2).getLastAccessedTimeInMilis();
					
					if(str2LastAccessedTime == str1LastAccessedTime) {
						return 0;
					} else if(str2LastAccessedTime > str1LastAccessedTime) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		});
		
		List<String> sortedWords = new ArrayList<String>();
		int i = 0;
		while(i<noOfTopFrequentWords && i<wordArray.length) {
			sortedWords.add(wordArray[i]);
			i++;
		}
		
		return sortedWords;
	}

	private boolean isValidPositiveInteger(String numberStr) {
		return true;
	}
}