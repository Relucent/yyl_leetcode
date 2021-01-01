package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>种花问题</h3><br>
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。<br>
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。<br>
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * 
 * 示例 2:
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 * </pre>
 * 
 * 注意: <br>
 * ├数组内已种好的花不会违反种植规则。<br>
 * ├输入的数组长度范围为 [1, 20000]。<br>
 * └ n 是非负整数，且不会超过输入数组的大小。<br>
 */
public class P0605_CanPlaceFlowers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.canPlaceFlowers(new int[] { 1, 0, 0, 0, 1 }, 1));
        Assert.assertFalse(solution.canPlaceFlowers(new int[] { 1, 0, 0, 0, 1 }, 2));
        Assert.assertFalse(solution.canPlaceFlowers(new int[] { 1, 0, 0, 0, 0, 1 }, 2));
    }

    // 贪心
    // 从贪心的角度考虑，应该在不打破种植规则的情况下种入尽可能多的花，然后判断可以种入的花的最多数量是否大于或等于 n。
    // 需要一个变量记录前一个位置是否满足条件（已经种了花，或者可以种花）
    // 循环数组，判断该位置是否可以种入花，最后记录可以种入花的数量。
    // 时间复杂度：O(m)，其中 m 是数组 flowerbed 的长度。需要遍历数组一次。
    // 空间复杂度：O(1)，使用的空间为常数。
    static class Solution {
        public boolean canPlaceFlowers(int[] flowerbed, int n) {
            int answer = 0;
            int m = flowerbed.length;
            // 前一个位置是否满足
            boolean prev = true;
            for (int i = 0; i < flowerbed.length; i++) {
                // 后一个位置是否满足
                boolean next = (i + 1 == m) ? true : flowerbed[i + 1] == 0;
                // 前中后位置都满足条件, 当前可以种个小花
                if (flowerbed[i] == 0 && prev && next) {
                    answer++;
                    prev = false;
                } else {
                    prev = flowerbed[i] == 0;
                }
            }
            return answer >= n;
        }
    }
}
