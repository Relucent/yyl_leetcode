package yyl.leetcode.p02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>组合总和 III</h3><br>
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。<br>
 * 说明：<br>
 * 1. 所有数字都是正整数。<br>
 * 2. 解集不能包含重复的组合。 <br>
 * 
 * <pre>
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 * </pre>
 */
public class P0216_CombinationSumIII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(Arrays.asList(Arrays.asList(1, 2, 4)), solution.combinationSum3(3, 7));
        Assert.assertEquals(Arrays.asList(Arrays.asList(1, 2, 6), Arrays.asList(1, 3, 5), Arrays.asList(2, 3, 4)), solution.combinationSum3(3, 9));
    }

    // 回溯法
    // 使用 深度优先遍历，搜索 所有 可能的解
    // 时间复杂度：O(C(M,k)×k)，其中 M为集合的大小，固定为 9 。一共有 C(M,k)个组合，每次判断需要的时间代价是 O(k)。
    // 空间复杂度：O(M)。temp数组的空间代价是 O(k)，递归栈空间的代价是 O(M)，故空间复杂度为 O(M+k)=O(M)。
    static class Solution {

        public List<List<Integer>> combinationSum3(int k, int n) {
            List<Integer> temp = new ArrayList<>();
            List<List<Integer>> answer = new ArrayList<>();
            dfs(k, n, 1, temp, answer);
            return answer;
        }

        /**
         * @param k 剩下要找 k个数
         * @param n 剩余多少
         * @param start 下一轮搜索的起始元素是多少
         * @param path 深度优先遍历的路径参数
         * @param answer 保存结果集的列表
         */
        public void dfs(int k, int n, int start, List<Integer> path, List<List<Integer>> answer) {
            if (n < 0) {
                return;
            }
            if (k == 0) {
                if (n == 0) {
                    answer.add(new ArrayList<>(path));
                    return;
                }
                return;
            }
            for (int i = start; i <= 9; i++) {
                path.add(i);
                dfs(k - 1, n - i, i + 1, path, answer);
                path.remove(path.size() - 1);
            }
        }
    }
}
