package yyl.leetcode.p014;

/**
 * <b3>最长公共前缀</b3><br>
 * 编写一个函数来查找字符串数组中的最长公共前缀。<br>
 * 如果不存在公共前缀，返回空字符串 ""。<br>
 * 示例 1:<br>
 * 输入: ["flower","flow","flight"]<br>
 * 输出: "fl"<br>
 * 示例 2:<br>
 * 输入: ["dog","racecar","car"]<br>
 * 输出: ""<br>
 * 解释: 输入不存在公共前缀。<br>
 * 说明:<br>
 * 所有输入只包含小写字母 a-z 。<br>
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
// 编写一个函数来查找字符串数组中最长的公共前缀字符串。
public class LongestCommonPrefix {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"abcdefghigk", "abcd", "abcdefg"};
        System.out.println(solution.longestCommonPrefix(strs));
    }

    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            if (strs.length == 1) {
                return strs[0];
            }
            int pos = 0;
            T: for (; pos < strs[0].length(); pos++) {
                char c = strs[0].charAt(pos);
                for (int i = 1; i < strs.length; i++) {
                    if (strs[i].length() == pos || strs[i].charAt(pos) != c) {
                        break T;
                    }
                }
            }
            return strs[0].substring(0, pos);
        }
    }
}
