package yyl.leetcode.p059;

import java.util.Arrays;

/**
 * <h3>螺旋矩阵 II</h3> 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。<br>
 * 
 * <pre>
 * 示例:
 * 输入: 3
 * 输出:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * </pre>
 */
public class GenerateMatrix {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.generateMatrix(3)));
    }

    // 模拟法，设定边界
    // 时间复杂度：O(n^2)，n^2 = n*n 矩阵所有元素的个数
    // 空间复杂度：O(n^2)，存储结果数组
    static class Solution {
        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            for (int x0 = 0, x1 = n - 1, y0 = 0, y1 = n - 1, i = 1, max = n * n; i <= max;) {
                for (int x = x0; x <= x1; matrix[y0][x++] = i++);
                y0++;
                for (int y = y0; y <= y1; matrix[y++][x1] = i++);
                x1--;
                for (int x = x1; x >= x0; matrix[y1][x--] = i++);
                y1--;
                for (int y = y1; y >= y0; matrix[y--][x0] = i++);
                x0++;
            }
            return matrix;
        }
    }

    // 模拟法，设定边界
    // 时间复杂度：O(n^2)，n^2 = n*n 矩阵所有元素的个数
    // 空间复杂度：O(n^2)，存储结果数组
    // 虽然时间复杂度与上面的算法相同，但是因为判断多了一些，所以实际执行效率会稍微慢一些(毫秒级别，实际测试有差别但是不明显)
    static class Solution2 {
        public int[][] generateMatrix(int n) {
            int[][] matrix = new int[n][n];
            int x0 = 0;
            int x1 = n - 1;
            int y0 = 0;
            int y1 = n - 1;
            int i = 1;
            while (x0 <= x1 && y0 <= y1) {
                for (int x = x0; x <= x1; x++) {
                    matrix[y0][x] = i++;
                }
                y0++;

                for (int y = y0; y <= y1; y++) {
                    matrix[y][x1] = i++;
                }
                x1--;

                for (int x = x1; x >= x0; x--) {
                    matrix[y1][x] = i++;
                }
                y1--;

                for (int y = y1; y >= y0; y--) {
                    matrix[y][x0] = i++;
                }
                x0++;
            }
            return matrix;
        }
    }
}
