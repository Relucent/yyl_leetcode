package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>24 点游戏</h3><br>
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 
 * 示例 2:
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * </pre>
 * 
 * 注意:<br>
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。<br>
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。<br>
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。<br>
 */
public class P0679_24Game {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.judgePoint24(new int[] { 4, 1, 8, 7 }));// (8-4) * (7-1) = 24
        Assert.assertFalse(solution.judgePoint24(new int[] { 1, 2, 1, 2 }));
    }

    // 回溯 （暴力穷举）
    // 一共有 4 个数和 3 个运算操作，因此可能性非常有限。
    // 排列公式：从n个不同元素任取m个元素排列，A(m,n) = n!/(n-m)!)
    // 首先从 4个数字中有序地选出 2个数字， 可以算出有 4×3=12 种选法，并选择加、减、乘、除 4种运算操作之一进行运算，得出结果取代这2个数字，剩下 3个数字。
    // 然后在剩下的 3 个数字中有序地选出 2个数字，可以算出有 共有 3×2=6种选法，并选择 4种运算操作之一，用得到的结果取代选出的 2个数字，剩下 2个数字。
    // 最后剩下 2 个数字，有 2种不同的顺序，并选择 4种运算操作之一进行计算，得出结果。
    // 因此，一共有 12×4×6×4×2×4=9216 种不同的可能性。
    // 除法运算为实数除法，因此结果为浮点数。判断结果是否等于 24 时需要考虑精度误差，两个浮点相差误差小于10^-10可以认为相等。
    // 除法运算时，除数不能为 0，如果遇到除数为 0 的情况，则可以直接排除。
    // 时间复杂度：O(1)。一共有 9216 种可能性，时间复杂度为常数。（实际运算复杂度可能为O(4^2*3^2*2^2*4)=O(36864)，依旧是常数）
    // 空间复杂度：O(1)。空间复杂度取决于递归调用层数与存储中间状态的列表，因为一共有 4个数，所以递归调用的层数最多为 4，存储中间状态的列表最多包含 4个元素，因此空间复杂度为常数。
    static class Solution {

        private static final int TARGET = 24;
        private static final double EPSILON = 1e-6;// 0.000001

        public boolean judgePoint24(int[] nums) {
            double[] decimals = new double[nums.length];
            for (int i = 0; i < nums.length; i++) {
                decimals[i] = (double) nums[i];
            }
            return solve(decimals, decimals.length);
        }

        private boolean solve(double[] nums, int length) {
            if (length == 0) {
                return false;
            }
            if (length == 1) {
                return Math.abs(TARGET - nums[0]) < EPSILON;
            }
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    if (i == j) {
                        continue;
                    }
                    int length2 = length - 1;
                    int p = 0;
                    double[] nums2 = new double[length2];
                    for (int k = 0; k < length; k++) {
                        if (k != i && k != j) {
                            nums2[p++] = nums[k];
                        }
                    }
                    // ＋
                    nums2[p] = nums[i] + nums[j];
                    if (solve(nums2, length2)) {
                        return true;
                    }
                    // －
                    nums2[p] = nums[i] - nums[j];
                    if (solve(nums2, length2)) {
                        return true;
                    }
                    // ×
                    nums2[p] = nums[i] * nums[j];
                    if (solve(nums2, length2)) {
                        return true;
                    }
                    // ÷
                    if (Math.abs(nums[j]) > EPSILON) {
                        nums2[p] = nums[i] / nums[j];
                        if (solve(nums2, length2)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    // 暴力穷举
    // 一共有 9216种可能性，考虑乘法加法有交换律，可以优化为3888种组合方式
    // 时间复杂度：O(1)。时间复杂度为常数级。
    // 空间复杂度：O(1)。空间复杂度为常数级。
    static class Solution2 {

        private static final int TARGET = 24;
        private static final double EPSILON = 1e-6;// 0.000001

        public boolean judgePoint24(int[] nums) {
            return solve(nums[0], nums[1], nums[2], nums[3]);
        }

        // 4个数2个组合有 6种方式， 每种组合6种运算方式 6 * 6 = 36
        public boolean solve(double a, double b, double c, double d) {
            return solve(a + b, c, d) || // a b 1
                    solve(a * b, c, d) || // 2
                    solve(a - b, c, d) || // 3
                    solve(b - a, c, d) || // 4
                    (Math.abs(b) > EPSILON && solve(a / b, c, d)) || // 5
                    (Math.abs(a) > EPSILON && solve(b / a, c, d)) || // 6
                    solve(a + c, b, d) || // a c
                    solve(a * c, b, d) || //
                    solve(a - c, b, d) || //
                    solve(c - a, b, d) || //
                    (Math.abs(c) > EPSILON && solve(a / c, b, d)) || //
                    (Math.abs(a) > EPSILON && solve(c / a, b, d)) || //
                    solve(a + d, b, c) || // a d
                    solve(a * d, b, c) || //
                    solve(a - d, b, c) || //
                    solve(d - a, b, c) || //
                    (Math.abs(d) > EPSILON && solve(a / d, b, c)) || //
                    (Math.abs(a) > EPSILON && solve(d / a, b, c)) || //
                    solve(b + c, a, d) || // b c
                    solve(b * c, a, d) || //
                    solve(b - c, a, d) || //
                    solve(c - b, a, d) || //
                    (Math.abs(c) > EPSILON && solve(b / c, a, d)) || //
                    (Math.abs(b) > EPSILON && solve(c / b, a, d)) || //
                    solve(b + d, a, c) || // b d
                    solve(b * d, a, c) || //
                    solve(b - d, a, c) || //
                    solve(d - b, a, c) || //
                    (Math.abs(d) > EPSILON && solve(b / d, a, c)) || //
                    (Math.abs(b) > EPSILON && solve(d / b, a, c)) || //
                    solve(c + d, a, b) || // c d
                    solve(c * d, a, b) || //
                    solve(c - d, a, b) || //
                    solve(d - c, a, b) || //
                    (Math.abs(d) > EPSILON && solve(c / d, a, b)) || //
                    (Math.abs(c) > EPSILON && solve(d / c, a, b)); //
        }

        // 3个数2个组合有 3种方式， 每种组合6种运算方式 6 * 3 = 36
        public boolean solve(double a, double b, double c) {
            return solve(a + b, c) || // 1
                    solve(a * b, c) || // 2
                    solve(a - b, c) || // 3
                    solve(b - a, c) || // 4
                    (Math.abs(b) > EPSILON && solve(a / b, c)) || // 5
                    (Math.abs(a) > EPSILON && solve(b / a, c)) || // 6
                    solve(a + c, b) || // 7
                    solve(a * c, b) || // 8
                    solve(a - c, b) || // 9
                    solve(c - a, b) || // 10
                    (Math.abs(c) > EPSILON && solve(a / c, b)) || // 11
                    (Math.abs(a) > EPSILON && solve(c / a, b)) || // 12
                    solve(b + c, a) || // 13
                    solve(b * c, a) || // 14
                    solve(b - c, a) || // 15
                    solve(c - b, a) || // 16
                    (Math.abs(c) > EPSILON && solve(b / c, a)) || // 17
                    (Math.abs(b) > EPSILON && solve(c / b, a)); // 18
        }

        // 2个数2个组合有 1种方式， 每种组合6种运算方式 6 * 1 = 6
        private boolean solve(double a, double b) {
            return Math.abs(a + b - TARGET) < EPSILON || // 1
                    Math.abs(a - b - TARGET) < EPSILON || // 2
                    Math.abs(b - a - TARGET) < EPSILON || // 3
                    Math.abs(a * b - TARGET) < EPSILON || // 4
                    (Math.abs(b) > EPSILON && Math.abs(a / b - TARGET) < EPSILON) || // 5
                    (Math.abs(a) > EPSILON && Math.abs(b / a - TARGET) < EPSILON); // 6
        }
    }
}
