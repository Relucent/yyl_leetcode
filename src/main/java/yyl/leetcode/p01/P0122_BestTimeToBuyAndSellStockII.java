package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>买卖股票的最佳时机 II</h3><br>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。<br>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。<br>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * </pre>
 * 
 * 提示：<br>
 * 1 <= prices.length <= 3 * 10 ^ 4<br>
 * 0 <= prices[i] <= 10 ^ 4<br>
 */

public class P0122_BestTimeToBuyAndSellStockII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(7, solution.maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        Assert.assertEquals(4, solution.maxProfit(new int[] { 1, 2, 3, 4, 5 }));
        Assert.assertEquals(0, solution.maxProfit(new int[] { 7, 6, 4, 3, 1 }));
    }

    // 动态规划
    // 考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
    // 转移方程
    // dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]}
    // dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
    // 时间复杂度：O(n)，其中 n 为数组的长度。一共有 2n 个状态，每次状态转移的时间复杂度为 O(1)，因此时间复杂度为 O(2n)=O(n)。
    // 空间复杂度：O(n)。需要开辟 O(n) 空间存储动态规划中的所有状态。
    static class Solution {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int[][] dp = new int[prices.length][2];
            dp[0][0] = 0; // -> 手里没有股票的状态
            dp[0][1] = -prices[0]; // -> 手里持有股票的状态
            for (int i = 1; i < prices.length; i++) {
                dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            }
            return dp[prices.length - 1][0];
        }
    }

    // 动态规划 + 空间压缩
    // 时间复杂度：O(n)，其中 n 为数组的长度。一共有 2n 个状态，每次状态转移的时间复杂度为 O(1)，因此时间复杂度为 O(2n)=O(n)。
    // 空间复杂度：O(1)。常数级空间复杂度。
    static class Solution1 {
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int x = 0;// 手里没有股票的状态
            int y = -prices[0]; // 手里持有股票的状态
            for (int i = 1; i < prices.length; i++) {
                int tx = Math.max(y + prices[i], x);
                int ty = Math.max(x - prices[i], y);
                x = tx;
                y = ty;
            }
            return x;
        }
    }
}
