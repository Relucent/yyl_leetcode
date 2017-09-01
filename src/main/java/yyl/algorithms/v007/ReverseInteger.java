package yyl.algorithms.v007;

/**
 * Reverse digits of an integer.<br>
 * Example1: x = 123, return 321 <br>
 * Example2: x = -123, return -321 <br>
 * Note:<br>
 * The input is assumed to be a 32-bit signed integer. <br>
 * Your function should return 0 when the reversed integer overflows.<br>
 */
// 数字逆序，如果数字溢出返回0
public class ReverseInteger {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] samples = { 0, 123, -2147483648, -100, -123, 2147447412, 1534236469, -2147483412 };
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
			//overflows
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
				//overflows
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
			//MIN_VALUE -2147483648
			//MAX_VALUE +2147483647
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
					return 0;//overflows
				}
				result *= 10;
				int digit = digits[i];

				if (result < limit + digit) {
					return 0;//overflows
				}
				result -= digit;
			}
			return negative ? result : -result;
		}
	}
}