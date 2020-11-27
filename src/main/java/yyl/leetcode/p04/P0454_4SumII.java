package yyl.leetcode.p04;

import java.util.HashMap;
import java.util.Map;

import yyl.leetcode.util.Assert;

/**
 * <h3>四数相加 II</h3><br>
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。<br>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。<br>
 * 
 * <pre>
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出: 2
 * 
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * </pre>
 */
public class P0454_4SumII {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.fourSumCount(//
                new int[] { 1, 2 }, //
                new int[] { -2, -1 }, //
                new int[] { -1, 2 }, //
                new int[] { 0, 2 }//
        ));
        Assert.assertEquals(6, solution.fourSumCount(//
                new int[] { -1, -1 }, //
                new int[] { -1, 1 }, //
                new int[] { -1, 1 }, //
                new int[] { 1, -1 }//
        ));
    }

    // 分组 + 哈希表
    // 将四个数组分成两部分，A 和 B 为一组，C 和 D 为一组。
    // 对于 A 和 B ，使用二重循环对它们进行遍历，得到所有 A[i]+B[j] 的值并存入哈希映射中。 每个键表示一种 A[i]+B[j]，对应的值为 A[i]+B[j] 出现的次数。
    // 对于 C 和 D ，使用二重循环对它们进行遍历。当遍历到 C[k]+D[l] 时，如果 −(C[k]+D[l]) 出现在哈希映射中，那么将 −(C[k]+D[l]) 对应的值累加进答案中。
    // 最终即可得到满足 A[i]+B[j]+C[k]+D[l]0 的四元组数目。
    // 时间复杂度：O(n^2)，使用了两次二重循环，时间复杂度均为 O(n^2)，哈希映射进行的修改以及查询操作的期望时间复杂度均为 O(1)。
    // 空间复杂度：O(n^2)，哈希映射需要使用的空间，在最坏的情况下，A[i]+B[j] 的值均不相同，因此值的个数为 n^2。
    static class Solution {
        public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
            Map<Integer, Integer> hash = new HashMap<>();
            for (int a : A) {
                for (int b : B) {
                    hash.merge(a + b, 1, Integer::sum);
                }
            }
            int answer = 0;
            for (int c : C) {
                for (int d : D) {
                    answer += hash.getOrDefault(-(c + d), 0);
                }
            }
            return answer;
        }
    }
}
