package yyl.leetcode.p06;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>三个数的最大乘积</h3><br>
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: 6
 * 
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: 24
 * </pre>
 * 
 * 注意:<br>
 * ├ 给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。<br>
 * └ 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。<br>
 */
public class P0628_MaximumProductOfThreeNumbers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(6, solution.maximumProduct(new int[] { 1, 2, 3 }));
        Assert.assertEquals(24, solution.maximumProduct(new int[] { 1, 2, 3, 4 }));
        Assert.assertEquals(-6, solution.maximumProduct(new int[] { -1, -2, -3, -4 }));
    }

    // 排序
    // 最大乘积的情况
    // ├ 如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；如果全是非正数，则最大的三个数相乘同样也为最大乘积。
    // ├ 如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对值最大）与最大正数的乘积。
    // └ 问题转换为查找最大的三个数和最小两个数
    // 时间复杂度：O(N*logN)，其中N是数组长度，排序需要 O(Nlog⁡N)的时间。
    // 空间复杂度：O(logN)，主要为排序的空间开销。
    static class Solution {
        public int maximumProduct(int[] nums) {
            Arrays.sort(nums);
            int n = nums.length;
            return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[0] * nums[1] * nums[n - 1]);
        }
    }

    // 线性扫描
    // 实际上只要求出数组中最大的三个数以及最小的两个数，因此可以不用排序，直接用线性扫描直接得出这五个数。
    // 时间复杂度：O(N) ，其中 NNN 为数组长度。仅需遍历数组一次。
    // 空间复杂度：O(1) 。
    static class Solution1 {
        public int maximumProduct(int[] nums) {
            // 最小的和第二小的
            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            // 最大的、第二大的和第三大的
            int max1 = Integer.MIN_VALUE;
            int max2 = Integer.MIN_VALUE;
            int max3 = Integer.MIN_VALUE;

            for (int x : nums) {
                if (x > max1) {
                    max3 = max2;
                    max2 = max1;
                    max1 = x;
                } else if (x > max2) {
                    max3 = max2;
                    max2 = x;
                } else if (x > max3) {
                    max3 = x;
                }
                if (x < min1) {
                    min2 = min1;
                    min1 = x;
                } else if (x < min2) {
                    min2 = x;
                }
            }
            return Math.max(max1 * max2 * max3, min1 * min2 * max1);
        }
    }
}
