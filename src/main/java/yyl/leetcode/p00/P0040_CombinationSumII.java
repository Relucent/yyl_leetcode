package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h3>组合总和II</h3><br>
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。<br>
 * candidates 中的每个数字在每个组合中只能使用一次。<br>
 * 说明：<br>
 * 所有数字（包括目标数）都是正整数。<br>
 * 解集不能包含重复的组合。 <br>
 * 示例 1:<br>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,<br>
 * 所求解集为:<br>
 * [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]<br>
 * 示例 2:<br>
 * 输入: candidates = [2,5,2,1,2], target = 5,<br>
 * 所求解集为:<br>
 * [ [1,2,2], [5] ]<br>
 */
public class P0040_CombinationSumII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8));// [ [1, 7], [1, 2, 5], [2, 6], [1, 1, 6] ]
        System.out.println(solution.combinationSum2(new int[] {2, 5, 2, 1, 2}, 5));// [ [1,2,2], [5] ]
    }

    static class Solution {

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            Arrays.sort(candidates);// 排序
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
                // #数字不能被重复被选取,所以传入的 offset=i+1
                combinationSum(candidates, target - value, i + 1, result, queue);
                queue.remove(queue.size() - 1);

                // 去重：已经遍历的节点不再遍历
                while (i < candidates.length - 1 && candidates[i + 1] == value) {
                    i++;
                }
            }
        }
    }
}
