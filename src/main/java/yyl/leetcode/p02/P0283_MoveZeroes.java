package yyl.leetcode.p02;

import yyl.leetcode.util.Assert;

/**
 * <h3>移动零</h3><br>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。<br>
 * 
 * <pre>
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * </pre>
 * 
 * 说明:<br>
 * 必须在原数组上操作，不能拷贝额外的数组。<br>
 * 尽量减少操作次数。<br>
 */
public class P0283_MoveZeroes {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] sample = { 0, 1, 0, 3, 12 };
        int[] expected = { 1, 3, 12, 0, 0 };
        solution.moveZeroes(sample);
        Assert.assertEquals(expected, sample);
    }

    // 双指针
    // 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
    // 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
    // 左指针左边均为非零数；右指针左边直到左指针处均为零。
    // 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
    // 时间复杂度：O(n)，其中 n 为序列长度。
    // 空间复杂度：O(1)。
    static class Solution {
        public void moveZeroes(int[] nums) {
            int n = nums.length;
            for (int left = 0, right = 0; right < n; right++) {
                if (nums[right] != 0) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    left++;
                }
            }
        }
    }
}
