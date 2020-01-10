package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>子集</h3><br>
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。<br>
 * 说明：解集不能包含重复的子集。<br>
 * 
 * <pre>
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * </pre>
 */
public class P0078_Subsets {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3};
        System.out.println(solution.subsets(nums));
    }

    // 回溯法
    // 时间复杂度：O(2^n)，幂集的个数
    // 空间复杂度：O(2^n)，幂集的个数
    static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            Integer[] buffer = new Integer[nums.length];
            backtrack(nums, 0, buffer, 0, result);
            return result;
        }

        private void backtrack(int[] nums, int index, Integer[] buffer, int length, List<List<Integer>> result) {
            Integer[] elements = new Integer[length];
            System.arraycopy(buffer, 0, elements, 0, length);
            result.add(Arrays.asList(elements));
            for (int i = index; i < nums.length; i++) {
                buffer[length] = nums[i];
                backtrack(nums, i + 1, buffer, length + 1, result);
            }
        }
    }
}
