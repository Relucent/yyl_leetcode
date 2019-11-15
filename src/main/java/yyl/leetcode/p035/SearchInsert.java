package yyl.leetcode.p035;

/**
 * <h3>搜索插入位置</h3><br>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。<br>
 * 你可以假设数组中无重复元素。<br>
 * 示例 1:<br>
 * 输入: [1,3,5,6], 5<br>
 * 输出: 2<br>
 * 示例 2:<br>
 * 输入: [1,3,5,6], 2<br>
 * 输出: 1<br>
 * 示例 3:<br>
 * 输入: [1,3,5,6], 7<br>
 * 输出: 4<br>
 * 示例 4:<br>
 * 输入: [1,3,5,6], 0<br>
 * 输出: 0<br>
 */
public class SearchInsert {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(new int[] {1, 3, 5, 6}, 5));// 2
        System.out.println(solution.searchInsert(new int[] {1, 3, 5, 6}, 2));// 1
        System.out.println(solution.searchInsert(new int[] {1, 3, 5, 6}, 7));// 4
        System.out.println(solution.searchInsert(new int[] {1, 3, 5, 6}, 0));// 0
    }

    static class Solution {

        // 二分法问题
        public int searchInsert(int[] nums, int target) {
            // 初始化左右端点位置
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                // 获取中点，如果是偶数取靠左的位置
                int mid = (left + right) / 2;
                // 找到该数,返回位置
                if (nums[mid] == target) {
                    return mid;
                }
                // 如果当前位置数比插入值小,更新左端点
                if (nums[mid] < target) {
                    left = mid + 1;
                }
                // (target < nums[mid])
                // 如果当前位置数比插入值大更新右端点
                else {
                    right = mid - 1;
                }
            }
            // 返回插入位置，这里是左端位置
            return left;
        }
    }
}
