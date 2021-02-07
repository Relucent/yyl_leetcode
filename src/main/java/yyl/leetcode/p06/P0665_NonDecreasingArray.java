package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>非递减数列</h3><br>
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。<br>
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * </pre>
 * 
 * 说明： <br>
 * ├ 1 <= n <= 10 ^ 4 <br>
 * └ - 10 ^ 5 <= nums[i] <= 10 ^ 5 <br>
 */
public class P0665_NonDecreasingArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.checkPossibility(new int[] { 4, 2, 3 }));
        Assert.assertFalse(solution.checkPossibility(new int[] { 4, 2, 1 }));
    }

    // 遍历数组（ 改变元素）
    // 因可以改变一次数组元素的值，那么定义一个变量(modified)用来记录是否已改变元素
    // ├ 遍历数组，判断当前遍历元素是否小于前一个元素
    // └ 如果小于第一个元素(递减)，那么需要改变元素
    // - ├ 如果已经改变过了(modified == true)，不能再改变，则返回 false，
    // - └ 如果没改变，可以进行改变，记录 modified = true，开始进行改变
    // - - ├ 如果小于前一个且小于前二个元素时，那么只能将当前元素改变为前一个元素的值
    // - - └ 如果当前遍历元素仅小于前一个元素且大于等于前二个元素时，那么改变前一个元素的值为当前元素的值
    // 时间复杂度：O(n)，其中 nnn 是数组 nums\textit{nums}nums 的长度。
    // 空间复杂度：O(1)，原数组上进行改变。
    static class Solution {
        public boolean checkPossibility(int[] nums) {
            boolean modified = false;
            for (int i = 1; i < nums.length; i++) {

                // 小于前一个元素时(发现递减)
                if (nums[i] < nums[i - 1]) {

                    // 已经改变过1次了，不能再改变了
                    if (modified) {
                        return false;
                    }

                    modified = true;

                    // 如果小于前一个元素，并且小于前二个元素时，那么只能将当前元素改变为前一个元素的值
                    if (i > 1 && nums[i] < nums[i - 2]) {
                        nums[i] = nums[i - 1];
                    }
                    // 小于前一个元素，并且大于等于前二个元素时，那么只能改变前一个元素的值
                    else {
                        nums[i - 1] = nums[i];
                    }
                }
            }
            return true;
        }
    }
}
