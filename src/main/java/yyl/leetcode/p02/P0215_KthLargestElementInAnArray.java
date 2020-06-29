package yyl.leetcode.p02;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * <h3>数组中的第K个最大元素</h3><br>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * </pre>
 * 
 * 说明: 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。<br>
 */
public class P0215_KthLargestElementInAnArray {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.findKthLargest(new int[] { 3, 2, 1, 5, 6, 4 }, 2));// 5
		System.out.println(solution.findKthLargest(new int[] { 3, 2, 3, 1, 2, 4, 5, 5, 6 }, 4));// 4
		System.out.println(solution.findKthLargest(new int[] { 1 }, 1));// 1
	}

	// 快速选择算法
	// 目标：找到第n大的数
	// 1、随机选择一个 pivot
	// 2、根据这个pivot，将小于其的数放左边，大于其的数放右边
	// 3、更新第n大数的估计值的位置，选择其中一边，直到=n
	// 4、重复2、3步骤
	// 时间复杂度：O(N)，如果不使用随机数那么理论上复杂度是O(N)~O(N^2)
	// 空间复杂度：O(1)
	static class Solution {

		private Random random = new Random();

		public int findKthLargest(int[] nums, int k) {
			return quickSelect(nums, 0, nums.length - 1, k);
		}

		// 快速查找算法
		private int quickSelect(int[] nums, int left, int right, int k) {
			// 如果对于一个已排序的数组，我们每次都选择最大/最小的值为 pivot，那么时间复杂度为 O(N^2) 。
			// 每次通过 random 选择 pivot 可以尽量避免最坏情况发生
			int pivot = random.nextInt(right - left + 1) + left;
			// 把 pivot 移动到最右的位置，以最右为标杆(nums[right])
			swap(nums, right, pivot);

			// 定义两个指针
			int i = left;
			int j = left;
			while (j < right) {
				// 如果 j 所在的指针是否小于等于 pivot的值，那么往左替换(和i位置交换)
				if (nums[j] <= nums[right]) {
					swap(nums, i, j);
					i++;
				}
				j++;
			}
			// 把标杆替换到分割位置（此处j===right）
			swap(nums, i, right);

			// pivot 是我们要找的 Top k
			if (right == k + i - 1) {
				return nums[i];
			}
			// Top k 在右边
			if (right > k + i - 1) {
				return quickSelect(nums, i + 1, right, k);
			}
			// Top k 在左边
			return quickSelect(nums, left, i - 1, k - (right - i + 1));
		}

		private void swap(int[] nums, int i, int j) {
			int temp = nums[i];
			nums[i] = nums[j];
			nums[j] = temp;
		}
	}

	// 排序法
	// 时间复杂度：O(NlogN)
	// 空间复杂度：O(1)
	static class Solution1 {
		public int findKthLargest(int[] nums, int k) {
			Arrays.sort(nums);
			return nums[nums.length - k];
		}
	}

	// 使用最小堆
	// 时间复杂度：O(NlogN)
	// 空间复杂度：O(K)
	static class Solution2 {
		public int findKthLargest(int[] nums, int k) {
			PriorityQueue<Integer> queue = new PriorityQueue<>();
			for (int num : nums) {
				queue.add(num);
				if (queue.size() > k)
					queue.poll();
			}
			return queue.peek();
		}
	}
}
