package yyl.leetcode.p13;

import yyl.leetcode.util.Assert;

/**
 * <h3>子数组异或查询</h3><br>
 * 有一个正整数数组 arr，现给你一个对应的查询数组 queries，其中 queries[i] = [Li, Ri]。<br>
 * 对于每个查询 i，请你计算从 Li 到 Ri 的 XOR 值（即 arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。<br>
 * 并返回一个包含给定查询 queries 所有结果的数组。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8] 
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001 
 * 3 = 0011 
 * 4 = 0100 
 * 8 = 1000 
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2 
 * [1,2] = 3 xor 4 = 7 
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14 
 * [3,3] = 8
 * 
 * 示例 2：
 * 输入：arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * 输出：[8,0,4,4]
 * 
 * 提示：
 *     1 <= arr.length <= 3 * 10^4
 *     1 <= arr[i] <= 10^9
 *     1 <= queries.length <= 3 * 10^4
 *     queries[i].length == 2
 *     0 <= queries[i][0] <= queries[i][1] < arr.length
 * </pre>
 */
public class P1310_XorQueriesOfASubarray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 2, 7, 14, 8 }, //
                solution.xorQueries(//
                        new int[] { 1, 3, 4, 8 }, //
                        new int[][] { { 0, 1 }, { 1, 2 }, { 0, 3 }, { 3, 3 } }//
                ));
        Assert.assertEquals(new int[] { 8, 0, 4, 4 }, //
                solution.xorQueries(//
                        new int[] { 4, 8, 2, 10 }, //
                        new int[][] { { 2, 3 }, { 1, 3 }, { 0, 0 }, { 0, 3 } }//
                ));
    }

    // 前缀异或
    // 利用异或运算的性质，异或运算的结合律
    // 首先计算数组的前缀异或
    // 定义长度为 n+1 的数组 xors。令 xors[0]=0，对于 0≤i<n，xors[i+1]=xors[i]⊕arr[i]
    // 对任意 0≤left≤right<n，都有 Q(left,right)=xors[left]⊕xors[right+1]。
    // 复杂度分析
    // 时间复杂度：O(n+m)，其中 n 是数组 arr 的长度，m 是数组 queries 的长度。需要遍历数组 arr，计算前缀异或数组的每个元素值，然后对每个查询分别使用 O(1)的时间计算查询结果。
    // 空间复杂度：O(n)，其中 n 是数组 arr 的长度。需要创建长度为 n+1 的前缀异或数组 。
    static class Solution {
        public int[] xorQueries(int[] arr, int[][] queries) {
            int n = arr.length;
            int[] xors = new int[n + 1];
            for (int i = 0; i < n; i++) {
                xors[i + 1] = xors[i] ^ arr[i];
            }
            int m = queries.length;
            int[] answer = new int[m];
            for (int i = 0; i < m; i++) {
                answer[i] = xors[queries[i][0]] ^ xors[queries[i][1] + 1];
            }
            return answer;
        }
    }
}
