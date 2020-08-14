package yyl.leetcode.p01;

/**
 * <h3>寻找旋转排序数组中的最小值 II</h3><br>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。<br>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。<br>
 * 请找出其中最小的元素。<br>
 * 注意数组中可能存在重复的元素。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入: [1,3,5]
 * 输出: 1
 * 
 * 示例 2：
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * </pre>
 */
public class P0154_FindMinimumInRotatedSortedArrayII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMin(new int[] { 1, 3, 5 }));// 1
        System.out.println(solution.findMin(new int[] { 2, 2, 2, 0, 1 }));// 0
    }

    // 二分查找
    // 二分查找算法的重点在于如何更新左右边界指针
    // 1、当中间值大于最右边值的时候，最小值一定在mid和right中间，移动左指针 left=mid+1；（mid大于rigth，最小值不可能是mid，所以mid+1）
    // 2、当中间值小于最右边值的时候，最小值一定在left和mid之间，移动右指针 right=mid；（mid小于rigth，最小值可能正好是是mid）
    // 3、当中间值等于最右边值的时候（重复元素），移动右指针左移(right--)，因为right的一定大于等于最小值
    // 时间复杂度：O(log⁡{N})，其中N为数组长度，但是在最坏情况下，最坏的情况（数组中元素都相同），需要逐个遍历元素，复杂度为 O(N)
    // 空间复杂度：O(1)
    static class Solution {
        public int findMin(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else if (nums[mid] < nums[right]) {
                    right = mid;
                } else {
                    right--;
                }
            }
            return nums[left];
        }
    }
}
