package yyl.leetcode.p065;

/**
 * <h3>有效数字</h3><br>
 * 验证给定的字符串是否可以解释为十进制数字。<br>
 * 
 * <pre>
 * 例如:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * </pre>
 * 
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：<br>
 * 数字 0-9 <br>
 * 指数 - "e" <br>
 * 正/负号 - "+"/"-" <br>
 * 小数点 - "." <br>
 * 当然，在输入中，这些字符的上下文也很重要。<br>
 */
public class ValidNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] samples = {//
                "0", //
                " 0.1 ", //
                "abc", //
                "1 a", //
                "2e10", //
                " -90e3 ", //
                " 1e", //
                "e3", //
                " 6e-1", //
                " 99e2.5 ", //
                "53.5e93", //
                " --6 ", //
                "-+3", //
                "95a54e53", //
                ".1.",//
        };
        for (String sample : samples) {
            System.out.println("\"" + sample + "\"   => " + solution.isNumber(sample));
        }
    }

    // 有限状态机
    // 考虑每次输入可能的状态以及状态之间的转换
    // 输入字符，可以分为以下几个类别
    // 0 空格
    // 1 符号 (+-)
    // 2 数字(0~9)
    // 3 点(.)
    // 4 幂符号(E)
    // 5 其他
    // 根据已输入字符的情况，可以分为以下几种状态
    // -1 错误状态
    // 0 首位输入状态(包含初始空格的状态)
    // 1 符号位状态(前面没有数字的+-符号)
    // 2 首位小数点状态(前面没有数字情况的小数点)
    // 3 整数状态
    // 4 小数状态
    // 5 幂位状态(E符号的状态)
    // 6 幂符号位状态(输入E后的+-符号)
    // 7 幂数字状态(输入E后输入数字的状态)
    // 8 结尾空格状态(前面输入有效，后面只能是空格)
    // 然后编写每个状态可能的情况
    // 时间复杂度：O(n)，n是字符串长度
    // 空间复杂度：O(1)，占用常量级别空间
    private static class Solution {

        // 状态迁移表
        private int transitionTable[][] = {
                // 空格,(+-),0~9,(.),E
                {+0, +1, +3, +2, -1}, // 状态0:初始输入状态(包含初始空格的状态)
                {-1, -1, +3, +2, -1}, // 状态1:符号位状态(前面没有数字的+-符号)
                {-1, -1, +4, -1, -1}, // 状态2:首位小数点状态(前面没有数字情况的小数点)
                {+8, -1, +3, +4, +5}, // 状态3:整数状态
                {+8, -1, +4, -1, +5}, // 状态4:小数状态
                {-1, +6, +7, -1, -1}, // 状态5:幂开始状态(E符号的状态)
                {-1, -1, +7, -1, -1}, // 状态6:幂符号位状态(输入E后的+-符号)
                {+8, -1, +7, -1, -1}, // 状态7:幂数字状态(输入E后输入数字的状态)
                {+8, -1, -1, -1, -1}, // 状态8:结尾空格状态(前面有效情况，输入空格的状态)
        };

        public boolean isNumber(String s) {
            int state = 0;
            for (char c : s.toCharArray()) {
                if (c == ' ') {
                    state = transitionTable[state][0];
                } else if (c == '+' || c == '-') {
                    state = transitionTable[state][1];
                } else if ('0' <= c && c <= '9') { // ASCII 48~57
                    state = transitionTable[state][2];
                } else if (c == '.') {
                    state = transitionTable[state][3];
                } else if (c == 'e' || c == 'E') {
                    state = transitionTable[state][4];
                } else {
                    state = -1;
                }
                if (state == -1) {
                    return false;
                }
            }
            return state == 3 || state == 4 || state == 7 || state == 8;
        }
    }
}
