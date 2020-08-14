package yyl.leetcode.p04;

import java.util.Arrays;

/**
 * <h3>分割数组的最大值</h3><br>
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。<br>
 * 注意:<br>
 * 数组长度 n 满足以下条件:<br>
 * 1 ≤ n ≤ 1000<br>
 * 1 ≤ m ≤ min(50, n)<br>
 * 
 * <pre>
 * 示例:
 * 输入:
 * nums = [7,2,5,10,8]
 * m = 2
 * 输出:
 * 18
 * 解释:
 * 一共有四种方法将nums分割为2个子数组。
 * 其中最好的方式是将其分为[7,2,5] 和 [10,8]，
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * </pre>
 */
public class P0410_SplitArrayLargestSum {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.splitArray(new int[] { 7, 2, 5, 10, 8 }, 2));// 18
        // System.out.println(solution.splitArray(new int[] { 1, Integer.MAX_VALUE }, 2));// 2147483647
    }

    // 二分查找 + 贪心 （最优解）
    // 思路及算法
    // ├ 1、设子数组最大值为 x，数组和为sum,数组中最大数为 maximum
    // ├ 2、直接求 x 不容易，但是如果已知 x，判断这个x是否正确比较容易
    // ├ 2、用二分法逼近 ，初始的下界为 left=0， 上界right=sum
    // ├ 4、统计出小于等于 x 的子数组个数 count
    // ├ 5、count <= m 说明用 x 能进行分割，缩小上限 right = middle
    // ├ 6、count > 说明用不能用 x 能进行分，扩大下限 left = middle + 1
    // ├ 7、终止条件为 left >= right，这时候返回 right（因为上一次循环的 right = middle 是最终答案）
    // ├ 8、为防止溢出，数组元素和相关变量为 long 类型
    // 复杂度分析
    // 时间复杂度：O(n*log⁡(sum−max)) ，其中 sum 表示数组 nums 中所有元素的和，max 表示数组所有元素的最大值。每次二分查找时，需要对数组进行一次遍历，时间复杂度为 O(n)，因此总时间复杂度是O(n*log⁡(sum−max))。
    // 空间复杂度：O(1)O(1)O(1)。
    static class Solution {
        public int splitArray(int[] nums, int m) {
            long left = 0;
            long right = 0;
            for (int num : nums) {
                left = Math.max(left, num);
                right += num;
            }
            while (left < right) {
                long middle = (right + left) / 2;
                if (check(nums, m, middle)) {
                    right = middle; // right = middle = x
                } else {
                    left = middle + 1;
                }
            }
            return (int) right;
        }

        private boolean check(int[] nums, int m, long x) {
            int count = 1;
            long total = 0;
            for (int num : nums) {
                total += num;
                if (total > x) {
                    count++;
                    total = num;
                }
            }
            return count <= m;
        }
    }

    // 动态规划 +前缀和
    // 思路及算法
    // ├ 数组长度为 n，设 dp[i][j] 表示将数组的前 i个数分割为 j 段所能得到的最大连续子数组和的最小值（i<=n，j<=m）
    // ├ 设 sub(i,j)表示表示数组 nums中下标落在区间 [i,j] 内的数的和。
    // ├ 设数字 k 为 第 j段 开头（其中前 k 个数被分割为 j−1 段，而第 k+1 到第 i 个数为第 j段）
    // ├ 那么设第j段的和为sub(k+1,i)，那这 j段子数组中和的最大值等于 max(dp[k][j−1],sub(k+1,i))
    // │ 由于我们要使得子数组中和的最大值最小，因此可以列出如下的状态转移方程：
    // │ └ dp[i][j] = min($for(i,j,k){max(dp[k][j−1],sub(k+1,i))})
    // └ 最终答案为 dp[n][m]
    // 边界控制：
    // ├ dp[0][0]=0
    // └ 合法的状态必须有 i>=j
    // - └ 对于不合法的状态，可以设置成很大的数，从而不会对最外层的 min⁡{⋯ }产生任何影响
    // 优化：计算区间 [i,j] 内的数的和的时间复杂度是 O(j-i)，使用前缀和可以使复杂度降低到O(1)
    // 复杂度分析
    // 时间复杂度：O(n^2*m)，其中 n 是数组的长度，m 是分成的非空的连续子数组的个数。总状态数为 O(n*m)，状态转移时间复杂度 O(n)，所以总时间复杂度为 O(n^2*m)。
    // 空间复杂度：O(n*m) )，为动态规划数组的开销。
    static class Solution2 {
        public int splitArray(int[] nums, int m) {
            int n = nums.length;
            int[][] dp = new int[n + 1][m + 1];

            // 考虑不合法的情况，用最大数填充
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            // 初始0，因为 i之前没有数据
            dp[0][0] = 0;

            // 前缀和
            int[] prefixs = new int[n + 1];
            for (int i = 0; i < n; i++) {
                prefixs[i + 1] = prefixs[i] + nums[i];
            }

            // 注意 i和j是从1开始；j不能大于m，也不能大于i；k 是(0~i]范围
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i && j <= m; j++) {
                    for (int k = 0; k < i; k++) {
                        // sub(k+1,i) 第j段的和
                        int sub = prefixs[i] - prefixs[k];
                        // 这 j段子数组中和的最大值，就等于 dp[k][j−1] 与 sub(k+1,i) 中的较大值
                        int sum = Math.max(sub, dp[k][j - 1]);
                        // 取最小结果
                        dp[i][j] = Math.min(dp[i][j], sum);
                    }
                }
            }
            return dp[n][m];
        }
    }
}
