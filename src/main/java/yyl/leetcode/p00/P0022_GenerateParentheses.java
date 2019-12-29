package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>括号生成</h3> 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。<br>
 * 例如，给出 n = 3，生成结果为：<br>
 * 
 * <pre>
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * </pre>
 * 
 * <br>
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.x Given n pairs of parentheses, write a
 * function to generate all combinations of well-formed parentheses.<br>
 * For example, given n = 3, a solution set is: <br>
 * 
 * <pre>
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 * </pre>
 */
// 给定一个非负整数n，生成n对括号的所有合法排列
public class P0022_GenerateParentheses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> result = solution.generateParenthesis(3);
        System.out.println(result);
    }

    static class Solution {

        /**
         * 生成所有可能的并且有效的括号组合
         * @param n 括号对个数
         * @return 括号组合列表
         */
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            generate(new char[n << 1], 0, n, n, result);
            return result;
        }

        /**
         * @param buffer 字符数组
         * @param pos 位置
         * @param leftNum 剩余左括号的个数
         * @param rightNum 剩余右括号的个数
         * @param result 结果列表
         */
        private void generate(char[] buffer, int pos, int leftNum, int rightNum, List<String> result) {
            if (leftNum == 0 && rightNum == 0) {
                result.add(new String(buffer));
                return;
            }
            if (leftNum > 0) {
                buffer[pos] = '(';
                generate(buffer, pos + 1, leftNum - 1, rightNum, result);
            }
            if (rightNum > 0 && rightNum > leftNum) {
                buffer[pos] = ')';
                generate(buffer, pos + 1, leftNum, rightNum - 1, result);
            }
        }
    }

}
