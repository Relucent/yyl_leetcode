package yyl.algorithms.v012;

/**
 * Given an integer, convert it to a roman numeral.<br>
 * Input is guaranteed to be within the range from 1 to 3999.<br>
 */
// 将整数转换为罗马数字 (输入保证在1到3999的范围内)
// I(1) V(5) X(10) L(50) C(100) D(500) M(1000)
public class IntegerToRoman {

	public static void main(String[] args) {
		for (int i = 0; i <= 3999; i++) {
			System.out.println(intToRoman(i));
		}
	}

	public static String intToRoman(int num) {
		if (num < 1 || 3999 < num) {
			return "";
		}
		String[] digitals = { //
				"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", //10^0
				"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", //10^1
				"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", //10^2
				"", "M", "MM", "MMM" //10^3
		};
		return digitals[((num / 1000) % 10) + 30]//10^3
				+ digitals[((num / 100) % 10) + 20]//10^2
				+ digitals[((num / 10) % 10) + 10]//10^1
				+ digitals[(num) % 10];//10^0
	}
}
