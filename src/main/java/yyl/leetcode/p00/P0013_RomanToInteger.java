package yyl.leetcode.p00;

/**
 * <h3>罗马数字转整数</h3><br>
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。<br>
 * 字符|数值 <br>
 * I = 1 <br>
 * V = 5 <br>
 * X = 10 <br>
 * L = 50 <br>
 * C = 100 <br>
 * D = 500 <br>
 * M = 1000<br>
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + II 。<br>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：<br>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。<br>
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。<br>
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。<br>
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。<br>
 * 示例 1: 输入: "III"; 输出: 3<br>
 * 示例 2: 输入: "IV"; 输出: 4<br>
 * 示例 3: 输入: "IX"; 输出: 9<br>
 * 示例 4: 输入: "LVIII"; 输出: 58; 解释: L = 50, V= 5, III = 3.<br>
 * 示例 5: 输入: "MCMXCIV"; 输出: 1994; 解释: M = 1000, CM = 900, XC = 90, IV = 4.<br>
 * <br>
 * Given a roman numeral, convert it to an integer.<br>
 * Input is guaranteed to be within the range from 1 to 3999.<br>
 */
// 将罗马数字转换为整数 (输入保证在1到3999的范围内)
// I(1) V(5) X(10) L(50) C(100) D(500) M(1000)
// 罗马数字的规则：重复相加，右加左减
// 1. 一个罗马数字重复几次，就表示这个数的几倍。
// 2. 在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
// 3. 在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
// 4. 同一数码最多只能连续出现三次，比如4写成IV，而不能写成IIII。
// 5. 左减时不可跨越一个位值：比如8写成VIII，而不能写成IIX。
public class P0013_RomanToInteger {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] samples = {"I", "V", "X", "L", "C", "D", "M", "VIII", "IV", "DCXXI", "XCIX", "MMMCMXCIX"};
        for (String value : samples) {
            System.out.println(String.format("%-10s=%5d", value, solution.romanToInt(value)));
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
            for (int i = 0; i < s.length() - 1; i++) {
                if (map[s.charAt(i)] < map[s.charAt(i + 1)]) {
                    sum -= map[s.charAt(i)];
                } else {
                    sum += map[s.charAt(i)];
                }
            }
            sum += map[s.charAt(s.length() - 1)];
            return sum;
        }
    }
}
