package yyl.leetcode.p02;

import java.util.Deque;
import java.util.LinkedList;

import yyl.leetcode.util.Assert;

/**
 * <h3>基本计算器 II</h3><br>
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 <br>
 * 整数除法仅保留整数部分。 <br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 * 
 * 示例 2：
 * 输入：s = " 3/2 "
 * 输出：1
 * 
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= s.length <= 3 * 105 <br>
 * ├ s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 <br>
 * ├ s 表示一个 有效表达式 <br>
 * ├ 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内 <br>
 * └ 题目数据保证答案是一个 32-bit 整数 <br>
 */
public class P0227_BasicCalculatorII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(7, solution.calculate("3+2*2"));
        Assert.assertEquals(1, solution.calculate(" 3/2 "));
        Assert.assertEquals(5, solution.calculate(" 3+5 / 2 "));
    }

    // 栈
    // 由于乘除优先于加减计算，因此需要先进行所有乘除运算，并将这些乘除运算后的整数值放回原表达式的相应位置，则随后整个表达式的值，就等于一系列整数加减后的值。
    // 因此，可以用一个栈，保存这些（进行乘除运算后的）整数的值。对于加减号后的数字，将其直接压入栈中；对于乘除号后的数字，可以直接与栈顶元素计算，并替换栈顶元素为计算后的结果。
    // 遍历字符串 s ，并用变量 preSign 记录每个数字之前的运算符，对于第一个数字，其之前的运算符视为加号。每次遍历到数字末尾时，根据 preSign 来决定计算方式：
    // 加号：将数字压入栈；
    // 减号：将数字的相反数压入栈；
    // 乘除号：计算数字与栈顶元素，并将栈顶元素替换为计算结果。
    // 代码实现中，若读到一个运算符，或者遍历到字符串末尾，即认为是遍历到了数字末尾。处理完该数字后，更新 preSign 为当前遍历的字符。
    // 遍历完字符串 s 后，将栈中元素累加，即为该字符串表达式的值。
    // 时间复杂度：O(n)，其中 n 为字符串 s 的长度。需要遍历字符串 s 一次，计算表达式的值。
    // 空间复杂度：O(n)，其中 n 为字符串 s 的长度。空间复杂度主要取决于栈的空间，栈中的元素数量不超过 n。
    static class Solution {
        public int calculate(String s) {
            Deque<Integer> stack = new LinkedList<Integer>();
            char preSign = '+';
            int num = 0;
            int n = s.length();
            for (int i = 0; i < n; i++) {
                if (Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                }
                if ((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == n - 1) {
                    switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                    }
                    preSign = s.charAt(i);
                    num = 0;
                }
            }
            int answer = 0;
            while (!stack.isEmpty()) {
                answer += stack.pop();
            }
            return answer;
        }
    }
}
