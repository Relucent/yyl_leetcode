package yyl.leetcode.p001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>两数之和</h3><br>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。<br>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。<br>
 * 示例:<br>
 * 给定 nums = [2, 7, 11, 15], target = 9<br>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9<br>
 * 所以返回 [0, 1]<br>
 * <br>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.<br>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.<br>
 */
public class TwoSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = solution.twoSum(nums, target);
        System.out.println(Arrays.toString(result));
    }

    // Time complexity : O(n^2)
    // Space complexity : O(1)
    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] + nums[i] == target) {
                        return new int[] {i, j};
                    }
                }
            }
            return null;
        }
    }

    // Time complexity : O(n)
    // Space complexity : O(n)
    static class Solution2 {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[] {map.get(complement), i};
                }
                map.put(nums[i], i);
            }
            return null;
        }
    }
}
