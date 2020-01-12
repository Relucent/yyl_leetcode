package yyl.leetcode.p00;

import java.util.Arrays;

/**
 * <h3>删除排序数组中的重复项 II</h3><br>
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。<br>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。<br>
 * 
 * <pre>
 * 示例 1:
 * 给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * 
 * 示例 2:
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 * </pre>
 */
public class P0080_RemoveDuplicatesFromSortedArrayII {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        int result = solution.removeDuplicates(nums);
        System.out.println(result + ":" + Arrays.toString(nums));
    }

    // 双指针单次扫描
    // 快指针：遍历整个数组；
    // 慢指针：记录可以覆写数据的位置；
    // 题目中规定每个元素最多出现两次，因此，检查快指针的元素和慢指针指针向前两位置的元素是否相等。
    // (因为数组是顺序的，如果向前两个位置的元素不相等，那么向前一个位置的元素也一定不相等)
    // 相等则不更新慢指针，只更新快指针；不相等，将快指针指向的元素覆写入慢指针指向的单元，最后更新快指针。
    // 时间复杂度：O(N)，N为数组长度
    // 空间复杂度：O(1)

    static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums == null) {
                return 0;
            }
            if (nums.length < 2) {
                return nums.length;
            }
            int k = 2;
            for (int i = 2; i < nums.length; i++) {
                if (nums[k - 2] != nums[i]) {
                    nums[k++] = nums[i];
                }
            }
            return k;
        }
    }
}
