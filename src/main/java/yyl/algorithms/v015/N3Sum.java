package yyl.algorithms.v015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in
 * the array which gives the sum of zero.<br>
 * Note: The solution set must not contain duplicate triplets.<br>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],<br>
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
// 给定一个数组的 n个整数，在其中找到了和为零元素个数为3的的子集合。
// 解集不能包含重复数组。
public class N3Sum {

	public static void main(String[] args) {
		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> result = threeSum(nums);
		for (List<Integer> list : result) {
			System.out.println(list);
		}
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums.length < 3) {
			return result;
		}
		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			if (i != 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int target = -nums[i];
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = nums[left] + nums[right];
				if (sum == target) {
					result.add(Arrays.asList(-target, nums[left], nums[right]));
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					while (left < right && nums[right] == nums[right - 1]) {
						right--;
					}
					left++;
					right--;
				} else if (sum > target) {
					right--;
				} else { // sum < target
					left++;
				}
			}
		}
		return result;
	}
}
