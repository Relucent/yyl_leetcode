package yyl.leetcode.p01;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <b3>最大间距</b3><br>
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。<br>
 * 如果数组元素个数小于 2，则返回 0。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 * 
 * 示例 2:
 * 输入: [10]
 * 输出: 0
 * 解释: 数组元素个数小于 2，因此返回 0。
 * </pre>
 * 
 * 说明:<br>
 * ├ 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。<br>
 * └ 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。<br>
 */

public class P0164_MaximumGap {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(3, solution.maximumGap(new int[] { 3, 6, 9, 1 }));
        Assert.assertEquals(0, solution.maximumGap(new int[] { 10 }));
    }

    // 暴力法：先排序，再比较
    // 最简单的思路是将数组排序后再找出最大间距。
    // 时间复杂度： O(Nlog⁡N)，排序的时间复杂度
    // 空间复杂度： O(Nlog⁡N)
    static class Solution {
        public int maximumGap(int[] nums) {
            Arrays.sort(nums);
            int maxDifference = 0;
            for (int i = 1; i < nums.length; i++) {
                maxDifference = Math.max(nums[i] - nums[i - 1], maxDifference);
            }
            return maxDifference;
        }
    }

    // 桶算法
    // 传统的基于比较的排序算法（快速排序、归并排序等）均需要 O(Nlog⁡N)的时间复杂度。所以需要使用其他算法保证在 O(N) 的时间内完成整数之间的排序。
    // 1、设长度为 N 的数组中最大值和最小值为 max 和 min，则相邻数字的最大间距不会小于 (max−min)/(N−1)
    // 2、选取整数 d = (max−min)/(N−1) ，将整个区间划分为若干个大小为 d 的桶，并找出每个整数所在的桶。
    // 3、找出每个元素所在的桶之后，维护每个桶内元素的最大值与最小值。
    // 4、根据前面(1)的结论，可以得出元素之间的最大间距一定不会出现在某个桶的内部，而一定会出现在不同桶当中。
    // 5、通过从前到后不断比较相邻的桶，用后一个桶的最小值与前一个桶的最大值之差作为两个桶的间距，最终就能得到所求的答案。
    // 时间复杂度：O(N)，其中 N 是数组的长度。
    // 空间复杂度：O(N)，其中 N 是数组的长度。
    static class Solution1 {
        public int maximumGap(int[] nums) {
            int n = nums.length;
            if (n < 2) {
                return 0;
            }

            // 找出最大值和最小值 为了方便后面确定桶的数量
            int max = -1, min = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                max = Math.max(nums[i], max);
                min = Math.min(nums[i], min);
            }
            // 数组的数值全部一样
            if (max == min) {
                return 0;
            }
            // 区间大小
            int d = Math.max(1, (max - min) / (n - 1));
            // 桶个数
            int bucketSize = (max - min) / d + 1;

            // 桶
            int[] minBucket = new int[bucketSize];
            int[] maxBucket = new int[bucketSize];

            // -1表示空桶
            Arrays.fill(minBucket, -1);

            // 放到桶中
            for (int i = 0; i < n; i++) {
                int idx = (nums[i] - min) / d;
                if (minBucket[idx] == -1) {
                    minBucket[idx] = nums[i];
                    maxBucket[idx] = nums[i];
                } else {
                    minBucket[idx] = Math.min(minBucket[idx], nums[i]);
                    maxBucket[idx] = Math.max(maxBucket[idx], nums[i]);
                }
            }

            // 比较相邻的桶，用后一个桶的最小值与前一个桶的最大值之差
            int answer = 0;
            int prevIdx = -1;
            for (int idx = 0; idx < bucketSize; idx++) {
                if (minBucket[idx] == -1) {
                    continue;
                }
                if (prevIdx != -1) {
                    answer = Math.max(answer, minBucket[idx] - maxBucket[prevIdx]);
                }
                prevIdx = idx;
            }
            return answer;
        }
    }
}
