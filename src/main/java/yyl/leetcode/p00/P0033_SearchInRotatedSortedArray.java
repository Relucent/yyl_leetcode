package yyl.leetcode.p00;

/**
 * <h3>搜索旋转排序数组</h3><br>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。<br>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。<br>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。 你可以假设数组中不存在重复的元素。<br>
 * 你的算法时间复杂度必须是 O(log n) 级别。<br>
 * 示例 1:<br>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0<br>
 * 输出: 4<br>
 * 示例 2:<br>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3<br>
 * 输出: -1<br>
 */
public class P0033_SearchInRotatedSortedArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0));// 2
        System.out.println(solution.search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3));// -1
    }

    // 这道题的难点在于算法时间复杂度必须是 O(log n)
    // 很明显这是一道二分搜索的题目, 二分搜索的时间复杂度是 O(1)~ O(log n)，
    // 使用二分搜索要求线性表必须采用顺序存储结构，而且表中元素按关键字有序排列

    // 二分搜索法的关键在于获得了中间数后，判断接下来要搜索左半段还是右半段
    // 如果 mid < right 那么右侧有序,如果 mid > right 那么左侧有序
    // 0 1 2 3 4
    // 4 0 1 2 3
    // 3 4 0 1 2
    // 2 3 4 0 1
    // 1 2 3 4 0

    static class Solution {
        public int search(int[] nums, int target) {
            int n = nums.length;
            int left = 0;
            int right = n - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                if (nums[left] == target) {
                    return left;
                }
                if (nums[right] == target) {
                    return right;
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
                else {
                    if (nums[left] < target && target < nums[mid]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return -1;
        }
    }
}
