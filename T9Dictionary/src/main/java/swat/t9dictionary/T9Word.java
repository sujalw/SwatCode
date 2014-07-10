package swat.t9dictionary;

import swat.swatcodeutilities.SwatStringUtils;

public class T9Word {
	
	private String value;
	private int frequency;
	private long lastAccessedTimeInMilis;
	
	public T9Word(String value) {
		this(value, 1, System.currentTimeMillis());
	}

	public T9Word(String value, int initialFrequency, long initialLastAccessedTimeInMilis) {
		
		assert !SwatStringUtils.isNullOrEmpty(value) && initialFrequency>0;
		
		this.value = value;
		this.frequency = initialFrequency;
		this.lastAccessedTimeInMilis = initialLastAccessedTimeInMilis;
	}

	public int getFrequency() {
		return frequency;
	}
	
	public void incrementFrequency() {
		this.frequency++;
		this.lastAccessedTimeInMilis = System.currentTimeMillis();
	}

	public long getLastAccessedTimeInMilis() {
		return lastAccessedTimeInMilis;
	}

	public String getValue() {
		return value;
	}
}
