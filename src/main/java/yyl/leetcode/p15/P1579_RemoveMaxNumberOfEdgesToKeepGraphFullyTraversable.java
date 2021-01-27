package yyl.leetcode.p15;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>保证图可完全遍历</h3><br>
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3 种类型的边： <br>
 * ├ 类型 1：只能由 Alice 遍历。 <br>
 * ├ 类型 2：只能由 Bob 遍历。 <br>
 * └ 类型 3：Alice 和 Bob 都可以遍历。 <br>
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。 <br>
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。 <br>
 * 
 * <pre>
 * 示例 1：
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * 输出：2
 * 解释：如果删除 [1,1,2] 和 [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。
 * {1,3}──1───{1}
 * │           │
 * 2────(t3)───3
 * │           │
 * {1}───4────{2}
 * 
 * 示例 2：
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * 输出：0
 * 解释：注意，删除任何一条边都会使 Alice 和 Bob 无法完全遍历这个图。
 *  1──{3}──2──{3}
 *  │   
 *  └──{1,2}──4
 * 
 * 示例 3：
 * 输入：n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * 输出：-1
 * 解释：在当前图中，Alice 无法从其他节点到达节点 4 。类似地，Bob 也不能达到节点 1 。因此，图无法完全遍历。
 * 1──{1}──2──{3}──3──{4}──4
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= n <= 10^5 ├ 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2) <br>
 * ├ edges[i].length == 3 <br>
 * ├ 1 <= edges[i][0] <= 3 <br>
 * ├ 1 <= edges[i][1] < edges[i][2] <= n <br>
 * └ 所有元组 (typei, ui, vi) 互不相同 <br>
 */
public class P1579_RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, //
                solution.maxNumEdgesToRemove(4, new int[][] { { 3, 1, 2 }, { 3, 2, 3 }, { 1, 1, 3 }, { 1, 2, 4 }, { 1, 1, 2 }, { 2, 3, 4 } }));
        Assert.assertEquals(0, //
                solution.maxNumEdgesToRemove(4, new int[][] { { 3, 1, 2 }, { 3, 2, 3 }, { 1, 1, 4 }, { 2, 1, 4 } }));
        Assert.assertEquals(-1, //
                solution.maxNumEdgesToRemove(4, new int[][] { { 3, 2, 3 }, { 1, 1, 2 }, { 2, 3, 4 } }));
    }

    // 并查集
    // 思路与算法
    // ├ 有三种类型的边：「Alice 独占边」「Bob 独占边」以及「公共边」。
    // ├ 由于题目描述中希望删除最多数目的边，这等价于保留最少数目的边。可以从一个仅包含 n 个节点（而没有边）的无向图开始，逐步添加边，使得满足上述的要求。
    // ├ 「公共边」的重要性大于「公共边独占边」
    // │ ├对于一条连接了两个不同的连通分量的「公共边」而言，如果不保留这条公共边，那么 Alice 和 Bob 就无法往返这两个连通分量，即他们分别需要使用各自的独占边。
    // │ └ 因此，如果不使用「公共边」 ，Alice 和 Bob 需要各自一条「独占边」 连接 两个连通分量的，这就严格不优于直接使用一条连接这两个连通分量的「公共边」了。
    // ├ 因此，添加边时候，需要遵从优先添加「公共边」的策略。
    // │ ├ 如果这两个节点在同一个连通分量中，那么添加这条「公共边」是无意义的；
    // │ └ 如果这两个节点不在同一个连通分量中，就可以添加这条「公共边」，然后合并这两个节点所在的连通分量。
    // ├ 在处理完了所有的「公共边」之后，需要处理他们各自的独占边，而方法也与添加「公共边」类似。
    // │ ├ 将当前的并查集复制一份，一份交给 Alice，一份交给 Bob。随后 Alice 不断地向并查集中添加「Alice 独占边」，Bob 不断地向并查集中添加「Bob 独占边」。
    // │ └ 在处理完了所有的独占边之后，如果这两个并查集都只包含一个连通分量，那么就说明 Alice 和 Bob 都可以遍历整个无向图。
    // └ 在使用并查集进行合并的过程中，每遇到一次失败的合并操作（即需要合并的两个点属于同一个连通分量），那么就说明当前这条边可以被删除，将答案增加 1。
    // 时间复杂度：O(m*α(n))，其中n是节点数量，其中 m 是数组 edges 的长度，α 是阿克曼函数的反函数。
    // 空间复杂度：O(n)，即为并查集需要使用的空间。
    static class Solution {
        public int maxNumEdgesToRemove(int n, int[][] edges) {

            // 节点编号改为从 0 开始
            for (int[] edge : edges) {
                --edge[1];
                --edge[2];
            }

            int answer = 0;

            // 并查集
            DisjointSet commonDsu = new DisjointSet(n);

            // 公共边
            for (int[] edge : edges) {
                if (edge[0] == 3) {
                    if (!commonDsu.union(edge[1], edge[2])) {
                        answer++;
                    }
                }
            }

            DisjointSet aliceDsu = commonDsu.clone();
            DisjointSet bobDsu = commonDsu.clone();

            // 独占边
            for (int[] edge : edges) {
                if (edge[0] == 1) {
                    // Alice 独占边
                    if (!aliceDsu.union(edge[1], edge[2])) {
                        ++answer;
                    }
                } else if (edge[0] == 2) {
                    // Bob 独占边
                    if (!bobDsu.union(edge[1], edge[2])) {
                        ++answer;
                    }
                }
            }
            if (aliceDsu.count != 1 || bobDsu.count != 1) {
                return -1;
            }
            return answer;
        }

        class DisjointSet implements Cloneable {
            int[] parent;
            int count;

            DisjointSet(int n) {
                parent = new int[n];
                count = n;
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            DisjointSet(DisjointSet dsu) {
                this.parent = Arrays.copyOf(dsu.parent, dsu.parent.length);
                this.count = dsu.count;
            }

            int find(int x) {
                return parent[x] == x ? x : (parent[x] = find(parent[x]));
            }

            boolean union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return false;
                }
                parent[rootX] = rootY;
                count--;
                return true;
            }

            @Override
            protected DisjointSet clone() {
                return new DisjointSet(this);
            }
        }
    }
}
