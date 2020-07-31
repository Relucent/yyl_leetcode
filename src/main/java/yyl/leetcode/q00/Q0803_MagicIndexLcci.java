package yyl.leetcode.q00;

import yyl.leetcode.util.Assert;

/**
 * <h3>面试题 08.03. 魔术索引</h3><br>
 * 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。<br>
 * 给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。<br>
 * 
 * <pre>
 * 示例1:
 * 输入：nums = [0, 2, 3, 4, 5]
 * 输出：0
 * 说明: 0下标的元素为0
 * 
 * 示例2:
 * 输入：nums = [1, 1, 1]
 * 输出：1
 * 提示:
 * nums长度在[1, 1000000]之间
 * </pre>
 */
public class Q0803_MagicIndexLcci {

	public static void main(String[] args) {
		Solution solution = new Solution();
		Assert.assertEquals(0, solution.findMagicIndex(new int[] { 0, 2, 3, 4, 5 }));
		Assert.assertEquals(1, solution.findMagicIndex(new int[] { 1, 1, 1 }));
	}

	// 迭代
	// 从头遍历到尾，如果 nums[i]==i，返回i；如果遍历到末尾，返回-1
	// 时间复杂度：O(N)
	// 空间复杂度：O(1)
	static class Solution {
		public int findMagicIndex(int[] nums) {
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == i) {
					return i;
				}
			}
			return -1;
		}
	}

	// 跳跃
	// 如果第一个魔术索引是i， 那么 ：mums[i-1] < i-1；
	// 于是可以根据位置元素的特点对下标进行切换
	// 时间复杂度：O(N)，虽然最差时间复杂度和迭代相同，但是一般情况可以节省中间比较。所以实际比迭代要快一些。
	// 空间复杂度：O(1)
	static class Solution1 {
		public int findMagicIndex(int[] nums) {
			for (int i = 0; i < nums.length;) {
				if (nums[i] == i) {
					return i;
				}
				if (nums[i] > i) {
					i = nums[i];
				}
				// nums[i] < i
				else {
					i++;
				}
			}
			return -1;
		}
	}
}
