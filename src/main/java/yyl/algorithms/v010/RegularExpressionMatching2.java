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
public class RegularExpressionMatching2 {
	public static void main(String[] args) {
		System.out.println(isMatch("abcdefg", "abcdefg"));//T
		System.out.println(isMatch("abcdefg", "ab.*defg"));//T
		System.out.println(isMatch("abcd", "a*.c"));//F
		System.out.println(isMatch("aaabbbccc", "aaa.**c"));//F
		System.out.println(isMatch("aaaa", "a*"));//T
		System.out.println(isMatch("abc", "..c"));//T
	}

	//动态规划算法
	public static boolean isMatch(String s, String p) {

		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

		for (int i = 0; i <= s.length(); i++) {
			for (int j = 0; j <= p.length(); j++) {
				if (j == 0) {
					dp[i][j] = i == 0;
				}
				// j>1  p[j-1]=='*'
				else if (p.charAt(j - 1) == '*') {

					for (int k = 0; k <= i; k++) {
						if (k != 0 && !isSame(s.charAt(i - k), p.charAt(j - 2))) {
							dp[i][j] = false;
							break;
						}
						if (dp[i - k][j - 2]) {
							dp[i][j] = true;
							break;
						}
					}
				}
				//p[j-1]!='*'
				else {
					dp[i][j] = i >= 1// 
							&& isSame(s.charAt(i - 1), p.charAt(j - 1))// 
							&& dp[i - 1][j - 1];
				}
			}
		}
		return dp[s.length()][p.length()];
	}

	private static boolean isSame(char c, char p) {
		return p == '.' || c == p;
	}
}