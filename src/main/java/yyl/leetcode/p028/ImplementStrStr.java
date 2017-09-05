package yyl.leetcode.p028;

/**
 * Implement strStr().<br>
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.<br>
 */
public class ImplementStrStr {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String haystack = "abcabcabcdabcde";
		String needle = "bcd";
		System.out.println(solution.strStr(haystack, needle));
	}

	static class Solution {
		public int strStr(String haystack, String needle) {
			if (needle.isEmpty()) {
				return 0;
			}
			int max = haystack.length() - needle.length();
			char first = needle.charAt(0);
			for (int i = 0; i <= max; i++) {
				if (haystack.charAt(i) != first) {
					while (++i <= max && haystack.charAt(i) != first)
						;
				}
				if (i <= max) {
					int j = i + 1;
					int end = i + needle.length();
					for (int k = 1; j < end && haystack.charAt(j) == needle.charAt(k); j++, k++)
						;
					if (j == end) {
						return i;
					}
				}
			}
			return -1;
		}
	}
}
