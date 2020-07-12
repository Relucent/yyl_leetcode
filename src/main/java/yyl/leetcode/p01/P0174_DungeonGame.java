package yyl.leetcode.p01;

/**
 * <h3>地下城游戏</h3><br>
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。<br>
 * 地下城是由 M x N 个房间组成的二维网格。<br>
 * 我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。<br>
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。<br>
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；<br>
 * 其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。 <br>
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。<br>
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。<br>
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。<br>
 * 
 * <pre>
 * ┌─────┬─────┬─────┐
 * │-2(K)│-3   │3    │
 * ├─────┼─────┼─────┤
 * │-5   │-10  │1    │
 * ├─────┼─────┼─────┤
 * │10   │30   │-5(P)│
 * └─────┴─────┴─────┘
 * </pre>
 * 
 * 说明:<br>
 * 骑士的健康点数没有上限。<br>
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。<br>
 */
public class P0174_DungeonGame {

	public static void main(String[] args) {
		Solution1 solution = new Solution1();
		int[][] dungeon = { //
				{ -2, -3, 3 }, //
				{ -5, -10, 1 }, //
				{ 10, 30, -5 }//
		};
		System.out.println(solution.calculateMinimumHP(dungeon));// 7
	}

	// 动态规划 + 逆向思维
	// 设数组dp[y][x]表示骑士从该格子到达公主位置所需要的健康点数
	// 如果骑士能到达右下角格子，那么必须保证生命大于0。
	// 右下角值的问题
	// 如果是零或正数，骑士到达这个位置时候，生命是1就可以。
	// 如果是负数，骑士到达这个位置时候，生命需要大于这个负数 dp[m-1][n-1]> dungeon[m-1][n-1]， dp[m-1][n-1] = abs(dungeon[m-1][n-1])+1
	// 每次只向右或向下移动一步，可以逆向考虑骑士走的路径
	// 模拟右下角退回左上角的步骤，从dp[m-1][n-1]向dp[0][0]推导，每次只能向左或者向上一格，并且保障左上角dp[0][0]最小
	// （因为是逆向回退，所以遇到负数回复生命，遇到正整数减少生命）
	// 1、逆向推导的时候，选取左面和上面两个值较小的值，较小的值才能保证最终推导出左上角的值为最小的值。
	// 2、如果两个值的较小值为负数的时候，需要将这个值设为1，因为要保证每个格子的值大于1
	// 得到状态转移方程：
	// dp[y][x] = max( min(dp[y][x+1],dp[y+1][x])- dungeon[y][x]),1)
	// 空间复杂度：O(MN),其中 MN为给定矩阵的长宽。
	// 时间复杂度：O(MN)
	static class Solution {
		public int calculateMinimumHP(int[][] dungeon) {
			int m = dungeon.length;
			int n = dungeon[0].length;
			int[][] dp = new int[m][n];

			// 右下角
			// 如果该格子大于等于0(不减少健康点数)，需要保证到达这个格子时候生命是1
			// 如果该格子是负数(减少健康点数)，那么需要保证，到达这个格子时候生命是-该格子的负数=1
			dp[m - 1][n - 1] = dungeon[m - 1][n - 1] < 0 ? 1 - dungeon[m - 1][n - 1] : 1;

			// 最下一行
			for (int x = n - 2, y = m - 1; x >= 0; x--) {
				dp[y][x] = Math.max(dp[y][x + 1] - dungeon[y][x], 1);
			}
			// 最右一列
			for (int x = n - 1, y = m - 2; y >= 0; y--) {
				dp[y][x] = Math.max(dp[y + 1][x] - dungeon[y][x], 1);
			}
			// 其余部分
			for (int y = m - 2; y >= 0; y--) {
				for (int x = n - 2; x >= 0; x--) {
					dp[y][x] = Math.max(Math.min(dp[y][x + 1], dp[y + 1][x]) - dungeon[y][x], 1);
				}
			}
			return dp[0][0];
		}
	}

	// 动态规划 + 滚动数组优化
	// 根据状态转移方程
	// dp[y][x] = max( min(dp[y][x+1],dp[y+1][x])- dungeon[y][x]),1)
	// 可以看出，每次状态只和前一次有关，所以可以对数组进行压缩
	// dp[x] = max( min(dp[x+1],dp[x])- dungeon[y][x]),1)
	// 空间复杂度：O(MN),其中 MN为给定矩阵的长宽。
	// 时间复杂度：O(N)
	static class Solution1 {
		public int calculateMinimumHP(int[][] dungeon) {
			int m = dungeon.length;
			int n = dungeon[0].length;
			int[] dp = new int[n];
			dp[n - 1] = dungeon[m - 1][n - 1] < 0 ? 1 - dungeon[m - 1][n - 1] : 1;
			for (int x = n - 2, y = m - 1; x >= 0; x--) {
				dp[x] = Math.max(dp[x + 1] - dungeon[y][x], 1);
			}
			for (int y = m - 2; y >= 0; y--) {
				dp[n - 1] = Math.max(dp[n - 1] - dungeon[y][n - 1], 1);
				for (int x = n - 2; x >= 0; x--) {
					dp[x] = Math.max(Math.min(dp[x + 1], dp[x]) - dungeon[y][x], 1);
				}
			}
			return dp[0];
		}
	}
}
