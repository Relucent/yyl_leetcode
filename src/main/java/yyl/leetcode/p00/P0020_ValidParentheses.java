package yyl.leetcode.p00;

/**
 * <h3>有效的括号</b3><br>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。<br>
 * 有效字符串需满足：<br>
 * 左括号必须用相同类型的右括号闭合。<br>
 * 左括号必须以正确的顺序闭合。<br>
 * 注意空字符串可被认为是有效字符串。<br>
 * 示例 1: 输入: "()"; 输出: true<br>
 * 示例 2: 输入: "()[]{}"; 输出: true<br>
 * 示例 3: 输入: "(]"; 输出: false<br>
 * 示例 4: 输入: "([)]"; 输出: false<br>
 * 示例 5: 输入: "{[]}"; 输出: true<br>
 * <br>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.<br>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.<br>
 */
// 给一串只包含有'(', ')', '{', '}', '[' 和']',的字符串，要求判定是否是合法的。
public class P0020_ValidParentheses {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()[]{}"));// true
        System.out.println(solution.isValid("(()"));// false
        System.out.println(solution.isValid("(]"));// false
        System.out.println(solution.isValid("(1)"));// false
        System.out.println(solution.isValid("[[({})]]"));// true
        System.out.println(solution.isValid("{}][}}{[))){}{}){(}]))})[({"));// false
        System.out.println(solution.isValid("[(({})}]")); // false
    }

    static class Solution {
        public boolean isValid(String s) {
            // 使用数组模拟一个栈
            char[] stack = new char[s.length()];
            int k = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '(':
                    case '[':
                    case '{':
                        // 压入栈
                        stack[k++] = c;
                        break;
                    case ')':
                        // 弹出栈
                        if (k == 0 || stack[--k] != '(') {
                            return false;
                        }
                        break;
                    case ']':
                        if (k == 0 || stack[--k] != '[') {
                            return false;
                        }
                        break;
                    case '}':
                        if (k == 0 || stack[--k] != '{') {
                            return false;
                        }
                        break;
                    default:
                        return false;
                }
            }
            return k == 0;
        }
    }
}
