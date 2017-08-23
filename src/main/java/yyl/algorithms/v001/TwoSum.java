package yyl.algorithms.v001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.<br>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.<br>
 */
// 给定一个整数数组，从其中获得两个数，使其相加等于指定数字，返回这两个数在数组中的的索引
public class TwoSum {
	public static void main(String[] args) {
		int[] nums = { 2, 7, 11, 15 };
		int target = 9;
		int[] result = twoSum(nums, target);
		System.out.println(Arrays.toString(result));
	}

	//Time complexity : O(n^2)
	// Space complexity : O(1)
	public static int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				if (nums[j] + nums[i] == target) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	// Time complexity : O(n)
	// Space complexity : O(n)
	public static int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement)) {
				return new int[] { map.get(complement), i };
			}
			map.put(nums[i], i);
		}
		return null;
	}
}
