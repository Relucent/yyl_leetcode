package yyl.leetcode.p00;

/**
 * <h3>最长有效括号</h3><br>
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 <br>
 * 
 * <pre>
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * </pre>
 */
public class P0032_LongestValidParentheses {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        System.out.println(solution.longestValidParentheses("(()"));// 2
        System.out.println(solution.longestValidParentheses(")()())"));// 4
        System.out.println(solution.longestValidParentheses("()(()"));// 2
        System.out.println(solution.longestValidParentheses("(()()"));// 4
        System.out.println(solution.longestValidParentheses("()"));// 2
        System.out.println(solution.longestValidParentheses(""));// 0
        System.out.println(solution.longestValidParentheses("("));// 0
    }

    // 栈
    // 1、对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
    // 2、对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
    // 3、如果栈为空，说明当前的右括号为没有被匹配的右括号，将其下标放入栈中来更新「最后一个没有被匹配的右括号的下标」
    // 4、如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
    // 5、从前往后遍历字符串并更新答案即可
    // 时间复杂度： O(n)，n 是给定字符串的长度。我们只需要遍历字符串一次即可。
    // 空间复杂度： O(n)。栈的大小在最坏情况下会达到 n，因此空间复杂度为 O(n)。
    static class Solution {
        public int longestValidParentheses(String s) {
            // 用数组模拟栈
            int[] stack = new int[s.length() + 1];
            int top = -1;
            // 返回的结果
            int result = 0;
            // 在一开始的时候往栈中放入一个值为 −1的元素，表示最后一个没有被匹配的右括号的下标
            stack[++top] = -1;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    // 将它的下标放入栈中
                    stack[++top] = i;
                }
                // c==')'
                else {
                    // 栈只有顶一个元素(最后一个没有被匹配的右括号的下标)
                    if (top == 0) {
                        // 更新最后一个没有被匹配的右括号的下标
                        stack[top = 0] = i;
                    } else {
                        // 弹出栈顶元素表示匹配了当前右括号
                        top--;
                        // 当前右括号的下标减去栈顶元素(最后一个没有被匹配的右括号的下标)即为子串长度，然后记录最大的
                        result = Math.max(result, i - stack[top]);
                    }
                }
            }
            return result;
        }
    }

    // 计数器（贪心）
    // 利用两个计数器 left和 right，我们从左到右遍历字符串，对于遇到的每个 ‘(’，增加 left计数器，对于遇到的每个 ‘)’ 增加 right计数器。
    // 1、每当 计数器相等时（left == right），计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。
    // 2、当 right 计数器比 left 计数器大时，将 left 和 right right 计数器同时变回 0。
    // 因为存在 遍历到结尾‘)’比‘(’多的情况，例如 ‘(()’的情况，这时候达不到left == right，所以需要再增加一个右往左遍历。
    // 1、当 left 计数器比 right 计数器大时，将 left 和 right right 计数器同时变回 0。
    // 2、每当 计数器相等时（left == right），计算当前有效字符串的长度，并且记录目前为止找到的最长子字符串。
    // 这样能涵盖所有情况从而求解出答案
    // 时间复杂度： O(n)，其中 n为字符串长度。需要正反遍历两边字符串。
    // 空间复杂度： O(1)。常数空间存放若干变量。
    static class Solution1 {
        public int longestValidParentheses(String s) {
            int result = 0;
            for (int left = 0, right = 0, i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    result = Math.max(result, left * 2);
                } else if (right > left) {
                    left = right = 0;
                }
            }
            for (int left = 0, right = 0, i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == '(') {
                    left++;
                } else {
                    right++;
                }
                if (left == right) {
                    result = Math.max(result, left * 2);
                } else if (left > right) {
                    left = right = 0;
                }
            }
            return result;
        }
    }
}
