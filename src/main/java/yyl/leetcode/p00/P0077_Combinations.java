package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>组合</h3><br>
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。<br>
 * 
 * <pre>
 * 示例：
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * </pre>
 */
public class P0077_Combinations {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combine(4, 2));
    }

    // 回溯法
    // 时间复杂度：O(n!/((n-k)!*k!))，组合的个数
    // 空间复杂度：O(n!/((n-k)!*k!))，组合的个数
    static class Solution {
        public List<List<Integer>> combine(int n, int k) {
            List<List<Integer>> result = new ArrayList<>();
            if (n < k) {
                return result;
            }
            Integer[] buffer = new Integer[k];
            backtrack(result, 0, 1, n - k + 1, buffer);
            return result;
        }

        private void backtrack(List<List<Integer>> result, int index, int min, int max, Integer[] buffer) {
            if (index == buffer.length) {
                List<Integer> group = new ArrayList<>();
                for (Integer i : buffer) {
                    group.add(i);
                }
                result.add(group);
                return;
            }
            for (int i = min; i <= max; i++) {
                buffer[index] = i;
                backtrack(result, index + 1, i + 1, max + 1, buffer);
            }
        }
    }
}
