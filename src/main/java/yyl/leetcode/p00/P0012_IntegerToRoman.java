package yyl.leetcode.p00;

/**
 * <h3>整数转罗马数字</h3><br>
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
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。<br>
 * 示例 1: 输入: 3; 输出: "III"<br>
 * 示例 2: 输入: 4; 输出: "IV"<br>
 * 示例 3: 输入: 9; 输出: "IX"<br>
 * 示例 4: 输入: 58; 输出: "LVIII"; 解释: L = 50, V = 5, III = 3.<br>
 * 示例 5: 输入: 1994; 输出: "MCMXCIV"; 解释: M = 1000, CM = 900, XC = 90, IV = 4.<br>
 * <br>
 * Given an integer, convert it to a roman numeral.<br>
 * Input is guaranteed to be within the range from 1 to 3999.<br>
 */
// 将整数转换为罗马数字 (输入保证在1到3999的范围内)
// I(1) V(5) X(10) L(50) C(100) D(500) M(1000)
public class P0012_IntegerToRoman {

    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i <= 3999; i++) {
            System.out.println(i + "	" + solution.intToRoman(i));
        }
    }

    // 硬编码数字
    // 千位数字只能由 M 表示；
    // 百位数字只能由 C CD D CM 表示；
    // 十位数字只能由 X XL L XC 表示；
    // 个位数字只能由 I IV V IX 表示。
    // 恰好把这 13 个符号分为四组，且组与组之间没有公共的符号。
    // 利用模运算和除法运算，我们可以得到 num 每个位上的数字
    // 最后，根据 num 每个位上的数字，在硬编码表中查找对应的罗马字符，并将结果拼接在一起，即为 num 对应的罗马数字。
    // 复杂度分析
    // 时间复杂度：O(1)。计算量与输入数字的大小无关。
    // 空间复杂度：O(1)。
    static class Solution {
        private static final String[] THOUSANDS = { "", "M", "MM", "MMM" };
        private static final String[] HUNDREDS = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        private static final String[] TENS = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        private static final String[] ONES = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };

        public String intToRoman(int num) {
            if (num < 1 || 3999 < num) {
                return "";
            }
            StringBuilder roman = new StringBuilder();
            roman.append(THOUSANDS[num / 1000]);
            roman.append(HUNDREDS[num % 1000 / 100]);
            roman.append(TENS[num % 100 / 10]);
            roman.append(ONES[num % 10]);
            return roman.toString();
        }
    }
}