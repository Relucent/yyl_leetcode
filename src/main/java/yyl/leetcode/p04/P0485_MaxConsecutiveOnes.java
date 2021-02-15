package yyl.leetcode.p04;

import yyl.leetcode.util.Assert;

/**
 * <h3>最大连续1的个数</h3><br>
 * 给定一个二进制数组， 计算其中最大连续1的个数。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * </pre>
 * 
 * 注意：<br>
 * ├ 输入的数组只包含 0 和1。<br>
 * └ 输入数组的长度是正整数，且不超过 10,000。<br>
 */
public class P0485_MaxConsecutiveOnes {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.findMaxConsecutiveOnes(new int[] { 1, 1, 0, 1, 1, 1 }));
        Assert.assertEquals(0, solution.findMaxConsecutiveOnes(new int[] {}));
        Assert.assertEquals(0, solution.findMaxConsecutiveOnes(new int[] { 0 }));
        Assert.assertEquals(1, solution.findMaxConsecutiveOnes(new int[] { 1 }));
    }

    // 一次遍历
    // 遍历数组，并记录最大的连续 1 的个数和当前的连续 1 的个数。
    // 如果当前元素是 1 ，则将当前的连续 1 的个数加 1 ，否则，使用之前的连续 1 的个数更新最大的连续 1 的个数，并将当前的连续 1 的个数清零。
    // 遍历数组结束之后，需要再次使用当前的连续 1 的个数更新最大的连续 1 的个数，因为数组的最后一个元素可能是 1 ，且最长连续 1 的子数组可能出现在数组的末尾。
    // 时间复杂度：O(n) ，其中 n 是数组的长度。需要遍历数组一次。
    // 空间复杂度：O(1) 。
    static class Solution {
        public int findMaxConsecutiveOnes(int[] nums) {
            int maxCount = 0;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    count++;
                } else {
                    maxCount = Math.max(maxCount, count);
                    count = 0;
                }
            }
            return Math.max(maxCount, count);
        }
    }

    // 双指针
    // 遍历数组，使用两个指针，遇到1后，快指针向后移动，直到末尾或者遇到0，快指针位置减去满指针位置就是中间1的个数，然后取最大连续1的个数即可
    // 时间复杂度：O(n) ，其中 n 是数组的长度。需要遍历数组一次。
    // 空间复杂度：O(1) 。
    static class Solution1 {
        public int findMaxConsecutiveOnes(int[] nums) {
            int max = 0;
            for (int i = 0; i < nums.length;) {
                if (nums[i] != 1) {
                    i++;
                } else {
                    int j = i;
                    while (i < nums.length && nums[i] == 1) {
                        i++;
                    }
                    max = Math.max(max, i - j);
                }
            }
            return max;
        }
    }
}
