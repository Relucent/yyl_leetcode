package yyl.algorithms.v013;

/**
 * Given a roman numeral, convert it to an integer.<br>
 * Input is guaranteed to be within the range from 1 to 3999.<br>
 */
// 将罗马数字转换为整数 (输入保证在1到3999的范围内)
// I(1) V(5) X(10) L(50) C(100) D(500) M(1000)
public class RomanToInteger {

	public static void main(String[] args) {
		String[] samples = { "I", "V", "X", "L", "C", "D", "M", "DCXXI", "XCIX", "MMMCMXCIX" };
		for (String value : samples) {
			System.out.println(romanToInt(value) + "	" + value);
		}
	}

	public static int romanToInt(String s) {
		String[][] digitals = { //
				{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }, //10^0
				{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }, //10^1
				{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, //10^2
				{ "", "M", "MM", "MMM" }//10^3
		};
		int result = 0;
		int pos = 0;
		for (int n = 3; n >= 0; n--) {
			result = result * 10;
			for (int i = digitals[n].length - 1; i > 0; i--) {
				if (s.startsWith(digitals[n][i], pos)) {
					result += i;
					pos += digitals[n][i].length();
					break;
				}
			}
		}
		return result;
	}
}
