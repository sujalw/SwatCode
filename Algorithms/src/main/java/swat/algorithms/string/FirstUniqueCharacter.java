package swat.algorithms.string;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sujal
 */
public class FirstUniqueCharacter {

	String sourceString;
	CharacterStatistics characterStatistics;

	public FirstUniqueCharacter(String sourceString) {
		this.sourceString = sourceString;
		characterStatistics = new CharacterStatistics();
	}

	public Character getFirstUniqueCharacter() {
		if(StringUtils.isEmpty(sourceString)) {
			return null;
		}

		Map<Character, CharacterStatistics> characterInfo = computeCharacterCountAndFirstOccurrence();
		return getFirstUniqueCharacter(characterInfo);
	}

	private Map<Character, CharacterStatistics> computeCharacterCountAndFirstOccurrence() {
		assert ! StringUtils.isEmpty(sourceString);

		Map<Character, CharacterStatistics> charInfo = new HashMap<Character, CharacterStatistics>();
		Character currentChar = null;
		CharacterStatistics currentCharStatistics;

		for(int index=0 ; index<sourceString.length() ; index++) {
			currentChar = sourceString.charAt(index);

			if(charInfo.containsKey(currentChar)) {
				currentCharStatistics = charInfo.get(currentChar);
				currentCharStatistics.incrementCount();
			} else {
				currentCharStatistics = new CharacterStatistics();
				currentCharStatistics.setCount(1);
				currentCharStatistics.setFirstOccurrenceIndex(index);
			}

			charInfo.put(currentChar, currentCharStatistics);
		}

		return charInfo;
	}

	private Character getFirstUniqueCharacter(Map<Character, CharacterStatistics> characterInfo) {
		if(characterInfo == null || characterInfo.isEmpty()) {
			return null;
		}

		int minFirstOccurrence = Integer.MAX_VALUE;
		Character firstUniqueCharacter = null;
		CharacterStatistics charStats;

		for(Character character : characterInfo.keySet()) {
			charStats = characterInfo.get(character);
			if(charStats.isUniqueCharacter()) {
				if(charStats.getFirstOccurrenceIndex() < minFirstOccurrence) {
					minFirstOccurrence = charStats.getFirstOccurrenceIndex();
					firstUniqueCharacter = character;
				}
			}
		}

		return firstUniqueCharacter;
	}

	private class CharacterStatistics {
		int count;
		int firstOccurrenceIndex;

		public CharacterStatistics() {
			count = -1;
			firstOccurrenceIndex = -1;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getFirstOccurrenceIndex() {
			return firstOccurrenceIndex;
		}

		public void setFirstOccurrenceIndex(int firstOccurrenceIndex) {
			this.firstOccurrenceIndex = firstOccurrenceIndex;
		}

		public void incrementCount() {
			this.count++;
		}

		public boolean isUniqueCharacter() {
			return count == 1;
		}
	}
}
