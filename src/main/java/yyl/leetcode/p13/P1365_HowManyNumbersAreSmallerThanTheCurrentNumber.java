package yyl.leetcode.p13;

import yyl.leetcode.util.Assert;

/**
 * <h3>有多少小于当前数字的数字</h3><br>
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。<br>
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。<br>
 * 以数组形式返回答案。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释： 
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。 
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。 
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。 
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 
 * 示例 2：
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 
 * 示例 3：
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * </pre>
 * 
 * 提示： <br>
 * 2 <= nums.length <= 500 <br>
 * 0 <= nums[i] <= 100 <br>
 */
public class P1365_HowManyNumbersAreSmallerThanTheCurrentNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(solution.smallerNumbersThanCurrent(new int[] { 8, 1, 2, 2, 3 }), new int[] { 4, 0, 1, 1, 3 });
        Assert.assertEquals(solution.smallerNumbersThanCurrent(new int[] { 6, 5, 4, 8 }), new int[] { 2, 1, 0, 3 });
        Assert.assertEquals(solution.smallerNumbersThanCurrent(new int[] { 7, 7, 7, 7 }), new int[] { 0, 0, 0, 0 });
    }

    // 计数排序
    // 由于数组元素的值域为 [0,100]，可以先统计所有元素出现的次数，然后计算所有出现次数的前缀和
    // 时间复杂度：O(N+K)，其中 K 为值域大小。需要遍历两次原数组，同时遍历一次频次数组 prefix找出前缀和。
    // 空间复杂度：O(K)。因为要额外开辟一个值域大小的数组。
    static class Solution {
        public int[] smallerNumbersThanCurrent(int[] nums) {
            int[] prefix = new int[102];
            for (int num : nums) {
                prefix[num + 1]++;
            }
            for (int i = 1; i < prefix.length; i++) {
                prefix[i] += prefix[i - 1];
            }
            int[] answer = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                answer[i] = prefix[nums[i]];
            }
            return answer;
        }
    }
}
