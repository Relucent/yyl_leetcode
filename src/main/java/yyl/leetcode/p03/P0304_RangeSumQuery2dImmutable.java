package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>P0304_RangeSumQuery2dImmutable</h3> <br>
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。 <br>
 * 
 * <pre>
 * 示例：
 * 给定 matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * </pre>
 * 
 * 提示：<br>
 * ├ 你可以假设矩阵不可变。<br>
 * ├ 会多次调用 sumRegion 方法。<br>
 * └ 你可以假设 row1 ≤ row2 且 col1 ≤ col2 。<br>
 * 
 * <pre>
 * // Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1, col1, row2, col2);
 * </pre>
 */
public class P0304_RangeSumQuery2dImmutable {

    public static void main(String[] args) {

        int[][] matrix = { //
                { 3, 0, 1, 4, 2 }, //
                { 5, 6, 3, 2, 1 }, //
                { 1, 2, 0, 1, 5 }, //
                { 4, 1, 0, 1, 7 }, //
                { 1, 0, 3, 0, 5 }//
        };

        NumMatrix numArray = new NumMatrix(matrix);
        Assert.assertEquals(8, numArray.sumRegion(2, 1, 4, 3));
        Assert.assertEquals(11, numArray.sumRegion(1, 1, 2, 2));
        Assert.assertEquals(12, numArray.sumRegion(1, 2, 2, 4));
    }

    // 前缀和
    // 参考 303题
    // 初始化时对矩阵的每一行计算前缀和，检索时对二维区域中的每一行计算子数组和，然后对每一行的子数组和计算总和。
    // 时间复杂度：初始化 O(mn) ，每次检索 O(m)，其中 m 和 n 分别是矩阵 matrix 的行数和列数。
    // 空间复杂度：O(mn)，需要创建一个 m 行 n+1 列的前缀和数组
    static class NumMatrix {

        private final int[][] prefix;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length;
            int n = m > 0 ? matrix[0].length : 0;
            prefix = new int[m][n + 1];
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    prefix[row][col + 1] += prefix[row][col] + matrix[row][col];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int total = 0;
            for (int row = row1; row <= row2; row++) {
                total += (prefix[row][col2 + 1] - prefix[row][col1]);
            }
            return total;
        }
    }

    // 二维前缀和
    // 一个矩阵(x1,y1,x2,y2)可以看做大矩阵(0,0,x2,y2)减去两个小矩阵 (0,0,x1,y2) (0,0,x2,y1) 在加上两个小矩阵重叠部分 (0,0,x1,y1)
    // sumRegion(x1,y1,x2,y2) = prefix[x2][x2] - prefix[x1][y1] - prefix[x1][y2] + - prefix[x1][y1]
    // 如果计算出prefix，就可以在 O(1)的时间复杂度内得到结果
    // 时间复杂度：初始化 O(mn) ，每次检索 O(1)。
    // 空间复杂度：O(mn)，需要创建一个 m+1 行 n+1 列的前缀和数组
    static class NumMatrix2 {

        // 设 prefix[i+1][j+1] 表示左上角为 matrix[0[0]，matrix[i][j]的阵列和
        private final int[][] prefix;

        public NumMatrix2(int[][] matrix) {
            int m = matrix.length;
            int n = m > 0 ? matrix[0].length : 0;
            prefix = new int[m + 1][n + 1];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    prefix[i + 1][j + 1] = prefix[i][j + 1] + prefix[i + 1][j] - prefix[i][j] + matrix[i][j];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return prefix[row2 + 1][col2 + 1] - prefix[row1][col2 + 1] - prefix[row2 + 1][col1] + prefix[row1][col1];
        }
    }
}
