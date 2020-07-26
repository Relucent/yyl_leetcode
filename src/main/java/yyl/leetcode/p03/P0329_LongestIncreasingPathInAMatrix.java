package yyl.leetcode.p03;

/**
 * <h3>矩阵中的最长递增路径</h3><br>
 * 给定一个整数矩阵，找出最长递增路径的长度。<br>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: nums = 
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ] 
 * 输出: 4 
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 
 * 示例 2:
 * 输入: nums = 
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ] 
 * 输出: 4 
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * </pre>
 */
public class P0329_LongestIncreasingPathInAMatrix {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.longestIncreasingPath(new int[][] { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } }));// 4
		System.out.println(solution.longestIncreasingPath(new int[][] { { 3, 4, 5 }, { 3, 2, 6 }, { 2, 2, 1 } }));// 4
	}

	// 记忆化深度优先搜索
	// 1、将矩阵看成一个有向图，每个单元格对应图中的一个节点（如果相邻的两个单元格的值不相等，则在相邻的两个单元格之间存在一条从较小值指向较大值的有向边）。
	// 2、问题转化成在有向图中寻找最长路径：使用深度优先搜索，是非常直观的方法。从一个单元格开始进行深度优先搜索，即可找到从该单元格开始的最长递增路径。
	// 3、对每个单元格分别进行深度优先搜索之后，即可得到矩阵中的最长递增路径的长度。
	// 4、同一个单元格会被访问多次，每次访问都要重新计算会很耗时。由于同一个单元格对应的最长递增路径的长度是固定不变的，因此可以使用记忆化的方法进行优化。用矩阵 memo作为缓存矩阵，已经计算过的单元格的结果存储到缓存矩阵中。
	// 时间复杂度：O(mn)，其中 m 和 n 分别是矩阵的行数和列数。因为使用了记忆化优化，所以每个格子不会重复计算。每个格子最多只可能有4个方向，深度优先搜索的时间复杂度小于O(4mn)，忽略常量时间复杂度为O(mn)
	// 空间复杂度：O(mn)，其中 m 和 n 分别是矩阵的行数和列数。空间复杂度主要取决于缓存和递归调用深度，缓存的空间复杂度是 O(mn)，递归调用深度不会超过 mn。
	static class Solution {
		public int longestIncreasingPath(int[][] matrix) {
			if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
				return 0;
			}
			int m = matrix.length;
			int n = matrix[0].length;
			int[][] memo = new int[m][n];
			int answer = 0;
			for (int y = 0; y < m; y++) {
				for (int x = 0; x < n; x++) {
					// 没计算过的节点
					if (memo[y][x] == 0) {
						answer = Math.max(answer, dfs(matrix, y, x, m, n, memo));
					}
				}
			}
			return answer;
		}

		private int dfs(int[][] matrix, int y, int x, int m, int n, int[][] memo) {
			if (memo[y][x] != 0) {
				return memo[y][x];
			}
			// 到达当前节点的最大路径
			int max = 0;
			// 上
			if (y > 0 && matrix[y - 1][x] > matrix[y][x]) {
				max = Math.max(max, dfs(matrix, y - 1, x, m, n, memo));
			}
			// 下
			if (y + 1 < m && matrix[y + 1][x] > matrix[y][x]) {
				max = Math.max(max, dfs(matrix, y + 1, x, m, n, memo));
			}
			// 左
			if (x > 0 && matrix[y][x - 1] > matrix[y][x]) {
				max = Math.max(max, dfs(matrix, y, x - 1, m, n, memo));
			}
			// 右
			if (x + 1 < n && matrix[y][x + 1] > matrix[y][x]) {
				max = Math.max(max, dfs(matrix, y, x + 1, m, n, memo));
			}
			// 当前节点最大路径 = max(四个方向到当前节点最大路径) + 1(当前节点)
			return memo[y][x] = max + 1;
		}
	}
}
