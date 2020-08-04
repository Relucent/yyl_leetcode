package yyl.leetcode.lcci;

import java.util.Arrays;

/**
 * <h3>面试题 16.11. 跳水板</h3><br>
 * 你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方法，生成跳水板所有可能的长度。<br>
 * 返回的长度需要从小到大排列。<br>
 * 
 * <pre>
 * 示例：
 * 输入：
 * shorter = 1
 * longer = 2
 * k = 3
 * 输出： {3,4,5,6}
 * 
 * 提示：
 *     0 < shorter <= longer
 *     0 <= k <= 100000
 * </pre>
 */

public class L1611_DivingBoardLcci {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(Arrays.toString(solution.divingBoard(1, 2, 3)));
		System.out.println(Arrays.toString(solution.divingBoard(1, 1, 0)));
	}

	// 数学法
	// 1、如果k=0，则不能建造任何跳水板，因此返回空数组。
	// 2、如果 shorter 和 longer 相等，则建造的跳水板的长度是唯一的，都等于 shorter*k，因此返回长度为 1的数组，数组中的元素为 shorter*k。
	// 3、其他情况，可能的长度 length = shorter * (k - i) + longer * i ; 因为不相等，所以不会出现重复情况，数组个数为 k + 1
	// 时间复杂度：O(k)，其中 k 是木板数量。短木板和长木板一共使用 k 块，一共有 k+1种组合，对于每种组合都要计算跳水板的长度。
	// 空间复杂度：O(1)。除了返回值以外，额外使用的空间复杂度为常数。
	static class Solution {
		public int[] divingBoard(int shorter, int longer, int k) {
			if (k == 0) {
				return new int[0];
			}
			if (shorter == longer) {
				return new int[] { shorter * k };
			}
			int[] lengths = new int[k + 1];
			for (int i = 0; i <= k; i++) {
				lengths[i] = shorter * (k - i) + longer * i;
			}
			return lengths;
		}
	}
}
