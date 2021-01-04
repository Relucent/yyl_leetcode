package yyl.leetcode.p05;

import yyl.leetcode.util.Assert;

/**
 * <h3>斐波那契数</h3><br>
 * 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：<br>
 * ├ F(0) = 0，F(1) = 1 <br>
 * └ F(n) = F(n - 1) + F(n - 2)，其中 n > 1 <br>
 * 给你 n ，请计算 F(n) 。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 
 * 示例 2：
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 
 * 示例 3：
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * </pre>
 * 
 * 提示： 0 <= n <= 30
 */
public class P0509_FibonacciNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(1, solution.fib(2));
        Assert.assertEquals(2, solution.fib(3));
        Assert.assertEquals(3, solution.fib(4));
    }

    // 动态规划
    // 根据题目，已经给出了斐波那契数的动态转移方程
    // ├ dp[0] = 0
    // ├ dp[1] = 1
    // └ dp[n] = F[n - 1] + F[n - 2]
    // 因为方程中每次只和前两次的结果相关，所以dp数组还可以进行压缩
    // 时间复杂度：O(n)
    // 时间复杂度：O(1)
    static class Solution {
        public int fib(int n) {
            if (n < 2) {
                return n;
            }
            int x = 0;
            int y = 1;
            for (int i = 2; i <= n; i++) {
                int z = x + y;
                x = y;
                y = z;
            }
            return y;
        }
    }

    // 数学法
    // 通项公式：an=(((1+sqrt(5))/2)^n-((1-sqrt(5))/2)^n)/sqrt(5)
    // 复杂度分析：取决于sqrt和 pow 函数的时空复杂度
    static class Solution1 {
        public int fib(int n) {
            double sqrt5 = Math.sqrt(5);
            double fibN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
            return (int) Math.round(fibN / sqrt5);
        }
    }
}
