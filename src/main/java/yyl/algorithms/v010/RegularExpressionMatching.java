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
		Solution solution = new Solution();
		System.out.println(solution.isMatch("abcdefg", "abcdefg"));//T
		System.out.println(solution.isMatch("abcdefg", "ab.*defg"));//T
		System.out.println(solution.isMatch("abcd", "a*.c"));//F
		System.out.println(solution.isMatch("aaabbbccc", "aaa.**c"));//F
		System.out.println(solution.isMatch("aaaa", "a*"));//T
		System.out.println(solution.isMatch("abc", "..c"));//T
	}

	static class Solution {

		public boolean isMatch(String s, String p) {
			return isMatch(s, p, 0, 0);
		}

		private boolean isMatch(String s, String p, int i, int j) {
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

	static class Solution2 {

		public boolean isMatch(String s, String p) {
			return matchhere(s, p, 0, 0);
		}

		private boolean matchhere(String s, String p, int i, int j) {
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

		private boolean matchstar(char c, String s, String p, int i, int j) {
			do {
				if (matchhere(s, p, i, j)) {
					return true;
				}
			} while (i < s.length() && (s.charAt(i++) == c || '.' == c));
			return false;
		}
	}

	//动态规划算法(比算法1和算法2要快)
	static class Solution3 {
		public boolean isMatch(String s, String p) {

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

		private boolean isSame(char c, char p) {
			return p == '.' || c == p;
		}
	}
}
