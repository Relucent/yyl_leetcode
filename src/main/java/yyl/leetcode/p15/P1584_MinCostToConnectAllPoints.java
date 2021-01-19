package yyl.leetcode.p15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>连接所有点的最小费用</h3><br>
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。<br>
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。<br>
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * 将所有点连接起来，得到最小总费用，总费用为 20。
 * 任意两个点之间只有唯一一条路径互相到达。
 * 
 * 示例 2：
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 
 * 示例 3：
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 
 * 示例 4：
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 
 * 示例 5：
 * 输入：points = [[0,0]]
 * 输出：0
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= points.length <= 1000 <br>
 * ├ -106 <= xi, yi <= 106 <br>
 * └ 所有点 (xi, yi) 两两不同。 <br>
 */
public class P1584_MinCostToConnectAllPoints {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(20, solution.minCostConnectPoints(new int[][] { { 0, 0 }, { 2, 2 }, { 3, 10 }, { 5, 2 }, { 7, 0 } }));
        Assert.assertEquals(18, solution.minCostConnectPoints(new int[][] { { 3, 12 }, { -2, 5 }, { -4, 1 } }));
        Assert.assertEquals(4, solution.minCostConnectPoints(new int[][] { { 0, 0 }, { 1, 1 }, { 1, 0 }, { -1, 1 } }));
        Assert.assertEquals(4000000, solution.minCostConnectPoints(new int[][] { { -1000000, -1000000 }, { 1000000, 1000000 } }));
        Assert.assertEquals(0, solution.minCostConnectPoints(new int[][] { { 0, 0 } }));
    }

    // Kruskal 算法
    // Kruskal 算法是一种最小生成树算法，由 Kruskal 发明。该算法的基本思想是从小到大加入边，是一个贪心算法。
    // 其算法流程为：
    // ├ 1、将图 G={V,E} 中的所有边按照长度由小到大进行排序，等长的边可以按任意顺序。
    // ├ 2、初始化图 G′ 为 {V,∅} ，从前向后扫描排序后的边，如果扫描到的边 e 在 G′ 中连接了两个相异的连通块,则将它插入 G′ 中。
    // └ 3、最后得到的图 G′ 就是图 G 的最小生成树。
    // 可以使用并查集来维护连通性。
    // 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val
    // 时间复杂度：O(n^2*log⁡(n))，其中 n 是节点数。一般 Kruskal 是 O(mlog⁡m) 的算法，本题中 m=n^2，因此总时间复杂度为O(n^2*log⁡(n))。
    // 空间复杂度：O(n^2)，其中 n 是节点数。并查集使用 O(n) 的空间，边集数组需要使用O(n^2) 的空间。
    static class Solution {

        public int minCostConnectPoints(int[][] points) {

            int n = points.length;

            // 将这张完全图中的边全部提取到边集数组中
            List<Edge> edges = new ArrayList<>();
            for (int x = 0; x < n; x++) {
                for (int y = x + 1; y < n; y++) {
                    edges.add(new Edge(x, y, distance(points, x, y)));
                }
            }

            // 对所有边进行排序
            Collections.sort(edges, (edge1, edge2) -> edge1.length - edge2.length);

            DisjointSetUnion dsu = new DisjointSetUnion(n);

            int answer = 0;

            // 联通的边数量等于 n - 1
            int count = n - 1;

            // 从小到大进行枚举
            for (Edge edge : edges) {
                int length = edge.length;
                int x = edge.x;
                int y = edge.y;

                // 连接了两个相异的连通块
                if (dsu.union(x, y)) {
                    answer += length;
                    if (count == 0) {
                        break;
                    }
                    count--;
                }
            }
            return answer;
        }

        // 曼哈顿距离
        public int distance(int[][] points, int x, int y) {
            return Math.abs(points[y][0] - points[x][0]) + Math.abs(points[y][1] - points[x][1]);
        }

        // 并查集
        class DisjointSetUnion {

            int[] parent;
            int[] rank;
            int n;

            public DisjointSetUnion(int n) {
                this.n = n;
                this.rank = new int[n];
                this.parent = new int[n];
                for (int i = 0; i < n; i++) {
                    this.parent[i] = i;
                }
            }

            public int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }

            public boolean union(int x, int y) {
                int fx = find(x);
                int fy = find(y);
                if (fx == fy) {
                    return false;
                }
                if (rank[fx] < rank[fy]) {
                    int temp = fx;
                    fx = fy;
                    fy = temp;
                }
                rank[fx] += rank[fy];
                parent[fy] = fx;
                return true;
            }
        }

        // 边
        class Edge {

            int length;
            int x;
            int y;

            public Edge(int x, int y, int length) {
                this.x = x;
                this.y = y;
                this.length = length;
            }
        }
    }
}
