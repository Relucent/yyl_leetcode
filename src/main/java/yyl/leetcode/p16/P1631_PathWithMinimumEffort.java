package yyl.leetcode.p16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import yyl.leetcode.util.Assert;

/**
 * <h3>最小体力消耗路径</h3><br>
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。<br>
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。<br>
 * 请你返回从左上角走到右下角的最小 体力消耗值 。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 
 * ┌───┬───┬───┐
 * │(1)│ 2 │ 2 │
 * ├───┼───┼───┤
 * │(3)│ 8 │ 2 │
 * ├───┼───┼───┤
 * │(5)│(3)│(5)│
 * └───┴───┴───┘
 * 
 * 示例 2：
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * ┌───┬───┬───┐
 * │(1)│(2)│(3)│
 * ├───┼───┼───┤
 * │ 3 │ 8 │(4)│
 * ├───┼───┼───┤
 * │ 5 │ 3 │(5)│
 * └───┴───┴───┘
 * 
 * 示例 3：
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * ┌───┬───┬───┬───┬───┐
 * │(1)│ 2 │(1)│(1)│(1)│
 * ├───┼───┼───┼───┼───┤
 * │(1)│ 2 │(1)│ 2 │(1)│
 * ├───┼───┼───┼───┼───┤
 * │(1)│ 2 │(1)│ 2 │(1)│
 * ├───┼───┼───┼───┼───┤
 * │(1)│ 2 │(1)│ 2 │ 2 │
 * ├───┼───┼───┬───┬───┤
 * │(1)│(1)│(1)│ 2 │(1)│
 * └───┴───┴───┴───┴───┘
 * 解释：上图所示路径不需要消耗任何体力。
 * </pre>
 * 
 * 提示： <br>
 * ├ rows == heights.length <br>
 * ├ columns == heights[i].length <br>
 * ├ 1 <= rows, columns <= 100 <br>
 * └ 1 <= heights[i][j] <= 106 <br>
 */
