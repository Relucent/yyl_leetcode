package yyl.leetcode.p14;

import yyl.leetcode.util.Assert;

/**
 * <h3>可获得的最大点数</h3><br>
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。<br>
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。<br>
 * 你的点数就是你拿到手中的所有卡牌的点数之和。<br>
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 * 输出：12
 * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 * 
 * 示例 2：
 * 输入：cardPoints = [2,2,2], k = 2
 * 输出：4
 * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 * 
 * 示例 3：
 * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
 * 输出：55
 * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 * 
 * 示例 4：
 * 输入：cardPoints = [1,1000,1], k = 1
 * 输出：1
 * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。 
 * 
 * 示例 5：
 * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * 输出：202
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= cardPoints.length <= 10^5 <br>
 * ├ 1 <= cardPoints[i] <= 10^4 <br>
 * └ 1 <= k <= cardPoints.length <br>
 */
public class P1423_MaximumPointsYouCanObtainFromCards {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(12, solution.maxScore(new int[] { 1, 2, 3, 4, 5, 6, 1 }, 3));
        Assert.assertEquals(4, solution.maxScore(new int[] { 2, 2, 2 }, 2));
        Assert.assertEquals(55, solution.maxScore(new int[] { 9, 7, 7, 9, 7, 7, 9 }, 7));
        Assert.assertEquals(1, solution.maxScore(new int[] { 1, 1000, 1 }, 1));
        Assert.assertEquals(202, solution.maxScore(new int[] { 1, 79, 80, 1, 1, 1, 200, 1 }, 3));
    }

    // 前缀和+后缀和
    // 设：前半部分选了x个
    // 则：后半部分需要选 k - x 个，枚举k种情况，即前缀和分别选 0 到 k - 1，求出每种情况取最大值
    // 时间复杂度 ：O(k)。
    // 空间复杂度 ：O(1)。
    static class Solution {
        public int maxScore(int[] cardPoints, int k) {
            int n = cardPoints.length;

            // 选前 n-k 个作为初始值
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += cardPoints[i];
            }
            int answer = sum;
            for (int i = 0; i < k; i++) {
                sum = sum - cardPoints[k - i - 1] + cardPoints[n - 1 - i];
                answer = Math.max(answer, sum);
            }
            return answer;
        }
    }
}
