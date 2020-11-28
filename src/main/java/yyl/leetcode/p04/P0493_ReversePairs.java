package yyl.leetcode.p04;

import yyl.leetcode.util.Assert;

/**
 * <h3>翻转对</h3><br>
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。<br>
 * 你需要返回给定数组中的重要翻转对的数量。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * </pre>
 * 
 * 注意:<br>
 * 给定数组的长度不会超过50000。<br>
 * 输入数组中的所有数字都在32位整数的表示范围内。<br>
 */
public class P0493_ReversePairs {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.reversePairs(new int[] { 1, 3, 2, 3, 1 }));
        Assert.assertEquals(3, solution.reversePairs(new int[] { 2, 4, 3, 5, 1 }));
    }

    // 归并法
    // 在归并排序的过程中：
    // 假设对于数组 nums[l..r] 而言，已经分别求出了子数组 nums[l..m] 与 nums[m+1..r] 的翻转对数目，并已将两个子数组分别排好序；
    // 则 nums[l..r] 中的翻转对数目，就等于两个子数组的翻转对数目之和，加上左右端点分别位于两个子数组的翻转对数目。
    // 时间复杂度：O(Nlog⁡N)，其中 N 为数组的长度。
    // 空间复杂度：O(N)，其中 N 为数组的长度。
    static class Solution {
        public int reversePairs(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            return reversePairsRecursive(nums, 0, nums.length - 1);
        }

        private int reversePairsRecursive(int[] nums, int left, int right) {
            if (left == right) {
                return 0;
            }
            int mid = (left + right) / 2;
            int n1 = reversePairsRecursive(nums, left, mid);
            int n2 = reversePairsRecursive(nums, mid + 1, right);
            int result = n1 + n2;

            // 首先统计下标对的数量（关键部分），两边数组都是递增的
            for (int i = left, j = mid + 1; i <= mid;) {
                while (j <= right && (long) nums[i] > 2 * (long) nums[j]) {
                    j++;
                }
                result += j - mid - 1;
                i++;
            }

            // 随后合并两个排序数组
            merge(nums, left, right, mid);
            return result;
        }

        private void merge(int[] nums, int left, int right, int mid) {
            int[] sorted = new int[right - left + 1];
            int i = left;
            int j = mid + 1;
            int k = 0;
            while (i <= mid && j <= right) {
                if (nums[i] < nums[j]) {
                    sorted[k++] = nums[i++];
                } else {
                    sorted[k++] = nums[j++];
                }
            }
            while (i <= mid) {
                sorted[k++] = nums[i++];
            }
            while (j <= right) {
                sorted[k++] = nums[j++];
            }
            System.arraycopy(sorted, 0, nums, left, right - left + 1);
        }
    }

    // 暴力法，遍历所有组合
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    static class Solution1 {
        public int reversePairs(int[] nums) {
            int answer = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if ((long) nums[i] > (long) nums[j] * 2) {
                        answer++;
                    }
                }
            }
            return answer;
        }
    }

}
