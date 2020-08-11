package yyl.leetcode.p01;

import yyl.leetcode.util.Assert;

/**
 * <h3>被围绕的区域</h3><br>
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。<br>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。<br>
 * 
 * <pre>
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * </pre>
 */
public class P0130_SurroundedRegions {

	public static void main(String[] args) {
		Solution solution = new Solution();
		char[][] actual = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' }, { 'X', 'O', 'X', 'X' } };
		solution.solve(actual);
		char[][] expected = { { 'X', 'X', 'X', 'X' }, { 'X', 'X', 'X', 'X' }, { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'X', 'X' } };
		Assert.assertEquals(expected, actual);
	}

	// 深度优先搜索
	// 任何边界上的 O 都不会被填充为 X。 可以得出，所有的不被包围的 O 都直接或间接与边界上的 O相连。
	// 1、以每一个边界上的 O为起点，标记所有与它直接或间接相连的字母 O；
	// 2、遍历这个矩阵，对于每一个字母
	// 如果该字母被标记过，则该字母为没有被字母 X 包围的字母 O，我们将其还原为字母 O；
	// 如果该字母没有被标记过，则该字母为被字母 X 包围的字母 O，我们将其修改为字母 X。
	// 时间复杂度：O(m*n)，其中 m和 n分别为矩阵的行数和列数。深度优先搜索过程中，每一个点至多只会被标记一次。
	// 空间复杂度：O(m*n)，为深度优先搜索的栈的开销。
	static class Solution {

		private static final char ENERGY = '0';

		public void solve(char[][] board) {
			int m = board.length;
			if (m == 0) {
				return;
			}
			int n = board[0].length;
			for (int x = 0; x < n; x++) {
				dfs(board, 0, x, m, n);
				dfs(board, m - 1, x, m, n);
			}
			for (int y = 1; y < m - 1; y++) {
				dfs(board, y, 0, m, n);
				dfs(board, y, n - 1, m, n);
			}
			for (int y = 0; y < m; y++) {
				for (int x = 0; x < n; x++) {
					if (board[y][x] == ENERGY) {
						board[y][x] = 'O';
					} else {
						board[y][x] = 'X';
					}
				}
			}
		}

		private void dfs(char[][] board, int y, int x, int m, int n) {
			if (y < 0 || m <= y || x < 0 || n <= x || board[y][x] != 'O') {
				return;
			}
			board[y][x] = ENERGY;
			dfs(board, y - 1, x, m, n);
			dfs(board, y + 1, x, m, n);
			dfs(board, y, x - 1, m, n);
			dfs(board, y, x + 1, m, n);
		}
	}
}
