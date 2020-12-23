package yyl.leetcode.p03;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>字符串中的第一个唯一字符</h3><br>
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。<br>
 * 
 * <pre>
 * 示例：
 * s = "leetcode"
 * 返回 0
 * 
 * s = "loveleetcode"
 * 返回 2
 * </pre>
 * 
 * 提示：你可以假定该字符串只包含小写字母。<br>
 */

public class P0387_FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(0, solution.firstUniqChar("leetcode"));
        Assert.assertEquals(2, solution.firstUniqChar("loveleetcode"));
    }

    // 使用哈希表存储频数
    // 对字符串进行两次遍历。
    // 在第一次遍历时，使用哈希映射统计出字符串中每个字符出现的次数。
    // 在第二次遍历时，只要遍历到了一个只出现一次的字符，那么就返回它的索引，否则在遍历结束后返回 −1。
    // 时间复杂度：O(n)，其中 n 是字符串 s 的长度。需要进行两次遍历。
    // 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，需要 O(∣Σ∣) 的空间存储哈希映射。在本题中 s 只包含小写字母，因此 ∣Σ∣≤26|。
    static class Solution {
        public int firstUniqChar(String s) {
            int[] frequency = new int[26];
            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                frequency[ch - 'a']++;
            }
            for (int i = 0; i < s.length(); ++i) {
                char ch = s.charAt(i);
                if (frequency[ch - 'a'] == 1) {
                    return i;
                }
            }
            return -1;
        }
    }

    // 使用哈希表存储索引
    // 对字符串进行遍历，使用哈希表存储字符的索引，如果已经存储过那么设置索引为 -1
    // 然后对哈希映射进行遍历，找出其中不为 −1 的最小值
    // 在第一次遍历时，使用哈希映射统计出字符串中每个字符出现的次数。
    // 在第二次遍历时，只要遍历到了一个只出现一次的字符，那么就返回它的索引，否则在遍历结束后返回 −1。
    // 时间复杂度：O(n)，其中 n 是字符串 s 的长度。需要进行两次遍历。 第一次遍历字符串的时间复杂度为 O(n)，第二次遍历哈希映射的时间复杂度为 O(∣Σ∣)，因此渐进意义下小于的时间复杂度为 O(n)。
    // 空间复杂度：O(∣Σ∣)，其中 Σ 是字符集，需要 O(∣Σ∣) 的空间存储哈希映射。在本题中 s 只包含小写字母，因此 ∣Σ∣≤26|。
    static class Solution1 {
        public int firstUniqChar(String s) {
            int[] position = new int[26];
            int n = s.length();
            Arrays.fill(position, Integer.MAX_VALUE);
            for (int i = 0; i < n; ++i) {
                int idx = s.charAt(i) - 'a';
                if (position[idx] == Integer.MAX_VALUE) {
                    position[idx] = i;
                } else {
                    position[idx] = -1;
                }
            }
            int answer = n;
            for (int i = 0; i < 26; ++i) {
                if (position[i] != -1 && position[i] < answer) {
                    answer = position[i];
                }
            }
            return answer == n ? -1 : answer;
        }
    }
}
