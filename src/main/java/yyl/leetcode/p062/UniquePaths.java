package yyl.leetcode.p062;

import java.util.Arrays;

/**
 * <h3>不同路径</h3><br>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。<br>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。<br>
 * 问总共有多少条不同的路径？<br>
 * S☐☐☐☐☐☐<br>
 * ☐☐☐☐☐☐☐<br>
 * ☐☐☐☐☐☐F<br>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？<br>
 * 说明：m 和 n 的值均不超过 100。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 * </pre>
 */
public class UniquePaths {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.uniquePaths(23, 12));// 193536720
    }

    // 动态规划
    // 设dp[i][j]为从S点到右下方格子(i,j)的步骤数，得出状态转移方式
    // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(m*n)
    static class Solution {
        public int uniquePaths(int m, int n) {
            if (m == 1 || n == 1) {
                return 1;
            }
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }

    // 动态规划(空间压缩)
    // 设dp[i][j]为从S点到右下方格子(i,j)的步骤数，得出状态转移方式
    // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    // 时间复杂度：O(m*n)
    // 空间复杂度O(min(m, n))
    static class Solution2 {
        public int uniquePaths(int m, int n) {
            if (m == 1 || n == 1) {
                return 1;
            }
            int less = Math.min(m, n);
            int more = Math.max(m, n);

            // 利用了dp暂存了上一轮的值，在这一次的赋值中循环利用
            int[] dp = new int[m];
            // 默认第一行或者第一列的值都是1
            Arrays.fill(dp, 1);

            for (int i = 1; i < more; i++) {
                for (int j = 1; j < less; j++) {
                    dp[j] = dp[j - 1] + dp[j];
                }
            }
            return dp[less - 1];
        }
    }
}
