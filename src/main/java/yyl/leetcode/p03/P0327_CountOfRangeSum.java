package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <b3>区间和的个数</b3><br>
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。<br>
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。<br>
 * 说明: 最直观的算法复杂度是 O(n^2) ，请在此基础上优化你的算法。<br>
 * 
 * <pre>
 * 示例:
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3 
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 * </pre>
 */
public class P0327_CountOfRangeSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.countRangeSum(new int[] { -2, 5, -1 }, -2, 2));
        Assert.assertEquals(3, solution.countRangeSum(new int[] { -2147483647, 0, -2147483647, 2147483647 }, -564, 3864));
    }

    // 暴力法
    // 尝试所有组合，最终算出匹配的个数；需要注意数值溢出问题，使用long求和避免int溢出。
    // 时间复杂度 : O(n^2)
    // 空间复杂度 : O(1)
    static class Solution {
        public int countRangeSum(int[] nums, int lower, int upper) {
            int answer = 0;
            for (int i = 0; i < nums.length; i++) {
                long sum = 0;
                for (int j = i; j < nums.length; j++) {
                    sum += nums[j];
                    if (lower <= sum && sum <= upper) {
                        answer++;
                    }
                }
            }
            return answer;
        }
    }
}
