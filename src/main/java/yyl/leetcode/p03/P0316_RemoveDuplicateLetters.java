package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>去除重复字母</h3><br>
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= s.length <= 104 <br>
 * └ s 由小写英文字母组成 <br>
 */

public class P0316_RemoveDuplicateLetters {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("abc", solution.removeDuplicateLetters("bcabc"));
        Assert.assertEquals("acdb", solution.removeDuplicateLetters("cbacdcbc"));
    }

    // 贪心 + 栈
    // 字典序：是指按照单词在字典出现顺序比较两个字符串，例如：'abc'的字典序在'acdb'前面。
    // 思路
    // ├ 如果一个字母只出现一次，那么这个字母是必须选择的
    // └ 按照字母升序排列的字符串，肯定是字典序最小的
    // 思考
    // ├ 问题：给定一个字符串 s ，如何去掉其中的一个字符 c，使得得到的字符串字典序最小呢？
    // └ 答案：找出最小的满足 s[i]>s[i+1] 的下标 i，并去除字符 s[i]。
    // 算法
    // ├ 计算每个字符出现的次数
    // ├ 遍历字符串，使用栈来维护字符。
    // ├ 如果栈顶字符大于当前字符 s[i] （满足 s[i]>s[i+1]），故应当被去除。去除后，新的栈顶字符就与 s[i] 相邻了，继续比较新的栈顶字符与 s[i] 的大小。 重复上述操作，直到栈为空或者栈顶字符不大于 s[i]。
    // ├ 在考虑字符 s[i] 时，如果它已经存在于栈中，则不能加入字符 s[i]。为此，需要记录每个字符是否出现在栈中。
    // └ 在弹出栈顶字符时，如果字符串在后面的位置上再也没有这一字符，则不能弹出栈顶字符。为此，需要记录每个字符的剩余数量，当这个值为 0 时，就不能弹出栈顶字符了。
    // 时间复杂度：O(N)，其中 N 为字符串长度。代码中虽然有双重循环，但是每个字符至多只会入栈、出栈各一次。
    // 空间复杂度：O(∣Σ∣)，其中 ∣Σ∣ 为字符集合，本题中字符均为小写字母，所以 ∣Σ∣=26|。由于栈中的字符不能重复，因此栈中最多只能有 ∣Σ∣个字符，另外需要维护两个数组，分别记录每个字符是否出现在栈中以及每个字符的剩余数量。
    static class Solution {
        public String removeDuplicateLetters(String s) {
            int n = s.length();
            boolean[] vis = new boolean[26];
            int[] counts = new int[26];
            for (int i = 0; i < n; i++) {
                counts[s.charAt(i) - 'a']++;
            }
            char[] stack = new char[26];
            int top = -1;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (!vis[c - 'a']) {
                    T: while (top >= 0 && stack[top] > c) {
                        if (counts[stack[top] - 'a'] > 0) {
                            vis[stack[top] - 'a'] = false;
                            top--;
                        } else {
                            break T;
                        }
                    }
                    vis[c - 'a'] = true;
                    stack[++top] = c;
                }
                counts[c - 'a']--;
            }
            return new String(stack, 0, top + 1);
        }
    }
}
