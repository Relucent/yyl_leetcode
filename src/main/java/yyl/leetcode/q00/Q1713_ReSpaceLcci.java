package yyl.leetcode.q00;

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
public class Q1713_ReSpaceLcci {

	public static void main(String[] args) {
		Solution solution = new Solution();
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
	// 时间复杂度：O(nm) ，其中 n为字符串的长度，m为字典个数。
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
}
