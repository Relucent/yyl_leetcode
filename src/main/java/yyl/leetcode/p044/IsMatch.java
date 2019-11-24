package yyl.leetcode.p044;


/**
 * <h3>通配符匹配</h3><br>
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。<br>
 * '?' 可以匹配任何单个字符。<br>
 * '*' 可以匹配任意字符串（包括空字符串）。<br>
 * 两个字符串完全匹配才算匹配成功。<br>
 * 说明:<br>
 * s 可能为空，且只包含从 a-z 的小写字母。<br>
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。<br>
 * 示例 1:<br>
 * 输入: s = "aa",p = "a"; 输出: false<br>
 * 解释: "a" 无法匹配 "aa" 整个字符串。<br>
 * 示例 2:<br>
 * 输入: s = "aa", p = "*"; 输出: true<br>
 * 解释: '*' 可以匹配任意字符串。<br>
 * 示例 3:<br>
 * 输入: s = "cb", p = "?a"; 输出: false<br>
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。<br>
 * 示例 4:<br>
 * 输入: s = "adceb", p = "*a*b"; 输出: true<br>
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".<br>
 * 示例 5:<br>
 * 输入: s = "acdcb", p = "a*c?b"; 输入: false<br>
 */
public class IsMatch {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isMatch("aa", "a"));// false
        System.out.println(solution.isMatch("aa", "*"));// true
        System.out.println(solution.isMatch("cb", "?a"));// false
        System.out.println(solution.isMatch("adceb", "*a*b"));// true
        System.out.println(solution.isMatch("acdcb", "a*c?b"));// false
    }

    static class Solution {

        // 这个算法最差情况的时间复杂度是 O(M*N) 最好的情况是 O(M)
        public boolean isMatch(String s, String p) {
            int si = 0;
            int pi = 0;

            int sj = -1;
            int pj = -1;

            while (si < s.length()) {

                // 一对一匹配，两个字符相等(或者模式字符为?)，两指针同时后移
                if (pi < p.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
                    si++;
                    pi++;
                }
                // 模式字符为* ,将*先当做空字符串来匹配
                // 记录 * 的位置，记录当前字符串和字符模式的位置，p后移
                else if (pi < p.length() && p.charAt(pi) == '*') {
                    sj = si;
                    pj = pi;
                    pi++;
                }
                // 当前字符不匹配，并且之前也没有 *可以使用
                else if (pj == -1) {
                    return false;
                }
                // {重点}
                // 当前字符不匹配，但是之前有*可用，尝试使用之前的 *来匹配一个字符
                // p 回到之前记录的*的位置，然后后移 ( pi= pj + 1)
                // s 回到之前记录*时候，s的位置，然后后移(si=sj), 再记录新的位置 sj=si
                // p 回到 *的下一个位置
                else {
                    sj = (si = sj + 1);
                    pi = pj + 1;
                }
            }

            // 如果末尾有多余 * 可以排除掉
            while (pi < p.length() && p.charAt(pi) == '*') {
                pi++;
            }

            // 判断字符模式是完全匹配了(到末尾了)
            return pi == p.length();
        }
    }
}
