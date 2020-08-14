package yyl.leetcode.p01;

import java.util.Arrays;

/**
 * <h3>两数之和 II - 输入有序数组</h3><br>
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。<br>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。<br>
 * 说明:<br>
 * 返回的下标值（index1 和 index2）不是从零开始的。<br>
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。<br>
 * 
 * <pre>
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * </pre>
 */
public class P0167_TwoSumII_InputArrayIsSorted {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(new int[] { 2, 7, 11, 15 }, 9)));// [1,2]
    }

    // 二分查找
    // 在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。
    // 利用数组的有序性质，可以通过二分查找的方法寻找第二个数。
    // 时间复杂度：O(n*log{n})，其中 n是数组的长度。需要遍历数组一次确定第一个数，时间复杂度是 O(n)，寻找第二个数使用二分查找，时间复杂度是 O(log⁡{n})
    // 空间复杂度：O(1)。
    static class Solution {
        public int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; i++) {
                int left = i + 1;
                int right = numbers.length - 1;
                int key = target - numbers[i];
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (numbers[mid] < key) {
                        left = mid + 1;
                    } else if (numbers[mid] > key) {
                        right = mid - 1;
                    } else {// numbers[mid] == key
                        return new int[] { i + 1, mid + 1 };
                    }
                }
            }
            return null;
        }
    }

    // 双指针
    // 算法思路
    // 1、初始时两个指针分别指向第一个元素位置和最后一个元素的位置
    // 2、每次计算两个指针指向的两个元素之和，并和目标值比较。
    // 3、如果两个元素之和等于目标值，则发现了唯一解。
    // 4、如果两个元素之和小于目标值，则将左侧指针右移一位。
    // 5、如果两个元素之和大于目标值，则将右侧指针左移一位。
    // 6、移动指针之后，重复上述操作，直到找到答案。
    // 扩展说明
    // 使用双指针的实质是缩小查找范围
    // 如果左指针先到达下标 i 的位置，此时右指针还在下标 j 的右侧，sum>target，因此一定是右指针左移，左指针不可能移到 i的右侧。
    // 如果右指针先到达下标 j 的位置，此时左指针还在下标 i 的左侧，sum<target，因此一定是左指针右移，右指针不可能移到 j 的左侧。
    // 因此，在整个移动过程中，左指针不可能移到 i 的右侧，右指针不可能移到 j 的左侧，因此不会把可能的解过滤掉。
    // 由于题目确保有唯一的答案，因此使用双指针一定可以找到答案。
    // 复杂度分析
    // 时间复杂度：O(n)，其中 n 是数组的长度。两个指针移动的总次数最多为 n次。
    // 空间复杂度：O(1)。
    static class Solution1 {
        public int[] twoSum(int[] numbers, int target) {
            int left = 0;
            int right = numbers.length - 1;
            while (left < right) {
                int sum = numbers[left] + numbers[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return new int[] { left + 1, right + 1 };
                }
            }
            return null;
        }
    }
}
