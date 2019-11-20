package yyl.leetcode.p041;

import java.util.Arrays;

/**
 * <h3>缺失的第一个正数</h3><br>
 * 给定一个未排序的整数数组，找出其中没有出现的最小的正整数。<br>
 * 示例 1:<br>
 * 输入: [1,2,0]<br>
 * 输出: 3<br>
 * 示例 2:<br>
 * 输入: [3,4,-1,1]<br>
 * 输出: 2<br>
 * 示例 3:<br>
 * 输入: [7,8,9,11,12]<br>
 * 输出: 1<br>
 * 说明:<br>
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的空间。<br>
 */
public class FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new Solution();
        // System.out.println(solution.firstMissingPositive(new int[] {1, 2, 0}));// 3
        // System.out.println(solution.firstMissingPositive(new int[] {3, 4, -1, 1}));// 2
        // System.out.println(solution.firstMissingPositive(new int[] {7, 8, 9, 11, 12}));// 1
        System.out.println(solution.firstMissingPositive(new int[] {2, 1}));// 1
    }

    static class Solution {

        // 实际的时间复杂度 为 O(2n) ≈ O(n)
        // 这个解法对原数组进行了改动，不过如果要求使用常数级别的空间，只能这么解决
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                int item = nums[i];
                // 调用交换方法，交换元素；然后得到交换后的元素后，继续进行交换
                while (0 < item && item < n && item != nums[item - 1]) {
                    System.err.println("：>" + Arrays.toString(nums));
                    // 交换元素, 记录交换后的元素
                    int temp = nums[item - 1];
                    nums[item - 1] = item;
                    item = nums[i] = temp;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                // 若当前数组元素的值不等于i+1，则直接返回i+1
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            // 当上述循环结束，则返回 (nums.length+1)
            return n + 1;
        }
    }

    static class Solution2 {
        // 这个算法容易理解: 时间复杂度 为 O(2n), 空间是 O(n)
        // 但是要求空间复杂度为常量，所以这个解法不符合题目
        public int firstMissingPositive(int[] nums) {
            int n = nums.length + 1;
            boolean[] bucket = new boolean[n];
            for (int i = 0; i < nums.length; i++) {
                int value = nums[i];
                if (0 < value && value < n) {
                    bucket[value] = true;
                }
            }
            for (int i = 1; i < bucket.length; i++) {
                if (!bucket[i]) {
                    return i;
                }
            }
            return n;
        }
    }
}
