package yyl.algorithms.v016;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution. <br>
 * For example, given array S = {-1 2 1 -4}, and target = 1. <br>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).<br>
 */
//给定一个数组的 n个整数，在其中找到3个整数，返回三个整数相加。(要求这三个整数相加与目标最接近)
public class ThreeSumClosest {
	public static void main(String[] args) {
		for (TestSample sample : TestSample.values()) {
			int result = threeSumClosest2(sample.nums(), sample.target());
			System.out.println("answer:" + result + "; expected:" + sample.expected());
		}
	}

	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);

		int len = nums.length;

		int min = nums[0] + nums[1] + nums[2];
		if (min >= target) {
			return min;
		}

		int max = nums[len - 3] + nums[len - 2] + nums[len - 1];
		if (max <= target) {
			return max;
		}

		for (int i = 0; i < len - 2; i++) {
			int minT = nums[i] + nums[i + 1] + nums[i + 2];
			if (minT > target) {
				if (minT < max) {
					max = minT;
				}
				break;
			}

			int maxT = nums[i] + nums[len - 2] + nums[len - 1];
			if (maxT < target) {
				if (maxT > min) {
					min = maxT;
				}
				continue;
			}

			int left = i + 1;
			int right = len - 1;
			while (left < right) {
				int sum = nums[left] + nums[right] + nums[i];
				if (sum == target) {
					return target;
				} else if (sum < target) {
					if (min < sum) {
						min = sum;
					}
					left++;
				} else {
					if (max > sum) {
						max = sum;
					}
					right--;
				}
			}
		}

		if (max == target || min == target) {
			return target;
		}
		return (max - target) > (target - min) ? min : max;
	}

	public static int threeSumClosest2(int[] nums, int target) {
		Arrays.sort(nums);
		int closest = 0;
		int minDifference = Integer.MAX_VALUE;
		for (int i = 0, I = nums.length - 2, R = nums.length - 1; i < I; i++) {
			int left = i + 1;
			int right = R;
			while (left < right) {
				int sum = nums[i] + nums[left] + nums[right];
				if (sum == target) {
					return sum;
				}
				if (sum > target) {
					int difference = sum - target;
					if (difference < minDifference) {
						minDifference = difference;
						closest = sum;
					}
					right--;
				} else {
					int difference = target - sum;
					if (difference < minDifference) {
						minDifference = difference;
						closest = sum;
					}
					left++;
				}
			}
		}
		return closest;
	}
}
