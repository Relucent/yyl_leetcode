package yyl.leetcode.p00;

/**
 * <h3>柱状图中最大的矩形</h3><br>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。<br>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。<br>
 * 
 * <pre>
 * +│-------------
 * 6│-------□-----
 * 5│-----□-□-----
 * 4│-----□-□-----
 * 3│-----□-□---□-
 * 2│-□---□-□-□-□-
 * 1│-□-□-□-□-□-□-
 * 0└──────────────>
 * </pre>
 * 
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。<br>
 * 
 * <pre>
* +│-------------
* 6│-------□-----
* 5│-----■-■-----
* 4│-----■-■-----
* 3│-----■-■---□-
* 2│-□---■-■-□-□-
* 1│-□-□-■-■-□-□-
* 0└──────────────>
 * </pre>
 * 
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * </pre>
 */
public class P0084_LargestRectangleInHistogram {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3}));// 10
        System.out.println(solution.largestRectangleArea(new int[] {2, 1, 2}));// 3
        System.out.println(solution.largestRectangleArea(new int[] {}));// 0
        System.out.println(solution.largestRectangleArea(new int[] {5, 4, 1, 2}));// 8
        System.out.println(solution.largestRectangleArea(new int[] {3, 6, 5, 7, 4, 8, 1, 0}));// 20
    }

    // 递增栈解法
    // 遍历数组(将索引压入栈内)
    // 如果遍历到的高度比栈顶高度大，那么将这个高度索引压入栈(保证栈单调递增，这样栈内每个高度的前一个高度就是这个当前高度的左边界)
    // 如果遍历到的高度比栈顶高度小，那么这个高度就是栈顶高度的右边界， 计算这个范围的面积(heights[stack[top]]*(i - stack[top - 1] - 1))，同时这个索引出栈。
    // 遍历结束，如果栈不为空，还需要进行出栈操作，出栈的同时记录出栈元素构成的最大面积(heights[stack[top]]*(heights.length - stack[top - 1] - 1))。
    // 取最大的面积
    // 时间复杂度 ：O(N)，每数字都会被压栈弹栈各一次
    // 空间复杂度 ：O(N)，用来存放栈中元素
    static class Solution {
        public int largestRectangleArea(int[] heights) {
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

    // 暴力法，直接遍历，尝试所有组合，然后获取最大
    // 时间复杂度 ：O(N^2)，需要枚举所有可能
    // 空间复杂度 ：O(1)，不需要额外的空间
    static class Solution1 {
        public int largestRectangleArea(int[] heights) {
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                int height = heights[i];
                for (int j = i; j < heights.length; j++) {
                    height = Math.min(height, heights[j]);
                    maxArea = Math.max(maxArea, (j - i + 1) * height);
                }
            }
            return maxArea;
        }
    }
}
