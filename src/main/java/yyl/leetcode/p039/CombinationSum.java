package yyl.leetcode.p039;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>组合总和</h3><br>
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。<br>
 * candidates 中的数字可以无限制重复被选取。<br>
 * 说明：<br>
 * 所有数字（包括 target）都是正整数。<br>
 * 解集不能包含重复的组合。 <br>
 * 示例 1:<br>
 * 输入: candidates = [2,3,6,7], target = 7,<br>
 * 所求解集为:<br>
 * [ [7], [2,2,3] ]<br>
 * 示例 2:<br>
 * 输入: candidates = [2,3,5], target = 8,<br>
 * 所求解集为:<br>
 * [ [2,2,2,2], [2,3,3], [3,5] ]
 */
public class CombinationSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinationSum(new int[] {2, 3, 6, 7}, 7));// [2,3,5]
        System.out.println(solution.combinationSum(new int[] {2, 3, 5}, 8));// [ [2,2,2,2], [2,3,3], [3,5] ]
    }

    static class Solution {

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            Arrays.sort(candidates);// 预先做排序
            combinationSum(candidates, target, 0, result, new ArrayList<Integer>());
            return result;
        }

        // 递归
        private void combinationSum(final int[] candidates, final int target, final int offset, final List<List<Integer>> result,
                final List<Integer> queue) {
            // 不符合的直接返回(这个判断理论上不会走到)
            if (target < 0) {
                return;
            }
            // 匹配到最后一个结果
            if (target == 0) {
                result.add(new ArrayList<>(queue));
                return;
            }
            for (int i = offset; i < candidates.length; i++) {
                int value = candidates[i];
                // 因为已经做排序了，如果value > target 后面不需要继续了
                if (value > target) {
                    break;
                }
                queue.add(value);
                // 数字可以无限制重复被选取,所以传入的 offset 是i而不是i+1
                combinationSum(candidates, target - value, i, result, queue);
                queue.remove(queue.size() - 1);
            }
        }
    }
}
