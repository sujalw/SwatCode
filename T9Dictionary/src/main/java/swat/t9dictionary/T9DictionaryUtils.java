package swat.t9dictionary;

public class T9DictionaryUtils {
	
	private static short[] characterToDigitMapping = new short[]{
			2, 2, 2,	// a, b, c
			3, 3, 3,	// d, e, f
			4, 4, 4,	// g, h, i
			5, 5, 5,	// j, k, l
			6, 6, 6,	// m, n, o
			7, 7, 7, 7,	// p, q, r, s
			8, 8, 8,	// t, u, v
			9, 9, 9, 9	// w, x, y, z
	};

	public static short getEnglishCharacterIndex(char wordCharacter) {
		return (short) (wordCharacter - 'a');
	}
	
	public static short getNodeValue(char wordCharacter) {
		short index = getEnglishCharacterIndex(wordCharacter);
		return characterToDigitMapping[index];
	}
}
