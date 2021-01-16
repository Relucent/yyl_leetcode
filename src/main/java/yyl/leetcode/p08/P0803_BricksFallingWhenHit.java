package yyl.leetcode.p08;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>打砖块</h3><br>
 * 有一个 m*n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：<br>
 * ├ 一块砖直接连接到网格的顶部，或者<br>
 * └ 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时<br>
 * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。<br>
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。<br>
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * 输出：[2]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0]，
 *  [1,1,1,0]]
 * 消除 (1,0) 处加的砖块，得到网格：
 * [[1,0,0,0]
 *  [0,1,1,0]]
 * 两个1的砖(1,1),(1,2)不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
 * [[1,0,0,0],
  * [0,0,0,0]]
 * 因此，结果为 [2] 。
 * 
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * 输出：[0,0]
 * 解释：
 * 网格开始为：
 * [[1,0,0,0],
 *  [1,1,0,0]]
 * 消除 (1,1) 处加的砖块，得到网格：
 * [[1,0,0,0],
  * [1,0,0,0]]
 * 剩下的砖都很稳定，所以不会掉落。网格保持不变：
 * [[1,0,0,0], 
 *  [1,0,0,0]]
 * 接下来消除 (1,0) 处加的砖块，得到网格：
 * [[1,0,0,0],
 *  [0,0,0,0]]
 *  剩下的砖块仍然是稳定的，所以不会有砖块掉落。
 *  因此，结果为 [0,0] 。
 * </pre>
 * 
 * 提示： <br>
 * ├ m == grid.length <br>
 * ├ n == grid[i].length <br>
 * ├ 1 <= m, n <= 200 <br>
 * ├ grid[i][j] 为 0 或 1 <br>
 * ├ 1 <= hits.length <= 4 * 104 <br>
 * ├ hits[i].length == 2 <br>
 * ├ 0 <= xi <= m - 1 <br>
 * ├ 0 <= yi <= n - 1 <br>
 * └ 所有 (xi, yi) 互不相同 <br>
 */
