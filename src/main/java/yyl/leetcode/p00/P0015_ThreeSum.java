package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>三数之和</h3> <br>
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。<br>
 * 注意：答案中不可以包含重复的三元组。<br>
 * 
 * <pre>
 * 示例：
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * l
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * </pre>
 * 
 * <br>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.<br>
 * Note: The solution set must not contain duplicate triplets.<br>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],<br>
 * A solution set is: [ [-1, 0, 1], [-1, -1, 2] ]
 */
public class P0015_ThreeSum {

	public static void main(String[] args) {
		Solution solution = new Solution();

		int[] nums = { -1, 0, 1, 2, -1, -4 };
		List<List<Integer>> result = solution.threeSum(nums);
		for (List<Integer> list : result) {
			System.out.println(list);
		}
	}

	// 时间复杂度：O(N2)，其中N 是数组的长度。
	// 空间复杂度：O(log⁡N)
	static class Solution {

		public List<List<Integer>> threeSum(int[] nums) {

			List<List<Integer>> result = new ArrayList<>();

			// 不够三个数
			if (nums.length < 3) {
				return result;
			}

			// 排序
			Arrays.sort(nums);

			for (int first = 0; first < nums.length - 2; first++) {

				// 因为三个数之和为0，最小的数一定小于等于0
				if (nums[first] > 0) {
					break;
				}

				// 去重: 需要和上一次的数不相同
				if (first != 0 && nums[first] == nums[first - 1]) {
					continue;
				}
				int third = nums.length - 1;
				int target = -nums[first];
				T: for (int second = first + 1; second < nums.length - 1; ++second) {
					// 去重: 需要和上一次的数不相同
					if (second > first + 1 && nums[second] == nums[second - 1]) {
						continue T;
					}
					// 需要保证指针second在的指针 third的左侧，并且他们的和大于目标值target
					while (second < third && nums[second] + nums[third] > target) {
						--third;
					}
					// 指针重合
					if (second == third) {
						break T;
					}

					// 找到结果
					if (nums[second] + nums[third] == target) {
						result.add(Arrays.asList(nums[first], nums[second], nums[third]));
					}
				}
			}
			return result;
		}
	}
}
