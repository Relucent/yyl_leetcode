package yyl.algorithms.v027;

import java.util.Arrays;

/**
 * Given an array and a value, remove all instances of that value in place and return the new length.<br>
 * Do not allocate extra space for another array, you must do this in place with constant memory.<br>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.<br>
 * Example:<br>
 * Given input array nums = [3,2,2,3], val = 3<br>
 * Your function should return length = 2, with the first two elements of nums being 2.<br>
 */
//给定一个数组和一个值，删除该值的所有实例并返回新长度。
//不要为另一个数组分配额外的空间，您必须在内存中执行此操作。
public class RemoveElement {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] nums = { 1, 1, 2, 3, 5, 7, 9 };
		int len = solution.removeElement(nums, 3);
		System.out.println(Arrays.toString(nums));
		System.out.println(len);
	}

	static class Solution {
		public int removeElement(int[] nums, int val) {
			if (nums == null || nums.length == 0) {
				return 0;
			}
			int k = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] != val) {
					nums[k++] = nums[i];
				}
			}
			return k;
		}
	}
}
