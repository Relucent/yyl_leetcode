package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>买卖股票的最佳时机含手续费</h3><br>
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。<br>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。<br>
 * 返回获得利润的最大值。<br>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:  
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * </pre>
 * 
 * 注意:<br>
 * ├ 0 < prices.length <= 50000.<br>
 * ├ 0 < prices[i] < 50000.<br>
 * └ 0 <= fee < 50000.<br>
 */

public class P0714_BestTimeToBuyAndSellStockWithTransactionFee {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(8, solution.maxProfit(new int[] { 1, 3, 2, 8, 4, 9 }, 2));
    }

    // 动态规划
    // 不能同时参与多笔交易，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
    // 定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润。
    // 如果这一天交易完后手里没有股票
    // ├ 可能的转移状态为前一天已经没有股票，即 dp[i][0] = dp[i-1][0]
    // ├ 或者前一天结束的时候手里持有一支股票，这一天将其卖出并支付的手续费，即 dp[i][0] = dp[i-1][1]+ prices[i] -fee
    // └ 得到状态转移方程：dp[i][0]=max{dp[i-1][0],dp[i-1][1]+prices[i]-fee}
    // 如果这一天交易完后手里持有股票
    // ├ 可能的转移状态为前一天已经持有一支股票，即 dp[i-1][1] = dp[i-1][1]
    // ├ 或者前一天结束时还没有股票当天买入，可以列出如下的转移方程，dp[i-1][0] - prices[i]
    // └ 得到状态转移方程：dp[i][1]=max{dp[i-1][1],dp[i-1][1]-prices[i]}
    // 因为最后股票一定是卖出的（最后不应该持有股票），所以 dp[n-1][0]为最终结果
    // 时间复杂度：O(n)，其中 n 为数组的长度。一共有 2n 个状态，每次状态转移的时间复杂度为 O(1)，因此时间复杂度为 O(2n)=O(n)。
    // 空间复杂度：O(2n)
    static class Solution {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int n = prices.length;
            int[][] dp = new int[n][2];
            dp[0][1] = -prices[0];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            return dp[n - 1][0];
        }
    }

    // 动态规划 + 空间压缩
    // 状态转移方程中，当前状态只与前一个状态有关，所以不必存储全部状态，只存储当前状态即可
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    static class Solution1 {
        public int maxProfit(int[] prices, int fee) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int n = prices.length;
            int sell = 0;
            int buy = -prices[0];
            for (int i = 1; i < n; i++) {
                sell = Math.max(sell, buy + prices[i] - fee);
                buy = Math.max(buy, sell - prices[i]);
            }
            return sell;
        }
    }
}
