package yyl.leetcode.p02;

import yyl.leetcode.util.Assert;

/**
 * <h3>数字范围按位与</h3><br>
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。<br>
 * 
 * <pre>
 * 示例 1: 
 * 输入: [5,7]
 * 输出: 4
 * 
 * 示例 2:
 * 输入: [0,1]
 * 输出: 0
 * </pre>
 */
public class P0201_BitwiseAndOfNumbersRange {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(4, solution.rangeBitwiseAnd(5, 7));
        Assert.assertEquals(0, solution.rangeBitwiseAnd(0, 1));
        Assert.assertEquals(8, solution.rangeBitwiseAnd(9, 12));
    }

    // 位移法
    // 按位与运算的性质。对于一系列的位，只要有一个零值的位，那么这一系列位的按位与运算结果都将为零。
    // 可以将问题重新表述为：给定两个整数，我们要找到它们对应的二进制字符串的公共前缀。
    // 示例 ：[9,12] 的公共前缀
    // 09)_01001 &
    // 10)_01010 &
    // 11)_01011 &
    // 12)_01100 &
    // 08)_01000
    // 算法：每次右移动shift，m==n停止
    // 09)_01001
    // 12)_01100
    // ---------- shift>>0
    // 04)_00100
    // 06)_00110
    // ---------- shift>>1
    // 02)_00010
    // 03)_00011
    // ---------- shift>>2
    // 01)_00001
    // 01)_00001
    // ---------- shift>>3
    // 08)_01000
    // ---------- shift<<3
    // 时间复杂度: O(log{⁡n})
    // 空间复杂度: O(1)
    static class Solution {
        public int rangeBitwiseAnd(int m, int n) {
            int shift = 0;
            while (m != n) {
                m >>= 1;
                n >>= 1;
                shift++;
            }
            return n << shift;
        }
    }

    // BrianKernighan 算法
    // BrianKernighan 算法用于清除二进制串中最右边的 1。
    // _01100 （n = 12）
    // _01011 （n - 1 = 11）
    // _01000 （n & (n-1) = 8）
    // 可以用它来计算两个二进制字符串的公共前缀。
    // 对于给定的范围 [m,n]（m<n），可以对数字 n迭代地应用上述技巧，清除最右边的 1，直到它小于或等于 m，此时非公共前缀部分的 1均被消去。最后返回 n 即可。
    // 时间复杂度: O(log{⁡n})
    // 空间复杂度: O(1)
    static class Solution1 {
        public int rangeBitwiseAnd(int m, int n) {
            while (m < n) {
                n = n & (n - 1);
            }
            return n;
        }
    }
}
