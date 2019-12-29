package yyl.leetcode.p00;


/**
 * <h3>最小路径和</h3><br>
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。<br>
 * 说明：每次只能向下或者向右移动一步。<br>
 * 
 * <pre>
 * 示例:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * </pre>
 */
public class P0064_MinimumPathSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] sample = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(solution.minPathSum(sample));
    }

    // 动态规划
    // 设dp[i][j]为从S点到右下方格子(i,j)的路径数，得出状态转移方式
    // dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
    // 空间压缩(优化)
    // 因为每一个位置的dp值只来源于左一格和上一格，上述的二维数组其实可以压缩为一维数组。
    // dp[j] = min(dp[j],[j-1])+grid[i][j];
    // 时间复杂度：O(m*n), m是网格列数，n是网格行数
    // 空间复杂度：O(m)
    private static class Solution {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) {
                return 0;
            }
            int[] dp = new int[grid[0].length];
            dp[0] = grid[0][0];
            for (int j = 1; j < dp.length; j++) {
                dp[j] = dp[j - 1] + grid[0][j];
            }
            for (int i = 1; i < grid.length; i++) {
                dp[0] += grid[i][0];
                for (int j = 1; j < dp.length; j++) {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
                }
            }
            return dp[dp.length - 1];
        }
    }
}
