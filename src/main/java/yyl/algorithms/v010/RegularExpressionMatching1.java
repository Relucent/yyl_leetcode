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
public class RegularExpressionMatching1 {
	public static void main(String[] args) {
		System.out.println(isMatch("abcdefg", "abcdefg"));//T
		System.out.println(isMatch("abcdefg", "ab.*defg"));//T
		System.out.println(isMatch("abcd", "a*.c"));//F
		System.out.println(isMatch("aaabbbccc", "aaa.**c"));//F
		System.out.println(isMatch("aaaa", "a*"));//T
		System.out.println(isMatch("abc", "..c"));//T
	}

	public static boolean isMatch(String s, String p) {
		return matchhere(s, p, 0, 0);
	}

	private static boolean matchhere(String s, String p, int i, int j) {
		if (j == p.length()) {
			return i == s.length();
		}
		if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
			return matchstar(p.charAt(j), s, p, i, j + 2);
		}
		if (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.')) {
			return matchhere(s, p, i + 1, j + 1);
		}
		return false;
	}

	//
	private static boolean matchstar(char c, String s, String p, int i, int j) {
		do {
			if (matchhere(s, p, i, j)) {
				return true;
			}
		} while (i < s.length() && (s.charAt(i++) == c || '.' == c));
		return false;
	}

}
