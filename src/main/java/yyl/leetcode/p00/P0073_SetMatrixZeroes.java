package yyl.leetcode.p00;

import java.util.Arrays;

/**
 * <h3>矩阵置零</h3><br>
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出: 
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 * 
 * 示例 2:
 * 输入: 
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出: 
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 * </pre>
 * 
 * 进阶:<br>
 * 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。<br>
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。<br>
 * 你能想出一个常数空间的解决方案吗？<br>
 */
public class P0073_SetMatrixZeroes {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] sample = {//
                {1, 1, 1}, //
                {1, 0, 1}, //
                {1, 1, 1},//
        };//
        solution.setZeroes(sample);
        System.out.println(Arrays.deepToString(sample));
    }

    // 需要记录为0的行和列，一般情况需要 O(m + n)的额外空间
    // 时间复杂度：O(M×N)，其中 M和N分别对应行数和列数。
    // 空间复杂度：O(M+N)
    static class Solution {
        public void setZeroes(int[][] matrix) {
            boolean[] zeroRows = new boolean[matrix.length];
            boolean[] zeroColumns = new boolean[matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                int[] row = matrix[i];
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == 0) {
                        zeroRows[i] = true;
                        zeroColumns[j] = true;
                    }
                }
            }
            for (int i = 0; i < matrix.length; i++) {
                int[] row = matrix[i];
                for (int j = 0; j < row.length; j++) {
                    if (zeroRows[i] || zeroColumns[j]) {
                        row[j] = 0;
                    }
                }
            }
        }
    }

    // 使用第一个包含0的行来记录该列是否有0，然后进行置零处理
    // 1、遍历矩阵，记录第一个包含 0 的行(sign)，用一行来记录存在 0 的列。
    // 2、将其他包含 0 的行置0
    // 3、遍历sign行数据，根据其中0的列来将列置为0
    // 4、将sign行置为0
    // 时间复杂度：O(M×N)，其中 M和N分别对应行数和列数。
    // 空间复杂度：O(1)
    static class Solution2 {
        public void setZeroes(int[][] matrix) {
            int[] sign = null;
            for (int i = 0; i < matrix.length; i++) {
                boolean hasZero = false;
                T: for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 0) {
                        if (sign == null) {
                            sign = matrix[i];
                            break T;
                        }
                        hasZero = true;
                        sign[j] = 0;
                    }
                }
                if (hasZero) {
                    Arrays.fill(matrix[i], 0);
                }
            }
            if (sign != null) {
                for (int j = 0; j < sign.length; j++) {
                    if (sign[j] == 0) {
                        for (int i = 0; i < matrix.length; i++) {
                            matrix[i][j] = 0;
                        }
                    }
                    sign[j] = 0;
                }
            }
        }
    }
}
