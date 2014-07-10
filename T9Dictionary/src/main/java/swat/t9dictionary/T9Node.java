package swat.t9dictionary;

import swat.swatcodeutilities.SwatStringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T9Node {
	private short value;
	List<T9Node> children;
	Set<String> words;
	
	public T9Node() {
		this((short)0, null);
	}
	
	public T9Node(short value, Set<String> words) {
		assertIsValidValue(value);
		
		this.value = value;
		this.children = new ArrayList<T9Node>();
		
		this.words = new HashSet<String>();
		if(words != null) {
			this.words.addAll(words);
		}
	}

	public short getValue() {
		return value;
	}

	public void addChild(short value, Set<String> words) {
		assertIsValidValue(value);
		
		if(hasChild(value)) {
			return;
		}
		
		T9Node newChild = new T9Node(value, words);
		children.add(newChild);
	}

	private boolean hasChild(short value) {
		for(T9Node child : children) {
			if(child.getValue() == value) {
				return true;
			}
		}
		
		return false;
	}

	public List<T9Node> getChildren() {
		return children;
	}
	
	public T9Node getChild(short value) {
		assertIsValidValue(value);
		
		for(T9Node child : children) {
			if(child.getValue() == value) {
				return child;
			}
		}
		
		return null;
	}
	
	private void assertIsValidValue(short value) {
		assert value>=0 && value<=9;
	}

	public void addWord(String word) {
		if(SwatStringUtils.isValidEnglishWord(word)) {
			addWordUsingPath(word, word);			
		}
	}

	private void addWordUsingPath(String word, String path) {

		if(path.length() == 0) {
			this.words.add(word);
			return;
		}
		
		char firstCharacter = path.charAt(0);
		short nodeValue = T9DictionaryUtils.getNodeValue(firstCharacter); 
		
		T9Node child = getChild(nodeValue);
		if(child == null) {
			addChild(nodeValue, null);
			child = getChild(nodeValue);
		}
		child.addWordUsingPath(word, path.substring(1));
	}

	public T9Node getNodeByPath(String numberStr) {
		
		if(numberStr.length() == 0) {
			return this;
		}
		
		char firstCharacter = numberStr.charAt(0);
		short value = Short.parseShort(String.valueOf(firstCharacter));
		
		T9Node child = getChild(value);
		if(child == null) {
			return null;
		}
		
		return child.getNodeByPath(numberStr.substring(1));
	}

	public List<String> getWordsWithThisNodeAsPrefix() {
		return getWordsWithThisNodeAsPrefixIncludingCurrentNode();
	}

	private List<String> getWordsWithThisNodeAsPrefixIncludingCurrentNode() {
		List<String> words = new ArrayList<String>();
		
		words.addAll(this.words);
		
		for(T9Node child : children) {
			words.addAll(child.getWordsWithThisNodeAsPrefixIncludingCurrentNode());
		}
		
		return words;
	}
}