package yyl.leetcode.p03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>两个数组的交集 II</h3><br>
 * 给定两个数组，编写一个函数来计算它们的交集。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 
 * 说明：
 *     输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 *     我们可以不考虑输出结果的顺序。
 * 
 * 进阶:
 *     如果给定的数组已经排好序呢？你将如何优化你的算法？
 *     如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 *     如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 * </pre>
 */
public class P0350_IntersectionOfTwoArraysII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.intersect(new int[] { 1, 3, 5, 7, 9 }, new int[] { 2, 4, 6, 8, 0 })));// []
        System.out.println(Arrays.toString(solution.intersect(new int[] { 1, 2, 2, 1 }, new int[] { 2, 2 })));// [2, 2]
        System.out.println(Arrays.toString(solution.intersect(new int[] { 4, 9, 5 }, new int[] { 9, 4, 9, 8, 4 })));// [4, 9]
        System.out.println(Arrays.toString(solution.intersect(new int[] { 1 }, new int[] { 1 })));// [1]
        System.out.println(Arrays.toString(solution.intersect(new int[0], new int[] { 1, 2, 3 })));// []
    }

    // 排序法+双指针
    // 如果两个数组是有序的，则可以便捷地计算两个数组的交集。
    // 首先对两个数组进行排序，然后使用两个指针遍历两个数组。
    // 初始时，两个指针分别指向两个数组的头部，每次比较两个指针指向的两个数组中的数字。
    // 1、如果两个数字不相等，则将指向较小数字的指针右移一位；
    // 2、，如果两个数字相果两个数字相等，将该数字添加到答案，并将两个指针都右移一位。
    // 3、 当至少有一个指针超出数组范围时，遍历结束。
    // 时间复杂度：O(mlog⁡m+nlog⁡n)，其中 m 和 n分别是两个数组的长度。对两个数组进行排序的时间复杂度是 O(mlog⁡m+nlog⁡n)，遍历两个数组的时间复杂度是 O(m+n)
    // 空间复杂度：O(min⁡(m,n))，空间复杂度为其中较短的数组的长度
    static class Solution {
        public int[] intersect(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int[] answer = new int[Math.min(nums1.length, nums2.length)];
            int index = 0;
            for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
                if (nums1[i] == nums2[j]) {
                    answer[index++] = nums1[i];
                    i++;
                    j++;
                } else if (nums1[i] < nums2[j]) {
                    i++;
                } else if (nums1[i] > nums2[j]) {
                    j++;
                }
            }
            return Arrays.copyOfRange(answer, 0, index);
        }
    }

    // 哈希表
    // 由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
    // 首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。
    // 为了降低空间复杂度，可以遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集。
    // 时间复杂度：O(m+n)，其中 m 和 n 分别是两个数组的长度。需要遍历两个数组并对哈希表进行操作，哈希表操作的时间复杂度是 O(1)，因此总时间复杂度与两个数组的长度和呈线性关系。
    // 空间复杂度：O(min⁡(m,n))，其中 m 和n 分别是两个数组的长度。对较短的数组进行哈希表的操作，哈希表的大小不会超过较短的数组的长度。为返回值创建一个数组 intersection，其长度为较短的数组的长度。
    static class Solution1 {
        public int[] intersect(int[] nums1, int[] nums2) {
            if (nums1.length == 0 || nums2.length == 0) {
                return new int[0];
            }
            if (nums1.length > nums2.length) {
                return intersect(nums2, nums1);
            }

            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int num : nums1) {
                int count = map.getOrDefault(num, 0) + 1;
                map.put(num, count);
            }

            int[] answer = new int[nums1.length];
            int index = 0;
            for (int num : nums2) {
                int count = map.getOrDefault(num, 0);
                if (count > 0) {
                    map.put(num, count - 1);
                    answer[index++] = num;
                }
            }
            return Arrays.copyOfRange(answer, 0, index);
        }
    }
}
