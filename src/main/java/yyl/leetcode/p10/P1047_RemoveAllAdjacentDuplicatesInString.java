package yyl.leetcode.p10;

import yyl.leetcode.util.Assert;

/**
 * <h3>删除字符串中的所有相邻重复项</h3><br>
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。<br>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。 <br>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。 <br>
 * 
 * <pre>
 * 示例：
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= S.length <= 20000 <br>
 * └ S 仅由小写英文字母组成。 <br>
 */
public class P1047_RemoveAllAdjacentDuplicatesInString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("ca", solution.removeDuplicates("abbaca"));
    }

    // 栈
    // 遍历字符串，将字符入栈，入栈前判断当前元素是否与栈顶元素相等，如果相等，出栈如果不相等，入栈。
    // 时间复杂度：O(n)，其中 n 是字符串的长度，需要遍历该字符串一次。
    // 空间复杂度：O(n)
    static class Solution {
        public String removeDuplicates(String S) {
            int n = S.length();
            char[] stack = new char[n];
            int top = -1;
            for (int i = 0; i < n; i++) {
                char ch = S.charAt(i);
                if (top != -1 && stack[top] == ch) {
                    top--;
                } else {
                    stack[++top] = ch;
                }
            }
            return new String(stack, 0, top + 1);
        }
    }
}
