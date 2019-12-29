package yyl.leetcode.p00;

/**
 * <h3>整数反转</h3> <br>
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。<br>
 * 示例 1:<br>
 * 输入: 123<br>
 * 输出: 321<br>
 * 示例 2:<br>
 * 输入: -123<br>
 * 输出: -321<br>
 * 示例 3:<br>
 * 输入: 120<br>
 * 输出: 21<br>
 * 注意:<br>
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。 请根据这个假设，如果反转后整数溢出那么就返回 0。<br>
 * <br>
 * Reverse digits of an integer.<br>
 * Example1: x = 123, return 321 <br>
 * Example2: x = -123, return -321 <br>
 * Note:<br>
 * The input is assumed to be a 32-bit signed integer. <br>
 * Your function should return 0 when the reversed integer overflows.<br>
 */
public class P0007_ReverseInteger {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] samples = {0, 123, -2147483648, -100, -123, 2147447412, 1534236469, -2147483412};
        for (int sample : samples) {
            System.out.println(sample + " -> " + solution.reverse(sample));
        }
    }

    static class Solution {
        public int reverse(int x) {
            long reverse = 0;
            while (x != 0) {
                reverse = (reverse * 10) + (x % 10);
                x /= 10;
            }
            // overflows
            if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) reverse;
        }
    }

    static class Solution2 {
        public int reverse(int x) {
            int result = 0;
            while (x != 0) {
                int digit = x % 10;
                int forward = result * 10 + digit;
                // overflows
                if ((forward - digit) / 10 != result) {
                    return 0;
                }
                result = forward;
                x /= 10;
            }
            return result;
        }
    }

    static class Solution3 {
        public int reverse(int x) {
            // MIN_VALUE -2147483648
            // MAX_VALUE +2147483647
            if (x == Integer.MIN_VALUE || x == Integer.MAX_VALUE) {
                return 0;
            }

            boolean negative = x < 0;
            if (negative) {
                x = -x;
            }
            int[] digits = new int[10];
            int n = 0;
            int d = x;
            while (d != 0) {
                digits[n++] = d % 10;
                d /= 10;
            }
            int limit = negative ? Integer.MIN_VALUE : -Integer.MAX_VALUE;
            int multiLimit = limit / 10;
            int result = 0;
            for (int i = 0; i < n; i++) {
                if (result < multiLimit) {
                    return 0;// overflows
                }
                result *= 10;
                int digit = digits[i];

                if (result < limit + digit) {
                    return 0;// overflows
                }
                result -= digit;
            }
            return negative ? result : -result;
        }
    }
}
