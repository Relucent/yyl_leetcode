package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>最长连续递增序列</h3> <br>
 * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。<br>
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。<br>
 * 
 * <pre>
 * 示例 1：
 * 
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。 
 * 
 * 示例 2：
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 * </pre>
 * 
 * 提示： <br>
 * ├ 0 <= nums.length <= 104 <br>
 * └ -109 <= nums[i] <= 109 <br>
 */
public class P0674_LongestContinuousIncreasingSubsequence {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.findLengthOfLCIS(new int[] { 1, 3, 5, 4, 7 }));
        Assert.assertEquals(1, solution.findLengthOfLCIS(new int[] { 2, 2, 2, 2, 2 }));
        Assert.assertEquals(1, solution.findLengthOfLCIS(new int[] { 0 }));
    }

    // 一次遍历
    // 对于下标范围 [l,r] 的连续子序列，如果对任意 l≤i<r 都满足 nums[i]<nums[i+1]，则该连续子序列是递增序列。
    // 时间复杂度：O(n)，其中 n 是数组 nums 的长度。需要遍历数组一次。
    // 空间复杂度：O(1)。额外使用的空间为常数。
    static class Solution {
        public int findLengthOfLCIS(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int answer = 1;
            for (int i = 1, current = 1; i < nums.length; i++) {
                current = nums[i - 1] < nums[i] ? current + 1 : 1;
                answer = Math.max(current, answer);
            }
            return answer;
        }
    }
}