public class P1631_PathWithMinimumEffort {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.minimumEffortPath(//
                new int[][] { { 1, 2, 2 }, { 3, 8, 2 }, { 5, 3, 5 } }//
        ));
        Assert.assertEquals(1, solution.minimumEffortPath(//
                new int[][] { { 1, 2, 3 }, { 3, 8, 4 }, { 5, 3, 5 } }//
        ));
        Assert.assertEquals(0, solution.minimumEffortPath(//
                new int[][] { { 1, 2, 1, 1, 1 }, { 1, 2, 1, 2, 1 }, { 1, 2, 1, 2, 1 }, { 1, 2, 1, 2, 1 }, { 1, 1, 1, 2, 1 } }//
        ));
        Assert.assertEquals(0, solution.minimumEffortPath(//
                new int[][] { { 3 } }//
        ));
        Assert.assertEquals(9, solution.minimumEffortPath(//
                new int[][] { { 1, 10, 6, 7, 9, 10, 4, 9 } }//
        ));
    }

    // 二分法+深度优先搜索
    // 可以将这个问题转化成一个「判定性」问题，即：是否存在一条从左上角到右下角的路径，其经过的所有边权的最大值不超过 x。
    // 可以用深度优先搜索去进行遍历判定
    // 时间复杂度：O(mnlog⁡C)，其中 m 和 n 分别是地图的行数和列数，C 是格子的最大高度，在本题中 C 不超过 10^6。我们需要进行 O(log⁡C) 次二分查找，每一步查找的过程中需要使用广度优先搜索，在 O(mn) 的时间判断是否可以从左上角到达右下角，因此总时间复杂度为 O(mnlog⁡C)。
    // 空间复杂度：O(mn)，visited数组和递归栈所需的空间。
    static class Solution {

        private static final int[][] DIRECTION = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        public int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;
            int left = 0;
            int right = 999999;
            int answer = 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                if (dfs(heights, 0, 0, m, n, new boolean[m][n], middle)) {
                    answer = middle;
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            return answer;
        }

        private boolean dfs(int[][] heights, int i, int j, int m, int n, boolean[][] visited, int x) {
            visited[i][j] = true;

            if (i == m - 1 && j == n - 1) {
                return true;
            }

            for (int[] direction : DIRECTION) {
                int ni = i + direction[0];
                int nj = j + direction[1];
                if (ni < 0 || ni >= m || nj < 0 || nj >= n || visited[ni][nj] || Math.abs(heights[i][j] - heights[ni][nj]) > x) {
                    continue;
                }
                if (dfs(heights, ni, nj, m, n, visited, x)) {
                    return true;
                }
            }
            return false;
        }
    }

    // 二分法 + 广度优先搜索
    // 可以将这个问题转化成一个「判定性」问题，即：是否存在一条从左上角到右下角的路径，其经过的所有边权的最大值不超过 x。
    // 可以用广度优先搜索去进行遍历判定
    // 时间复杂度：O(mnlog⁡C)，其中 m 和 n 分别是地图的行数和列数，C 是格子的最大高度，在本题中 C 不超过 10^6。我们需要进行 O(log⁡C) 次二分查找，每一步查找的过程中需要使用广度优先搜索，在 O(mn) 的时间判断是否可以从左上角到达右下角，因此总时间复杂度为 O(mnlog⁡C)。
    // 空间复杂度：O(mn)，即为广度优先搜索中使用的队列需要的空间。
    static class Solution1 {

        private static final int[][] DIRECTION = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        public int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;
            int left = 0;
            int right = 999999;
            int answer = 0;
            while (left <= right) {
                int middle = (left + right) / 2;
                boolean[][] visited = new boolean[m][n];
                Queue<int[]> queue = new LinkedList<int[]>();
                queue.offer(new int[] { 0, 0 });
                visited[0][0] = true;
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    int row = cell[0];
                    int column = cell[1];
                    for (int i = 0; i < DIRECTION.length; i++) {
                        int nextRow = row + DIRECTION[i][0];
                        int nextColumn = column + DIRECTION[i][1];
                        if (nextRow >= 0 && nextRow < m && nextColumn >= 0 && nextColumn < n && !visited[nextRow][nextColumn]
                                && Math.abs(heights[row][column] - heights[nextRow][nextColumn]) <= middle) {
                            queue.offer(new int[] { nextRow, nextColumn });
                            visited[nextRow][nextColumn] = true;
                        }
                    }
                }
                if (visited[m - 1][n - 1]) {
                    answer = middle;
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            return answer;
        }
    }

    // 并查集
    // 题目分析：
    // ├ 地图中的每一个格子看成图中的一个节点；
    // ├ 两个相邻（左右相邻或者上下相邻）的两个格子对应的节点之间连接一条无向边，边的权值为这两个格子的高度差的绝对值；
    // ├ 需要找到一条从左上角到右下角的「最短路径」，其中路径的长度定义为路径上所有边的权值的最大值。
    // └ 题目的目标是得到左上角走到右下角的最小体力消耗值（不是所有路径权值的和，而是最小路径中最大的权值）
    // 问题可以转换为连通性问题
    // ├ 首先将所有节点放入并查集中，实时维护它们的连通性。
    // ├ 由于需要找到从左上角到右下角的最短路径，因此我们可以将图中的所有边按照权值从小到大进行排序，并依次加入并查集中。
    // └ 当我们加入一条权值为 x 的边之后，如果左上角和右下角从非连通状态变为连通状态，那么 x 即为答案。
    // 时间复杂度：O(mnlog⁡(mn))，其中 m 和 n 分别是地图的行数和列数。
    // ├ 图中的边数为 O(mn)，因此排序的时间复杂度为 O(mnlog⁡(mn))。
    // ├ 并查集的时间复杂度为 O(mn*⋅α(mn))。
    // └ 由于后者在渐进意义下小于前者，因此总时间复杂度为 O(mnlog⁡(mn))。
    // 空间复杂度：O(mn)，即为存储所有边以及并查集需要的空间。
    static class Solution2 {

        public int minimumEffortPath(int[][] heights) {

            int m = heights.length;
            int n = heights[0].length;

            // 创建边 edge = int[]{x,y,length}
            List<int[]> edges = new ArrayList<int[]>(2 * m * n - m - n);
            for (int row = 0; row < m; row++) {
                for (int column = 0; column < n; column++) {
                    int idx = row * n + column;
                    if (row > 0) {
                        edges.add(new int[] { idx - n, idx, Math.abs(heights[row][column] - heights[row - 1][column]) });
                    }
                    if (column > 0) {
                        edges.add(new int[] { idx - 1, idx, Math.abs(heights[row][column] - heights[row][column - 1]) });
                    }
                }
            }

            // 根据边长排序
            Collections.sort(edges, (edge1, edge2) -> edge1[2] - edge2[2]);

            // 并查集
            int[] parent = new int[m * n];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
            // 右上角和左下角的索引
            int tlc = 0;
            int lrc = m * n - 1;
            for (int[] edge : edges) {
                union(edge[0], edge[1], parent);
                // 连通了
                if (connected(tlc, lrc, parent)) {
                    return edge[2];
                }
            }

            // 如果没有边，则会执行到这里
            return 0;
        }

        private void union(int x, int y, int[] parent) {
            parent[find(x, parent)] = find(y, parent);
        }

        private int find(int x, int[] parent) {
            return parent[x] == x ? x : (parent[x] = find(parent[x], parent));
        }

        private boolean connected(int x, int y, int[] parent) {
            return find(x, parent) == find(y, parent);
        }
    }
}
