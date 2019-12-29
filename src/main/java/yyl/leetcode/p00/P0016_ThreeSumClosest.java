package yyl.leetcode.p00;

import java.util.Arrays;

/**
 * <h3>最接近的三数之和</h3><br>
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。<br>
 * 返回这三个数的和。假定每组输入只存在唯一答案。<br>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.<br>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).<br>
 * <br>
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution. <br>
 * For example, given array S = {-1 2 1 -4}, and target = 1. <br>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).<br>
 */
public class P0016_ThreeSumClosest {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSumClosest(new int[] {1, 1, 1}, 0));// 3
        System.out.println(solution.threeSumClosest(new int[] {-1, 2, 1, -4}, 1));// 2
        System.out.println(solution.threeSumClosest(new int[] {-55, -24, -18, -11, -7, -3, 4, 5, 6, 9, 11, 23, 33}, 0));// 0
        System.out.println(solution.threeSumClosest(new int[] {0, 2, 1, -3}, 1));// 0
    }

    // 双指针法的优化
    // 先让数组有序(对数组排序)，然后每次固定一个元素，再去寻找另外两个元素
    // 优化部分：
    // 1.元素重复的问题
    // 2.超越界限的问题
    static class Solution {
        public int threeSumClosest(int[] nums, int target) {

            // 利用 Arrays.sort对数组进行排序
            Arrays.sort(nums);
            // 初始化一个用于保存结果的值 result = nusm[0] + nums[1] + nums[2]
            int result = nums[0] + nums[1] + nums[2];

            // 利用下标 i 对数组进行遍历，此时就是在固定第一个元素，注意，下标 i 的边界为 i < nums.length-2，否则设置指针的时候会出现数组越界
            for (int i = 0; i < nums.length - 2; i++) {

                // 每次遍历的过程中设置两个指针，分别是 left = i + 1、right = nums.length - 1
                int left = i + 1;
                int right = nums.length - 1;

                // 双指针遍历 (如果 left==right，说明我们已经将所有的元素都遍历过一遍了)
                while (left < right) {
                    // 判断最小值(优化点)
                    int min = nums[i] + nums[left] + nums[left + 1];
                    if (target < min) {
                        if (Math.abs(result - target) > Math.abs(min - target)) {
                            result = min;
                        }
                        break;
                    }

                    // 判断最大值(优化点)
                    int max = nums[i] + nums[right] + nums[right - 1];
                    if (target > max) {
                        if (Math.abs(result - target) > Math.abs(max - target)) {
                            result = max;
                        }
                        break;
                    }

                    // 获得三个数之和
                    int sum = nums[i] + nums[left] + nums[right];
                    // 如果 sum 之前保存的 result与 target 更接近就更新 result
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                    // 移动双指针
                    // 如果 sum 的值比 target 大，那么 right--
                    if (sum > target) {
                        right--;
                        // 元素重复，继续移动指针(优化点)
                        while (left != right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }
                    // 如果 sum 的值 target 小，那么 left++
                    else {
                        left++;
                        // 元素重复，继续移动指针(优化点)
                        while (left != right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                    }
                }
            }
            return result;
        }
    }

    // 双指针法
    // 先让数组有序(对数组排序)，然后每次固定一个元素，再去寻找另外两个元素
    static class Solution2 {
        public int threeSumClosest(int[] nums, int target) {
            // 利用 Arrays.sort对数组进行排序
            Arrays.sort(nums);
            // 初始化一个用于保存结果的值 result = nusm[0] + nums[1] + nums[2]
            int result = nums[0] + nums[1] + nums[2];
            // 利用下标 i 对数组进行遍历，此时就是在固定第一个元素，注意，下标 i 的边界为 i < nums.length-2，否则设置指针的时候会出现数组越界
            for (int i = 0; i < nums.length; i++) {
                // 每次遍历的过程中设置两个指针，分别是 left = i + 1、right = nums.length - 1
                int left = i + 1;
                int right = nums.length - 1;
                // 双指针遍历 (如果 left==right，说明我们已经将所有的元素都遍历过一遍了)
                while (left < right) {
                    // 获得三个数之和 sum = nums[i] + nums[left] + nums[right]
                    int sum = nums[i] + nums[left] + nums[right];

                    // 如果三个数之和与目标相等那么直接返回(相等必然最接近)
                    if (sum == target) {
                        return sum;
                    }
                    // 如果 sum 之前保存的 result与 target 更接近就更新 result
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                    // 移动双指针
                    // 如果 sum 的值比 target 大，那么 right--，因为数组是有序的，right --会使得下一次的 sum 更小，也就更接近 target 的值
                    if (sum > target) {
                        right--;
                    }
                    // 否则，如果 sum 的值 target 小，那么 left++
                    else {
                        left++;
                    }
                }
            }
            return result;
        }
    }
}
