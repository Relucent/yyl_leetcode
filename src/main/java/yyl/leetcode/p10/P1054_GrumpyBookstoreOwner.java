package yyl.leetcode.p10;

import yyl.leetcode.util.Assert;

/**
 * <h3>爱生气的书店老板</h3><br>
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。<br>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。 <br>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。 <br>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。 <br>
 * 
 * <pre>
 * 示例：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= X <= customers.length == grumpy.length <= 20000<br>
 * ├ 0 <= customers[i] <= 1000<br>
 * └ 0 <= grumpy[i] <= 1 <br>
 */

public class P1054_GrumpyBookstoreOwner {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(16, solution.maxSatisfied(new int[] { 1, 0, 1, 2, 1, 1, 7, 5 }, new int[] { 0, 1, 0, 1, 0, 1, 0, 1 }, 3));
    }

    // 滑动窗口
    // 先求出老板不生气时候的满意顾客总数
    // 然后使用滑动窗口求出，不生气所能增加的最大满意顾客总数
    // 时间复杂度：O(n)，其中 n 是数组 customers 和 grumpy 的长度（customers.length==grumpy.length）。
    // 空间复杂度：O(1)。
    static class Solution {
        public int maxSatisfied(int[] customers, int[] grumpy, int X) {
            int n = customers.length;
            int total = 0;
            for (int i = 0; i < n; i++) {
                if (grumpy[i] == 0) {
                    total += customers[i];
                }
            }
            int increase = 0;
            for (int i = 0; i < X; i++) {
                if (grumpy[i] == 1) {
                    increase += customers[i];
                }
            }
            int maxIncrease = increase;
            for (int i = X; i < n; i++) {
                if (grumpy[i - X] == 1) {
                    increase -= customers[i - X];
                }
                if (grumpy[i] == 1) {
                    increase += customers[i];
                }
                maxIncrease = Math.max(maxIncrease, increase);
            }
            return total + maxIncrease;
        }
    }
}
