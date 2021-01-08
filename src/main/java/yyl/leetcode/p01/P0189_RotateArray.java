package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>旋转数组</h3><br>
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释: 
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * </pre>
 * 
 * 说明:<br>
 * ├ 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。<br>
 * └ 要求使用空间复杂度为 O(1) 的 原地 算法。<br>
 */
public class P0189_RotateArray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        {
            int[] actual = { 1, 2, 3, 4, 5, 6, 7 };
            solution.rotate(actual, 3);
            int[] expected = { 5, 6, 7, 1, 2, 3, 4 };
            Assert.assertEquals(expected, actual);
        }
        {
            int[] actual = { -1, -100, 3, 99 };
            solution.rotate(actual, 2);
            int[] expected = { 3, 99, -1, -100 };
            Assert.assertEquals(expected, actual);
        }
    }

    // 模拟法
    // 每次将数组中的元素向右移动一位，移动 k 次
    // 时间复杂度 ：O(n*(n%k))，n是数组长度，k是移动次数
    // 空间复杂度 ：O(1)
    static class Solution {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            if (n < 2) {
                return;
            }
            k = k % n;
            for (int i = 0; i < k; i++) {
                int tail = nums[n - 1];
                System.arraycopy(nums, 0, nums, 1, n - 1);
                nums[0] = tail;
            }
        }
    }

    // 数组翻转法
    // 旋转数组有一个规律，将数组的元素向右移动 k 次后，尾部 k%n 个元素会移动至数组头部，其余元素向后移动 k%n 个位置。
    // 例如 n=7，k=3为例
    // 原始数组 ：[1,2,3,4,5,6,7]
    // 翻转数组 ：[7,6,5,4,3,2,1]
    // 翻转 [0,k%n−1] 区间的元素 [5,6,7,4,3,2,1]
    // 翻转 [k%n,n−1] 区间的元素 [5,6,7,1,2,3,4]
    // 算法：先将所有元素翻转，这样尾部的 k%n 个元素就被移至数组头部，然后再翻转 [0,k%n−1] 区间的元素和 [k%n,n−1] 区间的元素即能得到最后的答案。
    // 时间复杂度 ：O(n)，其中 n 为数组的长度。每个元素被翻转两次，一共 n 个元素，因此总时间复杂度为 O(2n)=O(n) 。
    // 空间复杂度 ：O(1)
    static class Solution1 {
        public void rotate(int[] nums, int k) {
            k = k % nums.length;
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }

        private void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }

    // 数学推导：环状替换
    // 时间复杂度 ：O(n)，其中 n 为数组的长度。每个元素只会被遍历一次。
    // 空间复杂度 ：O(1)
    static class Solution3 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            int count = gcd(k, n);
            for (int start = 0; start < count; ++start) {
                int current = start;
                int prev = nums[start];
                do {
                    int next = (current + k) % n;
                    int temp = nums[next];
                    nums[next] = prev;
                    prev = temp;
                    current = next;
                } while (start != current);
            }
        }

        private int gcd(int x, int y) {
            return y > 0 ? gcd(y, x % y) : x;
        }
    }
}
