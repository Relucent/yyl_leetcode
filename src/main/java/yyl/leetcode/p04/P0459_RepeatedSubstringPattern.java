package yyl.leetcode.p04;

import yyl.leetcode.util.Assert;

/**
 * <h3>重复的子字符串</h3><br>
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。<br>
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 
 * 示例 2:
 * 输入: "aba"
 * 输出: False
 * 
 * 示例 3:
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 * </pre>
 */
public class P0459_RepeatedSubstringPattern {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.repeatedSubstringPattern("abab"));
        Assert.assertFalse(solution.repeatedSubstringPattern("aba"));
        Assert.assertTrue(solution.repeatedSubstringPattern("abcabcabcabc"));
        Assert.assertTrue(solution.repeatedSubstringPattern("bb"));
        Assert.assertFalse(solution.repeatedSubstringPattern(""));
    }

    // 枚举（穷举）
    // 思路与算法
    // 如果一个长度为 n 的字符串 s 可以由它的一个长度为 n′ 的子串 s′ 重复多次构成，那么：
    // ├ n 一定是 n′的倍数；
    // ├ s′ 一定是 s 的前缀；
    // └ 对于任意的 i∈[n′,n)，有 s[i]=s[i−n′]。
    // 我们可以从小到大枚举 n′，并对字符串 s 进行遍历，进行上述的判断。
    // 因为子串至少需要重复一次，所以 n′ 不会大于 n 的一半，只需要在 [1,n/2] 的范围内枚举 n′ 即可。
    // 时间复杂度：O(n^2)，其中 n是字符串 s的长度。枚举 i的时间复杂度为 O(n)，遍历 s 的时间复杂度为 O(n)，相乘即为总时间复杂度。
    // 空间复杂度：O(1)。
    static class Solution {
        public boolean repeatedSubstringPattern(String s) {
            int n = s.length();
            for (int i = 1; i <= n / 2; ++i) {
                if (n % i == 0) {
                    boolean match = true;
                    T: for (int j = i; j < n; j++) {
                        if (s.charAt(j) != s.charAt(j - i)) {
                            match = false;
                            break T;
                        }
                    }
                    if (match) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    // 双倍字符串
    // 思路与算法
    // 如果长度为 n 的字符串 s 是字符串 t=s+s 的子串，并且 s 在 t 中的起始位置不为 0 或n，那么 s 就满足题目的要求
    // 如果 s 中没有循环节，那么 ss 中必然有且只有两个 s，此时从 ss[1] 处开始寻找 s ，必然只能找到第二个，所以此时返回值为 s.size()。
    // |_abcdabcd_|
    // |_____abcd_| find = s.sise()
    // 当 s 中有循环节时，设循环节长度为 m，那么 ss 中必然有 s.size()/m + 1 个 s。
    // |_abababab_|
    // |___ab_____| find = m
    // 因为去掉了s的第一个字符 (代码中，(s+s).find(s, 1)， 是从 ss[1] 处开始 find )所以此时必回找到第二个 s 的起点。
    // 复杂度分析
    // 时间复杂度：O(n^2)，取决于语言自带的字符串查找函数的实现（JDK8 indexOf的实现，时间复杂度为 O(n^2)。
    // 空间复杂度：O(n)
    static class Solution1 {
        public boolean repeatedSubstringPattern(String s) {
            return (s + s).indexOf(s, 1) != s.length();
        }
    }
}
