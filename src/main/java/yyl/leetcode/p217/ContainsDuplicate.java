package yyl.leetcode.p217;

import java.util.HashSet;

/**
 * <h3>给定一个整数数组，判断是否存在重复元素。</h3> <br>
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
public class ContainsDuplicate {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.containsDuplicate(new int[] {1, 2, 3, 1}));
        System.out.println(solution.containsDuplicate(new int[] {1, 2, 3, 4}));
        System.out.println(solution.containsDuplicate(new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }

    static class Solution {
        public boolean containsDuplicate(int[] nums) {
            HashSet<Integer> numSet = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {
                numSet.add(nums[i]);
            }
            return numSet.size() != nums.length;
        }
    }
}
