package yyl.algorithms.v018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?<br>
 * Find all unique quadruplets in the array which gives the sum of target.<br>
 * 
 * <pre>
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 * </pre>
 */
//给定n个整数的数组S，找到S中a + b + c + d = target的组合 
public class N4Sum {

	public static void main(String[] args) {
		int[] nums = { -3, -1, 0, 2, 4, 5 };
		List<List<Integer>> result = fourSum(nums, 1);
		for (List<Integer> list : result) {
			System.out.println(list);//[[-3,-1,0,5],[-3,0,0,4],[-1,0,0,2]]
		}
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length < 0) {
			return result;
		}
		Arrays.sort(nums);
		for (int right = nums.length - 1, i = right; i > 2; i--) {
			if (i == right || nums[i] != nums[i + 1]) {
				List<List<Integer>> values = threeSum(nums, i - 1, target - nums[i]);
				for (int j = 0; j < values.size(); j++) {
					values.get(j).add(nums[i]);
				}
				result.addAll(values);
			}
		}
		return result;
	}

	private static List<List<Integer>> threeSum(int[] nums, int right, int target) {
		List<List<Integer>> result = new ArrayList<>();
		for (int i = right; i > 1; i--) {
			if (i == right || nums[i] != nums[i + 1]) {
				List<List<Integer>> values = twoSum(nums, i - 1, target - nums[i]);
				for (int j = 0; j < values.size(); j++) {
					values.get(j).add(nums[i]);
				}
				result.addAll(values);
			}
		}
		return result;
	}

	private static List<List<Integer>> twoSum(int[] nums, int right, int target) {
		List<List<Integer>> result = new ArrayList<>();
		int left = 0;
		while (left < right) {
			if (nums[left] + nums[right] == target) {
				List<Integer> values = new ArrayList<Integer>();
				values.add(nums[left]);
				values.add(nums[right]);
				result.add(values);
				left++;
				right--;
				while (left < right && nums[left] == nums[left - 1]) {
					left++;
				}
				while (left < right && nums[right] == nums[right + 1]) {
					right--;
				}
			} else if (nums[left] + nums[right] > target) {
				right--;
			} else {
				left++;
			}
		}
		return result;
	}
}
