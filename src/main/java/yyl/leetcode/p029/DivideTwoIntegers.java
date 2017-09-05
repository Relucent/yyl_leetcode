package yyl.leetcode.p029;

/**
 * Divide Two Integers <br>
 * Divide two integers without using multiplication, division and mod operator.<br>
 * If it is overflow, return MAX_INT. <br>
 */
//在不使用乘法，除法和取余操作的情况下实现整数的除法运算
public class DivideTwoIntegers {
	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.divide(100, 10));
		System.out.println(solution.divide(32, 12));
		System.out.println(solution.divide(0, 100));
		System.out.println(solution.divide(100, 0));
		System.out.println(solution.divide(Integer.MIN_VALUE, -1));
	}
	//	 题目分析：
	//	 1. 被除数/除数=商(忽略余数) => 被除数=除数*商。
	//	 2. 商(任意整数)可以表示为：N0*2^0+N1*2^1+...Ni*2^i
	//	 3. 在Java中左移操作<<相当于对一个数乘以2，右移操作相当于除以2.
	//	 4. 我们让除数左移直到大于被除数前的的一个数，例如计算28/3，我们进行三次左移操作，使3*2*2*2=3*8=24<28(注意四次左移操作得到3*2^4=48>28).记录下2*2*2=2^3=8.
	//	 5. 我们让28减去24得到4，然后像第四步一样计算4/3,则3*2^0=3<4.记录下2^0=1.
	//	 6. 由于4-3=1小于除数3，停止计算，并将每轮得到的值相加，在本例中8+1=9，记得到商（即28/3=9）。
	//	 7. 将输入的Int(32位)型数字转换为long(64位)型。
	//	 8. 考虑MIN_VALUE/-1的特殊情况。

	static class Solution {
		public int divide(int dividend, int divisor) {
			if (dividend == 0) {
				return 0;
			}
			if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
				return Integer.MAX_VALUE;
			}
			boolean negative = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
			int result = 0;
			long dividendL = Math.abs((long) dividend);
			long divisorL = Math.abs((long) divisor);
			while (dividendL >= divisorL) {
				int shiftnum = 0;
				while (dividendL >= (divisorL << shiftnum)) {
					shiftnum++;
				}
				result += 1 << (shiftnum - 1);
				dividendL -= divisorL << (shiftnum - 1);
			}
			return negative ? -result : result;
		}
	}

}
