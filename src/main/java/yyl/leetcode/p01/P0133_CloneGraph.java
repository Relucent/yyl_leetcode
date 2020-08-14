package yyl.leetcode.p01;

import java.util.HashMap;
import java.util.Map;

import yyl.leetcode.bean.Node;
import yyl.leetcode.util.Assert;

/**
 * <h3>克隆图</h3><br>
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。<br>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。<br>
 * 
 * <pre>
 * class Node {
 *     public int val;
 *     public List<Node> neighbors;
 * }
 * </pre>
 * 
 * 测试用例格式：<br>
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。<br>
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。<br>
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：adjList = [[2,4],[1,3],[2,4],[1,3]]
 * 输出：[[2,4],[1,3],[2,4],[1,3]]
 * 解释：
 * 图中有 4 个节点。
 * 节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
 * 节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
 * 节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
 * 
 * 示例 2：
 * 输入：adjList = [[]]
 * 输出：[[]]
 * 解释：输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
 * 
 * 示例 3：
 * 输入：adjList = []
 * 输出：[]
 * 解释：这个图是空的，它不含任何节点。
 * 
 * 示例 4：
 * 
 * 输入：adjList = [[2],[1]]
 * 输出：[[2],[1]]
 * </pre>
 * 
 * 提示：<br>
 * 节点数不超过 100 。<br>
 * 每个节点值 Node.val 都是唯一的，1 <= Node.val <= 100。<br>
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。<br>
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。<br>
 * 图是连通图，你可以从给定节点访问到所有节点。<br>
 */
public class P0133_CloneGraph {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String edges = "[[2,4],[1,3],[2,4],[1,3]]";
        Node node = Node.createGraph(edges);
        Node clone = solution.cloneGraph(node);
        Assert.assertEquals(node, clone);
        Assert.assertFalse(node == clone);
    }

    // 深度优先搜索
    // ├ 使用一个哈希表存储所有已被访问和克隆的节点。哈希表中的 key 是原始图中的节点的值，value是克隆图中的对应节点。
    // └ 从给定节点开始遍历图。
    // _ ├ 如果某个节点已经被访问过，则返回其克隆图中的对应节点。
    // _ └ 如果当前访问的节点不在哈希表中，则创建它的克隆节点并存储在哈希表中，并返回克隆节点。
    // ___ └ 递归调用每个节点的邻接点，克隆邻接点的节点，将其放入对应克隆节点的邻接表中。
    // 时间复杂度：O(N)，其中 N 表示节点数量。深度优先搜索遍历图的过程中每个节点只会被访问一次。
    // 空间复杂度：O(N)，存储克隆节点和原节点的哈希表需要 O(N) 的空间，递归调用栈需要 O(H)的空间，其中 H是图的深度，O(H)<=O(N)，因此总体空间复杂度为 O(N)。
    static class Solution {
        public Node cloneGraph(Node node) {
            return clone(node, new HashMap<>());
        }

        private Node clone(Node node, Map<Integer, Node> visited) {
            if (node == null) {
                return node;
            }
            // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
            Node clone = visited.get(node.val);
            if (clone != null) {
                return clone;
            }
            // 克隆节点，并存储到哈希表
            visited.put(node.val, clone = new Node(node.val));
            // 遍历该节点的邻居并更新克隆节点的邻居列表
            for (Node neighbor : node.neighbors) {
                clone.neighbors.add(clone(neighbor, visited));
            }
            return clone;
        }
    }
}
