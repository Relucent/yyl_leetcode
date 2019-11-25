package yyl.leetcode.p008;

/**
 * <h3>字符串转换整数</h3><br>
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。<br>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。<br>
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。<br>
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。<br>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。<br>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。<br>
 * 说明：<br>
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231, 231 − 1]。如果数值超过这个范围，请返回 INT_MAX (231 − 1) 或 INT_MIN (−231) 。<br>
 * 示例 1:<br>
 * 输入: "42"; 输出: 42<br>
 * 示例 2:<br>
 * 输入: " -42"; 输出: -42<br>
 * 解释: 第一个非空白字符为 '-', 它是一个负号。<br>
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。<br>
 * 示例 3:<br>
 * 输入: "4193 with words"; 输出: 4193<br>
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。<br>
 * 示例 4:<br>
 * 输入: "words and 987"; 输出: 0<br>
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。<br>
 * 因此无法执行有效的转换。<br>
 * 示例 5:<br>
 * 输入: "-91283472332"; 输出: -2147483648<br>
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。 <br>
 * 因此返回 INT_MIN (−231) 。 <br>
 * <br>
 * mplement atoi to convert a string to an integer.<br>
 * Hint: Carefully consider all possible input cases. <br>
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.<br>
 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs).<br>
 * You are responsible to gather all the input requirements up front. <br>
 * <br>
 * Requirements for atoi:<br>
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
// 1. 若字符串开头是空格，则跳过所有空格，到第一个非空格字符，如果没有，则返回0.
// 2. 若第一个非空格字符是符号+/-，则标记sign的真假.
// 3. 若下一个字符不是数字，则返回0. (不考虑小数点)
// 4. 如果下一个字符是数字，则转为整形存下来，若接下来再有非数字出现，则返回目前的结果。
// 5. 如果超过了整形数的范围，则用边界值替代当前值。
// MAX_VALUE +2147483647
// MIN_VALUE -2147483648
public class StringToInteger {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi(" 2147483647"));
        System.out.println(solution.myAtoi(" 2147483648"));
        System.out.println(solution.myAtoi(""));
        System.out.println(solution.myAtoi("1a"));
        System.out.println(solution.myAtoi("++1a"));
        System.out.println(solution.myAtoi("+-1a"));
        System.out.println(solution.myAtoi("+2147483648"));
        System.out.println(solution.myAtoi("-2147483649"));
    }

    static class Solution {

        // 算法参考了 java.lang.Integer.toString 的源码
        public int myAtoi(String str) {
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
}
