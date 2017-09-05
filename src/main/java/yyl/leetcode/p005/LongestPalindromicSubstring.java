package yyl.leetcode.p005;

/**
 * Given a string s, find the longest palindromic substring in s. <br>
 * You may assume that the maximum length of s is 1000. * <br>
 * Example:<br>
 * Input: "babad"<br>
 * Output: "bab"<br>
 * Note: "aba" is also a valid answer.<br>
 * Example:<br>
 * Input: "cbbd"<br>
 * Output: "bb" <br>
 */
//给出一个字符串S，找到一个最长的连续回文串。
public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String sample = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
		System.out.println(solution.longestPalindrome(sample));//ranynar
	}

	static class Solution {
		public String longestPalindrome(String s) {
			int rLen = 0;
			int rBegin = 0;
			int rEnd = 0;
			boolean[][] ranges = new boolean[s.length()][s.length()];
			for (int end = 0; end < s.length(); end++) {
				ranges[end][end] = true;
				for (int begin = 0; begin < end; begin++) {
					if ((ranges[begin][end] = s.charAt(begin) == s.charAt(end) && (end - begin < 2 || ranges[begin + 1][end - 1]))//
							&& rLen < end - begin + 1) {
						rLen = end - begin + 1;
						rBegin = begin;
						rEnd = end;
					}
				}
			}
			return s.substring(rBegin, rEnd + 1);
		}
	}
}
