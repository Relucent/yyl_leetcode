package yyl.leetcode.p17;

import yyl.leetcode.util.Assert;

/**
 * <h3>解码异或后的数组</h3><br>
 * 未知 整数数组 arr 由 n 个非负整数组成。<br>
 * 经编码后变为长度为 n - 1 的另一个整数数组 encoded ，其中 encoded[i] = arr[i] XOR arr[i + 1] 。例如，arr = [1,0,2,1] 经编码后得到 encoded = [1,2,3] 。<br>
 * 给你编码后的数组 encoded 和原数组 arr 的第一个元素 first（arr[0]）。<br>
 * 请解码返回原数组 arr 。可以证明答案存在并且是唯一的。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：encoded = [1,2,3], first = 1
 * 输出：[1,0,2,1]
 * 解释：若 arr = [1,0,2,1] ，那么 first = 1 且 encoded = [1 XOR 0, 0 XOR 2, 2 XOR 1] = [1,2,3]
 * 
 * 示例 2：
 * 输入：encoded = [6,2,7,3], first = 4
 * 输出：[4,2,0,7,4]
 * 
 * 
 * 提示：
 *   2 <= n <= 104
 *   encoded.length == n - 1
 *   0 <= encoded[i] <= 105
 *   0 <= first <= 105
 * </pre>
 */
public class P1720_DecodeXoredArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 1, 0, 2, 1 }, solution.decode(new int[] { 1, 2, 3 }, 1));
        Assert.assertEquals(new int[] { 4, 2, 0, 7, 4 }, solution.decode(new int[] { 6, 2, 7, 3 }, 4));
    }

    // 利用异或运算的性质
    // 异或运算具有如下性质：
    // ├ 异或运算满足交换律和结合律；
    // ├ 任意整数和自身做异或运算的结果都等于 0，即 x⊕x=0；
    // └ 任意整数和 0 做异或运算的结果都等于其自身，即 x⊕0=0⊕x=x。
    // 因此，当 1≤i<n 时，有 arr[i]=arr[i−1]⊕encoded[i−1]
    // 由于，arr[0]=first 已知，因此对 i 从 1 到 n−1 依次计算 arr[i] 的值，即可解码得到原数组 arr。
    // 复杂度分析
    // 时间复杂度：O(n)，其中 n 是原数组 encoded 的长度。需要遍历码数组 encoded 一次，计算原数组 arr 的每个元素值。
    // 空间复杂度：O(1)。空间复杂度不考虑返回值。
    static class Solution {
        public int[] decode(int[] encoded, int first) {
            int n = encoded.length;
            int[] answer = new int[n + 1];
            answer[0] = first;
            for (int i = 0; i < n; i++) {
                answer[i + 1] = answer[i] ^ encoded[i];
            }
            return answer;
        }
    }
}
