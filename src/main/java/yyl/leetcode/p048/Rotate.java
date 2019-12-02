package yyl.leetcode.p048;

import java.util.Arrays;

/**
 * <h3>旋转图像</h3><br>
 * 给定一个 n × n 的二维矩阵表示一个图像。<br>
 * 将图像顺时针旋转 90 度。<br>
 * 说明：<br>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。<br>
 * <br>
 * 示例 1:
 * 
 * <pre>
 * 给定 matrix = 
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 * </pre>
 * 
 * 示例 2:
 * 
 * <pre>
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ], 
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 * </pre>
 */
public class Rotate {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        System.out.println(Arrays.deepToString(matrix));
        solution.rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    static class Solution {

        // 找规律
        // 3*3 的情况
        // [0][0] => [0][2] => [2][2] => [2][0] => [0][0]
        // [0][1] => [1][2] => [2][1] => [1][0] => [0][1]
        // [1][1] => [1][1] => [1][1] => [1][1] => [1][1]
        // 4*4 的情况
        // [0][0] => [0][3] => [3][3] => [3][0] => [0][0]
        // [0][1] => [1][3] => [3][2] => [2][0] => [0][1]
        // [0][2] => [2][3] => [3][1] => [1][0] => [0][2]
        // [1][1] => [1][2] => [2][2] => [2][1] => [1][1]
        // 总结出规律为：
        // [x][y] => [y][n-x-1]
        // [x][y] => [n-y-1][x] => [n-x-1][n-y-1] => [y][n-x-1] => [y][n-x-1] => [x][y]
        public void rotate(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int n = matrix.length;
            int m = (n + 1) / 2;
            for (int x = 0; x < m; x++) {
                for (int y = x; y < n - x - 1; y++) {
                    // 四个位置
                    int temp1 = matrix[x][y];
                    matrix[x][y] = matrix[n - y - 1][x];
                    matrix[n - y - 1][x] = matrix[n - x - 1][n - y - 1];
                    matrix[n - x - 1][n - y - 1] = matrix[y][n - x - 1];
                    matrix[y][n - x - 1] = temp1;
                }
            }
        }
    }
}
