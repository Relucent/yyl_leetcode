package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>拼接最大数</h3><br>
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。<br>
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。<br>
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 * </pre>
 */
public class P0321_CreateMaximumNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 9, 8, 6, 5, 3 }, solution.maxNumber(new int[] { 3, 4, 6, 5 }, new int[] { 9, 1, 2, 5, 8, 3 }, 5));
        Assert.assertEquals(new int[] { 6, 7, 6, 0, 4 }, solution.maxNumber(new int[] { 6, 7 }, new int[] { 6, 0, 4 }, 5));
        Assert.assertEquals(new int[] { 9, 8, 9 }, solution.maxNumber(new int[] { 3, 9 }, new int[] { 8, 9 }, 3));
    }

    // 单调栈
    // 为了找到长度为 k 的最大数，需要从两个数组中分别选出最大的子序列，这两个子序列的长度之和为 k ，然后将这两个子序列合并得到最大数。
    // 两个子序列的长度最小为 0 ，最大不能超过 k且不能超过对应的数组长度。
    // 设 nums1 的长度为m，设 nums2 的长度为 n，从数组 nums1 中选出长度为 x 的子序列，以及从数组 nums2 中选出长度为 y的子序列，其中 x+y=k，且满足 0≤x≤m 和 0≤y≤n。
    // 遍历所有可能的 x 和 y 的值，对于每一组 x 和 y 的值得到最大数。在整个过程中维护可以通过拼接得到的最大数。
    // 对于每一组 x 和 y 的值，得到最大数的过程分成两步，第一步是分别从两个数组中得到指定长度的最大子序列，第二步是将两个最大子序列合并。
    // 时间复杂度：O(k(m+n+k^2))，其中 m 和 n 分别是数组 nums1 和 nums2 的长度，k 是拼接最大数的长度。
    // ├ 两个子序列的长度之和为 k ，最多有 k 种不同的长度组合。对于每一种长度组合，需要首先得到两个最大子序列，然后进行合并。
    // ├ 得到两个最大子序列的时间复杂度为线性，即 O(m+n)。
    // ├ 合并两个最大子序列，需要进行 k 次合并，每次合并需要进行比较，最坏情况下，比较的时间复杂度为 O(k)，因此合并操作的时间复杂度为 O(k^2)。
    // └ 因此对于每一种长度组合，时间复杂度为 O(m+n+k^2)，总时间复杂度为 O(k(m+n+k^2))
    // 空间复杂度：O(k) ，其中 k 是拼接最大数的长度。每次从两个数组得到两个子序列，两个子序列的长度之和为 k。
    static class Solution {
        public int[] maxNumber(int[] nums1, int[] nums2, int k) {
            int m = nums1.length;
            int n = nums2.length;
            int[] result = new int[k];
            for (int i = Math.max(0, k - n), j = Math.min(k, m); i <= j; i++) {
                int[] subNums1 = getMax(nums1, i);
                int[] subNums2 = getMax(nums2, k - i);
                int[] merged = merge(subNums1, subNums2);
                if (compare(merged, 0, result, 0) > 0) {
                    System.arraycopy(merged, 0, result, 0, k);
                }
            }
            return result;
        }

        private int[] getMax(int[] nums, int k) {
            // 类似单调递减栈
            int[] stack = new int[k];
            // 栈顶位置
            int top = -1;
            // 表示还可以删去多少字符
            int remain = nums.length - k;
            for (int num : nums) {
                //
                while (top >= 0 && stack[top] < num && remain > 0) {
                    top--;
                    remain--;
                }

                if (top < k - 1) {
                    stack[++top] = num;
                } else {
                    remain--;
                }
            }
            return stack;
        }

        private int[] merge(int[] subNums1, int[] subNums2) {
            int x = subNums1.length;
            int y = subNums2.length;
            if (x == 0) {
                return subNums2;
            }
            if (y == 0) {
                return subNums1;
            }
            int length = x + y;
            int[] merged = new int[length];
            for (int i = 0, index1 = 0, index2 = 0; i < length; i++) {
                // 不能只比较当前值，如果当前值相等还需要比较后续哪个大
                if (compare(subNums1, index1, subNums2, index2) > 0) {
                    merged[i] = subNums1[index1++];
                } else {
                    merged[i] = subNums2[index2++];
                }
            }
            return merged;
        }

        private int compare(int[] subNums1, int index1, int[] subNums2, int index2) {
            int x = subNums1.length;
            int y = subNums2.length;
            while (index1 < x && index2 < y) {
                int difference = subNums1[index1] - subNums2[index2];
                if (difference != 0) {
                    return difference;
                }
                index1++;
                index2++;
            }
            return (x - index1) - (y - index2);
        }
    }
}
