package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>子数组最大平均数 I</h3><br>
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。<br>
 * 
 * <pre>
 * 示例：
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= k <= n <= 30,000。<br>
 * └ 所给数据范围 [-10,000，10,000]。<br>
 */
public class P0643_MaximumAverageSubarrayI {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(12.75, solution.findMaxAverage(new int[] { 1, 12, -5, -6, 50, 3 }, 4));
    }

    // 滑动窗口
    // 记录窗口内的数字和，每次窗口右移：新窗口数字和 = 之前的窗口数字和 - 移除的数字 + 加入的数字
    // 窗口从左移动到最右，取最大和的平均数即为结果
    // 注：因为数字范围 [-10,000，10,000]，所以不需要考虑 int 溢出问题
    // 时间复杂度：O(n)，其中 n 是数组 nums 的长度。遍历数组一次。
    // 空间复杂度：O(1)
    static class Solution {
        public double findMaxAverage(int[] nums, int k) {
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            int maxSum = sum;
            for (int i = k; i < nums.length; i++) {
                sum = sum - nums[i - k] + nums[i];
                maxSum = Math.max(maxSum, sum);
            }
            return (double) maxSum / k;
        }
    }
}
