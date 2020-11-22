package yyl.leetcode.p02;

import java.util.HashMap;
import java.util.Map;

import yyl.leetcode.util.Assert;

/**
 * <h3>有效的字母异位词</h3><br>
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * </pre>
 * 
 * 说明: 你可以假设字符串只包含小写字母。<br>
 * 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？<br>
 */
public class P0242_ValidAnagram {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.isAnagram("anagram", "nagaram"));
        Assert.assertFalse(solution.isAnagram("rat", "car"));
    }

    // 哈希表
    // 题目 t 是 s 的异位词等价于「两个字符串中字符出现的种类和次数均相等」。
    // 由于字符串只包含 26 个小写字母，因此可以维护一个长度为 26 的频次数组 table，先遍历记录字符串 s 中字符出现的频次，然后遍历字符串 t，减去 table中对应的频次，如果出现 table[i]<0，则说明 t包含一个不在 s中的额外字符，返回 false。
    // 时间复杂度：O(n) ，其中 n为s 的长度。
    // 空间复杂度：O(S)，其中 S 为字符集大小，此处 S=26
    static class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            int[] table = new int[26];
            for (char c : s.toCharArray()) {
                table[c - 'a']++;
            }
            for (char c : t.toCharArray()) {
                if (--table[c - 'a'] < 0) {
                    return false;
                }
            }
            return true;
        }
    }

    // 进阶：用哈希表维护对应字符的频次，支持 unicode
    static class Solution1 {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            Map<Character, Integer> table = new HashMap<Character, Integer>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                table.put(ch, table.getOrDefault(ch, 0) + 1);
            }
            for (int i = 0; i < t.length(); i++) {
                char ch = t.charAt(i);
                table.put(ch, table.getOrDefault(ch, 0) - 1);
                if (table.get(ch) < 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
