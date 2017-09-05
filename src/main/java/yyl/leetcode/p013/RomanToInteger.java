package yyl.leetcode.p013;

/**
 * Given a roman numeral, convert it to an integer.<br>
 * Input is guaranteed to be within the range from 1 to 3999.<br>
 */
// 将罗马数字转换为整数 (输入保证在1到3999的范围内)
// I(1) V(5) X(10) L(50) C(100) D(500) M(1000)
public class RomanToInteger {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] samples = { "I", "V", "X", "L", "C", "D", "M", "DCXXI", "XCIX", "MMMCMXCIX" };
		for (String value : samples) {
			System.out.println(solution.romanToInt(value) + "	" + value);
		}
	}

	// O(n)
	static class Solution {
		public int romanToInt(String s) {
			int[] map = new int[128];
			map['I'] = 1;
			map['V'] = 5;
			map['X'] = 10;
			map['L'] = 50;
			map['C'] = 100;
			map['D'] = 500;
			map['M'] = 1000;
			int sum = 0;
			char[] value = s.toCharArray();
			for (int i = 0; i < value.length - 1; ++i) {
				if (map[value[i]] < map[value[i + 1]]) {
					sum -= map[value[i]];
				} else {
					sum += map[value[i]];
				}
			}
			return sum + map[value[s.length() - 1]];
		}
	}

	// O(n*d)
	static class Solution2 {
		public int romanToInt2(String s) {
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
}
