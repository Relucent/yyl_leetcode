package yyl.leetcode.p00;

import java.util.Arrays;

/**
 * <h3>加一</h3><br>
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。<br>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。<br>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * </pre>
 */

public class P0066_PlusOne {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] samples = {//
                {1, 2, 3}, //
                {1, 1, 9}, //
                {1, 9, 9}, //
                {3, 2, 1}, //
                {9, 9, 9},//
        };
        for (int[] sample : samples) {
            System.out.println(Arrays.toString(sample) + " + [1] = " + Arrays.toString(solution.plusOne(sample)));
        }
    }

    // 数学解题，模拟竖式计算进位
    // 因为只是加一，所以可能的情况就只有两种，需要进位(9),不需要进位(0~8)
    // 需要注意的是，如果出现 999的情况，进位到最后需要增加一位(这时候除了第一位是1，其他位都是0)
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    private static class Solution {
        public int[] plusOne(int[] digits) {
            for (int i = digits.length - 1; i >= 0; i--) {
                digits[i]++;// +1
                if (digits[i] < 10) {
                    return digits;
                }
                digits[i] = digits[i] % 10;
            }
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }
    }
}
