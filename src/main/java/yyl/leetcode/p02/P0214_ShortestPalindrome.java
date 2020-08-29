package yyl.leetcode.p02;

import yyl.leetcode.util.Assert;

/**
 * <h3>最短回文串</h3><br>
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 
 * 示例 2:
 * 输入: "abcd"
 * 输出: "dcbabcd"
 * </pre>
 */
public class P0214_ShortestPalindrome {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("aaacecaaa", solution.shortestPalindrome("aacecaaa"));
        Assert.assertEquals("dcbabcd", solution.shortestPalindrome("abcd"));
    }

    // 暴力法
    // 找出字符串s中所有以s[0]为起点的回文字符串中最长的那一个回文字符串，那剩余的元素逆序拼接至开头即可。
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(n)
    static class Solution {
        public String shortestPalindrome(String s) {
            int n = s.length();
            if (n <= 1) {
                return s;
            }
            char[] chars = s.toCharArray();
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (isPalindrome(chars, 0, i)) {
                    index = i;
                }
            }
            StringBuilder builder = new StringBuilder();
            for (int i = n - 1; i > index; i--) {
                builder.append(chars[i]);
            }
            return builder.append(s).toString();
        }

        private boolean isPalindrome(char[] chars, int left, int right) {
            while (left < right) {
                if (chars[left++] != chars[right--]) {
                    return false;
                }
            }
            return true;
        }
    }
}
