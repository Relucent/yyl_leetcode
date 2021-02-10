package yyl.leetcode.p05;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>字符串的排列</h3><br>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。<br>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。<br>
 * 
 * <pre>
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * </pre>
 * 
 * 注意：<br>
 * ├ 输入的字符串只包含小写字母 <br>
 * └ 两个字符串的长度都在 [1, 10,000] 之间 <br>
 */

public class P0567_PermutationInString {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.checkInclusion("ab", "eidbaooo"));
        Assert.assertFalse(solution.checkInclusion("ab", "eidboaoo"));
        Assert.assertFalse(solution.checkInclusion("ab", "a"));
    }

    // 滑动窗口
    // 由于排列不会改变字符串中每个字符的个数，所以只有当两个字符串每个字符的个数均相等时，一个字符串才是另一个字符串的排列。
    // 根据这一性质，记 s1 的长度为 m ，我们可以遍历 s2 中的每个长度为 m 的子串，判断子串和 s1 中每个字符的个数是否相等，若相等则说明该子串是 s1 的一个排列。
    // ├ 由于需要遍历的子串长度均为 m，我们可以使用一个固定长度为 m 的滑动窗口来维护：
    // ├ 滑动窗口每向右滑动一次，就多统计一次进入窗口的字符，少统计一次离开窗口的字符。
    // └ 判断 count1 与 count2 是否相等，若相等则意味着 s1 的排列之一是 s2 的子串。
    // 时间复杂度：O(n*∣Σ∣))。其中 N 为字符串的长度，∣Σ∣是字符集大小，本题是常数26。
    // 空间复杂度：O(∣Σ∣)。
    static class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int m = s1.length();
            int n = s2.length();
            if (m > n) {
                return false;
            }
            int[] count1 = new int[26];
            int[] count2 = new int[26];
            for (int i = 0; i < m; i++) {
                count1[s1.charAt(i) - 'a']++;
                count2[s2.charAt(i) - 'a']++;
            }
            if (Arrays.equals(count1, count2)) {
                return true;
            }
            for (int i = m; i < n; i++) {
                count2[s2.charAt(i - m) - 'a']--;
                count2[s2.charAt(i) - 'a']++;
                if (Arrays.equals(count1, count2)) {
                    return true;
                }
            }
            return false;
        }
    }
}
