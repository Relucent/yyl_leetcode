package yyl.leetcode.p12;

import yyl.leetcode.util.Assert;

/**
 * <h3>停在原地的方案数</h3><br>
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。<br>
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。<br>
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。<br>
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：steps = 3, arrLen = 2
 * 输出：4
 * 解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
 * 向右，向左，不动
 * 不动，向右，向左
 * 向右，不动，向左
 * 不动，不动，不动
 * 
 * 示例  2：
 * 输入：steps = 2, arrLen = 4
 * 输出：2
 * 解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
 * 向右，向左
 * 不动，不动
 * 
 * 示例 3：
 * 输入：steps = 4, arrLen = 2
 * 输出：8
 * 
 * 提示：
 *     1 <= steps <= 500
 *     1 <= arrLen <= 10^6
 * </pre>
 */
public class P1269_NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(4, solution.numWays(3, 2));
        Assert.assertEquals(2, solution.numWays(2, 4));
        Assert.assertEquals(8, solution.numWays(4, 2));
        Assert.assertEquals(318671228, solution.numWays(47, 38));
    }

    // 动态规划
    // 用 dp[i][j] 表示在 i 步操作之后，指针位于下标 j 的方案数（i的取值范围是 0 ≤ i ≤steps ，j 的取值范围是 0≤j≤arrLen−1）。
    // 由于一共执行 steps 步操作，因此指针所在下标一定不会超过 steps，可以将 j 的取值范围进一步缩小到 0≤j≤min⁡(arrLen−1,steps)。
    // 每一步操作中，指针可以向左或向右移动 1 步，或者停在原地。
    // 因此，状态 dp[i][j] 可以从 dp[i−1][j−1]、dp[i−1][j] 和 dp[i−1][j+1] 这三个状态转移得到。
    // 状态转移方程如下：
    // dp[i][j] = dp[i−1][j−1] + dp[i−1][j] + dp[i−1][j+1];
    // 当没有进行任何操作时，指针一定位于下标 0 ，因此动态规划的边界条件是 dp[0][0]=1，当 1≤j≤min⁡(arrLen−1,steps)时有 dp[0][j]=0 （其他位置没有方案）
    // 指针不能移动到数组范围外，因此对于上述状态转移方程，需要注意下标边界情况。
    // 计算过程中需要对每个状态的值计算模 10^9+7 后的结果
    // 最终得到 dp[steps][0] 的值即为方案数 。
    // 时间复杂度：O(steps×min⁡(arrLen,steps))
    // 空间复杂度：O(steps×min⁡(arrLen,steps))
    static class Solution {

        private final int MODULO = 1000000007;

        public int numWays(int steps, int arrLen) {
            int maxColumn = Math.min(arrLen - 1, steps);
            int[][] dp = new int[steps + 1][maxColumn + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= steps; i++) {
                for (int j = 0; j <= maxColumn; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j - 1 >= 0) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MODULO;
                    }
                    if (j + 1 <= maxColumn) {
                        dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MODULO;
                    }
                }
            }
            return dp[steps][0];
        }
    }
}
