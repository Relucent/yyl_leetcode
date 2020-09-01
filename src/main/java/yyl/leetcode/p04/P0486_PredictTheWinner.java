package yyl.leetcode.p04;

import yyl.leetcode.util.Assert;

/**
 * <h3>预测赢家</h3><br>
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。<br>
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：[1, 5, 2]
 * 输出：False
 * 解释：一开始，玩家1可以从1和2中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 False 。
 * 
 * 示例 2：
 * 输入：[1, 5, 233, 7]
 * 输出：True
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 * 最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
 * </pre>
 * 
 * 提示：<br>
 * 【1】 1 <= 给定的数组长度 <= 20.<br>
 * 【2】数组里所有分数都为非负数且不会大于 10000000 。<br>
 * 【3】如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。<br>
 */
public class P0486_PredictTheWinner {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertFalse(solution.PredictTheWinner(new int[] { 1, 5, 2 }));
        Assert.assertTrue(solution.PredictTheWinner(new int[] { 1, 5, 233, 7 }));
    }

    // 递归(暴力法)
    // 1、每次选择只能从数组两端选择，所以剩下的数组是连续的。
    // 2、可以设置一个区间[i,j]，每次玩家只能选择nums[i]或者nums[j]
    // 3、当 i==j 时，只有一个数字，玩家只能选择这一个数字
    // 4、需要使用一个数字记录是先手后手（先手是正，后手是负)
    // 5、每次玩家都会选择最优的情况。
    // 时间复杂度：O(n^2)，其中n是数组的长度。
    // 空间复杂度：O(n)，空间复杂度取决于递归使用的栈空间。
    static class Solution {

        public boolean PredictTheWinner(int[] nums) {
            return count(nums, 0, nums.length - 1, true) >= 0;
        }

        private int count(int[] nums, int i, int j, boolean first) {
            if (first) {
                if (i == j) {
                    return nums[i];
                }
                int start = nums[i] + count(nums, i + 1, j, false);
                int end = nums[j] + count(nums, i, j - 1, false);
                return Math.max(start, end);
            } else {
                if (i == j) {
                    return -nums[i];
                }
                int start = -nums[i] + count(nums, i + 1, j, true);
                int end = -nums[j] + count(nums, i, j - 1, true);
                return Math.min(start, end);
            }
        }
    }

    // 动态规划
    // 定义二维数组 dp，其行数和列数都等于数组的长度，dp[i][j]表示当数组剩下的部分为下标 i 到下标 j 时，当前玩家与另一个玩家的分数之差的最大值，注意当前玩家不一定是先手。
    // 最后判断 dp[0][nums.length−1]的值，如果大于或等于 0，则先手得分大于或等于后手得分，因此先手成为赢家，否则后手成为赢家。
    // [i,j] 可能是从 nums[j]-[i,j-1]得来的，也可能是 nums[i]-[i+1,j]得来的
    // 当前玩家会选择对自己最有利的，所以可以得到状态转移方程：
    // dp[i][j]=max(nums[i]−dp[i+1][j],nums[j]−dp[i][j−1])
    // 边界：i==j ，dp[i][j] = nums[i];
    // 时间复杂度：O(n^2)，其中n是数组的长度，需要计算 (n(n+1))/2​ 个子数组。
    // 空间复杂度：O(n)，空间复杂度取决于递归使用的栈空间。
    static class Solution1 {
        public boolean PredictTheWinner(int[] nums) {
            int n = nums.length;
            int[][] dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                dp[i][i] = nums[i];
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = i + 1; j < n; j++) {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
            return dp[0][n - 1] >= 0;
        }
    }
}
