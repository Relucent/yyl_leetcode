package yyl.leetcode.p02;

import yyl.leetcode.util.Assert;

/**
 * <h3>基本计算器</h3><br>
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 * 
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= s.length <= 3 * 105<br>
 * ├ s 由数字、'+'、'-'、'('、')'、和 ' ' 组成<br>
 * └ s 表示一个有效的表达式<br>
 */
public class P0224_BasicCalculator {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.calculate("1 + 1"));
        Assert.assertEquals(3, solution.calculate(" 2-1 + 2 "));
        Assert.assertEquals(23, solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    // 递归
    // 由于字符串除了数字与括号外，只有加号和减号两种运算符。因此按照运算法则模拟运算即可，遇到括号就进行递归。
    // 时间复杂度：O(n)，其中 n 为字符串 s 的长度。需要遍历字符串 s 一次，计算表达式的值。
    // 空间复杂度：O(n)，其中 n 为字符串 s 的长度。空间复杂度主要取决于栈的空间，栈中的元素数量不超过 n。
    static class Solution {

        public int calculate(String s) {
            return doCalculate(s, new Index());
        }

        private int doCalculate(String s, Index index) {
            // 当前符号
            int sign = 1;
            int result = 0;
            int n = s.length();
            while (index.value < n) {
                // 忽略空格
                if (s.charAt(index.value) == ' ') {
                    index.value++;
                }
                // 遇到加号
                else if (s.charAt(index.value) == '+') {
                    sign = 1;
                    index.value++;
                }
                // 遇到减号
                else if (s.charAt(index.value) == '-') {
                    sign = -1;
                    index.value++;
                }
                // 遇到括号
                else if (s.charAt(index.value) == '(') {
                    index.value++;
                    result += sign * doCalculate(s, index);
                }
                // 括号结束
                else if (s.charAt(index.value) == ')') {
                    index.value++;
                    return result;
                }
                // 遇到数字
                else {
                    long num = 0;
                    while (index.value < n && Character.isDigit(s.charAt(index.value))) {
                        num = num * 10 + s.charAt(index.value) - '0';
                        index.value++;
                    }
                    result += sign * num;
                }
            }
            return result;
        }

        private class Index {
            private int value = 0;
        }
    }
}
