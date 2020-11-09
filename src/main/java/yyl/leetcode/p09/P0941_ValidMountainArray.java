package yyl.leetcode.p09;

import yyl.leetcode.util.Assert;

/**
 * <h3>有效的山脉数组</h3><br>
 * 给定一个整数数组 A，如果它是有效的山脉数组就返回 true，否则返回 false。<br>
 * 让我们回顾一下，如果 A 满足下述条件，那么它是一个山脉数组：<br>
 * ├ A.length >= 3 <br>
 * └ 在 0 < i < A.length - 1 条件下，存在 i 使得：<br>
 * ~ ├ A[0] < A[1] < ... A[i-1] < A[i]<br>
 * ~ └ A[i] > A[i+1] > ... > A[A.length - 1]<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：[2,1]
 * 输出：false
 * 
 * 示例 2：
 * 输入：[3,5,5]
 * 输出：false
 * 
 * 示例 3：
 * 输入：[0,3,2,1]
 * 输出：true
 * </pre>
 * 
 * 提示：<br>
 * 0 <= A.length <= 10000<br>
 * 0 <= A[i] <= 10000 <br>
 */

public class P0941_ValidMountainArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertFalse(solution.validMountainArray(new int[] { 2, 1 }));
        Assert.assertFalse(solution.validMountainArray(new int[] { 3, 5, 5 }));
        Assert.assertTrue(solution.validMountainArray(new int[] { 0, 3, 2, 1 }));
        Assert.assertFalse(solution.validMountainArray(new int[] { 14, 70, 70, 68, 67, 66, 63, 60, 58, 54, 4, 3 }));
    }

    // 线性扫描
    // 按题意模拟即可。我们从数组的最左侧开始向右扫描，直到找到第一个不满足A[i]<A[i+1] 的下标 i ，那么 i 就是这个数组的最高点的下标。
    // 如果 i=0 或者不存在（整个数组都是单调递增的），那么就返回 false。否则从 i 开始继续向右扫描，判断接下来的的下标 i 是否都满足 A[i]>A[i+1]，若都满足就返回 true，否则返回 false
    // 时间复杂度：O(N)，其中 N 是数组 A 的长度。
    // 空间复杂度：O(1)
    static class Solution {
        public boolean validMountainArray(int[] A) {
            int n = A.length;
            int i = 0;
            while (i + 1 < n && A[i] < A[i + 1]) {
                i++;
            }
            // 最高点不能是数组的第一个位置或最后一个位置
            if (i == 0 || i == n - 1) {
                return false;
            }
            // 递减扫描
            while (i + 1 < n && A[i] > A[i + 1]) {
                i++;
            }
            return i == n - 1;
        }
    }
}
