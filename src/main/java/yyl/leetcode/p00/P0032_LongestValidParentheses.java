package yyl.leetcode.p00;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 <br>
 * 示例 1:<br>
 * 输入: "(()"<br>
 * 输出: 2<br>
 * 解释: 最长有效括号子串为 "()"<br>
 * 示例 2:<br>
 * 输入: ")()())"<br>
 * 输出: 4<br>
 * 解释: 最长有效括号子串为 "()()"<br>
 */
public class P0032_LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestValidParentheses("(()"));// 2
        System.out.println(solution.longestValidParentheses(")()())"));// 4
        System.out.println(solution.longestValidParentheses("()(()"));// 2
    }

    static class Solution {
        public int longestValidParentheses(String s) {
            int[] stack = new int[s.length()];
            int offset = 0;
            int result = 0;
            int top = -1;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    top++;
                    stack[top] = i;
                }
                // c==')'
                else {
                    if (top == -1) {
                        offset = i + 1;
                    } else {
                        top--;
                        result = Math.max(result, top == -1 ? i - offset + 1 : i - stack[top]);
                    }
                }
            }
            return result;
        }
    }
}
