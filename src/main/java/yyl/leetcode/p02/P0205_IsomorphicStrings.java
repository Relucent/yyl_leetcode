package yyl.leetcode.p02;

import yyl.leetcode.util.Assert;

/**
 * <h3>同构字符串</h3><br>
 * 给定两个字符串 s 和 t，判断它们是否是同构的。<br>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。<br>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。<br>
 *
 * <pre>
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 *
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * </pre>
 * <p>
 * 说明: 你可以假设 s 和 t 具有相同的长度。
 */
public class P0205_IsomorphicStrings {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.isIsomorphic("egg", "add"));
        Assert.assertFalse(solution.isIsomorphic("foo", "bar"));
        Assert.assertTrue(solution.isIsomorphic("paper", "title"));
        Assert.assertTrue(solution.isIsomorphic("13", "42"));
    }

    // 哈希表
    // 需要判断 s 和 t 每个位置上的字符是否都一一对应，即 s 的任意一个字符被 t 中唯一的字符对应，同时 t 的任意一个字符被 s 中唯一的字符对应。这也被称为「双射」的关系。
    // 维护两张哈希表：
    // 第一张哈希表 s2t 以 s 中字符为键，映射至 t 的字符为值
    // 第二张哈希表 s2t 以 t 中字符为键，映射至 s 的字符为值
    // 从左至右遍历两个字符串的字符，不断更新两张哈希表，如果出现冲突时说明两个字符串无法构成同构，返回 false。
    // 如果遍历结束没有出现冲突，则表明两个字符串是同构的，返回 true即可。
    // 时间复杂度：O(n)，其中 n 为字符串的长度。只需同时遍历一遍字符串 s 和 t 即可。
    // 空间复杂度：O(∣Σ∣)，其中 Σ 是字符串的字符集。哈希表存储字符的空间取决于字符串的字符集大小，最坏情况下每个字符均不相同，需要 O(∣Σ∣) 的空间。
    static class Solution {
        public boolean isIsomorphic(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int n = s.length();
            char[] s2t = new char[128];
            char[] t2s = new char[128];
            for (int i = 0; i < n; i++) {
                char sc = s.charAt(i);
                char tc = t.charAt(i);
                char vtc = s2t[sc];
                if (vtc == 0) {
                    s2t[sc] = tc;
                } else if (vtc != tc) {
                    return false;
                }
                char vsc = t2s[tc];
                if (vsc == 0) {
                    t2s[tc] = sc;
                } else if (vsc != sc) {
                    return false;
                }
            }
            return true;
        }
    }
}
