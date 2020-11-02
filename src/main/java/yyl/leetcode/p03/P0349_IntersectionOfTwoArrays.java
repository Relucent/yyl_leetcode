package yyl.leetcode.p03;

import java.util.HashSet;
import java.util.Set;

import yyl.leetcode.util.Assert;

/**
 * <h3>两个数组的交集</h3><br>
 * 给定两个数组，编写一个函数来计算它们的交集。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * </pre>
 * 
 * 说明：<br>
 * 输出结果中的每个元素一定是唯一的。<br>
 * 我们可以不考虑输出结果的顺序。<br>
 */
public class P0349_IntersectionOfTwoArrays {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 2 }, solution.intersection(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 }));
        Assert.assertEquals(new int[] { 9, 4 }, solution.intersection(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 }));
    }

    // 哈希法
    // 如果使用哈希集合存储元素，则可以在 O(1)O(1)O(1) 的时间内判断一个元素是否在集合中，从而降低时间复杂度。
    // 时间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。
    // 空间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。
    static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set1 = new HashSet<>();
            Set<Integer> set2 = new HashSet<>();
            for (int num : nums1) {
                set1.add(num);
            }
            for (int num : nums2) {
                if (set1.contains(num)) {
                    set2.add(num);
                }
            }
            int[] answer = new int[set2.size()];
            int index = 0;
            for (int num : set2) {
                answer[index++] = num;
            }
            return answer;
        }
    }
}
