package yyl.leetcode.p081;

/**
 * <h3>搜索旋转排序数组 II</h3><br>
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )<br>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 你可以假设数组中不存在重复的元素。<br>
 * 你的算法时间复杂度必须是 O(log n) 级别。<br>
 * 示例 1:<br>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0<br>
 * 输出: true<br>
 * 示例 2:<br>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3<br>
 * 输出: false<br>
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums 可能包含重复元素<br>
 */
public class SearchRotationSortArrayII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 0));// true
        System.out.println(solution.search(new int[] {2, 5, 6, 0, 0, 1, 2}, 3));// false
    }

    // 这道题的难点在于算法时间复杂度必须是 O(log n)
    // 很明显这是一道二分搜索的题目, 二分搜索的时间复杂度是 O(1)~ O(log n)，
    // 使用二分搜索要求线性表必须采用顺序存储结构，而且表中元素按关键字有序排列

    // 二分搜索法的关键在于获得了中间数后，判断接下来要搜索左半段还是右半段
    // 如果 nums[mid] < nums[right] 那么右侧有序,如果 nums[mid] > nums[right] 那么左侧有序
    // 因为有重复值，可能出现 nums[mid] == nums[right] 的情况，这时候可以 right--，避免掉这种情况
    // 0 1 1 1 2
    // 2 0 1 1 1
    // 1 2 0 1 2
    // 1 1 2 0 1
    // 1 2 1 2 0

    static class Solution {
        public boolean search(int[] nums, int target) {
            int n = nums.length;
            int left = 0;
            int right = n - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[left] == target) {
                    return true;
                }
                if (nums[right] == target) {
                    return true;
                }
                // nums[mid] < nums[right] 右侧有序
                if (nums[mid] < nums[right]) {
                    if (nums[mid] < target && target < nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                // nums[mid] > nums[right] => 那么左侧有序
                else if (nums[mid] > nums[right]) {
                    if (nums[left] < target && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                // 因为有重复值，可能出现 nums[mid] == nums[right] 的情况，这时候可以 right--，避免掉这种情况
                else {
                    right = right - 1;
                }
            }
            return false;
        }
    }
}
