package yyl.leetcode.p03;

/**
 * <h3>判断子序列</h3><br>
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。<br>
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。<br>
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。<br>
 * 
 * <pre>
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * 
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 * </pre>
 * 
 * 后续挑战 :<br>
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 */
public class P0392_IsSubsequence {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isSubsequence("abc", "ahbgdc"));// true
        System.out.println(solution.isSubsequence("axc", "ahbgdc"));// false
    }

    // 双指针
    // 题目的子串定为原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
    // 只要能找到任意一种 s 在 t 中出现的方式，即可认为 s 是 t 的子序列。
    // 初始化两个指针 i 和 j，分别指向 s 和 t 的初始位置。进行匹配，匹配成功则 i和 j同时右移；如果匹配失败则 j右移，i不变，尝试用 t 的下一个字符匹配 s。
    // 最终如果 i 移动到 s 的末尾，就说明 s 是 t 的子序列。
    // 时间复杂度：O(m+n)，其中 m为 s的长度，n为t的长度。每次无论是匹配成功还是失败，都有至少一个指针发生右移，两指针能够位移的总距离为 m+n。
    // 空间复杂度：O(1)。
    static class Solution {
        public boolean isSubsequence(String s, String t) {
            int m = s.length();
            int n = t.length();
            int i = 0;
            int j = 0;
            while (i < m && j < n) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                }
                j++;
            }
            return i == m;
        }
    }
}
