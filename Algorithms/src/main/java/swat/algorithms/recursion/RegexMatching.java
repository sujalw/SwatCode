package swat.algorithms.recursion;

import swat.swatcodeutilities.SwatStringUtils;

/**
 * Ref = http://leetcode.com/2011/09/regular-expression-matching.html
 *
 * Problem Statement:
 * Implement regular expression matching with support for ‘.’ and ‘*’.
 *
 * ‘.’ Matches any single character.
 * ‘*’ Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 *
 * e.g.:
 * isMatch(“aa”,”a”) → false
 * isMatch(“aa”,”aa”) → true
 * isMatch(“aaa”,”aa”) → false
 * isMatch(“aa”, “a*”) → true
 * isMatch(“aa”, “.*”) → true
 * isMatch(“ab”, “.*”) → true
 * isMatch(“aab”, “c*a*b”) → true
 *
 * @author Sujal
 */
public class RegexMatching {

	boolean isMatch(String str, String pattern) {
		if(str==null || pattern==null) {
			return false;
		}

		if(SwatStringUtils.isEmpty(pattern)) {
			return SwatStringUtils.isEmpty(str);
		}

		if(SwatStringUtils.isEmpty(str)) {
			if(isStartsWithKleeneZeroOrMore(pattern)) {
				return isMatch(str, pattern.substring(2));
			}
			return false;
		}

		if(isFirstCharMatch(str, pattern)) {
			if(isStartsWithKleeneZeroOrMore(pattern)) {
				return isMatch(str.substring(1), pattern);
			} else {
				return isMatch(str.substring(1), pattern.substring(1));
			}
		} else {
			if(isStartsWithKleeneZeroOrMore(pattern)) {
				return isMatch(str, pattern.substring(2));
			}
			return false;
		}
	}

	private boolean isStartsWithKleeneZeroOrMore(String pattern) {
		if(pattern.length() < 2) {
			return false;
		}

		return pattern.charAt(0) != '*'
				&& pattern.charAt(1) == '*';
	}

	private boolean isFirstCharMatch(String str, String pattern) {
		return isFirstCharExactMatch(str, pattern)
				|| isFirstCharDot(pattern);
	}

	private boolean isFirstCharExactMatch(String str, String pattern) {
		return str.charAt(0) == pattern.charAt(0);
	}

	private boolean isFirstCharDot(String pattern) {
		return pattern.charAt(0) == '.';
	}
}