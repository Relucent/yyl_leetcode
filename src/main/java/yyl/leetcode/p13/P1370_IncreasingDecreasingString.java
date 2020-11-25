package yyl.leetcode.p13;

import yyl.leetcode.util.Assert;

/**
 * <h3>上升下降字符串</h3><br>
 * 给你一个字符串 s ，请你根据下面的算法重新构造字符串：<br>
 * 1、 从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。<br>
 * 2、 从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。<br>
 * 3、 重复步骤 2 ，直到你没法从 s 中选择字符。<br>
 * 4、从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。<br>
 * 5、从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。<br>
 * 6、 重复步骤 5 ，直到你没法从 s 中选择字符。<br>
 * 7、重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。<br>
 * 在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。<br>
 * 请你返回将 s 中字符重新排序后的 结果字符串 。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：s = "aaaabbbbcccc"
 * 输出："abccbaabccba"
 * 解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
 * 第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
 * 第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
 * 第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
 * 第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
 * 
 * 示例 2：
 * 输入：s = "rat"
 * 输出："art"
 * 解释：单词 "rat" 在上述算法重排序以后变成 "art"
 * 
 * 示例 3：
 * 输入：s = "leetcode"
 * 输出："cdelotee"
 * 
 * 示例 4：
 * 输入：s = "ggggggg"
 * 输出："ggggggg"
 * 
 * 示例 5：
 * 输入：s = "spo"
 * 输出："ops"
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= s.length <= 500<br>
 * └ s 只包含小写英文字母。<br>
 */
public class P1370_IncreasingDecreasingString {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("abccbaabccba", solution.sortString("aaaabbbbcccc"));
        Assert.assertEquals("art", solution.sortString("rat"));
        Assert.assertEquals("cdelotee", solution.sortString("leetcode"));
        Assert.assertEquals("ggggggg", solution.sortString("ggggggg"));
        Assert.assertEquals("ops", solution.sortString("spo"));
    }

    // 桶计数
    // 在构造时只需要关注字符本身，而不关注字符在原字符串中的位置。因此直接创建一个大小为 26 的桶，记录每种字符的数量。
    // 每次提取最长的上升或下降字符串时，直接顺序或逆序遍历这个桶。
    // 时间复杂度：O(∣Σ∣×∣s∣)，其中 Σ 为字符集，s为传入的字符串，在这道题中，字符集为全部小写字母，∣Σ∣=26。最坏情况下字符串中所有字符都相同，那么对于每一个字符，我们需要遍历一次整个桶。
    // 空间复杂度：O(∣Σ∣)，其中 Σ 为字符集。我们需要和 ∣Σ∣等大的桶来记录每一类字符的数量。
    static class Solution {

        private static final int S = 26;

        public String sortString(String s) {
            int[] bucket = new int[S];
            for (char c : s.toCharArray()) {
                bucket[c - 'a']++;
            }
            int n = s.length();
            char[] values = new char[n];
            for (int offset = 0; offset < n;) {
                for (int i = 0; i < S; i++) {
                    if (bucket[i] != 0) {
                        bucket[i]--;
                        values[offset++] = (char) (i + 'a');
                    }
                }
                for (int i = S - 1; i >= 0; i--) {
                    if (bucket[i] != 0) {
                        bucket[i]--;
                        values[offset++] = (char) (i + 'a');
                    }
                }
            }
            return new String(values);
        }
    }
}
