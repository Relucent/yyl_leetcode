package yyl.leetcode.p01;

import java.util.Arrays;
import java.util.List;

/**
 * <h3>三角形最小路径和</h3> 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。<br>
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。<br>
 * 
 * <pre>
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 * </pre>
 * 
 * 说明： 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 */
public class P0120_Triangle {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> triangle = Arrays.asList(//
                Arrays.asList(2), //
                Arrays.asList(3, 4), //
                Arrays.asList(6, 5, 7), //
                Arrays.asList(4, 1, 8, 3)//
        );
        System.out.println(solution.minimumTotal(triangle));// 11
    }

    // 动态规划
    // # 思路与算法
    // 在本题中，给定的三角形的行数为 n，并且第 i行（从 0 开始编号）包含了 i+1 个数。如果将每一行的左端对齐，那么会形成一个等腰直角三角形，如下所示：
    // [2],
    // [3,4],
    // [6,5,7],
    // [4,1,8,3]
    // 用dp[i][j]表示从三角形顶部走到(i,j)的最小路径和，由于每一步只能移动到下一行「相邻的节点」上，因此要想走到位置 (i,j)，上一步就只能在位置 (i−1,j−1)或者位置 (i−1,j)。
    // 反过来思考，从三角形底部往顶部走，求最小的路径，那么相当于每次只能向上或者向右上走，可以获得状态转移方程：
    // dp[i][j] = min(dp[i+1][j+1],[i+1][j+1]) + triangle[i][j];
    // 最后到达顶部，dp[0][0] 就是最终答案
    // # 复杂度分析
    // 时间复杂度： O(n^2)，其中 n 是三角形的行数。
    // 空间复杂度： O(n^2)，我们需要一个 n^2 的二维数组存放所有的状态
    static class Solution {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            if (n == 0) {
                return 0;
            }
            int[][] dp = new int[n][n];
            for (int i = n - 1, j = 0; j <= i; j++) {
                dp[i][j] = triangle.get(i).get(j);
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0][0];
        }
    }

    // 动态规划 + 空间优化
    // 在题目描述中的「说明」部分，提到了可以将空间复杂度优化至 O(n)
    // 根据状态转移方程可以看到，每次 dp[i][j]只与dp[i-1][...]相关，而与dp[i-2][...]及之前的状态无关，于是可以对dp空间压缩
    // 时间复杂度：O(n2，其中 n 是三角形的行数
    // 空间复杂度：O(n)
    static class Solution1 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            if (n == 0) {
                return 0;
            }
            int[] dp = new int[n];
            for (int i = n - 1, j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j);
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];
        }
    }
}
