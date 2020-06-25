package yyl.leetcode.p01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h3>单词拆分</h3><br>
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。<br>
 * 说明：<br>
 * 拆分时可以重复使用字典中的单词。<br>
 * 你可以假设字典中没有重复的单词。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * </pre>
 */
public class P0139_WordBreak {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.wordBreak("leetcode", Arrays.asList("leet", "code")));// true
		System.out.println(solution.wordBreak("applepenapple", Arrays.asList("apple", "pen")));// true
		System.out.println(solution.wordBreak("wordDict", Arrays.asList("cats", "dog", "sand", "and", "cat")));// false
	}

	// 动态规划（优化）
	// 状态转移方程：dp[i+j] = dp[i] && s.startsWith(word, i)
	// 时间复杂度：O(nm) ，其中 n为字符串 s的长度，m为字典个数。
	// 空间复杂度：O(n)，其中 n 为字符串 s 的长度。我们需要 O(n) 的空间存放 dp值，因此总空间复杂度为 O(n)。
	static class Solution {
		public boolean wordBreak(String s, List<String> wordDict) {
			boolean[] dp = new boolean[s.length() + 1];
			dp[0] = true;
			for (int i = 0; i < s.length(); i++) {
				for (String word : wordDict) {
					if (dp[i] && s.startsWith(word, i)) {
						dp[i + word.length()] = true;
					}
				}
			}
			return dp[s.length()];
		}
	}

	// 动态规划
	// 我们定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词。
	// 从前往后计算考虑转移方程，每次转移的时候我们需要枚举包含位置 i−1 的最后一个单词，看它是否出现在字典中。
	// 得出转移方程：dp[i] = dp[j] && check(s[j..i−1])
	// 时间复杂度：O(n^2)，其中 n为字符串 s的长度。
	// 空间复杂度：O(n)，其中 n 为字符串 s 的长度。我们需要 O(n) 的空间存放 dp值，因此总空间复杂度为 O(n)。
	static class Solution2 {
		public boolean wordBreak(String s, List<String> wordDict) {
			Set<String> wordDictSet = new HashSet<>(wordDict);
			boolean[] dp = new boolean[s.length() + 1];
			dp[0] = true;
			for (int i = 1; i <= s.length(); i++) {
				for (int j = 0; j < i; j++) {
					if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
						dp[i] = true;
						break;
					}
				}
			}
			return dp[s.length()];
		}
	}
}
