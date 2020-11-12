package yyl.leetcode.p09;

import yyl.leetcode.util.Assert;

/**
 * <h3>按奇偶排序数组 II</h3><br>
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。<br>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。<br>
 * 你可以返回任何满足上述条件的数组作为答案。<br>
 * 
 * <pre>
 *示例：
 *输入：[4,2,5,7]
 *输出：[4,5,2,7]
 *解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * </pre>
 * 
 * 提示：<br>
 * #├ 2 <= A.length <= 20000<br>
 * #├ A.length % 2 == 0<br>
 * #└ 0 <= A[i] <= 1000<br>
 */
public class P0922_SortArrayByParityII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 4, 5, 2, 7 }, solution.sortArrayByParityII(new int[] { 4, 2, 5, 7 }));
    }

    // 双指针
    // 为数组的偶数下标部分和奇数下标部分分别维护指针 i,j，然后遍历原数组。
    // 遍历出来的元素如果是偶数就放到answer[i]的位置并i+=2，否则就放到answer[j]，j+=2
    // 时间复杂度：O(N)，其中 N 是数组 A 的长度。
    // 空间复杂度：O(N)，新数组的长度。
    static class Solution {
        public int[] sortArrayByParityII(int[] A) {
            int[] answer = new int[A.length];
            int i = 0;
            int j = 1;
            for (int n : A) {
                if (n % 2 == 0) {
                    answer[i] = n;
                    i += 2;
                } else {
                    answer[j] = n;
                    j += 2;
                }
            }
            return answer;
        }
    }

    // 双指针，在原始数组上进行修改
    // 为数组的偶数下标部分和奇数下标部分分别维护指针 i,j，然后遍历原数组。
    // 如果 A[i] 为奇数，则不断地向前移动 j （每次移动两个单位），直到遇见下一个偶数。此时，可以直接将 A[i] 与 A[j] 交换，最终能够将所有的整数放在正确的位置上。
    // 时间复杂度：O(N)，其中 N 是数组 A 的长度。
    // 空间复杂度：O(1)。
    static class Solution2 {
        public int[] sortArrayByParityII(int[] A) {
            int j = 1;
            for (int i = 0; i < A.length; i += 2) {
                if (A[i] % 2 == 1) {
                    while (A[j] % 2 == 1) {
                        j += 2;
                    }
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
            return A;
        }
    }
}
