package yyl.leetcode.p04;

import yyl.leetcode.util.Assert;

/**
 * <h3>岛屿的周长</h3><br>
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。<br>
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。<br>
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。<br>
 * 
 * <pre>
 * 示例 :
 * 输入:
 * [[0,1,0,0],
 *  [1,1,1,0],
 *  [0,1,0,0],
 *  [1,1,0,0]]
 * 输出: 16
 * 解释: 它的周长是下面图片中的 16 个的边：
 * ┌─╔═╗─┬─┐
 * ╔═╬═╬═╗─┤
 * ╚═╬═╬═╝─┤
 * ╔═╬═╣─┼─┤
 * ╚═╩═╝─┴─┘
 * </pre>
 */
public class P0463_IslandPerimeter {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
        Assert.assertEquals(16, solution.islandPerimeter(grid));
    }

    // 迭代
    // 对于一个陆地格子的每条边，它被算作岛屿的周长当且仅当这条边为网格的边界或者相邻的另一个格子为水域。
    // 因此，可以遍历每个陆地格子，看其四个方向是否为边界或者水域，如果是，将这条边的贡献（即 1）加入答案 answer中即可。
    // 时间复杂度：O(mn) ，其中 m 为网格的高度，n 为网格的宽度。需要遍历每个格子，每个格子要看其周围 4个格子是否为岛屿，因此总时间复杂度为 O(4nm)=O(nm)
    // 空间复杂度：O(1)。只需要常数空间存放若干变量。
    static class Solution {
        public int islandPerimeter(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            int answer = 0;
            for (int y = 0; y < m; y++) {
                for (int x = 0; x < n; x++) {
                    if (grid[y][x] == 1) {
                        answer += isLand(grid, m, n, y - 1, x) ? 1 : 0;
                        answer += isLand(grid, m, n, y + 1, x) ? 1 : 0;
                        answer += isLand(grid, m, n, y, x - 1) ? 1 : 0;
                        answer += isLand(grid, m, n, y, x + 1) ? 1 : 0;
                    }
                }
            }
            return answer;
        }

        private boolean isLand(int[][] grid, int m, int n, int y, int x) {
            return y < 0 || y >= m || x < 0 || x >= n || grid[y][x] == 0;
        }
    }
}
