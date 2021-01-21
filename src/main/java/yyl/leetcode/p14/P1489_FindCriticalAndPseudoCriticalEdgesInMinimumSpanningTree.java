package yyl.leetcode.p14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import yyl.leetcode.util.Assert;

/**
 * <h3>找到最小生成树里的关键边和伪关键边</h3><br>
 * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。 <br>
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。 <br>
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。 <br>
 * 
 * <pre>
 * 示例 1：
 * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * 输出：[[0,1],[2,3,4,5]]
 * 解释：
 * 描述了给定图。
 *     ┌──────────(2)─────────┐
 *     │                      │
 *   <1,1>                  <2,2>
 *     │                      │
 *    (1)───<0,1>─(0)─<3,2>──(3)
 *     │           │          │
 *     │         <4,3>        │
 *     │           │          │
 *     └───<6,6>──(4)──<5,3>──┘
 *     
 * 图例： (n) 节点； <i,weight> 边号和权重。
 * 
 * 所有的最小生成树。
 *     ┌──────────(2)─────────┐
 *     │                      │
 *   <1,1>                  <2,2>
 *     │                      │
 *    (1)───<0,1>─(0)        (3)
 *                            │
 *                            │
 *                            │
 *                (4)──<5,3>──┘
 *     
 *     ┌──────────(2)─────────┐
 *     │                      │
 *   <1,1>                  <2,2>
 *     │                      │
 *    (1)───<0,1>─(0)        (3)
 *                 │           
 *               <4,3>         
 *                 │           
 *                (4)         
 *
 *     ┌──────────(2)          
 *     │                       
 *   <1,1>                     
 *     │                       
 *    (1)───<0,1>─(0)─<3,2>──(3)
 *                            │
 *                            │
 *                            │
 *                (4)──<5,3>──┘
 *     
 *     ┌──────────(2)          
 *     │                       
 *   <1,1>                     
 *     │                       
 *    (1)───<0,1>─(0)─<3,2>──(3)
 *                 │           
 *               <4,3>         
 *                 │           
 *                (4)          
 * 
 * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
 * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。

 * 示例 2 ：
 * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * 输出：[[],[0,1,2,3]]
 * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
 *     ┌─<0,1>──(1)───┐
 *     │              │
 *    (0)           <1,1>
 *     │              │
 *   <3,1>           (2)
 *     │              │
 *     └──(4)──<2,1>──┘
 * </pre>
 * 
 * 提示： <br>
 * ├ 2 <= n <= 100 <br>
 * ├ 1 <= edges.length <= min(200, n * (n - 1) / 2) <br>
 * ├ edges[i].length == 3 <br>
 * ├ 0 <= fromi < toi < n <br>
 * ├ 1 <= weighti <= 1000 <br>
 * └ 所有 (fromi, toi) 数对都是互不相同的。 <br>
 */
