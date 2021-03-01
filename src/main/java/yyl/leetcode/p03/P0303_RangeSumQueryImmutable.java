package yyl.leetcode.p03;

import yyl.leetcode.util.Assert;

/**
 * <h3>区域和检索 - 数组不可变</h3><br>
 * 给定一个整数数组 nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。<br>
 * 实现 NumArray 类：<br>
 * ├ NumArray(int[] nums) 使用数组 nums 初始化对象<br>
 * └ int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）<br>
 * 
 * <pre>
 * // class NumArray {
 * // NumArray(int[] nums);
 * // int sumRange(int i, int j);
 * // }
 * </pre>
 * 
 * <pre>
 * 示例：
 * 输入：
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * 输出：
 * [null, 1, -1, -3]
 * 解释：
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return 1 ((-2) + 0 + 3)
 * numArray.sumRange(2, 5); // return -1 (3 + (-5) + 2 + (-1)) 
 * numArray.sumRange(0, 5); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
 * </pre>
 * 
 * 提示：<br>
 * ├ 0 <= nums.length <= 104<br>
 * ├ -105 <= nums[i] <= 105<br>
 * ├ 0 <= i <= j < nums.length<br>
 * └ 最多调用 104 次 sumRange 方法<br>
 */
public class P0303_RangeSumQueryImmutable {

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[] { -2, 0, 3, -5, 2, -1 });
        Assert.assertEquals(1, numArray.sumRange(0, 2)); // return 1 ((-2) + 0 + 3)
        Assert.assertEquals(-1, numArray.sumRange(2, 5)); // return -1 (3 + (-5) + 2 + (-1))
        Assert.assertEquals(-3, numArray.sumRange(0, 5)); // return -3 ((-2) + 0 + 3 + (-5) + 2 + (-1))
    }

    // 前缀和
    // 思路：如果每次调用 sumRange 时在计算，检索的时间复杂度较高，如果检索次数较多，则会超出时间限制。因此可以考虑对数据进行预处理。
    // 在初始化的时候计算出数组 nums 在每个下标处的前缀和，当 i≤j 时，sumRange(i,j) = sumRange(0,j) - sumRange(0,i-1)
    // 具体实现方面，需要创建长度为 n+1 的前缀和数组（考虑 i=0，i-1=-1的情况，前缀数组需要多一位，方便计算）
    // sumRange(i,j) = prefix[j+1]−prefix[i]
    // 时间复杂度：初始化 O(n)，每次检索 O(1)，其中 n 是数组 nums的长度。
    // 空间复杂度：O(n) ，其中 n 是数组 nums 的长度。需要创建一个长度为 n+1 的前缀和数组。
    static class NumArray {

        private final int[] prefix;

        public NumArray(int[] nums) {
            prefix = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            if (i == 0) {
                return prefix[j + 1];
            }
            return prefix[j + 1] - prefix[i];
        }
    }

    /**
     * Your NumArray object will be instantiated and called as such:<br>
     * NumArray obj = new NumArray(nums);<br>
     * int param_1 = obj.sumRange(i,j);<br>
     */
}
