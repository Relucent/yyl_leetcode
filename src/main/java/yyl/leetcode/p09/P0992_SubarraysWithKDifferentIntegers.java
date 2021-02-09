package yyl.leetcode.p09;

import yyl.leetcode.util.Assert;

/**
 * <h3>K 个不同整数的子数组</h3><br>
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。<br>
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）<br>
 * 返回 A 中好子数组的数目。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 
 * 示例 2：
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= A.length <= 20000 <br>
 * ├ 1 <= A[i] <= A.length <br>
 * └ 1 <= K <= A.length <br>
 */
public class P0992_SubarraysWithKDifferentIntegers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(7, solution.subarraysWithKDistinct(new int[] { 1, 2, 1, 2, 3 }, 2));
        Assert.assertEquals(3, solution.subarraysWithKDistinct(new int[] { 1, 2, 1, 3, 4 }, 3));
    }

    // 滑动窗口
    // 思路：
    // 把原问题转换成为容易求解的问题
    // ├ 直接求「恰好」比较复杂，但是求「最多」则可以使用双指针一前一后交替向右的方法完成
    // │ └ 对于每一个确定的左边界，最多包含 K 种不同整数的右边界是唯一确定的，并且在左边界向右移动的过程中，右边界或者在原来的地方，或者在原来地方的右边。
    // ├ 经过分析可以得出
    // │ └ 「最多存在 K 个不同整数的子区间的个数」 - 「最多存在 K-1 个不同整数的子区间的个数」 = 「恰好存在 K 个不同整数的子区间的个数」
    // └ 问题转化为求两个最多（最多存在K个，最多存在K-1个）
    // 时间复杂度：O(N)，这里 N 是输入数组的长度；
    // 空间复杂度：O(1)，频数数组的长度为n + 1。
    static class Solution {

        public int subarraysWithKDistinct(int[] A, int K) {
            return subarraysMostKDistinct(A, K) - subarraysMostKDistinct(A, K - 1);
        }

        private int subarraysMostKDistinct(int[] A, int K) {
            int n = A.length;
            // 数字范围：1 <= A[i] <= A.length
            int[] freq = new int[n + 1];
            int left = 0;
            int right = 0;
            // [left, right) 里不同整数的个数
            int count = 0;
            int result = 0;
            // [left, right) 包含不同整数的个数小于等于 K
            while (right < n) {
                if (freq[A[right]] == 0) {
                    count++;
                }
                freq[A[right]]++;
                right++;

                while (count > K) {
                    freq[A[left]]--;
                    if (freq[A[left]] == 0) {
                        count--;
                    }
                    left++;
                }
                // [left, right) 区间的长度就是对结果的贡献
                result += right - left;
            }
            return result;
        }
    }
}
