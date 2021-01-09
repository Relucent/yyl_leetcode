package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>买卖股票的最佳时机 III</h3><br>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 <br>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 <br>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 <br>
 * 
 * <pre>
 * 示例 1:
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 
 * 示例 3：
 * 输入：prices = [7,6,4,3,1] 
 * 输出：0 
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * 
 * 示例 4：
 * 输入：prices = [1]
 * 输出：0
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= prices.length <= 105 <br>
 * └ 0 <= prices[i] <= 105 <br>
 */
public class P0123_BestTimeToBuyAndSellStockIII {

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        Assert.assertEquals(6, solution.maxProfit(new int[] { 3, 3, 5, 0, 0, 3, 1, 4 }));
        Assert.assertEquals(4, solution.maxProfit(new int[] { 1, 2, 3, 4, 5 }));
        Assert.assertEquals(0, solution.maxProfit(new int[] { 7, 6, 4, 3, 1 }));
        Assert.assertEquals(0, solution.maxProfit(new int[] { 1 }));
    }

    // 动态规划
    // 以 k 表示最大交易次数，j表示持有状态，f(i,k,j) 表示第 i 天，第 k 次交易，状态为 j 时，利润大小，则题目所求值为 f(n-1,2,0)。
    // 因为 j 只有 0 和 1 两种状态，则有如下递推关系式：
    // f(i,k,0)=max[f(i-1,k,0),f(i-1,k,1)+prices[i]]
    // f(i,k,1)=max[f(i-1,k,1),f(i-1,k-1,0)-prices[i]]
    // 最多只能交易两次，所以 k 的值只有三种情况，即 0、1、2。
    // 时间复杂度：O(n)，其中 n 是数组 prices 的长度。
    // 空间复杂度：O(1)。
    static class Solution {
        public int maxProfit(int[] prices) {
            // 交易次数只有 0,1,2 三种情况， 持有状态只有0,1 (未持有，持有)两种
            int[][] dp = { //
                    { 0, -prices[0] }, //
                    { 0, -prices[0] }, //
                    { 0, -prices[0] }//
            };
            for (int i = 1; i < prices.length; i++) {
                for (int k = 1; k < 3; k++) {
                    dp[k] = new int[] { //
                            Math.max(dp[k][0], dp[k][1] + prices[i]), //
                            Math.max(dp[k][1], dp[k - 1][0] - prices[i])//
                    };
                }
            }
            return dp[2][0];
        }
    }

    // 动态规划
    // 由于我们最多可以完成两笔交易，因此在任意一天结束之后，我们会处于以下五个状态中的一种：
    // ├ 未进行过任何操作；
    // ├ 只进行过一次买操作；
    // ├ 进行了一次买操作和一次卖操作，即完成了一笔交易；
    // ├ 在完成了一笔交易的前提下，进行了第二次买操作；
    // ├ 完成了全部两笔交易。
    // └ 由于第一个状态的利润显然为 0，因此我们可以不用将其记录。对于剩下的四个状态，我们分别将它们的最大利润记为 buy1，sell1，buy2，sell2​。
    // 时间复杂度：O(n)，其中 n 是数组 prices 的长度。
    // 空间复杂度：O(1)。
    static class Solution1 {
        public int maxProfit(int[] prices) {
            int buy1 = Integer.MIN_VALUE;
            int sell1 = 0;
            int buy2 = Integer.MIN_VALUE;
            int sell2 = 0;
            for (int price : prices) {
                buy1 = Math.max(buy1, -price);
                sell1 = Math.max(sell1, buy1 + price);
                buy2 = Math.max(buy2, sell1 - price);
                sell2 = Math.max(sell2, buy2 + price);
            }
            return sell2;
        }
    }
}
