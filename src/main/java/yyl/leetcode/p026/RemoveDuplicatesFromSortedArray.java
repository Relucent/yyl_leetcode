package yyl.leetcode.p026;

import java.util.Arrays;

/**
 * Remove Duplicates from Sorted Array<br>
 * DescriptionHintsSubmissionsDiscussSolution<br>
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.<br>
 * Do not allocate extra space for another array, you must do this in place with constant memory.<br>
 * For example,<br>
 * Given input array nums = [1,1,2],<br>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. <br>
 * It doesn't matter what you leave beyond the new length. <br>
 */
//给定一个排序数组，删除重复的位置，使每个元素只出现一次，并返回新的长度。
//不要为另一个数组分配额外的空间，您必须在内存中执行此操作。
public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums = { 1, 1, 2 };
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
