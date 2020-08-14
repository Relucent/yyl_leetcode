package yyl.leetcode.p02;

import java.util.Arrays;

/**
 * <h3>长度最小的子数组</h3><br>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。<br>
 * 
 * <pre>
 * 示例: 
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * </pre>
 * 
 * 进阶: 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。<br>
 */
public class P0209_MinimumSizeSubarraySum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[] { 2, 3, 1, 2, 4, 3 }));
    }

    // 双指针(滑动窗口)
    // 使用 2 个指针，一个指向数组开始的位置，一个指向数组最后的位置，并维护区间内的和 sum 大于等于 s 同时数组长度最小。
    // 时间复杂度： O(N)，N为数组长度
    // 时间复杂度： O(1)
    static class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            int answer = Integer.MAX_VALUE;
            int sum = 0;
            for (int left = 0, right = 0; right < nums.length; right++) {
                sum = sum + nums[right];
                while (sum >= s && left < nums.length) {
                    answer = Math.min(answer, right - left + 1);
                    sum = sum - nums[left++];
                }
            }
            return answer == Integer.MAX_VALUE ? 0 : answer;
        }
    }

    // 暴力法
    // 时间复杂度： O(N^3)，N为数组长度
    // 时间复杂度： O(1)
    static class Solution1 {
        public int minSubArrayLen(int s, int[] nums) {
            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    int sum = 0;
                    for (int k = i; k <= j; k++) {
                        sum += nums[k];
                    }
                    if (sum >= s) {
                        answer = Math.min(answer, j - i + 1);
                    }
                }
            }
            return (answer != Integer.MAX_VALUE) ? answer : 0;
        }
    }

    // 暴力法(优化)
    // 对求子数组的和部分进行优化
    // 使用一个sums数组表示累加和：sums[x]表示 [0~x)的和
    // 然后通过 sums[j]-sums[i]+nums[i]求出[i~j]子区间的和
    // 时间复杂度： O(N^2)，N为数组长度
    // 时间复杂度： O(N)
    static class Solution2 {
        public int minSubArrayLen(int s, int[] nums) {
            int answer = Integer.MAX_VALUE;
            int[] sums = new int[nums.length];
            for (int i = 1; i < nums.length; i++) {
                sums[i] = sums[i - 1] + nums[i];
            }
            for (int i = 0; i < nums.length; i++) {
                for (int j = i; j < nums.length; j++) {
                    int sum = sums[j] - sums[i] + nums[i];
                    if (sum >= s) {
                        answer = Math.min(answer, j - i + 1);
                    }
                }
            }
            return (answer != Integer.MAX_VALUE) ? answer : 0;
        }
    }

    // 前缀和 + 二分查找
    // 定义临时数组sums，其中sums[i]表示的是原数组nums前i个元素的和，题目规定元素是正整数，那么相加的和会越来越大，也就是sums数组中的元素是递增的。
    // 只需要找到sums[k]-sums[j]>=s，那么k-j就是满足的连续子数组，但不一定是最小的，所以我们要继续找，直到找到最小的为止。
    // 时间复杂度： O(NlogN)，N为数组长度
    // 时间复杂度： O(1)
    static class Solution3 {
        public int minSubArrayLen(int s, int[] nums) {
            int answer = Integer.MAX_VALUE;
            int n = nums.length;
            // sums[0] = 前 0 个元素的前缀和
            // sums[1] = 前 1 个元素的前缀和
            // sums[n] = 前 n 个元素的前缀和
            int[] sums = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
            for (int i = 1; i <= n; i++) {
                int target = s + sums[i - 1];
                int index = Arrays.binarySearch(sums, target);

                // 没找到关键字，返回值为负的插入点值（第一个比关键字大的元素在数组中的位置索引，位置索引从1开始）
                if (index < 0) {
                    // 负的插入点，所以需要转换成正数，位置索引从1开始所以需要 -1
                    index = -index - 1;
                }
                // 如果没找到的情况，负插入点绝对值可能大于 n
                if (index <= n) {
                    answer = Math.min(answer, index - i + 1);
                }
            }
            return (answer != Integer.MAX_VALUE) ? answer : 0;
        }
    }
}
