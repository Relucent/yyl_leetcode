package yyl.leetcode.p06;

import yyl.leetcode.util.Assert;

/**
 * <h3>冗余连接</h3><br>
 * 在本问题中, 树指的是一个连通且无环的无向图。<br>
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。<br>
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。<br>
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *    1
 *   / \
 *  2 - 3
 * 
 * 示例 2：
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 *  5 - 1 - 2
 *      |   |
 *      4 - 3
 * </pre>
 * 
 * 注意: <br>
 * ├ 输入的二维数组大小在 3 到 1000。<br>
 * └ 维数组中的整数在1到N之间，其中N是输入数组的大小。<br>
 */
public class P0684_RedundantConnection {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(new int[] { 2, 3 }, solution.findRedundantConnection(new int[][] { { 1, 2 }, { 1, 3 }, { 2, 3 } }));
        Assert.assertEquals(new int[] { 1, 4 }, solution.findRedundantConnection(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 4 }, { 1, 5 } }));
    }

    // 并查集
    // 通过并查集寻找附加的边。
    // ├初始时，每个节点都属于不同的连通分量。遍历每一条边，判断这条边连接的两个顶点是否属于相同的连通分量。
    // ├ 树的边的数量比节点的数量少 1，题目中edges是N个节点的树及一条附加的边，所以节点数量等于edges的数量
    // ├ 如果两个顶点属于不同的连通分量，则说明在遍历到当前的边之前，这两个顶点之间不连通，因此当前的边不会导致环出现，合并这两个顶点的连通分量。
    // └ 如果两个顶点属于相同的连通分量，则说明在遍历到当前的边之前，这两个顶点之间已经连通，因此当前的边导致环出现，为附加的边，将当前的边作为答案返回。
    // 时间复杂度：O(Nlog⁡N) ，其中 N 是图中的节点个数。
    // 空间复杂度：O(N)，其中 N 是图中的节点个数。使用数组记录每个节点的祖先。
    static class Solution {
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            int[] parent = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (int[] edge : edges) {
                int x = edge[0];
                int y = edge[1];
                int xRoot = find(parent, x);
                int yRoot = find(parent, y);
                if (xRoot == yRoot) {
                    return edge;
                }
                union(parent, x, y);
            }
            return null;
        }

        private void union(int[] parent, int x, int y) {
            parent[find(parent, x)] = find(parent, y);
        }

        private int find(int[] parent, int x) {
            if (parent[x] != x) {
                parent[x] = find(parent, parent[x]);
            }
            return parent[x];
        }
    }
}
