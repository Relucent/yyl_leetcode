package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>摆动序列</h3><br>
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。 <br>
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。 <br>
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。 <br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,7,4,9,2,5]
 * 输出: 6 
 * 解释: 整个序列均为摆动序列。
 * 
 * 示例 2:
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 * 
 * 示例 3:
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 * </pre>
 * 
 * 进阶: 你能否用 O(n) 时间复杂度完成此题?
 */
public class P0376_WiggleSubsequence {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(6, solution.wiggleMaxLength(new int[] { 1, 7, 4, 9, 2, 5 }));
        Assert.assertEquals(7, solution.wiggleMaxLength(new int[] { 1, 17, 5, 10, 13, 15, 10, 5, 16, 8 }));
        Assert.assertEquals(2, solution.wiggleMaxLength(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
    }

    // 动态规划
    // 记录 第i最长摆动子序列长度，我们用两个状态表示位置i前一个位置是上升，还是下降，状态转移方程为：
    // nums[i] < nums[i - 1]
    // ├ up[i] = up[i - 1];
    // └ down[i] = max(up[i - 1] + 1, down[i - 1]);
    // (nums[i] > nums[i - 1]
    // ├ up[i] = max(up[i - 1], down[i - 1] + 1);
    // └ down[i] = down[i - 1];
    // nums[i] == nums[i-1]
    // ├ up[i] = up[i - 1];
    // └ down[i] = down[i - 1];
    // 时间复杂度：O(n)，其中 n 是序列的长度。只需要遍历该序列一次。
    // 空间复杂度：O(n)，其中 n 是序列的长度。需要开辟两个长度为 n 的数组。
    static class Solution {
        public int wiggleMaxLength(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return n;
            }
            int[] up = new int[n];
            int[] down = new int[n];
            up[0] = 1;
            down[0] = 1;
            for (int i = 1; i < n; i++) {
                // 下降
                if (nums[i] < nums[i - 1]) {
                    up[i] = up[i - 1];
                    down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
                }
                // 上升
                else if (nums[i] > nums[i - 1]) {
                    up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                    down[i] = down[i - 1];
                }
                // 不变
                else {
                    up[i] = up[i - 1];
                    down[i] = down[i - 1];
                }
            }
            return Math.max(up[n - 1], down[n - 1]);
        }
    }

    // 动态规划 + 空间压缩
    // 当前状态仅与前一个状态有关，所以可以进行空间优化，我们维护两个变量即可。
    // 时间复杂度：O(n)，其中 n 是序列的长度。只需要遍历该序列一次。
    // 空间复杂度：O(1)。
    class Solution1 {
        public int wiggleMaxLength(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return n;
            }
            int up = 1, down = 1;
            for (int i = 1; i < n; i++) {
                // 下降
                if (nums[i] < nums[i - 1]) {
                    down = Math.max(up + 1, down);
                }
                // 上升
                else if (nums[i] > nums[i - 1]) {
                    up = Math.max(up, down + 1);
                }
            }
            return Math.max(up, down);
        }
    }
}
