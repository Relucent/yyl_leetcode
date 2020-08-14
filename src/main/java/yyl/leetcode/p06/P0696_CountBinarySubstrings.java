package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>计数二进制子串</h3><br>
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。<br>
 * 重复出现的子串要计算它们出现的次数。<br>
 * 
 * <pre>
 * 示例 1 :
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 
 * 示例 2 :
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * </pre>
 * 
 * 注意： s.length 在1到50,000之间。 s 只包含“0”或“1”字符。
 */
public class P0696_CountBinarySubstrings {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(6, solution.countBinarySubstrings("00110011"));
        Assert.assertEquals(4, solution.countBinarySubstrings("10101"));
    }

    // 中心扩展法
    // 二进制字符串中间必定是在01交界处，并且0和1的数量相等。
    // 首先查找到交界处(满足当前字符和前一个字符组合起来为 '01' 或者 '10' 的字符位置)，然后向前后进行扩展。累加，即可得到子字符串数。
    // 时间复杂度：O(n+k)，其中n为字符串长度，k为符合条件的子串的数目。
    // 空间复杂度：O(1)
    static class Solution {
        public int countBinarySubstrings(String s) {
            int answer = 0;
            for (int i = 1, n = s.length(); i < n; i++) {
                int left = i - 1;
                int right = i;
                char leftChar = s.charAt(left);
                char rightChar = s.charAt(right);
                if (leftChar != rightChar) {
                    while (0 <= left && right < n && s.charAt(left) == leftChar && s.charAt(right) == rightChar) {
                        left--;
                        right++;
                        answer++;
                    }
                }
            }
            return answer;
        }
    }

    // 分组计数法
    // 可以将字符串 s按照 0和 1的连续段分组，存在 counts数组中，例如 s=00111011，可以得到这样的 counts数组：counts={2,3,1,2}。
    // 这里 counts数组中两个相邻的数一定代表的是两种不同的字符。
    // 我们只要遍历所有相邻的数对，求它们的贡献总和，即可得到答案。
    // 因为对于某一个位置 i，只需要关心 i−1位置的 counts值是多少，所以可以用一个 last变量来维护当前位置的前一个位置，这样可以省去一个 counts数组的空间。
    // 时间复杂度：O(n)。
    // 空间复杂度：O(1)。
    static class Solution1 {
        public int countBinarySubstrings(String s) {
            int answer = 0;
            int last = 0;
            for (int i = 0; i < s.length();) {
                int c = s.charAt(i);
                int count = 0;
                while (i < s.length() && s.charAt(i) == c) {
                    i++;
                    count++;
                }
                answer += Math.min(count, last);
                last = count;
            }
            return answer;
        }
    }
}
