package yyl.leetcode.p09;

import java.util.HashMap;
import java.util.Map;

import yyl.leetcode.util.Assert;

/**
 * <h3>移除最多的同行或同列石头</h3><br>
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。<br>
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。<br>
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：stones = [
 *      [0,0],
 *      [0,1],
 *      [1,0],
 *      [1,2],
 *      [2,1],
 *      [2,2]
 *  ]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 
 * 示例 2：
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 
 * 示例 3：
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= stones.length <= 1000 <br>
 * ├ 0 <= xi, yi <= 104 <br>
 * └ 不会有两块石头放在同一个坐标点上 <br>
 */
public class P0947_MostStonesRemovedWithSameRowOrColumn {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(5, solution.removeStones(new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 } }));
        Assert.assertEquals(3, solution.removeStones(new int[][] { { 0, 0 }, { 0, 2 }, { 1, 1 }, { 2, 0 }, { 2, 2 } }));
        Assert.assertEquals(0, solution.removeStones(new int[][] { { 0, 0 } }));
    }

    // 并查集
    // 规则：如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
    // 思路：把二维坐标平面上的石头想象成图的顶点，如果两个石头横坐标相同、或者纵坐标相同，在它们之间形成一条边。
    // │ + + + +
    // │ + ● ● +
    // │ ● + ● +
    // │ ● ● + +
    // └─────────
    // 建立
    // ~ ~ ●───●
    // ~ ~ │ ~ │
    // ●───┼───●
    // │ ~ │ ~ ~
    // ●───● ~ ~
    // 可以发现：可以把一个连通图里的所有顶点根据这个规则删到只剩下一个顶点。（如果两个石子在一排，那么只能删除一个，因为删除一个后，另一个就是独立的了）
    // ├ 首先，我们构造图：只要两个点同行或同列，那么将两个点相连接
    // ├ 这样，最后的结果图应该是很多个连通图组成的非连通图
    // ├ 而对于任何连通图，我们都可以从一端开始移除直至只剩下一个点
    // ├ 所以，我们只需要判断有多少个连通图，最后便至少剩余多少个点
    // └ 用节点的数量 - 连通图的数列即为结果
    // 石头的位置是二维数组「x,y」，并查集的底层是「一维数组」，所以需要区分「横坐标」和「纵坐标」，进行特殊处理
    // ├ 根据题目的提示 0<=x,y<=10^4
    // └ 可以将 y 进行取反进行处理，保证 y 与 x 不会重复
    // 时间复杂度：O(nlog⁡(A))，其中 n 为石子的数量，A 是数组 stones 里横纵坐标不同值的总数；
    // 空间复杂度：O(A)，并查集的底层哈希表的长度为 A 。
    static class Solution {
        public int removeStones(int[][] stones) {
            UnionFind uf = new UnionFind();
            for (int[] stone : stones) {
                int x = stone[0];
                int y = stone[1];
                uf.union(x, ~y);
            }
            return stones.length - uf.getCount();
        }

        // 并查集(用于存储索引)
        class UnionFind {

            private Map<Integer, Integer> parent;
            private int count;

            public UnionFind() {
                this.parent = new HashMap<>();
                this.count = 0;
            }

            public int getCount() {
                return count;
            }

            // 查找
            public int find(int x) {
                if (x != parent.get(x)) {
                    parent.put(x, find(parent.get(x)));
                }
                // 根节点
                return parent.get(x);
            }

            // 联合
            public void union(int x, int y) {
                // 并查集集中新加入一个节点，结点的父节点是它自己，所以连通分量的总数 +1
                if (parent.putIfAbsent(x, x) == null) {
                    count++;
                }
                // 一个点加了两次，但是下面会做联合，所以最终一个节点最多还是一个连通量
                if (parent.putIfAbsent(y, y) == null) {
                    count++;
                }
                int rootX = find(x);
                int rootY = find(y);
                if (rootX == rootY) {
                    return;
                }
                parent.put(rootX, rootY);
                // 两个连通分量合并成为一个，连通分量的总数 -1
                count--;
            }
        }
    }
}
