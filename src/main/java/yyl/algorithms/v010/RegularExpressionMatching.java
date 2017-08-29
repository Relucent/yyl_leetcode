package yyl.algorithms.v010;

/**
 * Implement regular expression matching with support for '.' and '*'.<br>
 * 
 * '.' Matches any single character.<br>
 * '*' Matches zero or more of the preceding element.<br>
 * The matching should cover the entire input string (not partial).<br>
 * 
 * The function prototype should be:<br>
 * bool isMatch(const char *s, const char *p)<br>
 * 
 * Some examples:<br>
 * isMatch("aa","a") → false<br>
 * isMatch("aa","aa") → true<br>
 * isMatch("aaa","aa") → false<br>
 * isMatch("aa", "a*") → true<br>
 * isMatch("aa", ".*") → true<br>
 * isMatch("ab", ".*") → true<br>
 * isMatch("aab", "c*a*b") → true<br>
 */
// 正则匹配实现题
// '.' 匹配单个字符
// '*' 匹配零到多个前一个字符
public class RegularExpressionMatching {
	public static void main(String[] args) {
		System.out.println(isMatch("abcdefg", "abcdefg"));//T
		System.out.println(isMatch("abcdefg", "ab.*defg"));//T
		System.out.println(isMatch("abcd", "a*.c"));//F
		System.out.println(isMatch("aaabbbccc", "aaa.**c"));//F
		System.out.println(isMatch("aaaa", "a*"));//T
		System.out.println(isMatch("abc", "..c"));//T
	}

	public static boolean isMatch(String s, String p) {
		return isMatch(s, p, 0, 0);
	}

	private static boolean isMatch(String s, String p, int i, int j) {
		//p:end
		if (j == p.length()) {
			return i == s.length();
		}

		//p:last || p:next!='*'
		if (j == p.length() - 1 || p.charAt(j + 1) != '*') {
			//s:end && p!end
			if (i == s.length()) {
				return false;
			}
			if (p.charAt(j) != s.charAt(i) && p.charAt(j) != '.') {
				return false;
			}
			return isMatch(s, p, i + 1, j + 1);
		}

		//s!end && p:next=='*'
		while (i < s.length() && (s.charAt(i) == p.charAt(j) || '.' == p.charAt(j))) {
			//match after
			if (isMatch(s, p, i, j + 2)) {
				return true;
			}
			//!match after i+1
			i++;
		}
		return isMatch(s, p, i, j + 2);
	}
}
