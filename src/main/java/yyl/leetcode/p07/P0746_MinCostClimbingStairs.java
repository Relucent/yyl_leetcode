package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>使用最小花费爬楼梯</h3><br>
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。<br>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。<br>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 * 
 * 示例 2:
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * </pre>
 * 
 * 注意：<br>
 * ├ cost 的长度将会在 [2, 1000]。<br>
 * └ 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。<br>
 */
public class P0746_MinCostClimbingStairs {

    public static void main(String[] args) {
        Solution soultion = new Solution();
        Assert.assertEquals(15, soultion.minCostClimbingStairs(new int[] { 10, 15, 20 }));
        Assert.assertEquals(6, soultion.minCostClimbingStairs(new int[] { 1, 100, 1, 1, 1, 100, 1, 1, 100, 1 }));
    }

    // 动态规划 + 空间压缩
    // 创建长度为 n+1 的数组 dp，其中 dp[i] 表示达到下标 i 的最小花费。
    // 由于可以选择下标 0 或 1 作为初始阶梯，因此有 dp[0]=dp[1]=0。
    // 当 2≤i≤n2 时，可以从下标 i−1 使用 cost[i−1] 的花费达到下标 i ，或者从下标 i−2 使用 cost[i−2] 的花费达到下标 i。为了使总花费最小，dp[i]应取上述两项的最小值，因此状态转移方程如下：
    // dp[i]=min⁡(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])
    // 依次计算 dp 中的每一项的值，最终得到的 dp[n] 即为达到楼层顶部的最小花费。
    // 时间复杂度：O(n)，其中 n 是数组 cost 的长度。需要依次计算每个 dp 值，每个值的计算需要常数时间，因此总时间复杂度是 O(n)。
    // 空间复杂度：O(n)。
    static class Solution {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 0;
            for (int i = 2; i <= n; i++) {
                dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
            }
            return dp[n];
        }
    }

    // 动态规划 + 空间压缩
    // 时间复杂度：O(n)。
    // 空间复杂度：O(1)。使用滚动数组的思想，只需要使用有限的额外空间。
    static class Solution1 {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int previous = 0;
            int current = 0;
            for (int i = 2; i < n; i++) {
                int next = Math.min(current + cost[i - 1], previous + cost[i - 2]);
                previous = current;
                current = next;
            }
            return current;
        }
    }
}
