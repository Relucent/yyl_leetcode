package yyl.leetcode.p14;

import yyl.leetcode.util.Assert;

/**
 * <h3>重新排列数组</h3><br>
 * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。<br>
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：nums = [2,5,1,3,4,7], n = 3
 * 输出：[2,3,5,4,1,7] 
 * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
 * 
 * 示例 2：
 * 输入：nums = [1,2,3,4,4,3,2,1], n = 4
 * 输出：[1,4,2,3,3,2,4,1]
 * 
 * 示例 3：
 * 输入：nums = [1,1,2,2], n = 2
 * 输出：[1,2,1,2]
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 1 <= n <= 500 <br>
 * ├ 1 nums.length == 2n <br>
 * └ 1 1 <= nums[i] <= 10^3 <br>
 */
public class P1470_ShuffleTheArray {

	public static void main(String[] args) {
		Solution solution = new Solution();
		Assert.assertEquals(new int[] { 2, 3, 5, 4, 1, 7 }, solution.shuffle(new int[] { 2, 5, 1, 3, 4, 7 }, 3));
		Assert.assertEquals(new int[] { 1, 4, 2, 3, 3, 2, 4, 1 }, solution.shuffle(new int[] { 1, 2, 3, 4, 4, 3, 2, 1 }, 4));
		Assert.assertEquals(new int[] { 1, 2, 1, 2 }, solution.shuffle(new int[] { 1, 1, 2, 2 }, 2));
	}

	// 一次遍历
	// 用 answer 表示结果数组，数组 nums 和answer 的长度都是 2n。对于 0≤i<n，重新排列数组的规则如下：
	// ├ nums[i] 填到 answer[2×i]；
	// └ nums[i+n] 填到 answer[2×i+1]。
	// 根据该规则将原数组 nums 中的元素依次填入结果数组 answer 中，即可得到重新排列后的数组。
	// 复杂度分析
	// 时间复杂度：O(n)，其中 n 是给定的参数。需要遍历长度为 2n 的数组 nums 一次将数组重新排列，每个元素重新排列的时间是 O(1)。
	// 空间复杂度：O(1)，返回值不计入空间复杂度。
	static class Solution {
		public int[] shuffle(int[] nums, int n) {
			int[] answer = new int[n * 2];
			for (int i = 0; i < n; i++) {
				answer[i * 2] = nums[i];
				answer[i * 2 + 1] = nums[n + i];
			}
			return answer;
		}
	}
}
