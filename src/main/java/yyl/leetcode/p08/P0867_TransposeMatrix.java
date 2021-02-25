package yyl.leetcode.p08;

import yyl.leetcode.util.Assert;

/**
 * <h3>转置矩阵</h3> 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。 <br>
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。 <br>
 * 
 * <pre>
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[1,4,7],[2,5,8],[3,6,9]]
 * 
 * 示例 2：
 * 输入：matrix = [[1,2,3],[4,5,6]]
 * 输出：[[1,4],[2,5],[3,6]]
 * </pre>
 * 
 * 提示：<br>
 * ├ m == matrix.length <br>
 * ├ n == matrix[i].length <br>
 * ├ 1 <= m, n <= 1000 <br>
 * ├ 1 <= m * n <= 105 <br>
 * └ -109 <= matrix[i][j] <= 109 <br>
 */
public class P0867_TransposeMatrix {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[][] { //
                { 1, 4, 7 }, //
                { 2, 5, 8 }, //
                { 3, 6, 9 } //
        }, solution.transpose(new int[][] { //
                { 1, 2, 3 }, //
                { 4, 5, 6 }, //
                { 7, 8, 9 } //
        }));

        Assert.assertEquals(new int[][] { //
                { 1, 4 }, //
                { 2, 5 }, //
                { 3, 6 } //
        }, solution.transpose(new int[][] { //
                { 1, 2, 3 }, //
                { 4, 5, 6 } //
        }));
    }

    // 模拟
    // 如果矩阵 matrix 为 m 行 n 列，则转置后的矩阵 matrixT 为 n 行 m 列，且 matrixT[j][i]=matrix[i][j]
    // 创建一个 n 行 m 列的新矩阵，根据转置的规则对新矩阵中的每个元素赋值，则新矩阵为转置后的矩阵。
    // 时间复杂度：O(mn)，其中 m 为矩阵行数，n 为矩阵列数。
    // 空间复杂度：O(1)。
    static class Solution {
        public int[][] transpose(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] transposed = new int[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    transposed[j][i] = matrix[i][j];
                }
            }
            return transposed;
        }
    }
}
