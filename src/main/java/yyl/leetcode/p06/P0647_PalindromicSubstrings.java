package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>回文子串</h3><br>
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。<br>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * </pre>
 * 
 * 提示： 输入的字符串长度不会超过 1000 。
 */
public class P0647_PalindromicSubstrings {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.countSubstrings("abc"));
        Assert.assertEquals(6, solution.countSubstrings("aaa"));
        Assert.assertEquals(77, solution.countSubstrings("dnncbwoneinoplypwgbwktmvkoimcooyiwirgbxlcttgteqthcvyoueyftiwgwwxvxvg"));
    }

    // 中心拓展法
    // 遍历字符串字符，以遍历到的字符为中心两边做扩展，得出可以扩展的回文子串数加起来，单个字符也算作回文
    // 另外需要考虑中心在两个字符之间的情况
    // 时间复杂度：O(n^2)
    // 空间复杂度：O(1)
    static class Solution {
        public int countSubstrings(String s) {
            int answer = 0;
            int n = s.length();
            for (int k = 0; k < n; k++) {
                for (int i = k, j = k; i >= 0 && j < n && s.charAt(i) == s.charAt(j); i--, j++) {
                    answer++;
                }
                for (int i = k, j = k + 1; i >= 0 && j < n && s.charAt(i) == s.charAt(j); i--, j++) {
                    answer++;
                }
            }
            return answer;
        }
    }
}
