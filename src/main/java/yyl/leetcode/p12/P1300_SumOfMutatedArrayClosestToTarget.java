package yyl.leetcode.p12;

import java.util.Arrays;

/**
 * <h3>转变数组后最接近目标值的数组和</h3><br>
 * 给你一个整数数组 arr 和一个目标值 target ，请你返回一个整数 value ，使得将数组中所有大于 value 的值变成 value 后，数组的和最接近 target （最接近表示两者之差的绝对值最小）。<br>
 * 如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值。<br>
 * 请注意，答案不一定是 arr 中的数字。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：arr = [4,9,3], target = 10
 * 输出：3
 * 解释：当选择 value 为 3 时，数组会变成 [3, 3, 3]，和为 9 ，这是最接近 target 的方案。
 * 
 * 示例 2：
 * 输入：arr = [2,3,5], target = 10
 * 输出：5
 * 
 * 示例 3：
 * 输入：arr = [60864,25176,27249,21296,20204], target = 56803
 * 输出：11361
 * 
 * 提示：
 *     1 <= arr.length <= 10^4
 *     1 <= arr[i], target <= 10^5
 * </pre>
 */
public class P1300_SumOfMutatedArrayClosestToTarget {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.findBestValue(new int[] { 4, 9, 3 }, 10));// 3
		System.out.println(solution.findBestValue(new int[] { 2, 3, 5 }, 10));// 5
		System.out.println(solution.findBestValue(new int[] { 2, 3, 5 }, 11));// 5
		System.out.println(solution.findBestValue(new int[] { 60864, 25176, 27249, 21296, 20204 }, 56803));// 11361
		System.out.println(solution.findBestValue(new int[] { 1547, 83230, 57084, 93444, 70879 }, 71237));// 17422
	}

	// 双重二分查找
	// 时间复杂度：O(Nlog⁡N)，其中 N是数组 arr 的长度。排序需要的时间复杂度为 O(Nlog⁡N)，二分查找的时间复杂度为 O(log⁡C)，内层二分查找的时间复杂度为 O(log⁡N)O(\log N)O(logN)，它们的乘积在数量级上小于 O(Nlog⁡N)。
	// 空间复杂度：O(N)
	static class Solution {
		public int findBestValue(int[] arr, int target) {
			Arrays.sort(arr);

			int n = arr.length;

			int[] prefixSums = new int[n + 1];
			for (int i = 1; i <= n; ++i) {
				prefixSums[i] = prefixSums[i - 1] + arr[i - 1];
			}

			int left = 0;
			int right = arr[n - 1];
			int value = -1;

			while (left <= right) {
				int mid = (left + right) / 2;
				int index = Arrays.binarySearch(arr, mid);
				if (index < 0) {
					index = -index - 1;
				}
				int cur = prefixSums[index] + (n - index) * mid;
				if (cur <= target) {
					value = mid;
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			int chooseSmall = check(arr, value);
			int chooseBig = check(arr, value + 1);
			return Math.abs(chooseSmall - target) <= Math.abs(chooseBig - target) ? value : value + 1;
		}

		private int check(int[] arr, int x) {
			int sum = 0;
			for (int num : arr) {
				sum += Math.min(num, x);
			}
			return sum;
		}
	}

	// 二分法
	// 时间复杂度：O(Nlog⁡N)，其中 N是数组 arr 的长度。排序需要的时间复杂度为 O(Nlog⁡N)，二分查找的时间复杂度为 O(log⁡C)，内层求和的时间复杂度为 O(N)。
	// 空间复杂度：O(N)
	static class Solution2 {
		public int findBestValue(int[] arr, int target) {
			Arrays.sort(arr);
			int left = 0;
			int right = arr[arr.length - 1];
			while (left < right) {
				int mid = (left + right + 1) / 2;
				if (check(arr, mid) <= target) {
					left = mid;
				} else {
					right = mid - 1;
				}
			}
			if (Math.abs(target - check(arr, left)) <= Math.abs(target - check(arr, left + 1))) {
				return left;
			}
			return left + 1;
		}

		private int check(int[] arr, int x) {
			int sum = 0;
			for (int num : arr) {
				sum += Math.min(num, x);
			}
			return sum;
		}
	}
}
