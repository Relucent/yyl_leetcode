package yyl.leetcode.p088;

import java.util.Arrays;

/**
 * <h3>合并两个有序数组</h3><br>
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。<br>
 * 说明:<br>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。<br>
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。<br>
 * 示例:<br>
 * 输入:<br>
 * nums1 = [1,2,3,0,0,0], m = 3<br>
 * nums2 = [2,5,6], n = 3<br>
 * 输出: [1,2,2,3,5,6]<br>
 */
public class MergeOrderedArrays {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        solution.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    // 双指针(从尾到头)
    // 时间复杂度：O(n+m)
    // 空间复杂度：O(1)
    static class Solution {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int p1 = m - 1;
            int p2 = n - 1;
            int p0 = m + n - 1;
            while (p1 >= 0 && p2 >= 0) {
                nums1[p0--] = nums1[p1] < nums2[p2] ? nums2[p2--] : nums1[p1--];
            }
            while (p2 >= 0) {
                nums1[p0--] = nums2[p2--];
            }
        }
    }


    // 使用插入排序的思路
    // 时间复杂度为O(m*n)：
    // 空间复杂度为：O(1)
    static class Solution2 {
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for (int i = 0; i < n; i++, m++) {
                int j = m - 1;
                while (j >= 0 && nums2[i] < nums1[j]) {
                    nums1[j + 1] = nums1[j--];
                }
                nums1[j + 1] = nums2[i];
            }
        }
    }
}
