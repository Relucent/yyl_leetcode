package yyl.leetcode.p010;

/**
 * <h3>正则表达式匹配</h3> <br>
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 <br>
 * '.' 匹配任意单个字符<br>
 * '*' 匹配零个或多个前面的那一个元素<br>
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。<br>
 * 说明:<br>
 * s 可能为空，且只包含从 a-z 的小写字母。<br>
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。<br>
 * 示例 1:<br>
 * 输入: s = "aa" p = "a" <br>
 * 输出: false <br>
 * 解释: "a" 无法匹配 "aa" 整个字符串。<br>
 * 示例 2:<br>
 * 输入: s = "aa" p = "a*"<br>
 * 输出: true<br>
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。<br>
 * 示例 3:<br>
 * 输入: s = "ab" p = ".*"<br>
 * 输出: true<br>
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。<br>
 * 示例 4:<br>
 * 输入: s = "aab" p = "c*a*b"<br>
 * 输出: true<br>
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。<br>
 * 示例 5:<br>
 * 输入: s = "mississippi" p = "mis*is*p*."<br>
 * 输出: false <br>
 * <br>
 * Implement regular expression matching with support for '.' and '*'.<br>
 * '.' Matches any single character.<br>
 * '*' Matches zero or more of the preceding element.<br>
 * The matching should cover the entire input string (not partial).<br>
 * The function prototype should be:<br>
 * bool isMatch(const char *s, const char *p)<br>
 * Some examples:<br>
 * isMatch("aa","a") → false<br>
 * isMatch("aa","aa") → true<br>
 * isMatch("aaa","aa") → false<br>
 * isMatch("aa", "a*") → true<br>
 * isMatch("aa", ".*") → true<br>
 * isMatch("ab", ".*") → true<br>
 * isMatch("aab", "c*a*b") → true<br>
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        System.out.println(solution.isMatch("abcdefg", "abcdefg"));// T
        System.out.println(solution.isMatch("abcdefg", "ab.*defg"));// T
        System.out.println(solution.isMatch("abcd", "a*.c"));// F
        System.out.println(solution.isMatch("aaabbbccc", "aaa.**c"));// F
        System.out.println(solution.isMatch("aaaa", "a*"));// T
        System.out.println(solution.isMatch("abc", "..c"));// T
    }

    // 动态规划算法
    // 时间复杂度 O(SP)，S表示字符串长度，P表示表达式长度
    // 空间复杂度 O(SP)
    static class Solution {
        public boolean isMatch(String s, String p) {
            int m = s.length();
            int n = p.length();

            // dp(i,j) 表示s[i:] 和 p[j:]是否能匹配。
            boolean[][] dp = new boolean[m + 1][n + 1];
            // 起始位置，认为是匹配的
            dp[0][0] = true;
            for (int i = 0; i <= m; ++i) {
                for (int j = 1; j <= n; ++j) {
                    // 如果表达式字符为“*”
                    if (j > 1 && p.charAt(j - 1) == '*') {
                        // 因为是 * 所以表达式这个位置可以忽略
                        dp[i][j] = dp[i][j - 2] //
                                || (i > 0 && isSame(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j]);
                    } else {
                        // 如果之前的匹配，并且当前也匹配，那么这个位置就是匹配的
                        dp[i][j] = i > 0 //
                                && dp[i - 1][j - 1]//
                                && isSame(s.charAt(i - 1), p.charAt(j - 1));
                    }
                }
            }
            return dp[m][n];
        }

        // 字符是否相同(如果表达式字符为“.”，则可以匹配任意单个字符)
        private boolean isSame(char c, char p) {
            return p == '.' || c == p;
        }
    }

    // 递归求解
    // 如果没有星号，只需要从左到右检查匹配串 s 是否能匹配模式串 p 的每一个字符。
    // 当模式串中有星号时，我们需要检查匹配串 s 中的不同后缀，以判断它们是否能匹配模式串剩余的部分。
    static class Solution2 {

        public boolean isMatch(String s, String p) {
            return matchhere(s, p, 0, 0);
        }

        private boolean matchhere(String s, String p, int i, int j) {
            // 匹配到结尾
            if (j == p.length()) {
                return i == s.length();
            }
            boolean isMatchFirst = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');

            // 如果下个匹配字符是星号
            if (j < p.length() - 1 && p.charAt(j + 1) == '*') {
                // 如果下个模式是星号， 忽略模式串中这一部分，或者从继续从字符串下个字符开始匹配
                return matchhere(s, p, i, j + 2)//
                        || (isMatchFirst && matchhere(s, p, i + 1, j));
            }
            // 如果当前是匹配的 && 继续匹配剩余的
            return isMatchFirst && matchhere(s, p, i + 1, j + 1);
        }
    }
}
