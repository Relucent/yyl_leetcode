package yyl.leetcode.p031;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。<br>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。<br>
 * 必须原地修改，只允许使用额外常数空间。<br>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。<br>
 * 1,2,3 → 1,3,2<br>
 * 3,2,1 → 1,2,3<br>
 * 1,1,5 → 1,5,1<br>
 * 0,1,2,2,1,0 → 0,2,0,1,1,2<br>
 * 5,4,7,5,3,2 → 5,5,2,3,4,7<br>
 */
public class NextPermutation {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] sample = {{1, 2, 3}, {3, 2, 1}, {1, 1, 5}, {0, 1, 2, 2, 1, 0}, {5, 4, 7, 5, 3, 2}};
        for (int[] nums : sample) {
            solution.nextPermutation(nums);
            System.out.println(Arrays.toString(nums));
        }
    }

    private static class Solution {

        public void nextPermutation(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }

            // 从后往前扫描找到第一个下标i,使得nums[i] < nums[i + 1]; (递增的段)
            int i = nums.length - 2;
            for (; i >= 0; i--) {
                if (nums[i] < nums[i + 1]) {
                    break;
                }
            }
            // 若i = -1，表示该数组为单调递减序列
            // 否则，从后往前扫描找到找到下标j使得nums[j] > nums[i]，交换两数
            // 交换之后 nums(j, END]的数据是不变的
            if (i != -1) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[i] < nums[j]) {
                        swap(nums, i, j);
                        break;
                    }
                }
            }
            // 翻转 nums[i+1, END]使其成为单调增序列
            reverse(nums, i + 1, nums.length - 1);
        }

        private void reverse(int[] nums, int i, int j) {
            for (; i < j;) {
                swap(nums, i++, j--);
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
