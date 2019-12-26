package yyl.leetcode.p063;

/**
 * <h3>不同路径</h3><br>
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。<br>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。<br>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？<br>
 * S☐☐☐☐☐☐<br>
 * ☐☐☐☒☐☐☐<br>
 * ☐☐☐☐☐☐F<br>
 * 例如，上图是一个7 x 3 的网格。有多少可能的路径？<br>
 * 说明：m 和 n 的值均不超过 100。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 * </pre>
 */
public class UniquePathsII {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[][] sample = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(solution.uniquePathsWithObstacles(sample));
    }

    // 动态规划
    // 设dp[i][j]为从S点到右下方格子(i,j)的步骤数，得出状态转移方式
    // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
    // 如果某个格子是障碍物，那么到达的这个格子的步骤数为0(因为到达不了)
    // 时间复杂度：O(m*n), m是网格列数，n是网格行数
    // 空间复杂度：O(m*n)
    static class Solution {

        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
                return 0;
            }
            int n = obstacleGrid.length;
            int m = obstacleGrid[0].length;
            int[][] dp = new int[n][m];
            dp[0][0] = 1;
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    if (obstacleGrid[y][x] == 1) {
                        dp[y][x] = 0;
                    } else if (x > 0 && y > 0) {
                        dp[y][x] = dp[y - 1][x] + dp[y][x - 1];
                    } else if (x > 0) {
                        dp[y][x] = dp[y][x - 1];
                    } else if (y > 0) {
                        dp[y][x] = dp[y - 1][x];
                    }
                    // else(x=0,y=0){ dp[y][x] = 1; }
                }
            }
            return dp[n - 1][m - 1];
        }
    }

    // 动态规划(空间压缩)
    // 因为每一个位置的dp值只来源于左一格和上一格，上述的二维数组其实可以压缩为一维数组。
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(m)
    static class Solution2 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
                return 0;
            }
            int n = obstacleGrid.length;
            int m = obstacleGrid[0].length;
            int[] dp = new int[m];
            boolean obstacle = false;
            for (int x = 0; x < m; x++) {
                dp[x] = (obstacle || (obstacle = obstacleGrid[0][x] == 1)) ? 0 : 1;
            }
            obstacle = false;
            for (int y = 1; y < n; y++) {
                dp[0] = (obstacle || (obstacle = obstacleGrid[y][0] == 1)) ? 0 : dp[0];
                for (int x = 1; x < m; x++) {
                    dp[x] = obstacleGrid[y][x] == 1 ? 0 : dp[x - 1] + dp[x];
                }
            }
            return dp[m - 1];
        }
    }
}
