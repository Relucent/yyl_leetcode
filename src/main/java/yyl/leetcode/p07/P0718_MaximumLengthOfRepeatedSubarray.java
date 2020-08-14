package yyl.leetcode.p07;

/**
 * <h3>最长重复子数组</h3><br>
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释: 长度最长的公共子数组是 [3, 2, 1]。
 * 
 * 说明:
 *     1 <= len(A), len(B) <= 1000
 *     0 <= A[i], B[i] < 100
 * </pre>
 */
public class P0718_MaximumLengthOfRepeatedSubarray {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = { 1, 2, 3, 2, 1 };
        int[] B = { 3, 2, 1, 4, 7 };
        System.out.println(solution.findLength(A, B));
    }

    // 动态规划
    // 定义DP[][]，其中 PD[i][j] 表示 A[i-1]与B[j-1]结尾的串公共子串长度
    // 公共字串必须以A[i-1]，B[j-1]结束)
    // 当 A[i-1] == B[j-1] 时，计算A[i-1]和B[j-1]结尾的公共字串长度：为 DP[i][j] = DP[i-1][j-1] + 1;
    // 当 A[i-1] != B[j-1] 时，计算A[i-1]和B[j-1]结尾的公共字串长度：DP[i][j] = 0
    // 输出最大的公共字串的长度即为最长重复字串
    // 时间复杂度：O(MN)，M为A的长度，N为B的长度
    // 空间复杂度：O(MN)
    static class Solution {
        public int findLength(int[] A, int[] B) {
            if (A.length == 0 || B.length == 0) {
                return 0;
            }
            int dp[][] = new int[A.length + 1][B.length + 1];
            int max = 0;
            for (int i = 1; i <= A.length; i++) {
                for (int j = 1; j <= B.length; j++) {
                    if (A[i - 1] == B[j - 1]) {
                        max = Math.max(max, dp[i][j] = dp[i - 1][j - 1] + 1);
                    }
                }
            }
            return max;
        }
    }

    // 暴力解法
    // 枚举数组 A 中的起始位置 i与数组 B中的起始位置 j，然后计算 A[i:] 与 B[j:] 的最长公共前缀 k。最终答案即为所有的最长公共前缀的最大值。
    // 时间复杂度：O(n^3)
    // 空间复杂度：O(1)
    static class Solution1 {
        public int findLength(int[] A, int[] B) {
            if (A.length == 0 || B.length == 0) {
                return 0;
            }
            int max = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < B.length; j++) {
                    int k = 0;
                    while (i + k < A.length && j + k < B.length && A[i + k] == B[j + k]) {
                        k += 1;
                    }
                    max = Math.max(max, k);
                }
            }
            return max;
        }
    }

    // 滑动窗口，对齐方式
    // 假设子串开始位置，将 A 和 B 进行「对齐」
    // 通过指定初始位置 addA 与 addB 的方式来对齐
    // 对齐之后，子数组在 A 和 B 中的开始位置相同，我们就可以对这两个数组进行一次遍历，得到子数组的长度
    // 时间复杂度： O((N+M)×min⁡(N,M))
    // 空间复杂度：O(1)
    static class Solution2 {
        public int findLength(int[] A, int[] B) {
            if (A.length == 0 || B.length == 0) {
                return 0;
            }
            int result = 0;
            for (int i = 0; i < A.length; i++) {
                int len = Math.min(A.length, B.length - i);
                int maxlen = maxLength(A, B, i, 0, len);
                result = Math.max(result, maxlen);
            }
            for (int i = 0; i < B.length; i++) {
                int len = Math.min(A.length - i, B.length);
                int maxlen = maxLength(A, B, 0, i, len);
                result = Math.max(result, maxlen);
            }
            return result;
        }

        private int maxLength(int[] A, int[] B, int addA, int addB, int len) {
            int result = 0;
            for (int i = 0, k = 0; i < len; i++) {
                if (A[addA + i] == B[addB + i]) {
                    k++;
                } else {
                    k = 0;
                }
                result = Math.max(result, k);
            }
            return result;
        }
    }
}
