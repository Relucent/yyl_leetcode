package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>只出现一次的数字</h3><br>
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。<br>
 * 说明： 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 * 
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * </pre>
 */
public class P0136_SingleNumber {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(1, solution.singleNumber(new int[] { 2, 2, 1 }));
        Assert.assertEquals(4, solution.singleNumber(new int[] { 4, 1, 2, 1, 2 }));
    }

    // 位运算
    // 如果不限制时间复杂度和空间复杂度，这道题有很多种解法，例如：
    // 1、暴力法，每遇到一个数字就查找是否重复出现，得到不重复的数字。时间复杂度 O(n^2)，空间复杂度 O(n)。
    // 2、计数法，使用哈希表存储每个数字和该数字出现的次数。遍历数组即可得到每个数字出现的次数，并更新哈希表，最后遍历哈希表，得到只出现一次的数字。时间复杂度 O(n)，空间复杂度 O(n)。
    // 3、排序法，先排序，再两两比较。时间复杂度 O(nlogn)，空间复杂度 O(n)。
    // 但是加上时间复杂度和空间复杂度限制后，这些解法显然都不满足要求，因此需要考虑另外的解法
    // 考虑到数组中的元素，只有一个元素出现一次，其余都是两次，那么如果将两次的数消除，就可以得到最终结果，于是可以考虑异或运算。
    // ├ 异或性质：
    // │ ├ 何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=aa。
    // │ ├ 任何数和其自身做异或运算，结果是 0，即 a⊕a=0
    // │ └ 异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕(a⊕a)=b⊕0=b。
    // ├ 假设 A0 是数组中出现一次的元素（其余都是两次），那么异或运算后结果为 (A1​⊕A1​)⊕(A2​⊕A2​)⊕⋯⊕(Am​⊕Am​)⊕A0=A0​
    // └ 即：数组中的全部元素的异或运算结果即为数组中只出现一次的数字。
    // 时间复杂度：O(n)，其中 n 是数组长度。只需要对数组遍历一次。
    // 空间复杂度：O(1)。
    static class Solution {
        public int singleNumber(int[] nums) {
            int answer = 0;
            for (int num : nums) {
                answer ^= num;
            }
            return answer;
        }
    }
}
