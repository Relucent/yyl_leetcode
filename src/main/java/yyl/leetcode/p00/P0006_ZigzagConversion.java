package yyl.leetcode.p00;

/**
 * <h3>Z 字形变换</h3><br>
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。<br>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * 
 * <pre>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * </pre>
 * 
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。 请你实现这个将字符串进行指定行数变换的函数：<br>
 * string convert(string s, int numRows);<br>
 * 示例 1:<br>
 * 输入: s = "LEETCODEISHIRING", numRows = 3<br>
 * 输出: "LCIRETOESIIGEDHN"<br>
 * 示例 2:<br>
 * 输入: s = "LEETCODEISHIRING", numRows = 4<br>
 * 输出: "LDREOEIIECIHNTSG"<br>
 * 解释:
 * 
 * <pre>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * </pre>
 * 
 * <br>
 * <br>
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
// Z型转换 (输出的文本也是一行，并不是图形文本，这题其实就是找规律)
public class P0006_ZigzagConversion {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // A___I___Q___Y
        // B__HJ__PR__XZ
        // C_G_K_O_S_W
        // DF__LN__TV
        // E___M___U
        String result = solution.convert("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 5);
        System.out.println(result);
    }

    // ROWS=1 (special)
    // r0: |01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|

    // ROWS=2 (interval=2)
    // r0: |01|03|05|07|09|11|13|15|17|19|21|23|25|
    // r1: |02|04|06|08|10|12|14|16|18|20|22|24|26|

    // ROWS=3 (interval=4)
    // r0: |01|__|05|__|09|__|13|__|17|__|21|__|25|
    // r1: |02|04|06|08|10|12|14|16|18|20|22|24|26|
    // r2: |03|__|07|__|11|__|15|__|19|__|23|__|

    // ROWS=4 (interval=6)
    // r0: |01|__|__|07|__|__|13|__|__|19|__|__|25|
    // r1: |02|__|06|08|__|12|14|__|18|20|__|24|26|
    // r2: |03|05|__|09|11|__|15|17|__|21|23|
    // r3: |04|__|__|10|__|__|16|__|__|22|

    // ROWS=5 (interval=8) (midInterval=interval-2*row,2*row)
    // r0: |01|__|__|__|09|__|__|__|17|__|__|__|25|
    // r1: |02|__|__|08|10|__|__|16|18|__|__|24|26|
    // r2: |03|__|07|__|11|__|15|__|19|__|23|
    // r3: |04|06|__|__|12|14|__|__|20|22|
    // r4: |05|__|__|__|13|__|__|__|21|__|
    static class Solution {

        public String convert(String s, int numRows) {
            if (numRows < 2 || s.length() == 0) {
                return s;
            }
            int interval = (numRows - 1) << 1; // (numRows - 1) * 2
            int k = 0;
            char[] chars = new char[s.length()];

            // first line
            for (int i = 0; i < s.length(); i += interval) {
                chars[k++] = s.charAt(i);
            }

            // middle line
            for (int row = 1; row < numRows - 1; row++) {
                // row => +(interval–2*row) | +(2*row) | +(interval–2*row) | +(2*row) |
                int midInterval = row << 1; // row * 2
                // 1-> interval–2*row
                // 2-> interval–(interval–2*row) = 2*row
                for (int i = row; i < s.length(); i += (midInterval = interval - midInterval)) {
                    chars[k++] = s.charAt(i);
                }
            }

            // last line
            for (int i = numRows - 1; i < s.length(); i += interval) {
                chars[k++] = s.charAt(i);
            }
            return new String(chars);
        }
    }
}
