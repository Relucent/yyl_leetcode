package yyl.leetcode.p00;

/**
 * <h3>实现 strStr()函数</h3><br>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回 -1。<br>
 * 示例 1:<br>
 * 输入: haystack = "hello", needle = "ll" 输出: 2<br>
 * 示例 2: <br>
 * 输入: haystack = "aaaaa", needle = "bba" 输出: -1<br>
 * Implement strStr().<br>
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.<br>
 */
public class P0028_ImplementStrstr {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String haystack = "abcabcabcdabcde";
        String needle = "bcd";
        System.out.println(solution.strStr(haystack, needle));
    }

    static class Solution {

        public int strStr(String haystack, String needle) {
            if (needle.isEmpty()) {
                return 0;
            }
            int max = haystack.length() - needle.length();
            char first = needle.charAt(0);
            for (int i = 0; i <= max; i++) {
                if (haystack.charAt(i) != first) {
                    while (++i <= max && haystack.charAt(i) != first);
                }
                if (i <= max) {
                    int j = i + 1;
                    int end = i + needle.length();
                    for (int k = 1; j < end && haystack.charAt(j) == needle.charAt(k); j++, k++);
                    if (j == end) {
                        return i;
                    }
                }
            }
            return -1;
        }
    }
}