public class P1489_FindCriticalAndPseudoCriticalEdgesInMinimumSpanningTree {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(Arrays.asList(//
                Arrays.asList(0, 1), //
                Arrays.asList(2, 3, 4, 5)//
        ), solution.findCriticalAndPseudoCriticalEdges(//
                5, // n
                new int[][] { { 0, 1, 1 }, { 1, 2, 1 }, { 2, 3, 2 }, { 0, 3, 2 }, { 0, 4, 3 }, { 3, 4, 3 }, { 1, 4, 6 } }// edges
        ));
        Assert.assertEquals(Arrays.asList(//
                Arrays.asList(), //
                Arrays.asList(0, 1, 2, 3) //
        ), solution.findCriticalAndPseudoCriticalEdges(//
                4, // n
                new int[][] { { 0, 1, 1 }, { 1, 2, 1 }, { 2, 3, 1 }, { 0, 3, 1 } }// edges
        ));
    }

    // 枚举 + 最小生成树判定(Kruskal)
    // 基础知识
    // ├ 最小生成树：有n个顶点的连通网可以建立不同的生成树,每一颗生成树都可以作为一个通信网,当我们构造这个连通网所花的成本最小时,搭建该连通网的生成树,就称为最小生成树。
    // └ Kruskal（克鲁斯卡尔）算法：是一种最小生成树算法，由 Kruskal 发明。该算法的基本思想是从小到大加入边，是一个贪心算法。其算法流程为：
    // - ├ 1、将图 G={V,E} 中的所有边按照长度由小到大进行排序，等长的边可以按任意顺序。
    // - ├ 2、初始化图 G′ 为 {V,∅} ，从前向后扫描排序后的边，如果扫描到的边 e 在 G′ 中连接了两个相异的连通块,则将它插入 G′ 中。
    // - └ 3、最后得到的图 G′ 就是图 G 的最小生成树。
    // 思路与算法
    // 返回结果包含两个数值，第一个数组是关键边，第二个数组是伪关键边。
    // 首先需要理解题目描述中对于「关键边」和「伪关键边」的定义：
    // ├ 关键边
    // │ ├ 如果最小生成树中删去某条边，会导致最小生成树的权值和增加，那么它就是一条关键边。
    // │ └ 也就是说，如果设原图最小生成树的权值为 value，那么去掉这条边后：
    // │ - ├ 要么整个图不连通，不存在最小生成树；
    // │ - └ 要么整个图联通，对应的最小生成树的权值大于 value。
    // ├ 伪关键边
    // │ ├ 可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
    // │ ├ 也就是说，在计算最小生成树的过程中，最先考虑这条边，即最先将这条边的两个端点在并查集中合并。
    // │ └ 设最终得到的最小生成树权值为 v，如果 v=value，那么这条边就是伪关键边。
    // └ 因为关键边也满足伪关键边对应的性质。
    // - └ 因此，需要首先对原图执行 Kruskal 算法，得到最小生成树的权值 value，随后再枚举每一条边，首先根据上面的方法判断其是否是关键边，如果不是关键边，再判断其是否是伪关键边。
    // 时间复杂度：O(m^2*⋅α(n))，其中 n 和 m 分别是图中的节点数和边数。首先需要对所有的边进行排序，时间复杂度为 O(mlog⁡m)。一次 Kruskal 算法的时间复杂度为 O(m*α(n))，α 是阿克曼函数的反函数。最多需要执行 2m + 1次Kruskal 算法。
    // 空间复杂度：O(m+n)。在进行排序时，需要额外存储每条边原始的编号，用来返回答案，空间复杂度为 O(m)。Kruskal 算法中的并查集需要使用 O(n) 的空间，因此总空间复杂度为 O(m+n)。
    static class Solution {
        public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

            // 边数
            int m = edges.length;

            Edge[] newEdges = new Edge[m];
            for (int i = 0; i < m; ++i) {
                int[] edge = edges[i];
                newEdges[i] = new Edge(i, edge[0], edge[1], edge[2]);
            }
            // 排序
            Arrays.sort(newEdges, (u, v) -> u.weight - v.weight);

            // 计算 value
            int value = 0;
            {
                UnionFind uf = new UnionFind(n);
                for (int i = 0; i < m; ++i) {
                    Edge edge = newEdges[i];
                    if (uf.unite(edge.x, edge.y)) {
                        value += edge.weight;
                    }
                }
            }

            List<List<Integer>> answer = new ArrayList<>();
            for (int i = 0; i < 2; ++i) {
                answer.add(new ArrayList<Integer>());
            }

            // 关键边
            List<Integer> critical = new ArrayList<>();
            // 伪关键边
            List<Integer> pseudoCritical = new ArrayList<>();

            for (int i = 0; i < m; ++i) {
                // 判断是否是关键边
                {
                    UnionFind uf = new UnionFind(n);
                    int v = 0;
                    for (int j = 0; j < m; ++j) {
                        Edge edge = newEdges[j];
                        if (i != j && uf.unite(edge.x, edge.y)) {
                            v += edge.weight;
                        }
                    }
                    // (整个图不连通，不存在最小生成树) 或者 (整个图联通，对应的最小生成树的权值 v 大于 value)
                    if (uf.count != 1 || (uf.count == 1 && v > value)) {
                        critical.add(newEdges[i].id);
                        continue;
                    }
                }
                // 判断是否是伪关键边
                {
                    UnionFind uf = new UnionFind(n);
                    Edge edge = newEdges[i];
                    uf.unite(edge.x, edge.y);
                    int v = edge.weight;
                    for (int j = 0; j < m; ++j) {
                        edge = newEdges[j];
                        if (i != j && uf.unite(edge.x, edge.y)) {
                            v += edge.weight;
                        }
                    }
                    if (v == value) {
                        pseudoCritical.add(newEdges[i].id);
                    }
                }
            }
            return Arrays.asList(critical, pseudoCritical);
        }

        // 边
        class Edge {
            int id;
            int x;
            int y;
            int weight;

            public Edge(int id, int x, int y, int weight) {
                this.id = id;
                this.x = x;
                this.y = y;
                this.weight = weight;
            }
        }

        // 并查集
        class UnionFind {
            int[] parent;
            int[] rank;
            int n;
            // 当前连通分量数目
            int count;

            public UnionFind(int n) {
                this.n = n;
                this.parent = new int[n];
                this.rank = new int[n];
                this.count = n;
                Arrays.fill(rank, 1);
                for (int i = 0; i < n; ++i) {
                    parent[i] = i;
                }
            }

            public int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }

            public boolean unite(int x, int y) {
                x = find(x);
                y = find(y);
                if (x == y) {
                    return false;
                }
                if (rank[x] < rank[y]) {
                    int temp = x;
                    x = y;
                    y = temp;
                }
                parent[y] = x;
                rank[x] += rank[y];
                count--;
                return true;
            }
        }
    }
}
