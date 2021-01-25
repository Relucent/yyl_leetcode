package yyl.leetcode.p09;

import yyl.leetcode.util.Assert;

/**
 * <h3>由斜杠划分区域</h3><br>
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。<br>
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。<br>
 * 返回区域的数目。<br>
 * 
 * <pre>
 * 比如这个图，被 / 和  \ 分成了两部分，应当返回3。
 * ┌────┐
 * │  / │
 * │ /\ │
 * │/  \│
 * └────┘
 * </pre>
 * 
 * <pre>
 * 示例 1：
 * 输入：
 * [
 *   " /",
 *   "/ "
 * ]
 * 输出：2
 * 
 * 示例 2：
 * 
 * 输入：
 * [
 *   " /",
 *   "  "
 * ]
 * 输出：1
 * 
 * 示例 3：
 * 输入：
 *   "\\/",
 *   "/\\"
 * ]
 * 输出：4
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 * 
 * 示例 4：
 * 输入：
 * [
 *   "/\\",
 *   "\\/"
 * ]
 * 
 * 输出：5
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
 * 
 * 示例 5：
 * 
 * 输入：
 * [
 *   "//",
 *   "/ "
 * ]
 * 输出：3
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= grid.length == grid[0].length <= 30 <br>
 * └ grid[i][j] 是 '/'、'\'、或 ' '。 <br>
 */
public class P0959_RegionsCutBySlashes {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.regionsBySlashes(new String[] { " /", "/ " }));
        Assert.assertEquals(1, solution.regionsBySlashes(new String[] { " /", "  " }));
        Assert.assertEquals(4, solution.regionsBySlashes(new String[] { "\\/", "/\\" }));
        Assert.assertEquals(5, solution.regionsBySlashes(new String[] { "/\\", "\\/" }));
        Assert.assertEquals(3, solution.regionsBySlashes(new String[] { "//", "/ " }));
    }

    // 并查集
    // 思路
    // 沿着一个网格的两条对角线，能够将正方形切分成四个三角形。(将一个格子想象成4个部分）
    // ┌──┐
    // │\/│
    // │/\│
    // └──┘
    // 顺时针编号为 0、1、2、3
    // |╲0╱|
    // |3╳1|
    // |╱2╲|
    // 如果网格上的字符为 /，则右下角的两个三角形会与左上角的两个三角形分隔开；
    // ┌──┐
    // │ /│
    // │/ │
    // └──┘
    // 同理，如果字符为 \，则右上角的两个三角形会和左下角的两个三角形分隔开。
    // ┌──┐
    // │\ │
    // │ \│
    // └──┘
    // 可以发现，如果将每个三角形看作为一张图上的节点，则网格中的一个共边区域，就相当于图中的一个连通分量。。
    // 设网格为 n×n 大小，则图中有 4(n^2) 个节点，每个格子对应其中的 4 个节点。
    // ├ 对于每个格子而言，考虑当前位置的字符：
    // │ ├ 如果为空格，则该格子对应的 4 个节点应当同属于同一区域，因此在它们之间各连接一条边；
    // │ ├ 如果为字符 /，则将左上角的两个格子连接一条边，并将右下角的两个格子连接一条边；
    // │ └ 如果为字符 \，则将右上角的两个格子连接一条边，并将左下角的两个格子连接一条边。
    // ├ 相关格子的情况：
    // │ ├ 一个格子中最下方的三角形，必然和下面的格子（如果存在）中最上方的三角形连通；
    // │ ├ 一个格子中最右方的三角形，必然和右边的格子（如果存在）中最左方的三角形连通。
    // │ └ 因此，需要根据上面两条规则，在相邻格子的相应三角形中间，再连接边。
    // └ 在构造出图后，利用并查集就可以求出连通分量的数目了。
    // 具体实现方面，每个格子的 4 个节点按照上、右、下、左的顺序依次编号 0、1、2、3，每个节点可以根据格子所在的行和列以及节点在格子中的编号唯一地确定。
    // 时间复杂度：O(n^2*log{n})，其中 n 是网格的边长。
    // 空间复杂度：O(n^2)。
    static class Solution {
        public int regionsBySlashes(String[] grid) {

            int n = grid.length;

            // 并查集（1个格子分4部分，一共4(n^2) 个节点
            int m = n * n * 4;
            DisjointSet dsu = new DisjointSet(m);

            // 构造图合并
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int idx = (i * n + j) * 4;
                    char c = grid[i].charAt(j);

                    // 单元格内合并
                    if (c == '/') {
                        // 合并 0、3，合并 1、2
                        dsu.union(idx + 0, idx + 3);
                        dsu.union(idx + 1, idx + 2);
                    } else if (c == '\\') {
                        // 合并 0、1，合并 2、3
                        dsu.union(idx + 0, idx + 1);
                        dsu.union(idx + 2, idx + 3);
                    } else {
                        // 合并 0、1，2、3
                        dsu.union(idx + 0, idx + 1);
                        dsu.union(idx + 1, idx + 2);
                        dsu.union(idx + 2, idx + 3);
                    }

                    // 单元格间合并
                    // 向下合并：一个格子中最下方的三角形，必然和下面的格子（如果存在）中最上方的三角形连通；
                    if (i < n - 1) {
                        // 合并当前格子2 和 下面格子的0
                        int bottomIdx = idx + (4 * n);
                        dsu.union(idx + 2, bottomIdx + 0);
                    }

                    // 向右合并：一个格子中最右方的三角形，必然和右边的格子（如果存在）中最左方的三角形连通。
                    if (j < n - 1) {
                        // 合并当前格子1 和 右侧格子的3
                        int rightIdx = idx + 4;
                        dsu.union(idx + 1, rightIdx + 3);
                    }
                }
            }

            return dsu.count;
        }

        class DisjointSet {
            int[] parent;
            int count;

            DisjointSet(int n) {
                parent = new int[n];
                count = n;
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }

            void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return;
                }
                parent[rootX] = rootY;
                count--;
            }
        }
    }
}