public class P0803_BricksFallingWhenHit {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 2 }, solution.hitBricks(//
                new int[][] { { 1, 0, 0, 0 }, { 1, 1, 1, 0 } }, //
                new int[][] { { 1, 0 } }//
        ));
        Assert.assertEquals(new int[] { 0, 0 }, solution.hitBricks(//
                new int[][] { { 1, 0, 0, 0 }, { 1, 1, 0, 0 } }, //
                new int[][] { { 1, 1 }, { 1, 0 } }//
        ));

        Assert.assertEquals(new int[] { 0, 3, 0 }, solution.hitBricks(//
                new int[][] { { 1, 0, 1 }, { 1, 1, 1 } }, //
                new int[][] { { 0, 0 }, { 0, 2 }, { 1, 1 } }//
        ));

        Assert.assertEquals(//
                new int[] { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1 }, //
                solution.hitBricks(//
                        new int[][] { //
                                { 0, 1, 1, 1, 1, 1 }, //
                                { 1, 1, 1, 1, 1, 1 }, //
                                { 0, 0, 1, 0, 0, 0 }, //
                                { 0, 0, 0, 0, 0, 0 }, //
                                { 0, 0, 0, 0, 0, 0 } //
                        }, //
                        new int[][] { //
                                { 1, 3 }, { 3, 5 }, { 0, 3 }, { 3, 3 }, { 1, 1 }, { 4, 2 }, { 1, 0 }, { 3, 0 }, { 4, 5 }, { 2, 1 }, //
                                { 4, 4 }, { 4, 0 }, { 2, 4 }, { 2, 5 }, { 3, 4 }, { 0, 5 }, { 0, 4 }, { 3, 2 }, { 1, 5 }, { 4, 1 }, //
                                { 2, 2 }, { 0, 2 } //
                        }//
                )//
        );
    }

    // 逆序思维 + 并查集
    // 如果使用模拟法进行求解，时间复杂度是 O(n*⋅h*w)，会超出时间限制，所以需要考虑其他解法
    // 思路：
    // ├ 问题是一个图的连通性问题，砖块和砖块如果在 4 个方向上相邻，表示这两个砖块上有一条边。砖块的相邻关系而产生的连接关系具有传递性；
    // │ ├ 第 0 行的砖块连接着「屋顶」；
    // │ ├ 击碎了一个砖块以后，可能会使得其它与「被击碎砖块」 连接 的砖块不再与顶部相连，然后它们消失；
    // │ ├ 题目只问结果，没有问具体连接的情况；
    // │ └ 连通性问题，一般可以联想到「并查集」
    // ├ 并查集内部可以维护「以当前结点为根结点的子树的结点总数」。
    // │ └ 并查集一般用来合并连通分量，而不是拆分连通分量，正好和并查集的用法相反，所以需要逆向思考
    // └ 逆向思考
    // - ├ 考虑：补上被击碎的砖块以后，有多少个砖块因为这个补上的这个砖块而与屋顶的砖块相连。
    // - └ 每一次击碎一个砖块，因击碎砖块而消失的砖块只会越来越少。因此可以按照数组 hits 的顺序 逆序地 把这些砖块依次补上。
    // 实现步骤
    // ├ 根据数组 hits，将输入的表格 grid 里的对应位置全部设置为 0，这是因为要逆序补全出整个初始化的时候二维表格的砖块；
    // └ 从最后一个击碎的砖块开始，计算补上这个被击碎的砖块的时候，有多少个砖块因为这个补上的砖块而与屋顶相连，这个数目就是按照题目中的描述，击碎砖块以后掉落的砖块的数量。
    // 实现细节
    // ├ 在并查集中设置一个特殊的结点，表示「屋顶」；
    // └ 逆序补回的时候，增加的连接到「屋顶」的砖块数：result[i] = Math.max(0, current - origin - 1); （存在补回一个砖块前后，连接到「屋顶」的砖块总数没有变化的情况）
    // 复杂度分析
    // 时间复杂度：O((N+H*W)*log⁡(H*W))，其中 N 为数组 Hits 的长度，H 和 W 分别为矩阵的行数和列数，只使用了路径压缩，并查集的单次操作的时间复杂度为O(log⁡(H*⋅W))。
    // 空间复杂度：O(H*W)。
    static class Solution {

        private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

        public int[] hitBricks(int[][] grid, int[][] hits) {
            int h = grid.length;
            int w = grid[0].length;

            // 第 1 步：把 grid 中的砖头全部击碎
            int[][] copy = new int[h][w];
            for (int i = 0; i < h; i++) {
                copy[i] = Arrays.copyOf(grid[i], w);
            }

            // 把 copy 中的砖头全部击碎
            for (int[] hit : hits) {
                copy[hit[0]][hit[1]] = 0;
            }

            // 第 2 步：创建并查集，把砖块和砖块的连接关系输入并查集，size 表示二维网格的大小，也表示虚拟的「屋顶」在并查集中的编号
            int size = h * w;
            UnionFind unionFind = new UnionFind(size + 1);

            // 将下标为 0 的这一行的砖块与「屋顶」相连
            for (int j = 0; j < w; j++) {
                if (copy[0][j] == 1) {
                    unionFind.union(j, size);
                }
            }

            // 其余网格，如果是砖块向上、向左看一下，如果也是砖块，在并查集中进行合并
            for (int i = 1; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (copy[i][j] == 1) {
                        // 如果上方也是砖块
                        if (copy[i - 1][j] == 1) {
                            unionFind.union(getIndex(i - 1, j, h, w), getIndex(i, j, h, w));
                        }
                        // 如果左边也是砖块
                        if (j > 0 && copy[i][j - 1] == 1) {
                            unionFind.union(getIndex(i, j - 1, h, w), getIndex(i, j, h, w));
                        }
                    }
                }
            }

            // 第 3 步：按照 hits 的逆序，在 copy 中补回砖块，把每一次因为补回砖块而与屋顶相连的砖块的增量记录到 res 数组中
            int[] answer = new int[hits.length];
            for (int i = hits.length - 1; i >= 0; i--) {
                int row = hits[i][0];
                int column = hits[i][1];

                // 如果原来在 grid 中，这一块是空白，这一步不会产生任何砖块掉落
                // 逆向补回的时候，与屋顶相连的砖块数量也肯定不会增加
                if (grid[row][column] == 0) {
                    continue;
                }

                // 补回之前与屋顶相连的砖块数
                int origin = unionFind.getSize(size);

                // 注意：如果补回的这个结点在第 1 行，要告诉并查集它与屋顶相连（逻辑同第 2 步）
                if (row == 0) {
                    unionFind.union(column, size);
                }

                // 在 4 个方向上看一下，如果相邻的 4 个方向有砖块，合并它们
                for (int[] direction : DIRECTIONS) {
                    int nRow = row + direction[0];
                    int nColumn = column + direction[1];
                    if (inBound(nRow, nColumn, h, w) && copy[nRow][nColumn] == 1) {
                        unionFind.union(getIndex(row, column, h, w), getIndex(nRow, nColumn, h, w));
                    }
                }

                // 补回之后与屋顶相连的砖块数
                int current = unionFind.getSize(size);
                // 减去的 1 是逆向补回的砖块（正向移除的砖块），与 0 比较大小，是因为存在一种情况，添加当前砖块，不会使得与屋顶连接的砖块数更多
                answer[i] = Math.max(0, current - origin - 1);

                // 真正补上这个砖块
                copy[row][column] = 1;
            }
            return answer;
        }

        // 输入坐标在二维网格中是否越界
        private boolean inBound(int i, int j, int h, int w) {
            return i >= 0 && i < h && j >= 0 && j < w;
        }

        // 二维坐标转换为一维坐标
        private int getIndex(int i, int j, int h, int w) {
            return i * w + j;
        }

        private class UnionFind {

            private int[] parent;
            private int[] size;

            public UnionFind(int n) {
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            // 查找+路径压缩
            public int find(int x) {
                if (x != parent[x]) {
                    parent[x] = find(parent[x]);
                }
                return parent[x];
            }

            // 联合
            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);

                if (rootX == rootY) {
                    return;
                }
                parent[rootX] = rootY;
                // 在合并的时候维护数组 size
                size[rootY] += size[rootX];
            }

            // 在并查集的根结点的子树包含的结点总数
            public int getSize(int x) {
                return size[find(x)];
            }
        }
    }

    // 逆向思维 + 深度优先搜索
    // 逆序补回砖块，然后判断补回砖块有多少个砖块可以连接到顶部
    // 时间复杂度：O((N+H*W)*log⁡(H*W))。
    // 空间复杂度：O(1)，在原始网格修改
    static class Solution1 {

        private static int[][] DIRECTIONS = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

        public int[] hitBricks(int[][] grid, int[][] hits) {
            for (int[] hit : hits) {
                grid[hit[0]][hit[1]]--;
            }

            for (int i = 0; i < grid[0].length; i++) {
                dfs(grid, 0, i);
            }

            int[] answer = new int[hits.length];
            for (int k = hits.length - 1; k >= 0; k--) {
                int i = hits[k][0];
                int j = hits[k][1];
                grid[i][j]++;
                if (grid[i][j] == 1 && isConnected(grid, i, j)) {
                    answer[k] = dfs(grid, i, j) - 1;
                }
            }
            return answer;
        }

        private int dfs(int[][] grid, int i, int j) {
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
                return 0;
            }

            grid[i][j] = 2;
            return 1 + dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
        }

        private boolean isConnected(int[][] grid, int x, int y) {
            if (x == 0) {
                return true;
            }
            for (int[] dir : DIRECTIONS) {
                int i = x + dir[0];
                int j = y + dir[1];
                if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 2) {
                    continue;
                }
                return true;
            }

            return false;
        }
    }
}
