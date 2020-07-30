package yyl.leetcode.p03;

/**
 * <h3>整数拆分</h3><br>
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 * </pre>
 */
public class P0343_IntegerBreak {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.integerBreak(2));
		System.out.println(solution.integerBreak(10));
		System.out.println(solution.integerBreak(58));
	}

	// 动态规划
	// 对于的正整数 n，当 n≥2时，可以拆分成至少两个正整数的和。
	// 令 k 是拆分出的第一个正整数，则剩下的部分是 n−k 可以不继续拆分，或者继续拆分成至少两个正整数的和。
	// 由于每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积，因此可以使用动态规划求解。
	// 有 两种方案：
	// 1、 将 i拆分成 j和i-j的和，且 i−j 不再拆分成多个正整数，此时的乘积是 j*(i−j)
	// 2、 将 i 拆分成 j和 i−j 的和，且 i−j 继续拆分成多个正整数，此时的乘积是 j*dp[i−j]
	// 边界条件：0和 1都不能拆分，因此 dp[0]=dp[1]=0，dp[2]=1；所以i从3开始计算
	// 时间复杂度：O(n^2)，其中 n是给定的正整数。
	// 空间复杂度：O(n)，数组 dp的长度为 n+1。
	static class Solution {
		public int integerBreak(int n) {
			int[] dp = new int[n + 1];
			dp[2] = 1;
			for (int i = 2; i <= n; i++) {
				for (int j = 1; j < i; j++) {
					dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
				}
			}
			return dp[n];
		}
	}

	// 数学归纳法
	// ├最优的拆分方案中不会出现大于 4 的整数
	// │ └ x>4 时 2*(x−2)>x 成立 （将x拆分成 2和 x−2 可以增大乘积）。因此最优的拆分方案中不会出现大于 4的整数。
	// ├最优的拆分方案中可以不出现整数 4
	// │ └ 如果出现了整数 4，我们可以用 2*2 代替之，乘积不变。
	// ├证明当 n>=5 时，最优的拆分方案中不会出现整数 1 （最优的拆分方案中只会出现 2和3）
	// │ └ n>=5的情况， n−1 < (n-1) * 2
	// └当 n>=5 时，最优的拆分方案中 2的个数不会超过 2个
	// - └ 如果出现了超过 3 个2 ，那么将它们转换成 2个 3，可以增大乘积，即3*3>2*2*2
	// 结论：
	// 时间复杂度：O(1)
	// 空间复杂度：O(1)
	static class Solution1 {
		public int integerBreak(int n) {
			if (n == 2) {
				return 1;
			}
			if (n == 3) {
				return 2;
			}
			if (n == 4) {
				return 4;
			}
			int x = n / 3;
			int y = n % 3;

			if (y == 0) {
				return (int) Math.pow(3, x);
			}
			// 3x + 1 = n
			// 3(x-1) + 3 + 1 = n
			if (y == 1) {
				return (int) Math.pow(3, x - 1) * 4;
			}
			// y==3
			// 3x+2=n
			return (int) Math.pow(3, x - 1) * 2;
		}
	}
}
