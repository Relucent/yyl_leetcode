package yyl.leetcode.p00;

import java.util.Arrays;

/**
 * <h3>移除元素</h3><br>
 * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。<br>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。<br>
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。<br>
 * 示例 1:<br>
 * 给定 nums = [3,2,2,3], val = 3,<br>
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素为 2, 2。<br>
 * 你不需要考虑数组中超出新长度后面的元素。<br>
 * 示例 2:<br>
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,<br>
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。<br>
 * <br>
 * Given an array and a value, remove all instances of that value in place and return the new length.<br>
 * Do not allocate extra space for another array, you must do this in place with constant memory.<br>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.<br>
 * Example:<br>
 * Given input array nums = [3,2,2,3], val = 3<br>
 * Your function should return length = 2, with the first two elements of nums being 2.<br>
 */
public class P0027_RemoveElement {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 2, 3, 5, 7, 9};
        int len = solution.removeElement(nums, 3);
        System.out.println(Arrays.toString(nums));
        System.out.println(len);
    }

    static class Solution {
        public int removeElement(int[] nums, int val) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int k = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != val) {
                    nums[k++] = nums[i];
                }
            }
            return k;
        }
    }
}
