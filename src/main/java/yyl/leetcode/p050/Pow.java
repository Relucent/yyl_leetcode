package yyl.leetcode.p050;

/**
 * <h3>Pow(x, n)</b3><br>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。 <br>
 * 示例 1: 输入: 2.00000, 10; 输出: 1024.00000<br>
 * 示例 2: 输入: 2.10000, 3; 输出: 9.26100<br>
 * 示例 3: 输入: 2.00000, -2; 输出: 0.25000; 解释: 2-2 = 1/22 = 1/4 = 0.25<br>
 * 说明:<br>
 * -100.0 < x < 100.0<br>
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。<br>
 */
public class Pow {

    // [34.00515,-2147483648]
    public static void main(String[] args) {
        double base = 8.88023;
        int power = 3;
        System.out.println(base + "^" + power + "=?");
        System.out.println(new Solution().myPow(base, power));
        System.out.println(new Solution2().myPow(base, power));
        System.out.println(new Solution3().myPow(base, power));
    }

    // 快速幂算法
    // x^(a+b) == x^a * x^b
    // 我们可以将 n 看做一系列正整数之和，以下将 n转换成2进制来看
    // x^1 -> x^0b0001 -> x^1
    // x^2 -> x^0b0010 -> x^2 * 1
    // x^3 -> x^0b0011 -> x^2 * x^1
    // x^4 -> x^0b0100 -> x^(2^2) * 1 * 1
    // x^5 -> x^0b0101 -> [(x^2)^2] * 1 * [x]
    // x^1 -> x^0b0111 -> [(x^2)^2] * [x^2] * [x]
    // 时间复杂度：O(log ⁡n)
    // 对每一个 n 的二进制位表示，我们都至多需要累乘 1 次
    // 空间复杂度：O(1)
    // 我们只需要一个变量来保存最终 x 的连乘结果。
    static class Solution {

        public double myPow(double x, int n) {
            if (x == 1.0) {
                return x;
            }
            if (n < 0) {
                return Integer.MIN_VALUE == n //
                        ? 1.0 / (myPow(x, Integer.MAX_VALUE) * x)//
                        : 1.0 / myPow(x, -n);
            }
            double r = 1.0;
            while (n != 0) {
                if ((n & 1) == 1) {
                    r *= x;
                }
                x = x * x;
                n = n >> 1;
            }
            return r;
        }
    }

    // 常规求幂
    // 直接模拟该过程，将 x 连乘 n 次。如果 n<0，则 x^n == 1/(x^-n)
    // 关注边界条件，尤其是正整数和负整数的不同范围限制。
    // 时间复杂度：O(n). 我们需要将 x 连乘 n 次。
    // 空间复杂度：O(1) . 我们只需要一个变量来保存最终 x 的连乘结果。
    static class Solution2 {
        public double myPow(double x, int n) {
            if (x == 1.0) {
                return x;
            }
            if (n < 0) {
                return Integer.MIN_VALUE == n //
                        ? 1.0 / (myPow(x, Integer.MAX_VALUE) * x)//
                        : 1.0 / myPow(x, -n);
            }
            double r = 1.0;
            while ((n--) > 0) {
                r *= x;
            }
            return r;
        }
    }

    // Native
    static class Solution3 {
        public double myPow(double x, int n) {
            return Math.pow(x, n);
        }
    }
}
// MIN_VALUE -2147483648
// MAX_VALUE 2147483647
