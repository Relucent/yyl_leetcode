package yyl.leetcode.p10;

/**
 * <h3>最佳观光组合</h3><br>
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。<br>
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。<br>
 * 返回一对观光景点能取得的最高分。<br>
 * 
 * <pre>
 * 示例：
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 * 
 * 提示：
 *     2 <= A.length <= 50000
 *     1 <= A[i] <= 1000
 * </pre>
 */
public class P1014_BestSightseeingPair {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxScoreSightseeingPair(new int[] { 8, 1, 5, 2, 6 }));// 11
        System.out.println(solution.maxScoreSightseeingPair(new int[] { 1, 2 }));// 2
        System.out.println(solution.maxScoreSightseeingPair(new int[] { 6, 9, 10, 5, 9, 10, 4, 5 }));// 18
    }

    // 枚举法
    // 思路：
    // A[i] + A[j] + i − j = (A[i] + i) + (A[j] − j)
    // 遍历数组，根据前面最大的 数(A[i] + i)和当前的数(A[j] − j)的算最大值
    // 时间复杂度：O(N)，其中 n 为数组 A 的大小。只需要遍历一遍数组即可。
    // 空间复杂度：O(1)，只需要常数空间来存放若干变量。
    static class Solution {
        public int maxScoreSightseeingPair(int[] A) {
            int ans = Integer.MIN_VALUE;
            int max = A[0];
            for (int i = 1; i < A.length; i++) {
                ans = Math.max(max + A[i] - i, ans);
                max = Math.max(A[i] + i, max);
            }
            return ans;
        }
    }

    // 暴力遍历法
    // 时间复杂度：O(N^2)
    // 空间复杂度：O(1)
    static class Solution2 {
        public int maxScoreSightseeingPair(int[] A) {
            int score = 0;
            for (int i = 0; i < A.length - 1; i++) {
                for (int j = i + 1; j < A.length; j++) {
                    score = Math.max(score, A[i] + A[j] + i - j);
                }
            }
            return score;
        }
    }
}
