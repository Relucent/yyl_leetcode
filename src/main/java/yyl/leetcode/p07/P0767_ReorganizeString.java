package yyl.leetcode.p07;

import yyl.leetcode.util.Assert;

/**
 * <h3>重构字符串</h3><br>
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。<br>
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 * 
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 * </pre>
 * 
 * 注意: S 只包含小写字母并且长度在[1, 500]区间内。<br>
 */

public class P0767_ReorganizeString {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("aba", solution.reorganizeString("aab"));
        Assert.assertEquals("", solution.reorganizeString("aaab"));
    }

    // 贪心算法 + 计数 + 插空
    // 首先统计每个字母的出现次数，然后根据每个字母的出现次数重构字符串。
    // 当 n 是奇数且出现最多的字母的出现次数是 (n+1)/2 时，出现次数最多的字母必须全部放置在偶数下标，否则一定会出现相邻的字母相同的情况。其余情况下，每个字母放置在偶数下标或者奇数下标都是可行的。
    // 先插入最多的字母，每隔一位放入结果，然后再插入其他字母（先插入偶数位置，再插入基数位置），最后得出结果。
    // 时间复杂度：O(n+∣Σ∣)，其中 n 是字符串的长度，Σ 是字符集，(本题中 ∣Σ∣=26)。
    // 空间复杂度：O(∣Σ∣)，在本题中字符集为所有小写字母。
    static class Solution {

        private static final int SIGMA = 26;
        private static final int OFFSET = 'a';

        public String reorganizeString(String S) {
            if (S == null) {
                return S;
            }
            int n = S.length();
            if (n == 0) {
                return S;
            }
            int[] hash = new int[SIGMA];
            int maxIndex = 0;
            int maxCount = 0;
            for (int i = 0; i < n; i++) {
                int idx = (int) S.charAt(i) - OFFSET;
                hash[idx]++;
                if (hash[idx] > maxCount) {
                    maxIndex = idx;
                    maxCount = hash[idx];
                }
            }
            // 如果最多的字符超过了一半，返回空
            if (maxCount > (n + 1) / 2) {
                return "";
            }

            // 把最多的字符隔一位放入结果
            char[] result = new char[n];
            int index = 0;
            while (hash[maxIndex] > 0) {
                result[index] = (char) (maxIndex + OFFSET);
                index = index + 2;
                hash[maxIndex]--;
            }

            // 遍历每一个不为0的字符，隔一位加入结果
            for (int i = 0; i < SIGMA; i++) {
                while (hash[i] > 0) {
                    // 当偶数位用完之后，从头开始用奇数位放元素
                    if (index >= n) {
                        index = 1;
                    }
                    result[index] = (char) (i + OFFSET);
                    index = index + 2;
                    hash[i]--;
                }
            }
            return new String(result);
        }
    }
}
