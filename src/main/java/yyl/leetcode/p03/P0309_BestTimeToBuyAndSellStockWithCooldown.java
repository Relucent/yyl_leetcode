package yyl.leetcode.p03;

/**
 * <h3>最佳买卖股票时机含冷冻期</h3><br>
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​<br>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:<br>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。<br>
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3 
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * </pre>
 */
public class P0309_BestTimeToBuyAndSellStockWithCooldown {

	public static void main(String[] args) {
		Solution1 solution = new Solution1();
		System.out.println(solution.maxProfit(new int[] { 1, 2, 3, 0, 2 }));// 3
	}

	// 动态规划
	// dp[i] 表示第 i 天结束之后的「累计最大收益」。
	// 根据题目描述，由于我们最多只能同时买入（持有）一支股票，并且卖出股票后有冷冻期的限制，因此我们会有三种不同的状态：
	// ├ (0)持有：对应的「累计最大收益」记为 dp[i][0]
	// ├ (1)不持有，不可买：对应的「累计最大收益」记为 dp[i][1]
	// ├ (2)不持有，可买入：对应的「累计最大收益」记为 dp[i][2]
	// 状态分析
	// ├ 1、状态为持有股票，可以不做操作(继续持有)，也可以卖出
	// │ ├ 1.1 不做操作，状态不变(0)，方程：dp[i][0] = dp[i-1][0]
	// │ └ 1.2 卖出，状态变为(1)，方程：dp[i][1] = dp[i-1][0]+ prices[i]； {前一天持有的收益}+{当天卖出金额 }
	// ├ 2、状态为不持有，不可买(冷冻期)，那么不能做操作
	// │ └ 2.1 不做操作，状态转移方程 ，状态变为(1)，方程：dp[i][2] = dp[i-1][1]
	// └ 3、状态为不持有，可以继续买入股票，也可以不做操作
	// - ├ 3.1 买入股票，状态变为(0)，方程：dp[i][0] = dp[i-1][2] - prices[i]； {前一天状态2的收益}-{当天购买金额 }
	// - └ 3.2 不做操作，状态不变(2)，方程：dp[i][2] = dp[i-1][2]
	// 合并几个状态转移方程
	// dp[i][0] = max(dp[i-1][0],dp[i-1][2] - prices[i]);
	// dp[i][1] = dp[i][1] = dp[i-1][0]+ prices[i];
	// dp[i][2] = max(dp[i-1][1],dp[i-1][2]);
	// 在第 0 天时，持有股票，那么一定是当天买入的，所以 dp[0][0] = -prices[0]；买股票用了prices[0]
	// 之后从第 1 天开始，根据上面的状态转移方程进行进行动态规划，直到计算出第 n−1 天的结果。（n是数组长度)
	// 获得答案为：max(dp[n-1][0],dp[n-1][1],dp[n-1][2])
	// 复杂度分析
	// 时间复杂度：O(n)，其中 n为数组 prices 的长度。
	// 空间复杂度：O(n)。我们需要 3*n 的空间存储动态规划中的所有状态。
	static class Solution {
		public int maxProfit(int[] prices) {
			int n = prices.length;
			if (n == 0) {
				return 0;
			}
			int[][] dp = new int[prices.length][3];
			dp[0][0] = -prices[0];
			for (int i = 1; i < prices.length; i++) {
				dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
				dp[i][1] = dp[i][1] = dp[i - 1][0] + prices[i];
				dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
			}
			return Math.max(Math.max(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]);
		}
	}

	// 动态规划+空间优化
	// 状态转移方程
	// dp[i][0] = max(dp[i-1][0],dp[i-1][2] - prices[i]);
	// dp[i][1] = dp[i][1] = dp[i-1][0]+ prices[i];
	// dp[i][2] = max(dp[i-1][1],dp[i-1][2]);
	// 可以发现，dp[i][X] 只与 dp[i-1][X]有关系，所以dp数组可以压缩
	// 时间复杂度：O(n)，其中 n为数组 prices 的长度。
	// 空间复杂度：O(1)
	static class Solution1 {
		public int maxProfit(int[] prices) {
			int n = prices.length;
			if (n == 0) {
				return 0;
			}
			int[] dp = new int[3];
			dp[0] = -prices[0];
			for (int i = 1; i < prices.length; i++) {
				int temp0 = Math.max(dp[0], dp[2] - prices[i]);
				int temp1 = dp[0] + prices[i];
				int temp2 = Math.max(dp[1], dp[2]);
				dp[0] = temp0;
				dp[1] = temp1;
				dp[2] = temp2;
			}
			return Math.max(Math.max(dp[0], dp[1]), dp[2]);
		}
	}
}
