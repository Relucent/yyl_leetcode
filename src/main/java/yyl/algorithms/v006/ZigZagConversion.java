package yyl.algorithms.v006;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: <br>
 * (you may want to display this pattern in a fixed font for better legibility) <br>
 * 
 * <pre>
 * P   A   H   N 
 * A P L S I I G 
 * Y   I   R
 * </pre>
 * 
 * Write the code that will take a string and make this conversion given a number of rows: <br>
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".<br>
 */
//Z型转换 (输出的文本也是一行，并不是图形文本)
public class ZigZagConversion {

	public static void main(String[] args) {
		//1___9___7___5
		//2__80__68__46
		//3_7_1_5_9_3_7
		//46__24__02__8_0
		//5___3___1___9
		String result = convert("123456789012345678901234567890", 5);
		System.out.println(result);
	}

	public static String convert(String s, int numRows) {
		if (numRows < 2 || s.length() == 0) {
			return s;
		}
		int len = s.length();
		int interval = numRows * 2 - 2;

		StringBuilder sbr = new StringBuilder();

		//first line
		for (int i = 0; i < len; i += interval) {
			sbr.append(s.charAt(i));
		}

		//middle line
		for (int row = 1; row < numRows - 1; row++) {
			// row => +(interval–2*row) | +(2*row) | +(interval–2*row) | +(2*row) | 
			int midInterval = 2 * row;
			for (int i = row; i < len; i += midInterval) {
				sbr.append(s.charAt(i));
				//1-> interval–2*row
				//2-> interval–(interval–2*row) = 2*row
				midInterval = interval - midInterval;
			}
		}

		//last line
		for (int i = numRows - 1; i < len; i += interval) {
			sbr.append(s.charAt(i));
		}
		return sbr.toString();
	}
}
