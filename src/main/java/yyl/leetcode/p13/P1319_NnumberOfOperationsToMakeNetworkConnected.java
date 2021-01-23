package yyl.leetcode.p13;

import yyl.leetcode.util.Assert;

/**
 * <h3>连通网络的操作次数</h3><br>
 * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。<br>
 * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。<br>
 * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。 <br>
 * 
 * <pre>
 * 示例 1：
 * 
 * [0]──────────[1]         [0]──────────[1]
 *  │            │           │            │
 *  │     ┌──────┘    ==>    │            │
 *  │     │                  │            │
 * [2]────┘     [3]         [2]          [3]
 * 
 * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
 * 输出：1
 * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
 * 
 * 
 * 
 * 示例 2：
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
 * 输出：2
 * 
 * 示例 3：
 * 输入：n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
 * 输出：-1
 * 解释：线缆数量不足。
 * 
 * 示例 4：
 * 输入：n = 5, connections = [[0,1],[0,2],[3,4],[2,3]]
 * 输出：0
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= n <= 10^5 <br>
 * ├ 1 <= connections.length <= min(n*(n-1)/2, 10^5) <br>
 * ├ connections[i].length == 2 <br>
 * ├ 0 <= connections[i][0], connections[i][1] < n <br>
 * ├ connections[i][0] != connections[i][1] <br>
 * ├ 没有重复的连接。 <br>
 * └ 两台计算机不会通过多条线缆连接。 <br>
 */
public class P1319_NnumberOfOperationsToMakeNetworkConnected {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(1, solution.makeConnected(4, new int[][] { { 0, 1 }, { 0, 2 }, { 1, 2 } }));
        Assert.assertEquals(2, solution.makeConnected(6, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 } }));
        Assert.assertEquals(-1, solution.makeConnected(6, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 } }));
        Assert.assertEquals(0, solution.makeConnected(5, new int[][] { { 0, 1 }, { 0, 2 }, { 3, 4 }, { 2, 3 } }));
    }

    // 并查集
    // 算法和思路
    // ├ 返回-1的情况
    // │ ├ 当计算机的数量为 n 时，至少需要 n−1 根线才能将它们进行连接。如果线的数量少于 n−1 ，那么无论如何都无法将这 n 台计算机进行连接。
    // │ └ 因此如果数组 connections 的长度小于 n−1，直接返回 −1 作为答案，
    // └ 操作方式
    // - ├ n 台电脑只需要 n−1 根线就可以将它们进行连接。如果一个节点数为 n 的连通分量中的边数超过 n−1，就一定可以找到一条多余的边，且移除这条边之后，连通性保持不变。
    // - ├ 此时，可以用移除这条边，然后用这条边来连接两个连通分量，使得图中连通分量的个数减少 1。
    // - └ 因此，需要移动的网线数量其实就是连通分量的个数-1 (全部连通之后，应该只有1个连通分量，每次合并一个分量都需要移动一个网线)
    // 时间复杂度：O(m*α(n))，其中 m 是数组 connections 的长度，α 是阿克曼函数的反函数。
    // 空间复杂度：O(n)，即为并查集需要使用的空间。
    static class Solution {
        public int makeConnected(int n, int[][] connections) {
            if (connections.length < n - 1) {
                return -1;
            }
            UnionFind uf = new UnionFind(n);
            for (int[] conn : connections) {
                uf.unite(conn[0], conn[1]);
            }
            return uf.setCount - 1;
        }

        // 并查集模板
        class UnionFind {

            int[] parent;
            int[] rank;
            // 当前连通分量数目
            int setCount;

            public UnionFind(int n) {
                this.setCount = n;
                this.parent = new int[n];
                this.rank = new int[n];
                for (int i = 0; i < n; ++i) {
                    parent[i] = i;
                    rank[i] = 1;
                }
            }

            public int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }

            public boolean unite(int x, int y) {
                int xRoot = find(x);
                int yRoot = find(y);

                if (xRoot == yRoot) {
                    return false;
                }

                if (rank[xRoot] < rank[yRoot]) {
                    int temp = xRoot;
                    xRoot = yRoot;
                    yRoot = temp;
                }

                parent[yRoot] = xRoot;
                rank[xRoot] += rank[yRoot];
                --setCount;
                return true;
            }
        }
    }
}
