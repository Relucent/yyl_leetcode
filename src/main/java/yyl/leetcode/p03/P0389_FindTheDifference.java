package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>找不同</h3><br>
 * 给定两个字符串 s 和 t，它们只包含小写字母。<br>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。<br>
 * 请找出在 t 中被添加的字母。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 * 
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 * 
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 * 
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 * </pre>
 * 
 * 提示：<br>
 * ├ 0 <= s.length <= 1000<br>
 * ├ t.length == s.length + 1<br>
 * └ s 和 t 只包含小写字母<br>
 */
public class P0389_FindTheDifference {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals('e', solution.findTheDifference("abcd", "abcde"));
        Assert.assertEquals('y', solution.findTheDifference("", "y"));
        Assert.assertEquals('a', solution.findTheDifference("a", "aa"));
        Assert.assertEquals('a', solution.findTheDifference("ae", "aea"));
        Assert.assertEquals('d', solution.findTheDifference("abc", "cdba"));
    }

    // 计数
    // 首先遍历字符串 s，对其中的每个字符都将计数值加 1 ；然后遍历字符串 t ，对其中的每个字符都将计数值减 1。
    // 当发现某个字符计数值为负数时，说明该字符在字符串 t 中出现的次数大于在字符串 s 中出现的次数，此该字符为被添加的字符。
    // 时间复杂度：O(N) ，其中 N 为字符串的长度。
    // 空间复杂度：O(N)。
    static class Solution {
        public char findTheDifference(String s, String t) {
            int[] hash = new int[26];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                hash[c - 'a']++;
            }
            for (int i = 0; i < t.length(); i++) {
                char c = t.charAt(i);
                if ((--hash[c - 'a']) < 0) {
                    return c;
                }
            }
            return 0;
        }
    }

    // 求和
    // 将字符串 s 中每个字符的 ASCII 码的值求和，得到 As；对字符串 t 同样的方法得到 At。两者的差值 At-As 即代表了被添加的字符。
    // 时间复杂度：O(N) ，其中 N 为字符串的长度。
    // 空间复杂度：O(1)。
    static class Solution1 {
        public char findTheDifference(String s, String t) {
            long as = 0;
            long at = 0;
            for (int i = 0; i < s.length(); ++i) {
                as += s.charAt(i);
            }
            for (int i = 0; i < t.length(); ++i) {
                at += t.charAt(i);
            }
            return (char) (at - as);
        }
    }

    // 求和(优化一次循环)
    // 时间复杂度：O(N) ，其中 N 为字符串的长度。
    // 空间复杂度：O(1)。
    static class Solution2 {
        public char findTheDifference(String s, String t) {
            long as = 0;
            long at = 0;
            for (int i = 0; i < s.length(); i++) {
                as += s.charAt(i);
                at += t.charAt(i);
            }
            at += t.charAt(t.length() - 1);
            return (char) (at - as);
        }
    }

    // 位运算
    // 时间复杂度：O(N) ，其中 N 为字符串的长度。
    // 空间复杂度：O(1)。
    static class Solution3 {
        public char findTheDifference(String s, String t) {
            int result = 0;
            for (int i = 0; i < s.length(); i++) {
                result ^= s.charAt(i);
                result ^= t.charAt(i);
            }
            result ^= t.charAt(t.length() - 1);
            return (char) result;
        }
    }
}
