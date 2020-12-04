package yyl.leetcode.p06;

import java.util.HashMap;
import java.util.Map;

import yyl.leetcode.util.Assert;

/**
 * <h3>分割数组为连续子序列</h3><br>
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。<br>
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 : 
 * 1, 2, 3
 * 3, 4, 5
 * 
 * 示例 2：
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 : 
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 
 * 示例 3：
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 * </pre>
 * 
 * 提示： 输入的数组长度范围为 [1, 10000]<br>
 */
public class P0659_SplitArrayIntoConsecutiveSubsequences {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.isPossible(new int[] { 1, 2, 3, 3, 4, 5 }));
        Assert.assertTrue(solution.isPossible(new int[] { 1, 2, 3, 3, 4, 4, 5, 5 }));
        Assert.assertFalse(solution.isPossible(new int[] { 1, 2, 3, 4, 4, 5 }));
    }

    // 贪心算法
    // 思路
    // ├ 对于数组中的元素 x ，如果存在一个子序列以 x−1 结尾，则可以将 x 加入该子序列中。
    // ├ 将 x 加入已有的子序列总是比新建一个只包含 x 的子序列更优，因为前者可以将一个已有的子序列的长度增加 1，而后者新建一个长度为 1 的子序列，而题目要求分割成的子序列的长度都不小于 3，因此应该尽量避免新建短的子序列。
    // └ 基于此，可以通过贪心的方法判断是否可以完成分割。
    // 算法
    // ├ 使用两个哈希表，第一个哈希表存储数组中的每个数字的剩余次数，第二个哈希表存储数组中的每个数字作为结尾的子序列的数量。
    // ├ 在初始化第一个哈希表之后，遍历数组，更新两个哈希表。只有当一个数字的剩余次数大于 000 时，才需要考虑这个数字是否属于某个子序列。
    // ├ 假设当前元素是 x ，进行如下操作：
    // │ ├ 首先判断是否存在以 x−1 结尾的子序列，即根据第二个哈希表判断 x−1 作为结尾的子序列的数量是否大于 0，如果大于 0，则将元素 x 加入该子序列中。由于 x 被使用了一次，因此需要在第一个哈希表中将 x 的剩余次数减 1。又由于该子序列的最后一个数字从 x−1 变成了 x ，因此需要在第二个哈希表中将 x−1 作为结尾的子序列的数量减 1，以及将 x 作为结尾的子序列的数量加 1。
    // │ ├ 否则，x 为一个子序列的第一个数，为了得到长度至少为 3 的子序列，x+1 和 x+2 必须在子序列中， 因此需要判断在第一个哈希表中 x+1 和 x+2 的剩余次数是否都大于 0。
    // │ │ └ 由于这三个数都被使用了一次，因此需要在第一个哈希表中将这三个数的剩余次数分别减 1。又由于该子序列的最后一个数字是 x+2，因此需要在第二个哈希表中将 x+2 作为结尾的子序列的数量加1。
    // │ └ 否则，无法得到长度为 3 ，因此无法完成分割，返回 false
    // └ 如果整个数组遍历结束时，没有遇到无法完成分割的情况，则可以完成分割，返回 true。
    // 时间复杂度：O(n)，其中 n 是数组的长度。需要遍历数组两次，对于数组中的每个元素，更新哈希表的时间复杂度是常数。
    // 空间复杂度：O(n)。需要使用两个哈希表，每个哈希表的大小都不会超过 n。
    static class Solution {
        public boolean isPossible(int[] nums) {
            Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
            Map<Integer, Integer> endMap = new HashMap<Integer, Integer>();
            for (int x : nums) {
                countMap.compute(x, (k, v) -> (v == null) ? 1 : v + 1);
            }
            for (int x : nums) {
                int count = countMap.get(x);
                if (count == 0) {
                    continue;
                }
                int prevEndCount = endMap.getOrDefault(x - 1, 0);
                if (prevEndCount > 0) {
                    countMap.put(x, count - 1);
                    endMap.put(x - 1, prevEndCount - 1);
                    endMap.compute(x, (k, v) -> (v == null) ? 1 : v + 1);
                    continue;
                }
                int count1 = countMap.getOrDefault(x + 1, 0);
                int count2 = countMap.getOrDefault(x + 2, 0);
                if (count1 > 0 && count2 > 0) {
                    countMap.put(x, count - 1);
                    countMap.put(x + 1, count1 - 1);
                    countMap.put(x + 2, count2 - 1);
                    endMap.compute(x + 2, (k, v) -> (v == null) ? 1 : v + 1);
                    continue;
                }
                return false;
            }
            return true;
        }
    }
}
