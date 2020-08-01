package yyl.leetcode.p06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import yyl.leetcode.util.Assert;

/**
 * <h3>最小区间</h3><br>
 * 你有 k 个升序排列的整数数组。找到一个最小区间，使得 k 个列表中的每个列表至少有一个数包含在其中。<br>
 * 我们定义如果 b-a < d-c 或者在 b-a == d-c 时 a < c，则区间 [a,b] 比 [c,d] 小。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * 输出: [20,24]
 * 解释: 
 * 列表 1：[4, 10, 15, 24, 26]，24 在区间 [20,24] 中。
 * 列表 2：[0, 9, 12, 20]，20 在区间 [20,24] 中。
 * 列表 3：[5, 18, 22, 30]，22 在区间 [20,24] 中。
 * </pre>
 * 
 * 注意:<br>
 * 给定的列表可能包含重复元素，所以在这里升序表示 >= 。<br>
 * 1 <= k <= 3500<br>
 * -10^5 <= 元素的值 <= 10^5
 */
public class P0632_SmallestRangeCoveringElementsFromKLists {

	public static void main(String[] args) {
		Solution solution = new Solution();
		List<List<Integer>> nums = new ArrayList<>();
		nums.add(Arrays.asList(4, 10, 15, 24, 26));
		nums.add(Arrays.asList(0, 9, 12, 20));
		nums.add(Arrays.asList(5, 18, 22, 30));
		int[] actual = solution.smallestRange(nums);
		int[] expected = { 20, 24 };
		Assert.assertEquals(expected, actual);
	}

	// 最小堆+活动窗口
	// 给定 k个列表，需要找到最小区间，使得每个列表都至少有一个数在该区间中。该问题可以转化为，从 k个列表中各取一个数，使得这 k个数中的最大值与最小值的差最小。
	// ├ 假设这 k 个数中的最小值是第 i个列表中的 x
	// │ ├ 对于任意 j!=i ，设第 j个列表中被选为的数是 y，则为了找到最小区间，y应该取第 j个列表中大于等于 x的最小的数。
	// │ └简单证明如下：假设 z也是第 j个列表中的数，且 z>y，则有 z−x>y−x，同时包含 x和 z 的区间一定不会小于同时包含 x和 y的区间。因此，其余 k−1 个列表中应该取大于等于 x的最小的数。
	// ├ 由于列表都是升序排列的，因此对每个列表维护一个指针，通过指针得到列表中的元素，指针右移之后指向的元素一定大于或等于之前的元素。
	// ├ 使用最小堆维护 k个指针指向的元素中的最小值，同时维护堆中元素的最大值。
	// │ ├ 初始时，k 个指针都指向下标 0，最大元素即为所有列表的下标 0位置的元素中的最大值。
	// │ └ 每次从堆中取出最小值，根据最大值和最小值计算当前区间，如果当前区间小于最小区间则用当前区间更新最小区间，然后将对应列表的指针右移，将新元素加入堆中，并更新堆中元素的最大值。
	// └ 如果一个列表的指针超出该列表的下标范围，则说明该列表中的所有元素都被遍历过，堆中不会再有该列表中的元素，因此退出循环。
	// 时间复杂度：O(nk*log{k})，其中 n 是所有列表的平均长度，k是列表数量。所有的指针移动的总次数最多是 n*k 次，每次从堆中取出元素和添加元素都需要更新堆，时间复杂度是 O(log⁡{k})，因此总时间复杂度是 O(nklog{⁡k})
	// 空间复杂度：O(k)，其中 k是列表数量。空间复杂度取决于堆的大小，堆中维护 k个元素。
	static class Solution {
		public int[] smallestRange(List<List<Integer>> nums) {
			int[] answer = { -100000, 100000 };// -10^5 <= 元素的值 <= 10^5
			int minRange = answer[1] - answer[0];
			int max = Integer.MIN_VALUE;
			int size = nums.size();

			// k个指针，表示每个列表最小位置的索引
			int[] next = new int[size];

			// 最小堆(表示小元素的列表索引号排到前面)
			PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((index1, index2) -> {
				return nums.get(index1).get(next[index1]) - nums.get(index2).get(next[index2]);
			});

			// 初始化最小堆 | 初始化最大数
			// 最小堆表示的数 和 最大列表合的区间一定符合条件（至少有一个数包含在其中）
			for (int i = 0; i < size; i++) {
				priorityQueue.offer(i);
				max = Math.max(max, nums.get(i).get(0));
			}

			while (true) {
				// 获得最小元素的列表索引
				int minIndex = priorityQueue.poll();

				// 计算范围（如果当前的范围比之前记录的最小范围小那么更新）
				int min = nums.get(minIndex).get(next[minIndex]);
				int currentRange = max - min;
				if (currentRange < minRange) {
					minRange = currentRange;
					answer[0] = min;
					answer[1] = max;
				}

				// 指针指向下一个元素
				next[minIndex]++;

				// 已经到列表结尾了
				if (next[minIndex] == nums.get(minIndex).size()) {
					break;
				}

				// 将这个列表再插入回去（会触发堆的排序，next[minIndex]已经发生变化了，排序会和之前不同）
				priorityQueue.offer(minIndex);

				// 更新max
				max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
			}
			return answer;
		}
	}
}
