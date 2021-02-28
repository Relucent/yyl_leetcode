package yyl.leetcode.p08;

import yyl.leetcode.util.Assert;

/**
 * <h3>单调数列</h3><br>
 * 如果数组是单调递增或单调递减的，那么它是单调的。<br>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。<br>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：[1,2,2,3]
 * 输出：true
 * 
 * 示例 2：
 * 输入：[6,5,4,4]
 * 输出：true
 * 
 * 示例 3：
 * 输入：[1,3,2]
 * 输出：false
 * 
 * 示例 4：
 * 输入：[1,2,4,5]
 * 输出：true
 * 
 * 示例 5：
 * 输入：[1,1,1]
 * 输出：true
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= A.length <= 50000 <br>
 * └ -100000 <= A[i] <= 100000 <br>
 */

public class P0896_MonotonicArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.isMonotonic(new int[] { 1, 2, 2, 3 }));
        Assert.assertTrue(solution.isMonotonic(new int[] { 6, 5, 4, 4 }));
        Assert.assertFalse(solution.isMonotonic(new int[] { 1, 3, 2 }));
        Assert.assertTrue(solution.isMonotonic(new int[] { 1, 2, 4, 5 }));
        Assert.assertTrue(solution.isMonotonic(new int[] { 1, 1, 1 }));
    }

    // 遍历
    // 遍历数组 A，若遇到了 A[i]>A[i+1]又遇到了 A[i′]<A[i′+1]，则说明 A 既不是单调递增的，也不是单调递减的。
    // 时间复杂度：O(n)，其中 n 是数组 A 的长度。
    // 空间复杂度：O(1)。
    static class Solution {
        public boolean isMonotonic(int[] A) {
            boolean inc = true;
            boolean dec = true;
            for (int i = 1; i < A.length; i++) {
                if (A[i - 1] > A[i]) {
                    inc = false;
                }
                if (A[i - 1] < A[i]) {
                    dec = false;
                }
            }
            return inc || dec;
        }
    }
}
