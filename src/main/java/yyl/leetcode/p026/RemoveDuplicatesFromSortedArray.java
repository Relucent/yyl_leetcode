package yyl.leetcode.p026;

import java.util.Arrays;

/**
 * <h3>删除排序数组中的重复项</h3><br>
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。<br>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。<br>
 * 示例 1:<br>
 * 给定数组 nums = [1,1,2], <br>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 <br>
 * 你不需要考虑数组中超出新长度后面的元素。<br>
 * 示例 2:<br>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],<br>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。<br>
 * 你不需要考虑数组中超出新长度后面的元素。 <br>
 * <br>
 * <h3>Remove Duplicates from Sorted Array</h3><br>
 * DescriptionHintsSubmissionsDiscussSolution<br>
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.<br>
 * Do not allocate extra space for another array, you must do this in place with constant memory.<br>
 * For example,<br>
 * Given input array nums = [1,1,2],<br>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. <br>
 * It doesn't matter what you leave beyond the new length. <br>
 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 2};
        int len = solution.removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(len);
    }

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int k = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[k] != nums[i]) {
                    nums[++k] = nums[i];
                }
            }
            return k + 1;
        }
    }
}
