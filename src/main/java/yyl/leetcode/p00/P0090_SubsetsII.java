package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>子集 II</h3><br>
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。<br>
 * 说明：解集不能包含重复的子集。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 * </pre>
 */
public class P0090_SubsetsII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 2};
        System.out.println(solution.subsetsWithDup(nums));
    }

    // 回溯法
    // 这道题目的重点是去重：
    // 首先排序，回溯时候，如果发现当前数字和上一个数字相同就跳过，避免重复。
    // 时间复杂度：O(2^N+log(N)，幂集的个数+排序时间
    // 空间复杂度：O(2^N)，幂集的个数
    static class Solution {
        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Arrays.sort(nums); // 排序
            Integer[] buffer = new Integer[nums.length];
            backtrack(nums, 0, buffer, 0, result);
            return result;
        }

        private void backtrack(int[] nums, int index, Integer[] buffer, int length, List<List<Integer>> result) {
            Integer[] elements = new Integer[length];
            System.arraycopy(buffer, 0, elements, 0, length);
            result.add(Arrays.asList(elements));
            for (int i = index; i < nums.length; i++) {
                // 和上个数字相等就跳过
                if (i > index && nums[i] == nums[i - 1]) {
                    continue;
                }
                buffer[length] = nums[i];
                backtrack(nums, i + 1, buffer, length + 1, result);
            }
        }
    }

}
