package yyl.leetcode.p04;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>滑动窗口中位数</h3><br>
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。<br>
 * 例如： <br>
 * ├ [2,3,4]，中位数是 3 <br>
 * └ [2,3]，中位数是 (2 + 3) / 2 = 2.5 <br>
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。<br>
 * 
 * <pre>
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * 
 *  窗口位置                      中位数
 *  ---------------               -----
 *  [1  3  -1] -3  5  3  6  7       1
 *   1 [3  -1  -3] 5  3  6  7      -1
 *   1  3 [-1  -3  5] 3  6  7      -1
 *   1  3  -1 [-3  5  3] 6  7       3
 *   1  3  -1  -3 [5  3  6] 7       5
 *   1  3  -1  -3  5 [3  6  7]      6
 * 
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * </pre>
 * 
 * 提示：<br>
 * ├ 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。 <br>
 * └ 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。 <br>
 */
public class P0480_SlidingWindowMedian {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new double[] { 1, -1, -1, 3, 5, 6 }, solution.medianSlidingWindow(new int[] { 1, 3, -1, -3, 5, 3, 6, 7 }, 3));
        Assert.assertEquals(new double[] { 2147483647.0 }, solution.medianSlidingWindow(new int[] { 2147483647, 2147483647 }, 2));
    }

    // 滑动窗口 + 查找排序
    // 思路：对窗口内的数组进行排序，取得中位数
    // 优化：每次窗口移除一个元素，添加一个元素，可以利用二分查找找到移除元素的位置，然后从移除位置开始进行比较，将移除元素插入到正确位置（插入排序）
    // 时间复杂度：O((n-k)log⁡{k})。 n 是数组元素个数，滑动窗口从头滑动到尾部需要O(n-k)，滑动窗口数组排序需要O(log⁡⁡{k})的时间复杂度 （二分查找），总的时间复杂度为O((n-k)log⁡{k})。
    // 空间复杂度：O(k)。滑动窗口数组所需要的空间。
    static class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            double[] answer = new double[n - k + 1];
            int[] ordinal = Arrays.copyOfRange(nums, 0, k);
            Arrays.sort(ordinal);

            if (k % 2 == 0) {
                int p = k / 2;
                int q = p - 1;
                for (int i = 0; i < answer.length; i++) {
                    // 为了防止 int 溢出，先转换为double
                    answer[i] = ((double) 0D + ordinal[p] + (double) ordinal[q]) / 2;
                    if (i < answer.length - 1) {
                        change(ordinal, nums[i], nums[i + k]);
                    }
                }
            } else {
                int p = k / 2;
                for (int i = 0; i < answer.length; i++) {
                    answer[i] = (double) ordinal[p];
                    if (i < answer.length - 1) {
                        change(ordinal, nums[i], nums[i + k]);
                    }
                }
            }

            return answer;
        }

        private void change(int[] ordinal, int remove, int add) {
            if (remove == add) {
                return;
            }
            int index = Arrays.binarySearch(ordinal, remove);
            if (remove < add) {
                for (index += 1; index < ordinal.length && ordinal[index] < add; index++) {
                    ordinal[index - 1] = ordinal[index];
                }
                ordinal[index - 1] = add;
            }
            // (remove > add)
            else {
                for (index -= 1; index >= 0 && ordinal[index] > add; index--) {
                    ordinal[index + 1] = ordinal[index];
                }
                ordinal[index + 1] = add;
            }
        }
    }
}
