package yyl.leetcode.p00;

/**
 * <h3>有效的括号</b3><br>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。<br>
 * 有效字符串需满足：<br>
 * 左括号必须用相同类型的右括号闭合。<br>
 * 左括号必须以正确的顺序闭合。<br>
 * 注意空字符串可被认为是有效字符串。<br>
 * 
 * <pre>
 * 示例 1: 
 * 输入: "()"; 
 * 输出: true
 * 
 * 示例 2: 
 * 输入: "()[]{}"; 
 * 输出: true
 * 
 * 示例 3: 
 * 输入: "(]"; 
 * 输出: false
 * 
 * 示例 4: 
 * 输入: "([)]"; 
 * 输出: false
 * 
 * 示例 5: 
 * 输入: "{[]}"; 
 * 输出: true
 * </pre>
 * 
 * <br>
 * English original title：<br>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.<br>
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.<br>
 */
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

    // 栈
    // 判断括号的有效性可以使用「栈」这一数据结构来解决。
    // 对 s 进行遍历，当遇到一个左括号时，期望在后续的遍历中，有一个相同类型的右括号将其闭合。
    // 由于后遇到的左括号要先闭合，因此可以将这个左括号放入栈顶。
    // 当遇到一个右括号时，需要将一个相同类型的左括号闭合。 取出栈顶的左括号并判断它们是否是相同类型的括号。如果不是相同的类型，或者栈中并没有左括号，那么字符串 s 无效，返回 False。
    // 在遍历结束后，如果栈中没有左括号（栈为空），说明字符串 s 中的所有左括号闭合，返回 True，否则返回 False。
    // 注：有效字符串的长度一定为偶数，因此如果字符串的长度为奇数，可以直接返回 False，省去后续的遍历判断过程。
    // 复杂度分析
    // 时间复杂度：O(n)，其中 n 是字符串 s 的长度。
    // 空间复杂度：O(n)，栈中的字符数量为 O(n)。
    static class Solution {
        public boolean isValid(String s) {
            int n = s.length();
            if (n % 2 == 1) {
                return false;
            }
            // 使用数组模拟一个栈
            char[] stack = new char[n];
            int k = 0;
            for (int i = 0; i < n; i++) {
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
