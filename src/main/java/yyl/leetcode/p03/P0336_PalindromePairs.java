package yyl.leetcode.p03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>回文对</h3><br>
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]] 
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * 示例 2:
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]] 
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 * </pre>
 */
public class P0336_PalindromePairs {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.palindromePairs(new String[] { "abcd", "dcba", "lls", "s", "sssll" }));
		System.out.println(solution.palindromePairs(new String[] { "bat", "tab", "cat" }));
	}

	// 暴力法
	// 枚举每一对字符串的组合，暴力判断它们是否能够构成回文串即可。
	// 时间复杂度：O(n^2*m)，n是单词数量，m是单词平均长度。
	// 空间复杂度：O(1)，除了返回结果没有额外空间消耗。
	static class Solution {
		public List<List<Integer>> palindromePairs(String[] words) {
			List<List<Integer>> answer = new ArrayList<>();
			for (int i = 0; i < words.length; i++) {
				for (int j = i + 1; j < words.length; j++) {
					String word1 = words[i];
					String word2 = words[j];
					if (isPalindrome(word1 + word2)) {
						answer.add(Arrays.asList(i, j));
					}
					if (isPalindrome(word2 + word1)) {
						answer.add(Arrays.asList(j, i));
					}
				}
			}
			return answer;
		}

		private boolean isPalindrome(String string) {
			int left = 0;
			int right = string.length() - 1;
			while (left < right) {
				if (string.charAt(left++) != string.charAt(right--)) {
					return false;
				}
			}
			return true;
		}
	}

	// 枚举前缀和后缀
	// 思路及算法
	// 假设字符串 s1 + s2，是一个回文串，两个字符串的长度分别为 len1 和 len2，分三种情况进行讨论：
	// len1=len2 ，这种情况下 s1 是 s2 的翻转。
	// len1>len2，这种情况下我们可以将 s1 拆成左右两部分：t1和t2 ，其中 t1 是 s2 的翻转，t2是一个回文串。
	// len1<len2，这种情况下我们可以将 s2 拆成左右两部分：t1和t2 ，其中 t2是 s1 的翻转，t1是一个回文串。
	// 遍历对于每一个字符串，我们令其为 s1 和 s2 中较长的那一个（len1>=len2），然后找到可能和它构成回文串的字符串。
	// 需要注意：空串也是回文串
	// 时间复杂度：O(n*m^2)，n是单词数量，m是单词平均长度。对于每一个字符串，我们需要 O(m^2)地判断其所有前缀与后缀是否是回文串，并 O(m^2)地寻找其所有前缀与后缀是否在给定的字符串序列中出现。
	// 空间复杂度：O(n*m)，需要哈希表存储所有字符串。
	static class Solution1 {
		public List<List<Integer>> palindromePairs(String[] words) {

			Map<String, Integer> reverseIndices = new HashMap<String, Integer>();
			for (int i = 0; i < words.length; ++i) {
				reverseIndices.put(new StringBuilder(words[i]).reverse().toString(), i);
			}
			List<List<Integer>> answer = new ArrayList<>();
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				int m = word.length();
				for (int j = 0; j <= m; j++) {
					if (isPalindrome(word, j, m - 1)) {
						Integer index = reverseIndices.get(word.substring(0, j));
						if (index != null && index.intValue() != i) {
							answer.add(Arrays.asList(i, index));
						}
					}
					if (j != 0 && isPalindrome(word, 0, j - 1)) {
						Integer index = reverseIndices.get(word.substring(j, m));
						if (index != null && index.intValue() != i) {
							answer.add(Arrays.asList(index, i));
						}
					}
				}
			}
			return answer;
		}

		private boolean isPalindrome(String s, int left, int right) {
			while (left < right) {
				if (s.charAt(left++) != s.charAt(right--)) {
					return false;
				}
			}
			return true;
		}
	}
}
