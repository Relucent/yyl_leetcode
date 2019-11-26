package yyl.leetcode.p047;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>全排列 II</h3><br>
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。<br>
 * 示例:<br>
 * 输入: [1,1,3]<br>
 * 输出:<br>
 * 
 * <pre>
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * </pre>
 */
public class Permute {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // System.out.println(solution.permute(new int[] {1, 1, 2}));// [[1, 2, 1], [1, 1, 2], [2, 1, 1]]
        System.out.println(solution.permute(new int[] {2, 2, 1, 1}));
    }

    static class Solution {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            permute(nums, 0, result);
            return result;
        }

        private void permute(int[] nums, int level, List<List<Integer>> result) {
            if (level == nums.length - 1) {
                List<Integer> item = new ArrayList<>();
                for (int num : nums) {
                    item.add(num);
                }
                result.add(item);
            } else {
                for (int i = level; i < nums.length; i++) {

                    // 去重
                    if (isRepeat(nums, level, i)) {
                        continue;
                    }

                    swap(nums, level, i);
                    permute(nums, level + 1, result);
                    swap(nums, level, i);
                }
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        // 先判断是否交换过
        private boolean isRepeat(int[] nums, int i, int j) {
            for (; i < j; i++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
            return false;
        }
    }
}
