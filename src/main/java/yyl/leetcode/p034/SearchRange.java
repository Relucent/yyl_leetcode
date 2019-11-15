package yyl.leetcode.p034;

import java.util.Arrays;

/**
 * <h3>在排序数组中查找元素的第一个和最后一个位置</h3><br>
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。<br>
 * 你的算法时间复杂度必须是 O(log n) 级别。<br>
 * 如果数组中不存在目标值，返回 [-1, -1]。<br>
 * 示例 1:<br>
 * 输入: nums = [5,7,7,8,8,10], target = 8<br>
 * 输出: [3,4]<br>
 * 示例 2:<br>
 * 输入: nums = [5,7,7,8,8,10], target = 6<br>
 * 输出: [-1,-1]<br>
 */
public class SearchRange {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8)));// [3,4]
        System.out.println(Arrays.toString(solution.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6)));// [-1,-1]
    }

    // 二分搜索的时间复杂度是 O(1)~ O(log n)，
    static class Solution {
        public int[] searchRange(int[] nums, int target) {
            int n = nums.length;
            int left = 0;
            int right = n - 1;

            // 二分法搜索到 index
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    int start = 0;
                    int end = n - 1;
                    left = mid;
                    right = mid;

                    // 使用二分算法查找最左侧匹配
                    while (start < left) {
                        int temp = (start + left) / 2;
                        if (nums[temp] == target) {
                            left = temp;
                        } else {
                            start = temp + 1;
                        }
                    }
                    // 使用二分算法查找最右侧匹配
                    while (right < end) {
                        // 取中位数 +1是为了偶数个数向上取整
                        int temp = (right + end + 1) / 2;
                        if (nums[temp] == target) {
                            right = temp;
                        } else {
                            end = temp - 1;
                        }
                    }
                    return new int[] {left, right};
                }
                if (nums[mid] < target) {
                    left = mid + 1;
                }
                // (target < nums[mid])
                else {
                    right = mid - 1;
                }
            }
            return new int[] {-1, -1};
        }
    }
}
