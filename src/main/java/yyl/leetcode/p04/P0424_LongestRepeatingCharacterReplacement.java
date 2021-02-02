package yyl.leetcode.p04;

import yyl.leetcode.util.Assert;

/**
 * <h3>替换后的最长重复字符</h3><br>
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。 <br>
 * 注意：字符串长度 和 k 不会超过 104。 <br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * 
 * 示例 2：
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 * </pre>
 */
public class P0424_LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(4, solution.characterReplacement("ABAB", 2));
        Assert.assertEquals(4, solution.characterReplacement("AABABBA", 1));
    }

    // 滑动窗口（双指针）
    // 枚举字符串中的每一个位置作为右端点，然后找到其最远的左端点的位置，满足该区间内除了出现次数最多的那一类字符之外，剩余的字符（即非最长重复字符）数量不超过 k 个。
    // 使用双指针维护这些区间，每次右指针右移，如果区间仍然满足条件，那么左指针不移动，否则左指针至多右移一格，保证区间长度不减小。
    // 本题中由于字符串中仅包含大写字母，可以使用一个长度为 26 的数组维护区间中每一个字符的出现次数。
    // 时间复杂度：O(n)，其中 n 是字符串的长度。只需要遍历该字符串一次。
    // 空间复杂度：O(∣Σ∣)，其中 ∣Σ∣是字符集的大小。需要存储每个大写英文字母的出现次数。
    static class Solution {
        public int characterReplacement(String s, int k) {
            // 使用哈希表维护区间中每一个字符的出现次数
            int[] hash = new int[26];
            int answer = 0;
            for (int left = 0, right = 0, maxCount = 0; right < s.length(); right++) {
                maxCount = Math.max(maxCount, ++hash[s.charAt(right) - 'A']);
                // 替换 k 个字符，不够区间内字母保持重复
                while (right - left + 1 - maxCount > k) {
                    hash[s.charAt(left) - 'A']--;
                    left++;
                }
                answer = Math.max(right - left + 1, answer);
            }
            return answer;
        }
    }
}
