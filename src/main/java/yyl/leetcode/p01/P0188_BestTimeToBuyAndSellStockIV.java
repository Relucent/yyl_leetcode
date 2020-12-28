package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>买卖股票的最佳时机 IV</h3><br>
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。<br>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。<br>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 
 * 示例 2：
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 *     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * </pre>
 */
public class P0188_BestTimeToBuyAndSellStockIV {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.maxProfit(2, new int[] { 2, 4, 1 }));
        Assert.assertEquals(7, solution.maxProfit(2, new int[] { 3, 2, 6, 5, 0, 3 }));
    }

    // 动态规划
    // 思路与算法
    // ├ 使用一系列变量存储「买入」的状态，再用一系列变量存储「卖出」的状态，通过动态规划的方法解决本题。
    // │ ├ 用 buy[i][j] 表示对于数组 prices[0..i] 中的价格而言，进行恰好 j 笔交易，并且当前手上持有一支股票，这种情况下的最大利润；
    // │ └ 用 sell[i][j] 表示恰好进行 j 笔交易，并且当前手上不持有股票，这种情况下的最大利润。
    // ├ 对状态转移方程进行推导。
    // │ ├ 对于 buy[i][j] ，虑当前手上持有的股票是否是在第 i 天买入的。
    // │ │ ├ 如果是第 i 天买入的，那么在第 i−1 天时，我们手上不持有股票，对应状态 sell[i−1][j]，并且需要扣除 prices[i] 的买入花费；
    // │ │ ├ 如果不是第 i 天买入的，那么在第 i−1 天时，我们手上持有股票，对应状态 buy[i][j]。
    // │ │ └ 可以得到状态转移方程： buy[i][j]=max⁡{buy[i−1][j],sell[i−1][j]−price[i]}
    // │ └ 对于 sell[i][j]
    // │ _ ├ 如果是第 i 天卖出的，那么在第 i−1，我们手上持有股票，对应状态 buy[i−1]，并且需要增加 prices[i] 的卖出收益；
    // │ _ ├ 如果不是第 iii 天卖出的，那么在第 i−1 天时，我们手上不持有股票，对应状态 sell[i−1][j]
    // │ _ └ 可以得到状态转移方程： sell[i][j]=max⁡{sell[i−1][j],buy[i−1][j−1]+price[i]}
    // ├ 所有的 n 天结束后
    // │ ├ 完成的交易数并不是越多越好
    // │ ├ 手上不持有股票对应的最大利润一定是严格由于手上持有股票对应的最大利润的
    // │ └ 因此最终的答案即为 sell[n−1][0..k] 中的最大值。
    // └ 边界
    // _ ├ 对于 buy[0][0..k]，由于只有 prices[0] 唯一的股价，因此不可能进行过任何交易，可以将所有的 buy[0][1..k] 设置为一个非常小的值，表示不合法的状态。
    // _ ├ 而对于 buy[0][0]，它的值为 −prices[0]，即「我们在第 0 天以 prices[0] 的价格买入股票」是唯一满足手上持有股票的方法。
    // _ ├ 对于 sell[0][0..k]，同理我们可以将所有的 sell[0][1..k] 设置为一个非常小的值，表示不合法的状态。
    // _ ├ 而对于 sell[0][0]，它的值为 0，即「我们在第 0 天不做任何事」是唯一满足手上不持有股票的方法。
    // _ └ 因为 n 天最多只能进行n/2 笔交易，因此我们可以将 k 对 n/2 取较小值之后再进行动态规划。
    // 时间复杂度：O(n*min⁡(n,k))，其中 n 是数组 prices 的大小，即使用二重循环进行动态规划需要的时间。
    // 空间复杂度：O(n*min⁡(n,k))。
    static class Solution {
        public int maxProfit(int k, int[] prices) {
            int n = prices.length;
            if (n == 0) {
                return 0;
            }

            k = Math.min(k, n / 2);
            int[][] buy = new int[n][k + 1];
            int[][] sell = new int[n][k + 1];

            buy[0][0] = -prices[0];
            sell[0][0] = 0;
            for (int i = 1; i <= k; ++i) {
                buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
            }

            for (int i = 1; i < n; ++i) {
                buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
                for (int j = 1; j <= k; ++j) {
                    buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                    sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
                }
            }

            int answer = 0;
            for (int x : sell[n - 1]) {
                answer = Math.max(answer, x);
            }
            return answer;
        }
    }
}
