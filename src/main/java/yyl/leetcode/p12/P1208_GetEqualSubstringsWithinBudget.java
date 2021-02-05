package yyl.leetcode.p12;

import yyl.leetcode.util.Assert;

/**
 * <h3>尽可能使字符串相等</h3><br>
 * 给你两个长度相同的字符串，s 和 t。<br>
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。<br>
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。<br>
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。<br>
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "abcd", t = "bcdf", cost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 * 
 * 示例 2：
 * 输入：s = "abcd", t = "cdef", cost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 * 
 * 示例 3：
 * 输入：s = "abcd", t = "acde", cost = 0
 * 输出：1
 * 解释：你无法作出任何改动，所以最大长度为 1。
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= s.length, t.length <= 10^5 <br>
 * ├ 0 <= maxCost <= 10^6 <br>
 * └ s 和 t 都只含小写英文字母。 <br>
 */

public class P1208_GetEqualSubstringsWithinBudget {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.equalSubstring("abcd", "bcdf", 3));
        Assert.assertEquals(1, solution.equalSubstring("abcd", "cdef", 3));
        Assert.assertEquals(1, solution.equalSubstring("abcd", "acde", 0));
    }

    // 滑动窗口（双指针）
    // 题目分析：题目可以理解为，经过转换后，相同的最长的子序列长度
    // 对于每一对下标相等的字符，s[i]和t[i]，把它们转化成相等的 cost 是已知的，cost = |s[i] - t[i]|，
    // 所以可以直接生成一个数组 costs， costs[i] 就表示把 s[i] 和 t[i] 转化成相等的 cost，
    // 接着问题就转化为： 在一个数组中，在连续子数组的和小于等于 maxCost 的情况下，找到最长的连续子数组长度。
    // 因此可以用滑动窗口解题。
    // 时间复杂度：O(n) ，其中 n 是字符串的长度。遍历数组的过程中，两个指针的移动次数都不会超过 n 次
    // 空间复杂度：O(n) ，其中 n 是字符串的长度。需要创建长度为 n 的数组 costs。
    static class Solution {
        public int equalSubstring(String s, String t, int maxCost) {
            int n = s.length();
            int[] costs = new int[n];
            for (int i = 0; i < n; i++) {
                costs[i] = Math.abs(s.charAt(i) - t.charAt(i));
            }
            int answer = 0;
            for (int left = 0, rigth = 0, sum = 0; rigth < n; rigth++) {
                sum += costs[rigth];
                while (sum > maxCost) {
                    sum -= costs[left++];
                }
                answer = Math.max(answer, rigth - left + 1);
            }
            return answer;
        }
    }
}
