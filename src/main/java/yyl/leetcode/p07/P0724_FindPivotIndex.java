package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>寻找数组的中心索引</h3><br>
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。<br>
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。<br>
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 解释：
 * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * 
 * 示例 2：
 * 输入：
 * nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心索引。
 * </pre>
 * 
 * 说明：<br>
 * ├ nums 的长度范围为 [0, 10000]。<br>
 * └ 任何一个 nums[i] 将会是一个范围在 [-1000, 1000]的整数。<br>
 */
public class P0724_FindPivotIndex {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.pivotIndex(new int[] { 1, 7, 3, 6, 5, 6 }));
        Assert.assertEquals(-1, solution.pivotIndex(new int[] { 1, 2, 3 }));
    }

    // 前缀和
    // 记数组的全部元素之和为 total，当遍历到第 i 个元素时，左侧元素之和为 sum，则其右侧元素之和为 total-sum−nums[i]。
    // 左右侧元素相等即为: sum=total-sum−nums[i]
    // 找到索引 i 返回即可，如果没有找到则返回 -1
    // 时间复杂度：O(n)，其中 n 为数组的长度。
    // 空间复杂度：O(1)。
    static class Solution {
        public int pivotIndex(int[] nums) {
            int n = nums.length;
            int total = 0;
            for (int num : nums) {
                total += num;
            }
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (sum == total - sum - nums[i]) {
                    return i;
                }
                sum += nums[i];
            }
            return -1;
        }
    }
}
