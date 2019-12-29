package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>字母异位词分组</h3><br>
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。<br>
 * 示例:<br>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],<br>
 * 输出: [ ["ate","eat","tea"], ["nat","tan"], ["bat"] ]<br>
 * 说明： 所有输入均为小写字母。 不考虑答案输出的顺序。<br>
 */
public class P0049_GroupAnagrams {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] sample = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams(sample));
    }

    // 时间复杂度：O(nK), K是字符串的最长长度
    // 空间复杂度：O(nK), 存储在MAP中的全部信息内容
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                String key = digest(str);
                List<String> list = map.get(key);
                if (list == null) {
                    map.put(key, list = new ArrayList<>());
                }
                list.add(str);
            }
            return new ArrayList<>(map.values());
        }

        private String digest(String str) {
            int[] alpha = new int[26];
            for (int i = 0; i < str.length(); i++) {
                alpha[str.charAt(i) - 'a']++;
            }
            StringBuilder buffer = new StringBuilder();
            for (int a : alpha) {
                buffer.append(a).append(',');
            }
            return buffer.toString();
        }
    }
}
