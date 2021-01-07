package yyl.leetcode.p05;

import yyl.leetcode.util.Assert;

/**
 * <h3>省份数量</h3><br>
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。<br>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。<br>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。<br>
 * 返回矩阵中 省份 的数量。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= n <= 200 <br>
 * ├ n == isConnected.length <br>
 * ├ n == isConnected[i].length <br>
 * ├ isConnected[i][j] 为 1 或 0 <br>
 * ├ isConnected[i][i] == 1 <br>
 * └ isConnected[i][j] == isConnected[j][i] <br>
 */
public class P0547_NumberOfProvinces {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.findCircleNum(new int[][] { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } }));
        Assert.assertEquals(3, solution.findCircleNum(new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } }));
    }

    // 深度优先搜索
    // 遍历所有城市，对于每个城市，如果该城市尚未被访问过，则从该城市开始深度优先搜索，通过矩阵 isConnected 得到与该城市直接相连的城市有哪些，这些城市和该城市属于同一个连通分量，然后对这些城市继续深度优先搜索，直到同一个连通分量的所有城市都被访问到，即可得到一个省份。
    // 遍历完全部城市以后，即可得到连通分量的总数，即省份的总数。
    // 时间复杂度：O(n^2)，其中 n 是城市的数量。需要遍历矩阵 n 中的每个元素。
    // 空间复杂度：O(n)，其中 n 是城市的数量。需要使用数组 visited 记录每个城市是否被访问过，数组长度是 n ，递归调用栈的深度不会超过 n 。
    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            // 城市数量
            int n = isConnected.length;
            // 已访问的城市
            boolean[] visited = new boolean[n];
            int answer = 0;
            // 深度优先搜索
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    dfs(i, n, isConnected, visited);
                    answer++;
                }
            }
            return answer;
        }

        private void dfs(int i, int n, int[][] isConnected, boolean[] visited) {
            for (int j = 0; j < n; j++) {
                if (!visited[j] && isConnected[i][j] == 1) {
                    visited[j] = true;
                    dfs(j, n, isConnected, visited);
                }
            }
        }
    }
}
