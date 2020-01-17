package yyl.leetcode.p00;

import java.util.Arrays;

/**
 * <h3>最大矩形</h3><br>
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。<br>
 * 
 * <pre>
 * 示例:
 * 输入:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * 输出: 6
 * </pre>
 */
public class P0085_MaximalRectangle {

    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] matrix = {//
                {'1', '0', '1', '0', '0'}, //
                {'1', '0', '1', '1', '1'}, //
                {'1', '1', '1', '1', '1'}, //
                {'1', '0', '0', '1', '0'}//
        };//
        System.out.println(solution.maximalRectangle(matrix));// 6
    }

    // 递增栈解法
    // 可以将这个问题转化为柱状图中最大的矩形的问题(P0084)，输入矩阵的每一行以上的所有行看成是一个柱状图。
    // 将矩阵
    // |1|0|1|0|0|
    // |1|0|1|1|1|
    // |1|1|1|1|1|
    // |1|0|0|1|0|
    // 转化为
    // |1|0|1|0|0|
    // |2|0|2|1|1|
    // |3|1|3|2|2|
    // |4|0|0|3|0|
    // 求每一行的 maxArea，然后取最大值。
    // 时间复杂度：O(NM)，N矩阵的行数，M矩阵的列数
    // 空间复杂度：O(M)，M矩阵列数，存储高度的数组的长度
    static class Solution {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int n = matrix.length;
            int m = matrix[0].length;
            int[] heights = new int[m];
            int maxArea = 0;
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    heights[x] = matrix[y][x] == '1' ? (heights[x] + 1) : 0;
                }
                maxArea = Math.max(maxArea, largestRectangleArea(heights));
            }
            return maxArea;
        }

        private int largestRectangleArea(int[] heights) {
            int[] stack = new int[heights.length + 1];
            int top = -1;
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                while (top != -1 && heights[i] <= heights[stack[top]]) {
                    int height = heights[stack[top]];
                    int width = top == 0 ? i : i - stack[top - 1] - 1;
                    maxArea = Math.max(maxArea, width * height);
                    top--;
                }
                stack[++top] = i;
            }
            while (top != -1) {
                int height = heights[stack[top]];
                int width = top == 0 ? heights.length : (heights.length - stack[top - 1] - 1);
                maxArea = Math.max(maxArea, width * height);
                top--;
            }
            return maxArea;
        }
    }

    // 动态规划
    // 对每个点我们会通过以下步骤计算一个矩形：
    // 1、 首先不断向上方遍历，直到遇到0，以此找到矩形的最大高度。
    // 2、 然后向左右两边扩展，直到无法容纳矩形最大高度。
    // 使用动态规划，在线性时间内用上一行每个点的 H，L，和 R 计算出下一行每个点的的H，L，和R。
    // 问题转化为如何更新每个数组
    // 1、 计算高度：高度的定义是从该点出发连续的1的个数
    // new_height[j] = row[j] == '1' ? old_height[j] + 1 : 0
    // 2、 计算左边界：如果当前行出现'0',那么左边界会右移，否则等于之前的左边界
    // cur_left 是当前点左侧边界(当前点左边第一个0的序号加1)
    // new_left[j] = max(old_left[j], cur_left)
    // 3、 计算右边界：
    // new_right[j] = min(old_right[j], cur_right)
    // 4、 left，right，和 height数组更新后，计算每个矩形的面积。
    // 5、获得最大的矩形面积
    // 时间复杂度：O(NM)，N矩阵的行数，M矩阵的列数
    // 空间复杂度：O(M)，辅助数组的长度
    static class Solution1 {
        public int maximalRectangle(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int n = matrix.length;
            int m = matrix[0].length;
            int[] heights = new int[m];
            int[] lefts = new int[m];
            int[] rights = new int[m];
            Arrays.fill(rights, m);

            int maxArea = 0;
            for (int i = 0; i < n; i++) {
                // # update height
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == '1') {
                        heights[j] = heights[j] + 1;
                    } else {
                        heights[j] = 0;
                    }
                }
                // # update left
                int currentLeft = 0;
                for (int j = 0; j < m; j++) {
                    if (matrix[i][j] == '1') {
                        lefts[j] = Math.max(lefts[j], currentLeft);
                    } else {
                        lefts[j] = 0;
                        currentLeft = j + 1;
                    }
                }
                // # update right
                int currentRight = m;
                for (int j = m - 1; j >= 0; j--) {
                    if (matrix[i][j] == '1') {
                        rights[j] = Math.min(rights[j], currentRight);
                    } else {
                        rights[j] = m;
                        currentRight = j;
                    }
                }
                // # area
                for (int j = 0; j < m; j++) {
                    maxArea = Math.max(maxArea, heights[j] * (rights[j] - lefts[j]));
                }
            }
            return maxArea;
        }
    }
}
