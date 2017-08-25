package yyl.algorithms.v008;

/**
 * mplement atoi to convert a string to an integer.<br>
 * Hint: Carefully consider all possible input cases. <br>
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.<br>
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).<br>
 * You are responsible to gather all the input requirements up front. <br>
 * <br>
 * Requirements for atoi:<br>
 * 
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical
 * value.<br>
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of
 * this function.<br>
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty
 * or it contains only whitespace characters, no conversion is performed.<br>
 * If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX
 * (2147483647) or INT_MIN (-2147483648) is returned.<br>
 */
// 字符串转为整数
//1. 若字符串开头是空格，则跳过所有空格，到第一个非空格字符，如果没有，则返回0.
//2. 若第一个非空格字符是符号+/-，则标记sign的真假.
//3. 若下一个字符不是数字，则返回0. (不考虑小数点)
//4. 如果下一个字符是数字，则转为整形存下来，若接下来再有非数字出现，则返回目前的结果。
//5. 如果超过了整形数的范围，则用边界值替代当前值。
//MAX_VALUE +2147483647
//MIN_VALUE -2147483648
public class StringToInteger {

	public static void main(String[] args) {
		System.out.println(myAtoi(" 2147483647"));
		System.out.println(myAtoi(" 2147483648"));
		System.out.println(myAtoi(""));
		System.out.println(myAtoi("1a"));
		System.out.println(myAtoi("++1a"));
		System.out.println(myAtoi("+-1a"));
	}

	public static int myAtoi(String str) {
		if (str == null) {
			return 0;
		}

		boolean negative = false;
		int len = str.length();
		int i = 0;
		int result = 0;

		while (i < len) {
			char c = str.charAt(i);
			if (c > ' ') {
				break;
			}
			i++;
		}

		if (i == len) {
			return result;
		}

		char firstChar = str.charAt(i);

		if (firstChar == '-') {
			negative = true;
			i++;
		} else if (firstChar == '+') {
			i++;
		}

		int limit = negative ? Integer.MIN_VALUE : -Integer.MAX_VALUE;// -MAX
		int multiplicationLimit = limit / 10;
		while (i < len) {
			int digit = str.charAt(i++) - '0';
			if (digit < 0 || digit > 9) {
				break;
			}
			if (result < multiplicationLimit) {
				return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}
			result *= 10;
			if (result < limit + digit) {
				return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}
			result -= digit;
		}
		return negative ? result : -result;
	}
}
