package yyl.leetcode.p02;

import java.util.Arrays;
import java.util.HashSet;

/**
 * <h3>存在重复元素</h3> <br>
 * 给定一个整数数组，判断是否存在重复元素。<br>
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。<br>
 * 示例 1:<br>
 * 输入: [1,2,3,1]<br>
 * 输出: true<br>
 * 示例 2:<br>
 * 输入: [1,2,3,4]<br>
 * 输出: false<br>
 * 示例 3:<br>
 * 输入: [1,1,1,3,3,4,3,2,4,2]<br>
 * 输出: true<br>
 */
public class P0217_ContainsDuplicate {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.containsDuplicate(new int[] { 1, 2, 3, 1 }));// true
		System.out.println(solution.containsDuplicate(new int[] { 1, 2, 3, 4 }));// false
		System.out.println(solution.containsDuplicate(new int[] { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 }));// true
	}

	// 哈希表
	// 利用支持快速搜索和插入操作的动态数据结构。
	// 时间复杂度 : O(n)，各自使用 n 次，每个操作耗费常数时间。
	// 空间复杂度 : O(n)，哈希表占用的空间与元素数量是线性关系。
	static class Solution {
		public boolean containsDuplicate(int[] nums) {
			HashSet<Integer> numSet = new HashSet<>();
			for (int i = 0; i < nums.length; i++) {
				numSet.add(nums[i]);
			}
			return numSet.size() != nums.length;
		}
	}

	// 排序
	// 先排序，再比较
	// 时间复杂度 : O(n*log{n})，排序的复杂度是 O(n*log{n})，扫描的复杂度是 O(n)
	// 空间复杂度 : O(1)，取决于具体的排序算法实现（原数组的排序会修改原数组元素顺序）
	static class Solution2 {
		public boolean containsDuplicate(int[] nums) {
			Arrays.sort(nums);
			for (int i = 0; i < nums.length - 1; i++) {
				if (nums[i] == nums[i + 1]) {
					return true;
				}
			}
			return false;
		}
	}
}
