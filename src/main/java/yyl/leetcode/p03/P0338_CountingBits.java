package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>比特位计数</h3> 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。 <br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * 
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * </pre>
 */
public class P0338_CountingBits {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 0, 1, 1 }, solution.countBits(2));
        Assert.assertEquals(new int[] { 0, 1, 1, 2, 1, 2 }, solution.countBits(5));
    }

    // 直接计算
    // 最直观的方法是对从 000 到 num\textit{num}num 的每个数直接计算「一比特数」
    // 时间复杂度：O(n*sizeof(integer))。
    // 空间复杂度：O(1)。
    static class Solution {
        public int[] countBits(int num) {
            int[] answer = new int[num + 1];
            for (int i = 0; i <= num; i++) {
                answer[i] = Integer.bitCount(i);
            }
            return answer;
        }
    }

    // 动态规划——位运算
    // 对于正整数 x ，将其二进制表示右移一位，等价于将其二进制表示的最低位去掉，得到的数是 ⌊x/2⌋。如果 bits[⌊x2⌋] 的值已知，则可以得到 bits[x] 的值：
    // 如果 x 是偶数，则 bits[x]=bits[⌊x2⌋]；
    // 如果 x 是奇数，则 bits[x]=bits[⌊x2⌋]+1；
    // 上述两种情况可以合并成：bits[x] 的值等于 bits[⌊x2⌋] 的值加上 x 除以 2 的余数。
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)。
    static class Solution1 {
        public int[] countBits(int num) {
            int[] answer = new int[num + 1];
            for (int i = 1; i <= num; i++) {
                answer[i] = answer[i >> 1] + (i & 1);
            }
            return answer;
        }
    }
}
