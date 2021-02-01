package yyl.leetcode.p08;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import yyl.leetcode.util.Assert;

/**
 * <h3>公平的糖果棒交换</h3><br>
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。 <br>
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。） <br>
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。 <br>
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。 <br>
 * 
 * <pre>
示例 1：

输入：A = [1,1], B = [2,2]
输出：[1,2]

示例 2：

输入：A = [1,2], B = [2,3]
输出：[1,2]

示例 3：

输入：A = [2], B = [1,3]
输出：[2,3]

示例 4：

输入：A = [1,2,5], B = [2,4]
输出：[5,4]
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= A.length <= 10000 <br>
 * ├ 1 <= B.length <= 10000 <br>
 * ├ 1 <= A[i] <= 100000 <br>
 * ├ 1 <= B[i] <= 100000 <br>
 * ├ 保证爱丽丝与鲍勃的糖果总量不同。 <br>
 * └ 答案肯定存在。 <br>
 */
public class P0888_FairCandySwap {

    public static void main(String[] args) {
        Solution solution = new Solution();

        Assert.assertEquals(new int[] { 1, 2 }, solution.fairCandySwap(//
                new int[] { 1, 1 }, //
                new int[] { 2, 2 } //
        ));
        Assert.assertEquals(new int[] { 1, 2 }, solution.fairCandySwap(//
                new int[] { 1, 2 }, //
                new int[] { 2, 3 } //
        ));
        Assert.assertEquals(new int[] { 2, 3 }, solution.fairCandySwap(//
                new int[] { 2 }, //
                new int[] { 1, 3 } //
        ));
        Assert.assertEquals(new int[] { 5, 4 }, solution.fairCandySwap(//
                new int[] { 1, 2, 5 }, //
                new int[] { 2, 4 } //
        ));
        Assert.assertEquals(new int[] { 4, 5 }, solution.fairCandySwap(//
                new int[] { 2, 4 }, //
                new int[] { 1, 2, 5 } //
        ));
    }

    // 排序 + 双指针
    // sumA - sumB 差值其实就是交换需要弥补的差距
    // 定义需要取出来的是 xA 和 xB， 那么它们差值 xA - xB = (sumA - sumB) / 2
    // 思路就是：
    // ├ 按照从小到大的排序A和B
    // └ 双指针，去遍历A和B， 考虑三种情况
    // - ├ xA-xB == (sumA - sumB) / 2 找到答案，返回即可
    // - ├ xA-xB > (sumA - sumB) / 2 , 则增大xB
    // - └ xA-xB < (sumA - sumB) / 2 , 则增大xA
    // 时间复杂度：O(NlogN)，主要是排序所需的时间复杂度。
    // 空间复杂度：O(NlogN)，主要是排序所需的空间复杂度。
    static class Solution {
        public int[] fairCandySwap(int[] A, int[] B) {
            int sumA = sum(A);
            int sumB = sum(B);
            int target = (sumA - sumB) / 2;

            Arrays.sort(A);
            Arrays.sort(B);

            for (int i = 0, j = 0; i < A.length && j < B.length;) {
                int current = A[i] - B[j];
                if (current == target) {
                    return new int[] { A[i], B[j] };
                } else if (current < target) {
                    i++;
                } else { // current> target
                    j++;
                }
            }
            return null;
        }

        private int sum(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            return sum;
        }
    }

    // 哈希表
    // 设爱丽丝的糖果棒的总大小为 sumA，鲍勃的糖果棒的总大小为 sumB，设答案为 {x,y} 则有如下等式：
    // sumA − x + y = sumB + x − y
    // 化简，得：
    // x= y + (sumA − sumB)/2
    // 即对于 B 中的任意一个数 y′ ，只要 A 中存在一个数 x′ 满足 x′=y′+(sumA−sumB)/2，即为一组可行解。
    // 为了快速查询 A 中是否存在某个数，我们可以先将 A 中的数字存入哈希表中。然后遍历 B 序列中的数 y′，在哈希表中查询是否有对应的 x′。
    // 时间复杂度：O(n+m)，其中 n 是序列 A 的长度，m 是序列 B 的长度。
    // 空间复杂度：O(n)，其中 n 是序列 A 的长度。需要建立一个和序列 A 等大的哈希表。
    static class Solution1 {
        public int[] fairCandySwap(int[] A, int[] B) {
            int sumA = 0;
            int sumB = 0;
            Set<Integer> rec = new HashSet<>();
            for (int num : A) {
                sumA += num;
                rec.add(num);
            }
            for (int num : B) {
                sumB += num;
            }
            int delta = (sumA - sumB) / 2;
            for (int y : B) {
                int x = y + delta;
                if (rec.contains(x)) {
                    return new int[] { x, y };
                }
            }
            return null;
        }
    }
}
