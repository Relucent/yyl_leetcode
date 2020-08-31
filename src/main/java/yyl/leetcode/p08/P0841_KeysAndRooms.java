package yyl.leetcode.p08;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

import yyl.leetcode.util.Assert;

/**
 * <h3>钥匙和房间</h3><br>
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。<br>
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。<br>
 * 最初，除 0 号房间外的其余所有房间都被锁住。<br>
 * 你可以自由地在房间之间来回走动。<br>
 * 如果能进入每个房间返回 true，否则返回 false。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入: [[1],[2],[3],[]]
 * 输出: true
 * 解释:  
 * 我们从 0 号房间开始，拿到钥匙 1。
 * 之后我们去 1 号房间，拿到钥匙 2。
 * 然后我们去 2 号房间，拿到钥匙 3。
 * 最后我们去了 3 号房间。
 * 由于我们能够进入每个房间，我们返回 true。
 * 
 * 示例 2：
 * 输入：[[1,3],[3,0,1],[2],[0]]
 * 输出：false
 * 解释：我们不能进入 2 号房间。
 * </pre>
 * 
 * 提示：<br>
 * 1 <= rooms.length <= 1000<br>
 * 0 <= rooms[i].length <= 1000<br>
 * 所有房间中的钥匙数量总计不超过 3000。<br>
 */
public class P0841_KeysAndRooms {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // [[1],[2],[3],[]] => true
        Assert.assertTrue(solution.canVisitAllRooms(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3), Arrays.asList())));
        // [[1,3],[3,0,1],[2],[0]] => false
        Assert.assertFalse(solution.canVisitAllRooms(Arrays.asList(Arrays.asList(1, 3), Arrays.asList(3, 0, 1), Arrays.asList(2), Arrays.asList())));
    }

    // 深度优先搜索
    // 使用深度优先搜索的方式遍历整张图，统计可以到达的节点个数，并利用数组 visited 标记当前节点是否访问过，以防止重复访问。
    // 时间复杂度：O(n+m)，其中 n 是房间的数量，m 是所有房间中的钥匙数量的总数。
    // 空间复杂度：O(n) ，其中 n 是房间的数量。主要为栈空间的开销。
    static class Solution {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int n = rooms.size();
            int[] num = new int[1];
            boolean[] visited = new boolean[n];
            dfs(rooms, 0, visited, num);
            return num[0] == n;
        }

        private void dfs(List<List<Integer>> rooms, int room, boolean[] visited, int[] num) {
            if (visited[room]) {
                return;
            }
            visited[room] = true;
            num[0]++;
            for (int v : rooms.get(room)) {
                dfs(rooms, v, visited, num);
            }
        }
    }

    // 广度优先搜索
    // 使用广度优先搜索的方式遍历整张图，统计可以到达的节点个数，并利用数组 visited 标记当前节点是否访问过，以防止重复访问。
    // 时间复杂度：O(n+m)，其中 n 是房间的数量，m 是所有房间中的钥匙数量的总数。
    // 空间复杂度：O(n) ，其中 n 是房间的数量。主要为队列的开销。
    static class Solution1 {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            int n = rooms.size();
            int num = 0;
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new ArrayDeque<>();
            visited[0] = true;
            queue.offer(0);
            while (!queue.isEmpty()) {
                int room = queue.poll();
                num++;
                for (int next : rooms.get(room)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            return num == n;
        }
    }
}
