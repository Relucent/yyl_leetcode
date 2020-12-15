package yyl.leetcode.p07;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>单调递增的数字
 * <h3><br>
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。<br>
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 * 
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 * 
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 * </pre>
 * 
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。<br>
 */
public class P0738_MonotoneIncreasingDigits {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(9, solution.monotoneIncreasingDigits(10));
        Assert.assertEquals(1234, solution.monotoneIncreasingDigits(1234));
        Assert.assertEquals(299, solution.monotoneIncreasingDigits(332));
        Assert.assertEquals(99, solution.monotoneIncreasingDigits(100));
    }

    // 贪心
    // 从高到低按位构造这个小于等于 N 的最大单调递增的数字。假设不考虑 N 的限制，那么对于一个长度为 n 的数字，最大单调递增的数字一定是每一位都为 9 的数字。
    // ├ 找到第一个位置 i 使得 [0,i−1] 的数位单调递增且 strN[i−1]>strN[i] ，此时 [0,i] 的数位都与 N 的对应数位相等。
    // ├ 从贪心的角度考虑，需要尽量让高位与 N 的对应数位相等，故尝试让 strN[i−1] 自身数位减 1， 让剩余的低位全部变成 9；
    // ├ 因为 strN[i−1] 自身数位减 1 后可能会使得 strN[i−1] 和 strN[i−2] 不再满足递增的关系；
    // ├ 因此需要从 i−1 开始递减比较相邻数位的关系，直到找到第一个位置 j 使得 strN[j] 自身数位减 1 后 strN[j−1]和 strN[j] 仍然保持递增关系，或者位置 j 已经到最左边（即j==0）；
    // └ 此时将 [j+1,n−1] 的数全部变为 9 得到最终正确的答案。
    // 时间复杂度：O(log⁡N)，其中 O(log⁡N) 表示数字 N 的位数。遍历 O(log⁡N) 的时间即能构造出满足条件的数字。
    // 空间复杂度：O(⁡N)。需要 O(⁡N) 的空间存放数字 N 每一位的数字大小。
    static class Solution {
        public int monotoneIncreasingDigits(int N) {
            char[] strN = Integer.toString(N).toCharArray();
            int i = 1;
            while (i < strN.length && strN[i - 1] <= strN[i]) {
                i++;
            }
            if (i < strN.length) {
                while (i > 0 && strN[i - 1] > strN[i]) {
                    strN[i - 1]--;
                    i -= 1;
                }
                Arrays.fill(strN, i + 1, strN.length, '9');
            }
            return Integer.parseInt(new String(strN), 10);
        }
    }
}
