package yyl.leetcode.p25;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import yyl.leetcode.util.Assert;

/**
 * <h3>在网格图中访问一个格子的最少时间</h3><br>
 * 给你一个 M*N 的矩阵 grid ，每个元素都为 非负 整数，其中 grid[row][col] 表示可以访问格子 (row, col) 的 最早 时间。<br>
 * 也就是说当你访问格子 (row, col) 时，最少已经经过的时间为 grid[row][col] 。<br>
 * 你从 最左上角 出发，出发时刻为 0 ，你必须一直移动到上下左右相邻四个格子中的 任意 一个格子（即不能停留在格子上）。每次移动都需要花费 1 单位时间。<br>
 * 请你返回 最早 到达右下角格子的时间，如果你无法到达右下角的格子，请你返回 -1 。<br>
 * 
 * <pre>
 * 示例 1：
 * ┌─┬─┬─┬─┐
 * │0│1│3│2│
 * │─┼─┼─┼─┤
 * │5│1│2│5│
 * │─┼─┼─┼─┤
 * │4│3│8│6│
 * └─┴─┴─┴─┘
 * 
 * 输入：grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
 * 输出：7
 * 解释：一条可行的路径为：
 * - 时刻 t = 0 ，我们在格子 (0,0) 。
 * - 时刻 t = 1 ，我们移动到格子 (0,1) ，可以移动的原因是 grid[0][1] <= 1 。
 * - 时刻 t = 2 ，我们移动到格子 (1,1) ，可以移动的原因是 grid[1][1] <= 2 。
 * - 时刻 t = 3 ，我们移动到格子 (1,2) ，可以移动的原因是 grid[1][2] <= 3 。
 * - 时刻 t = 4 ，我们移动到格子 (1,1) ，可以移动的原因是 grid[1][1] <= 4 。
 * - 时刻 t = 5 ，我们移动到格子 (1,2) ，可以移动的原因是 grid[1][2] <= 5 。
 * - 时刻 t = 6 ，我们移动到格子 (1,3) ，可以移动的原因是 grid[1][3] <= 6 。
 * - 时刻 t = 7 ，我们移动到格子 (2,3) ，可以移动的原因是 grid[2][3] <= 7 。
 * 最终到达时刻为 7 。这是最早可以到达的时间。
 * 
 * 示例 2：
 * ┌─┬─┬─┐
 * │0│2│4│
 * ├─┼─┼─┤
 * │3│2│1│
 * ├─┼─┼─┤
 * │1│0│4│
 * └─┴─┴─┘
 * 输入：grid = [[0,2,4],[3,2,1],[1,0,4]]
 * 输出：-1
 * 解释：没法从左上角按题目规定走到右下角。
 * 
 * 提示：
 *  m == grid.length
 *  n == grid[i].length
 *  2 <= m, n <= 1000
 *  4 <= m * n <= 105
 *  0 <= grid[i][j] <= 105
 *  grid[0][0] == 0
 * </pre>
 */
public class P2577_MinimumTimeToVisitACellInAGrid {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(7, solution.minimumTime(new int[][] { { 0, 1, 3, 2 }, { 5, 1, 2, 5 }, { 4, 3, 8, 6 } }));
        Assert.assertEquals(-1, solution.minimumTime(new int[][] { { 0, 2, 4 }, { 3, 2, 1 }, { 1, 0, 4 } }));
    }

    // 分析
    // 1、如果网格起点是可出发的，接下来总可以通过来回走的方式耗时间，最终到达重点，因此 −1 的情况只需要考虑 grid[1][0] 和 grid[0][1] 中是否有可达点即可。
    // 2、通过来回走的方式，出发时间可以为 0,2,4,⋯⋯这些偶数
    // 3、 定义 dis[i][j] 为到达 (i,j) 的最小时间，那么 dis[0][0]=0，答案为 dis[m−1][n−1]。
    // 4、如果 u点用时为 Tu，其相邻点 v 用时 Tv，那么 Tu >= Tv + 1，同时 Tu >= grid[m−1][n−1]
    // 5、到达一个点的时间的奇偶性是确定的。（可以考虑经过 t 单位时间后，横纵坐标的和的奇偶性与 t一致）
    // 时间复杂度： O(nm*log⁡(nm))
    // 空间复杂度：m*n
    static class Solution {

        private final static int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        public int minimumTime(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            // 无解
            if (grid[0][1] > 1 && grid[1][0] > 1) {
                return -1;
            }

            int[][] distances = new int[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(distances[i], Integer.MAX_VALUE);
            }
            distances[0][0] = 0;

            class Node {
                int row;
                int column;
                int distance;

                public Node(int row, int column, int distance) {
                    this.row = row;
                    this.column = column;
                    this.distance = distance;
                }
            }

            Queue<Node> queue = new PriorityQueue<>((a, b) -> a.distance - b.distance);
            queue.add(new Node(0, 0, 0));

            for (;;) {
                Node node = queue.poll();

                int row = node.row;
                int column = node.column;
                int distance = node.distance;

                // 终点
                if (row == m - 1 && column == n - 1) {
                    return distance;
                }

                // 枚举周围四个格子
                for (int[] direction : DIRECTIONS) {

                    int x = row + direction[0];
                    int y = column + direction[1];

                    if (0 <= x && x < m && 0 <= y && y < n) {

                        // 目前路径，到该格子需要的时间
                        int t1 = Math.max(distance + 1, grid[x][y]);

                        // nd 必须和 x+y 同奇偶
                        t1 += (t1 - x - y) % 2;

                        if (t1 < distances[x][y]) {
                            distances[x][y] = t1; // 更新最短路
                            queue.add(new Node(x, y, t1));
                        }
                    }
                }
            }
        }
    }
}
