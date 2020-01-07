package yyl.leetcode.p00;

/**
 * <h3>搜索二维矩阵</h3><br>
 * 编写一个高效的算法来判断 m*n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：<br>
 * 每行中的整数从左到右按升序排列。<br>
 * 每行的第一个整数大于前一行的最后一个整数。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 3
 * 输出: true
 * 
 * 示例 2:
 * 输入:
 * matrix = [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * target = 13
 * 输出: false
 * </pre>
 */
public class P0074_SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {//
                {1, 3, 5, 7}, //
                {10, 11, 16, 20}, //
                {23, 30, 34, 50},//
        };
        int target = 3;
        System.out.println(solution.searchMatrix(matrix, target));
    }

    // 二分法查找
    // 根据题目矩阵特征，每行的元素都是升序，可以将输入的 m*n矩阵可以视为长度为 m*n的有序数组，然后使用二分法来查找
    // 时间复杂度：O(log(M*N))，标准的二分查找
    // 空间复杂度：O(1)
    static class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int columns = matrix[0].length;
            int low = 0;
            int high = matrix.length * columns - 1;
            while (low <= high) {
                int middle = (low + high) / 2;
                int value = matrix[middle / columns][middle % columns];
                if (target == value) {
                    return true;
                } else if (target > value) {
                    low = middle + 1;
                } else /* if (target < value) */ {
                    high = middle - 1;
                }
            }
            return false;
        }
    }

    // 直接循环遍历
    // 时间复杂度：O(M*N)，M和N是矩阵的宽高
    // 空间复杂度：O(1)
    static class Solution2 {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int n = matrix[0].length;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
