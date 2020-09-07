package yyl.leetcode.p03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import yyl.leetcode.util.Assert;

/**
 * <h3>前 K 个高频元素</h3><br>
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * </pre>
 * 
 * 提示：<br>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。<br>
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。<br>
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。<br>
 * 你可以按任意顺序返回答案。<br>
 */
public class P0347_TopKFrequentElements {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 1, 2 }, solution.topKFrequent(new int[] { 1, 1, 1, 2, 2, 3 }, 2));
        Assert.assertEquals(new int[] { 1 }, solution.topKFrequent(new int[] { 1 }, 1));
    }

    // 桶排序法
    // 使用哈希表统计频率；使用桶存储频率与的数字集合的关系；最后对数组从后向前遍历，得到答案。
    // 时间复杂度：O(n)
    // 空间复杂度：O(n)
    static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // 统计每个数字出现的次数
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int num : nums) {
                freqMap.compute(num, (key, value) -> value = value == null ? 1 : value + 1);
            }
            // 创建桶
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] buckets = new ArrayList[nums.length + 1];
            for (int num : freqMap.keySet()) {
                int freq = freqMap.get(num);
                if (buckets[freq] == null) {
                    buckets[freq] = new ArrayList<>();
                }
                buckets[freq].add(num);
            }
            // 按照出现频次，从大到小遍历
            int[] answer = new int[k];
            for (int i = buckets.length - 1, j = 0; i >= 0 && j < k; i--) {
                if (buckets[i] == null) {
                    continue;
                }
                for (int num : buckets[i]) {
                    answer[j++] = num;
                }
            }
            return answer;
        }
    }
}
