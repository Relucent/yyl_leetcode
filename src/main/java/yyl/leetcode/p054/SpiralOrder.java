package yyl.leetcode.p054;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>螺旋矩阵</h3><br>
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 
 * <pre>
 * 示例 1:
 * 输入:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 
 * 示例 2:
 * 输入:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 * </pre>
 */
public class SpiralOrder {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {//
                {1, 2, 3, 4}, //
                {5, 6, 7, 8}, //
                {9, 10, 11, 12}//
        };
        System.out.println(solution.spiralOrder(matrix));
    }

    // 最外层所有元素按照顺时针顺序输出，其次是次外层，以此类推
    // 1.首先设定上下左右边界
    // 2.其次向右移动到最右，此时第一行因为已经使用过了，在代码中就是重新定义上边界
    // 3.若重新定义后，上下边界交错，表明螺旋矩阵遍历结束，跳出循环
    // 4.若上下边界不交错，则遍历还未结束，接着向下向左向上移动，操作过程与第一，二步同理
    // 4.不断循环以上步骤，直到某两条边界交错，跳出循环，返回答案
    // 时间复杂度 O(N)，N 是输入矩阵所有元素的个数
    // 空间复杂度 O(N)，需要数组result存储信息
    static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> result = new ArrayList<>();
            if (matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }
            int x0 = 0;
            int x1 = matrix[0].length - 1;
            int y0 = 0;
            int y1 = matrix.length - 1;
            while (x0 <= x1 && y0 <= y1) {
                for (int x = x0; x <= x1; x++) {
                    result.add(matrix[y0][x]);
                }
                y0++;
                for (int y = y0; y <= y1; y++) {
                    result.add(matrix[y][x1]);
                }
                x1--;
                if (x0 > x1 || y0 > y1) {
                    break;
                }
                for (int x = x1; x >= x0; x--) {
                    result.add(matrix[y1][x]);
                }
                y1--;
                for (int y = y1; y >= y0; y--) {
                    result.add(matrix[y][x0]);
                }
                x0++;
            }
            return result;
        }
    }
}
