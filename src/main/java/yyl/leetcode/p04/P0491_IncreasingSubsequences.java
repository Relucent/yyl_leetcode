package yyl.leetcode.p04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import yyl.leetcode.util.Assert;

/**
 * <h3>递增子序列</h3><br>
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。 <br>
 * 
 * <pre>
 * 示例:
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * </pre>
 * 
 * 说明:<br>
 * 1、给定数组的长度不会超过15。<br>
 * 2、数组中的整数范围是 [-100,100]。<br>
 * 3、给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。<br>
 */
public class P0491_IncreasingSubsequences {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> actual = solution.findSubsequences(new int[] { 4, 6, 7, 7 });
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(4, 6));
        expected.add(Arrays.asList(4, 7));
        expected.add(Arrays.asList(4, 6, 7));
        expected.add(Arrays.asList(4, 6, 7, 7));
        expected.add(Arrays.asList(6, 7));
        expected.add(Arrays.asList(6, 7, 7));
        expected.add(Arrays.asList(7, 7));
        expected.add(Arrays.asList(4, 7, 7));
        Assert.assertEquals(new HashSet<>(expected), new HashSet<>(actual));
    }

    // 前缀序列迭代（暴力法）
    // 1. 加入当前数字会产生新序列（只要遍历当前存在的序列并比较最后一个数字和当前数字，如果当前数字更大则加入产生新的序列）
    // 2. 由 1 产生的新序列加上已经存在的序列以及当前数字作为一个新序列起点的情况作为新的结果
    // 3. 需要对序列去重
    // 4. 最终结果需要过滤掉长度不足 2 的序列
    // 时间复杂度：O((2^n)^2)。这里枚举所有子序列的时间代价是 O(2^n)，去重的时间复杂度是 O((2^n)^2)，故渐近时间复杂度为O((2^n)^2)。
    // 空间复杂度：O(2^n)。最坏情况下整个序列都是递增的，这里哈希表中要加入 2^n个元素，空间代价为 O(2^n)。
    static class Solution {
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> answer = new ArrayList<>();
            List<List<Integer>> prefixList = new ArrayList<>();
            Set<List<Integer>> prefixSet = new HashSet<>();
            // 遍历数组
            for (int i = 0; i < nums.length; i++) {
                int size = prefixList.size();
                for (int j = 0; j < size; j++) {
                    List<Integer> prefix = prefixList.get(j);
                    // 加入当前数字，产生新序列
                    if (nums[i] >= prefix.get(prefix.size() - 1)) {
                        List<Integer> newList = new ArrayList<>(prefix);
                        newList.add(nums[i]);
                        // 使用Set去重
                        if (prefixSet.add(newList)) {
                            prefixList.add(newList);
                            // 序列加入数字，那么长度一定大于1
                            answer.add(newList);
                        }
                    }
                }
                prefixList.add(Arrays.asList(nums[i]));
            }
            return answer;
        }
    }

    // DFS解法 (递归回溯+剪枝)
    // 时间复杂度：O(2^n*n)。需要对子序列做枚举（两个分支的枚举：选择当前或者不选当前）复杂度为O(n^2)，需要 O(n)的时间添加到答案中。
    // 空间复杂度：O(n)。不考虑返回结果、临时数组的空间代价是 O(n)，递归使用的栈空间的空间代价也是 O(n)。
    static class Solution1 {
        public List<List<Integer>> findSubsequences(int[] nums) {
            List<List<Integer>> answer = new ArrayList<>();
            dfs(nums, 0, Integer.MIN_VALUE, new ArrayList<>(), answer);
            return new ArrayList<>(answer);
        }

        private void dfs(int[] nums, int index, int last, List<Integer> temp, List<List<Integer>> answer) {
            // 迭代到末尾了
            if (index == nums.length) {
                // 符合条件
                if (temp.size() >= 2) {
                    answer.add(new ArrayList<>(temp));
                }
                return;
            }
            // 如果选择当前元素
            if (nums[index] >= last) {
                temp.add(nums[index]);
                dfs(nums, index + 1, nums[index], temp, answer);
                temp.remove(temp.size() - 1);
            }
            // 如果不选择当前元素(当前的元素不等于上一个选择的元素的时候，才考虑不选择当前元素，直接递归后面的元素。因为如果有两个相同的元素，那么会出现重复)
            if (nums[index] != last) {
                dfs(nums, index + 1, last, temp, answer);
            }
        }
    }
}
