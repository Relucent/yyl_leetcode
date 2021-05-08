package yyl.leetcode.p17;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>完成所有工作的最短时间</h3><br>
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。<br>
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。<br>
 * 返回分配方案中尽可能 最小 的 最大工作时间 。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：jobs = [3,2,3], k = 3
 * 输出：3
 * 解释：给每位工人分配一项工作，最大工作时间是 3 。
 * 
 * 示例 2：
 * 输入：jobs = [1,2,4,7,8], k = 2
 * 输出：11
 * 解释：按下述方式分配工作：
 * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
 * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
 * 最大工作时间是 11 。
 * 
 * 提示：
 *     1 <= k <= jobs.length <= 12
 *     1 <= jobs[i] <= 107
 * </pre>
 */
public class P1723_FindMinimumTimeToFinishAllJobs {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.minimumTimeRequired(new int[] { 3, 2, 3 }, 3));
        Assert.assertEquals(11, solution.minimumTimeRequired(new int[] { 1, 2, 4, 7, 8 }, 2));
    }

    // 二分查找 + 回溯 + 剪枝
    // 思路及算法
    // 因为很难直接计算出完成所有工作的最短时间，所以可以考虑使用尝试的方法。
    // 当完成所有工作的最短时间已经确定为 limit时，如果存在可行的方案，那么对于任意长于 limit的最短时间，都一定也存在可行的方案。
    // 因此我们可以考虑使用二分查找的方法寻找最小的存在可行方案的 limit值。
    // ├ 二分查找的上下限，下限为所有工作中的最大工作量，上限为所有工作的工作量之和
    // ├ 优先分配工作量大的工作
    // ├ 优先给有工作的人分配工作，当工人 i 还没被分配工作时，不给工人 i+1 分配工作
    // └ 当将工作 i 分配给工人 j ，使得工人 j 的工作量恰好达到 limit，且计算分配下一个工作的递归函数返回了 false，此时即无需尝试将工作 i 分配给其他工人，直接返回 false 即可。
    // 复杂度分析
    // 时间复杂度：O(nlog⁡n+log⁡(S−M)×n!)，其中 n 是数组 jobs 长度，S 是数组 jobs 的元素之和，M 是数组 jobs 中元素的最大值。最坏情况下每次二分需要遍历所有分配方案的排列。
    // 空间复杂度：O(n)。空间复杂度主要取决于递归的栈空间的消耗，而递归至多有 n 层。
    static class Solution {
        public int minimumTimeRequired(int[] jobs, int k) {

            // 排序(从小到大)
            Arrays.sort(jobs);

            // 翻转(从大到小)
            for (int i = 0, j = jobs.length - 1; i < j; i++, j--) {
                int temp = jobs[j];
                jobs[j] = jobs[i];
                jobs[i] = temp;
            }

            int left = jobs[0];
            int right = 0;
            for (int i = 0; i < jobs.length; i++) {
                right += jobs[i];
            }
            while (left < right) {
                int middle = (left + right) / 2;
                if (backtrack(jobs, new int[k], 0, middle)) {
                    right = middle;
                } else {
                    left = middle + 1;
                }
            }
            return left;
        }

        // 判断该limit是否可行
        private boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {

            // 所有工作均已分配
            if (i >= jobs.length) {
                return true;
            }

            // 当前工作工时
            int current = jobs[i];
            for (int j = 0; j < workloads.length; j++) {
                // 工作可分配给 j
                if (workloads[j] + current <= limit) {
                    // 工作分配给 j
                    workloads[j] += current;
                    // 继续分配后续的工作
                    if (backtrack(jobs, workloads, i + 1, limit)) {
                        return true;
                    }
                    // 分配方式不可行
                    workloads[j] -= current;
                }
                // 如果当前工人未被分配工作，那么下一个工人也必然未被分配工作
                // 或者当前工作恰能使该工人的工作量达到了上限
                // 这两种情况下无需尝试继续分配工作
                if (workloads[j] == 0 || workloads[j] + current == limit) {
                    break;
                }
            }
            return false;
        }
    }
}
