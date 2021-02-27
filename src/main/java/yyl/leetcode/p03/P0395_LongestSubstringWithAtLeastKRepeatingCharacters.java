package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>至少有 K 个重复字符的最长子串</h3><br>
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * </pre>
 * 
 * 提示： <br>
 * 1 <= s.length <= 104 <br>
 * s 仅由小写英文字母组成 <br>
 * 1 <= k <= 105 <br>
 */
public class P0395_LongestSubstringWithAtLeastKRepeatingCharacters {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.longestSubstring("aaabb", 3));
        Assert.assertEquals(5, solution.longestSubstring("ababbc", 2));
        Assert.assertEquals(0, solution.longestSubstring("ababacb", 3));
        Assert.assertEquals(0, solution.longestSubstring("aabcabb", 3));
    }

    // 分治
    // 对于字符串 s ，如果存在某个字符 c ，它的出现次数大于 0 且小于 k ，则任何包含 ch 的子串都不可能满足要求。
    // 将字符串按照 ch 切分成若干段，则满足要求的最长子串一定出现在某个被切分的段内，而不能跨越一个或多个段。因此，可以考虑分治的方式求解本题。
    // 时间复杂度：O(N⋅∣Σ∣)，其中 N 为字符串的长度，Σ为字符集，本题中字符串仅包含小写字母，因此 ∣Σ∣=26。由于每次递归调用都会完全去除某个字符，因此递归深度最多为 ∣Σ∣
    // 空间复杂度：O(∣Σ∣^2)。递归的深度为 O(∣Σ∣)，每层递归需要开辟 O(∣Σ∣) 的额外空间。
    static class Solution {

        public int longestSubstring(String s, int k) {
            return dfs(s.toCharArray(), k, 0, s.length() - 1);
        }

        private int dfs(char[] chars, int k, int left, int right) {

            // 记录字符数量的数组
            int[] counts = new int[26];
            for (int i = left; i <= right; i++) {
                counts[chars[i] - 'a']++;
            }

            // 获得最为分割的字符（多个取第一个即可）
            char split = 0;
            for (int i = 0; i < 26; i++) {
                if (counts[i] > 0 && counts[i] < k) {
                    split = (char) (i + 'a');
                    break;
                }
            }
            // 没有找到分割的字符，整个子串都满足要求
            if (split == 0) {
                return right - left + 1;
            }

            // 分割
            int max = 0;
            for (int i = left; i <= right;) {
                // 优化：如果多个连续分割的字符的情况
                while (i <= right && chars[i] == split) {
                    i++;
                }
                // 到达末尾
                if (i > right) {
                    break;
                }
                int start = i;
                while (i <= right && chars[i] != split) {
                    i++;
                }
                int length = dfs(chars, k, start, i - 1);
                max = Math.max(max, length);
            }
            return max;
        }
    }
}
