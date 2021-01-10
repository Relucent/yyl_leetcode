package yyl.leetcode.p02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>汇总区间</h3><br>
 * 给定一个无重复元素的有序整数数组 nums 。<br>
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。<br>
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：<br>
 * ├ "a->b" ，如果 a != b <br>
 * └ "a" ，如果 a == b <br>
 * 
 * <pre>
 * 示例 1：
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * 解释：区间范围是：
 * [0,2] --> "0->2"
 * [4,5] --> "4->5"
 * [7,7] --> "7"
 * 
 * 示例 2：
 * 输入：nums = [0,2,3,4,6,8,9]
 * 输出：["0","2->4","6","8->9"]
 * 解释：区间范围是：
 * [0,0] --> "0"
 * [2,4] --> "2->4"
 * [6,6] --> "6"
 * [8,9] --> "8->9"
 * 
 * 示例 3：
 * 输入：nums = []
 * 输出：[]
 * 
 * 示例 4：
 * 输入：nums = [-1]
 * 输出：["-1"]
 * 
 * 示例 5：
 * 输入：nums = [0]
 * 输出：["0"]
 * </pre>
 * 
 * 提示：<br>
 * ├ 0 <= nums.length <= 20 <br>
 * ├ -231 <= nums[i] <= 231 - 1 <br>
 * ├ nums 中的所有值都 互不相同 <br>
 * └ nums 按升序排列 <br>
 */
public class P0228_SummaryRanges {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(Arrays.asList("0->2", "4->5", "7"), solution.summaryRanges(new int[] { 0, 1, 2, 4, 5, 7 }));
        Assert.assertEquals(Arrays.asList("0", "2->4", "6", "8->9"), solution.summaryRanges(new int[] { 0, 2, 3, 4, 6, 8, 9 }));
        Assert.assertEquals(Arrays.asList(), solution.summaryRanges(new int[] {}));
        Assert.assertEquals(Arrays.asList("-1"), solution.summaryRanges(new int[] { -1 }));
        Assert.assertEquals(Arrays.asList("0"), solution.summaryRanges(new int[] { 0 }));
    }

    // 一次遍历
    // 从数组的位置 0 出发，向右遍历。每次遇到相邻元素之间的差值大于 1 时，就找到了一个区间。遍历完数组之后，就能得到一系列的区间的列表。
    // 在遍历过程中，维护 begin 和 end 分别记录区间的起点和终点，对于任何区间都有 begin≤end。
    // 当得到一个区间时，根据 begin 和 end的值生成区间的字符串表示。
    // ├ 当 begin = end 时 ，区间的字符串表示为 begin
    // └ 当 begin < end 时，区间的字符串表示为 begin->end
    // 时间复杂度：O(n)，其中 n 为数组的长度。
    // 空间复杂度：O(1)。
    static class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> answer = new ArrayList<>();
            int i = 0;
            int n = nums.length;
            while (i < n) {
                int begin = i;
                i++;
                while (i < n && nums[i] == nums[i - 1] + 1) {
                    i++;
                }
                int end = i - 1;
                if (begin == end) {
                    answer.add(Integer.toString(nums[begin]));
                } else {
                    answer.add(nums[begin] + "->" + nums[end]);
                }
            }
            return answer;
        }
    }
}
