package yyl.leetcode.p09;

import yyl.leetcode.util.Assert;

/**
 * <h3>最长湍流子数组</h3><br>
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：<br>
 * ├ 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]； <br>
 * └ 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。 <br>
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。<br>
 * 返回 A 的最大湍流子数组的长度。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 * 
 * 示例 3：
 * 输入：[100]
 * 输出：1
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= A.length <= 40000 <br>
 * └ 0 <= A[i] <= 10^9 <br>
 */
public class P0978_LongestTurbulentSubarray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(5, solution.maxTurbulenceSize(new int[] { 9, 4, 2, 10, 7, 8, 8, 1, 9 }));
        Assert.assertEquals(2, solution.maxTurbulenceSize(new int[] { 4, 8, 12, 16 }));
        Assert.assertEquals(1, solution.maxTurbulenceSize(new int[] { 100 }));
    }

    // 动态规划
    // 本题中湍流子数组的意思是：一个增长和降低互相交替的子数组，如果在坐标轴上画出来就是个波浪状数组，↗ ↘ ↗ ↘，即这个形状。
    // ├ 记 up[i] 为以 arr[i] 结尾，且 arr[i−1]<arr[i] 的「湍流子数组」的最大长度。
    // ├ 记 down[i] 为以 arr[i] 结尾，且 arr[i−1]>arr[i] 的「湍流子数组」的最大长度；
    // ├ 以下标 0 结尾的「湍流子数组」的最大长度为 1，因此边界情况为 down[0]=up[0]=1。
    // ├ 状态转移方程
    // │ ├ 当 arr[i−1]>arr[i] 时，（以下标 i−1 结尾的子数组是「湍流子数组」，应满足 i−1=0 ，或者 i−1>0 时 arr[i−2]<arr[i−1]）
    // │ │ └ down[i]=up[i−1]+1， up[i]=1
    // │ ├ 当 arr[i−1]<arr[i] 时，（以下标 i−1 结尾的子数组是「湍流子数组」，应满足 i−1=0 ，或者 i−1>0 时 arr[i−2]>arr[i−1]）
    // │ │ └ up[i]=down[i−1]+1， down[i]=1
    // │ └ 当 arr[i−1] == arr[i] 时，（arr[i−1] 和 arr[i] 不能同时出现在同一个湍流子数组中 ）
    // │ - └ up[i]=down[i]=1
    // └ 取 up，down 数组的最大值即为所求的答案
    // 空间压缩：当 i>0 时，下标 i 处 dp 值只和下标 i−1 处的 dp 值有关，因此可以用两个变量代替数组
    // 时间复杂度：O(n)，其中 n 为数组的长度。需要遍历数组 arr 一次，计算 dp 的值。
    // 空间复杂度：O(1)。使用空间优化的做法，只需要维护常数额外空间。
    static class Solution {
        public int maxTurbulenceSize(int[] arr) {
            int n = arr.length;
            int up = 1;
            int down = 1;
            int answer = 1;
            for (int i = 1; i < n; i++) {
                // 上升
                if (arr[i - 1] > arr[i]) {
                    up = down + 1;
                    down = 1;
                }
                // 下降
                else if (arr[i - 1] < arr[i]) {
                    down = up + 1;
                    up = 1;
                }
                // 相等
                else {
                    up = down = 1;
                }
                answer = Math.max(answer, Math.max(up, down));
            }
            return answer;
        }
    }
}
