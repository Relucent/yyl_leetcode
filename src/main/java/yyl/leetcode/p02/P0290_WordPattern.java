package yyl.leetcode.p02;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import yyl.leetcode.util.Assert;

/**
 * <h3>单词规律</h3><br>
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。<br>
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。<br>
 * 
 * <pre>
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 
 * 示例 4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * </pre>
 * 
 * 说明: 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 */
public class P0290_WordPattern {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.wordPattern("abba", "dog cat cat dog"));
        Assert.assertFalse(solution.wordPattern("abba", "dog cat cat fish"));
        Assert.assertFalse(solution.wordPattern("aaaa", "dog cat cat dog"));
        Assert.assertFalse(solution.wordPattern("abba", "dog dog dog dog"));
        Assert.assertFalse(solution.wordPattern("aaaa", "dog dog dog dog dog"));
    }

    // 哈希表
    // 需要判断字符与字符串之间是否恰好一一对应。即任意一个字符都对应着唯一的字符串，任意一个字符串也只被唯一的一个字符对应。
    // 注：在集合论中，这种关系被称为「双射」。
    // 可以利用哈希表记录每一个字符对应的字符串，以及每一个字符串对应的字符。然后枚举每一对字符与字符串的配对过程，不断更新哈希表，如果发生了冲突，则说明给定的输入不满足双射关系。
    // 时间复杂度：O(n+m)，其中 n 为 pattern 的长度，m 为 s 的长度。插入和查询哈希表的均摊时间复杂度均为 O(n+m)。每一个字符至多只被遍历一次。
    // 空间复杂度：O(n+m)，其中 n 为 pattern 的长度，m 为 s 的长度 的长度。最坏情况下，需要存储 pattern 中的每一个字符和 s 中的每一个字符串。
    static class Solution {
        public boolean wordPattern(String pattern, String s) {
            String[] fragments = s.split(" ");
            if (pattern.length() != fragments.length) {
                return false;
            }
            Map<String, Character> hash = new HashMap<>();
            Set<Character> use = new HashSet<>();
            for (int i = 0; i < pattern.length(); i++) {
                String fragment = fragments[i];
                Character symbol = pattern.charAt(i);
                Character match = hash.get(fragment);
                if (match == null) {
                    if (use.contains(symbol)) {
                        return false;
                    }
                    hash.put(fragment, symbol);
                    use.add(symbol);
                } else if (!match.equals(symbol)) {
                    return false;
                }
            }
            return true;
        }
    }

    // 一次遍历，不使用额外函数分割字符串
    // 时间复杂度：O(n+m)
    // 空间复杂度：O(n+m)
    static class Solution1 {
        public boolean wordPattern(String pattern, String s) {
            String[] map = new String[26];
            Set<String> set = new HashSet<>();

            int i = 0;
            int j = 0;
            int n = pattern.length();
            int m = s.length();

            for (; i < n; i++, j++) {
                int idx = pattern.charAt(i) - 'a';
                int k = j;
                while (j < m && s.charAt(j) != ' ') {
                    j++;
                }
                if (j == k) {
                    return false;
                }
                String word = s.substring(k, j);
                if (map[idx] == null && !set.contains(word)) {
                    set.add(word);
                    map[idx] = word;
                    continue;
                }
                if (map[idx] != null && map[idx].equals(word)) {
                    continue;
                }
                return false;

            }
            return i == n && j >= m;
        }
    }

}
