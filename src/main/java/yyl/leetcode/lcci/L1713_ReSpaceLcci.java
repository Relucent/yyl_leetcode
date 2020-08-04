package yyl.leetcode.lcci;

/**
 * <h3>面试题 17.13. 恢复空格</h3><br>
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。<br>
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。<br>
 * 在处理标点符号和大小写之前，你得先把它断成词语。<br>
 * 当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。<br>
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。<br>
 * 
 * <pre>
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。

 * 提示：
 *     0 <= len(sentence) <= 1000
 *     dictionary中总字符数不超过 150000。
 *     你可以认为dictionary和sentence中只包含小写字母。
 * </pre>
 */
public class L1713_ReSpaceLcci {

	public static void main(String[] args) {
		Solution1 solution = new Solution1();
		String[] dictionary = { "looked", "just", "like", "her", "brother" };
		String sentence = "jesslookedjustliketimherbrother";
		System.out.println(solution.respace(dictionary, sentence));
	}

	// 动态规划
	// 1、定义 dp[i] 表示考 i结尾字符最少的未识别的字符数量
	// 2、初始的 dp[i] = dp[i-1]+1; （dp[i−1]的状态再加上当前未被识别的第 i个字符）
	// 字符串[i]可以分为 [0,j-1] 和 [j,i] 两部分，如果[j,i]能够匹配词典一个词，那么dp=字符串[0,j-1]的未识别数量， 即 dp[i] = dp[j]
	// 取最小 dp[i] = min(dp[i],dp[j]); 设匹配的词长度为k,那么 j=i-k
	// 3、循环判断词典匹配情况，如果找到匹配：dp[i] = Math.min(dp[i], dp[i - k]);
	// 时间复杂度：O(m*n^2) ，其中 n为字符串的长度，m为字典个数。
	// 空间复杂度：O(n)，n为字符串长度
	static class Solution {
		public int respace(String[] dictionary, String sentence) {
			int[] dp = new int[sentence.length() + 1];
			dp[sentence.length()] = sentence.length();
			for (int i = 1; i <= sentence.length(); i++) {
				// dp[i−1]的状态再加上当前未被识别的第 i个字符
				dp[i] = dp[i - 1] + 1;
				// 循环词典单词
				T: for (String d : dictionary) {
					// 单词长度
					int k = d.length();
					// 单词可以 i位置结尾
					if (k <= i && sentence.startsWith(d, i - k)) {
						dp[i] = Math.min(dp[i], dp[i - k]);
						// (优化)已经没有未被识别的了，后面的词不用再判断了
						if (dp[i] == 0) {
							break T;
						}
					}
				}
			}
			return dp[sentence.length()];
		}
	}

	// 动态规划 + 字典树
	// # 动态规划
	// 定义 dp[i] 表示考虑前 i个字符最少的未识别的字符数量，从前往后计算 dp值。
	// 转移方程，每次转移的时候我们考虑第 j(j≤i) 个到第 i 个字符组成的子串 sentence[j−1⋯i−1] （字符串下标从 0开始）是否能在词典中找到
	// 如果能找到转移方程为：dp[i]=min⁡(dp[i],dp[j−1])
	// 没有找到转移方程为：dp[i]=dp[i-1]+1 （dp[i−1]状态再加上当前未被识别的第 i个字符）
	// # 字典树
	// 使用字典树是为了可以快速判断当前子串是否存在于词典中
	// 词典中的词长度不相同，为了简化代码，提高效率，子串需要从后往前匹配，构建字典树时需要使用「反序」的字典树。
	// # 复杂度分析
	// 时间复杂度：O(|dictionary|+n^2)，其中|∣dictionary|代表词典中的总字符数，n=sentence.length。建字典树的时间复杂度取决于单词的总字符数，即 |dictionary|；dp数组一共有 n+1个状态，每个状态转移的时候最坏需要 O(n)的时间复杂度，因此时间复杂度为 O(n^2)。
	// 空间复杂度：O(|dictionary|+n)，词典树 + dp数组的空间
	static class Solution1 {
		public int respace(String[] dictionary, String sentence) {
			Trie root = new Trie();
			for (String word : dictionary) {
				root.insert(word);
			}
			int[] dp = new int[sentence.length() + 1];
			dp[0] = 0;
			for (int i = 1; i <= sentence.length(); i++) {
				dp[i] = dp[i - 1] + 1;
				Trie node = root;
				for (int j = i; j >= 1; j--) {
					int t = sentence.charAt(j - 1) - 'a';
					if (node.next[t] == null) {
						break;
					}
					if (node.next[t].isEnd) {
						dp[i] = Math.min(dp[i], dp[j - 1]);
						if (dp[i] == 0) {
							break;
						}
					}
					node = node.next[t];
				}
			}
			return dp[sentence.length()];
		}

		// 「反序」字典树
		private class Trie {
			Trie[] next = new Trie[26];
			boolean isEnd = false;

			void insert(String s) {
				Trie node = this;
				for (int i = s.length() - 1; i >= 0; i--) {
					int t = s.charAt(i) - 'a';
					if (node.next[t] == null) {
						node.next[t] = new Trie();
					}
					node = node.next[t];
				}
				node.isEnd = true;
			}
		}
	}
}
