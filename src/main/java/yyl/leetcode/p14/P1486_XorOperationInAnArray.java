package yyl.leetcode.p14;

import yyl.leetcode.util.Assert;

/**
 * <h3>数组异或操作</h3><br>
 * 给你两个整数，n 和 start 。<br>
 * 数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。<br>
 * 请返回 nums 中所有元素按位异或（XOR）后得到的结果。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：n = 5, start = 0
 * 输出：8
 * 解释：数组 nums 为 [0, 2, 4, 6, 8]，其中 (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8 。
 *      "^" 为按位异或 XOR 运算符。
 * 
 * 示例 2：
 * 输入：n = 4, start = 3
 * 输出：8
 * 解释：数组 nums 为 [3, 5, 7, 9]，其中 (3 ^ 5 ^ 7 ^ 9) = 8.
 * 
 * 示例 3：
 * 输入：n = 1, start = 7
 * 输出：7
 * 
 * 示例 4：
 * 输入：n = 10, start = 5
 * 输出：2
 * 
 * 提示：
 *     1 <= n <= 1000
 *     0 <= start <= 1000
 *     n == nums.length
 * </pre>
 */

public class P1486_XorOperationInAnArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(8, solution.xorOperation(5, 0));
        Assert.assertEquals(8, solution.xorOperation(4, 3));
        Assert.assertEquals(7, solution.xorOperation(1, 7));
        Assert.assertEquals(2, solution.xorOperation(10, 5));
    }

    // 模拟
    // 按照题意模拟即可：
    // 初始化 answer=0；遍历区间 [0,n−1]中的每一个整数 i，令 answer与每一个 start+2×i 做异或运算；最终返回 answer。
    // 异或：
    // 0⊕0=0
    // 0⊕1=1
    // 1⊕0=1
    // 1⊕1=0
    // 复杂度分析
    // 时间复杂度：O(n)。使用一重循环对 n个数字进行异或。
    // 空间复杂度：O(1)。使用常量级别的辅助空间。
    static class Solution {
        public int xorOperation(int n, int start) {
            int answer = 0;
            for (int i = 0; i < n; i++) {
                answer ^= start + 2 * i;
            }
            return answer;
        }
    }
}
