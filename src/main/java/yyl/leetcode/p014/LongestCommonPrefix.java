package yyl.leetcode.p014;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
// 编写一个函数来查找字符串数组中最长的公共前缀字符串。
public class LongestCommonPrefix {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] strs = { "abcdefghigk", "abcd", "abcdefg" };
		System.out.println(solution.longestCommonPrefix(strs));
	}

	static class Solution {
		public String longestCommonPrefix(String[] strs) {
			if (strs == null || strs.length == 0) {
				return "";
			}
			if (strs.length == 1) {
				return strs[0];
			}
			int pos = 0;
			T: for (; pos < strs[0].length(); pos++) {
				char c = strs[0].charAt(pos);
				for (int i = 1; i < strs.length; i++) {
					if (strs[i].length() == pos || strs[i].charAt(pos) != c) {
						break T;
					}
				}
			}
			return strs[0].substring(0, pos);
		}
	}
}
