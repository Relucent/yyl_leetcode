package yyl.leetcode.p12;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import yyl.leetcode.util.Assert;

/**
 * <h3>独一无二的出现次数</h3><br>
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。<br>
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * 
 * 示例 2：
 * 输入：arr = [1,2]
 * 输出：false
 * 
 * 示例 3：
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 * </pre>
 * 
 * 提示： <br>
 * 1 <= arr.length <= 1000 <br>
 * -1000 <= arr[i] <= 1000 <br>
 */
public class P1207_UniqueNumberOfOccurrences {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertTrue(solution.uniqueOccurrences(new int[] { 1, 2, 2, 1, 1, 3 }));
        Assert.assertFalse(solution.uniqueOccurrences(new int[] { 1, 2 }));
        Assert.assertTrue(solution.uniqueOccurrences(new int[] { -3, 0, 1, -3, 1, 1, 1, -3, 10, 0 }));
    }

    // 哈希表
    // 首先使用哈希表记录每个数字的出现次数；随后再利用新的哈希表，统计不同的出现次数的数目。如果不同的出现次数的数目等于不同数字的数目，则返回 true，否则返回 false。
    // 时间复杂度：O(N)，其中 N 为数组的长度。遍历原始数组需要 O(N) 时间，而遍历中间过程产生的哈希表又需要 O(N) 的时间。
    // 空间复杂度：O(N)。
    static class Solution {
        public boolean uniqueOccurrences(int[] arr) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : arr) {
                countMap.put(num, countMap.getOrDefault(num, 0) + 1);
            }
            Set<Integer> set = new HashSet<>();
            for (int count : countMap.values()) {
                if (!set.add(count)) {
                    return false;
                }
            }
            return true;
        }
    }
}
