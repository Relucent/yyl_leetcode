package yyl.leetcode.p003;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.<br>
 * Examples:<br>
 * Given "abcabcbb", the answer is "abc", which the length is 3.<br>
 * Given "bbbbb", the answer is "b", with the length of 1.<br>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.<br>
 */
//计算最长无重复子串长度
public class LongestSubstring {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.lengthOfLongestSubstring("abcabcbb"));
		System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
		System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
	}

	static class Solution {
		public int lengthOfLongestSubstring(String s) {
			if (s.isEmpty()) {
				return 0;
			}
			int maxLength = 0;
			int[] indexs = new int[128];//ASCII
			for (int begin = 0, end = 0; end < s.length(); end++) {
				begin = Math.max(indexs[s.charAt(end)], begin);
				maxLength = Math.max(maxLength, end - begin + 1);
				indexs[s.charAt(end)] = end + 1;
			}
			return maxLength;
		}
	}

	static class Solution2 {
		public int lengthOfLongestSubstring(String s) {
			int maxLength = 0;
			Map<Character, Integer> map = new HashMap<>();
			for (int begin = 0, end = 0; end < s.length(); end++) {
				char c = s.charAt(end);
				if (map.containsKey(c)) {
					begin = Math.max(map.get(c), begin);
				}
				maxLength = Math.max(maxLength, end - begin + 1);
				map.put(c, end + 1);

			}
			return maxLength;
		}
	}
}
