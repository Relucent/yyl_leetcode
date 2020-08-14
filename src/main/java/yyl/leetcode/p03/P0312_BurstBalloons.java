package yyl.leetcode.p03;

/**
 * <h3>戳气球</h3><br>
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。<br>
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。<br>
 * 求所能获得硬币的最大数量。<br>
 * 
 * <pre>
 * 说明:
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167 
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *  coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * </pre>
 */
public class P0312_BurstBalloons {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxCoins(new int[] { 3, 1, 5, 8 }));// 167
    }

    // 动态规划
    // #解题思路
    // 考虑最后一个戳破的气球，这个气球的位置可以把整个气球数组分成两部分。
    // 设令 dp[i][j] 表示区间 (i,j)能戳破气球的最大值， 设置k为最后一个戳破的气球，那么有 i < k < j
    // dp[i][j] 等于 max(dp[i][k] + DP[k][j] + nums[i] * nums[k] * nums[j]);
    // 考虑开头和结尾部分，题目说明 nums[-1] = nums[n] = 1，为了计算方便，我们可以从开头结尾插入数值为1的气球（任何数乘以1还是本身）
    // 注意：
    // 1、新数的组 newNums 的长度是 n + 2
    // 2、新加入的两个气球（0和n+1）并不会被捏爆（i < k < j ，所以k的范围是 1~n ）
    // 动态转移方程
    // dp[i][j]= max(for(k=i+1;k<j-1;k++){newNums[i]*newNums[k]*newNums[j]+dp[i,k]+dp[i,k]})
    // 边界条件：
    // 如果 i >= j 的时候 dp[i][j] = 0 （因为i不大于j）
    // i >= 1；（因为新数组开头的1是插入的，所以i从0开始）
    // j<=n+1；（因为新数组结尾的1是插入的，所以j从到n+1结束）
    // 最终答案即为 dp[0][n+1]
    // 实现时要注意到动态规划的次序
    // # 复杂度分析
    // 时间复杂度：O(n^3)，其中 n是气球数量。状态数为 n^2，状态转移复杂度为 O(n)，最终复杂度为 O(n^2*n)=O(n^3)
    // 空间复杂度：O(n^2)，其中 n是气球数量。
    static class Solution {
        public int maxCoins(int[] nums) {
            int n = nums.length;

            // 为了方便运算，在原数组两边插入一个1
            int[] newnum = new int[n + 2];
            newnum[0] = newnum[n + 1] = 1;
            for (int i = 1; i <= n; i++) {
                newnum[i] = nums[i - 1];
            }
            // dp[i][j] 表示区间 (i,j)能戳破气球的最大值，k表示区间 (i,j)最后一个被捏爆的气球
            // k将数组切分成了两个部分(i,k) (k,j)
            // 新数组两头插入的1是不会被捏爆的，因为 k 范围是 i < k < j
            int[][] dp = new int[n + 2][n + 2];
            // i从后往前，便于判断j的范围
            for (int i = n - 1; i >= 0; i--) {
                for (int j = i + 2; j <= n + 1; j++) {
                    for (int k = i + 1; k < j; k++) {
                        int sum = newnum[i] * newnum[k] * newnum[j] + dp[i][k] + dp[k][j];
                        dp[i][j] = Math.max(dp[i][j], sum);
                    }
                }
            }
            return dp[0][n + 1];
        }
    }
}
