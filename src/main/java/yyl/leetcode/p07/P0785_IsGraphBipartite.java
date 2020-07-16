package yyl.leetcode.p07;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <h3>判断二分图</h3><br>
 * 给定一个无向图graph，当这个图为二分图时返回true。<br>
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。<br>
 * graph将会以邻接表方式给出，graph[i]表示图中与节点i相连的所有节点。每个节点都是一个在0到graph.length-1之间的整数。这图中没有自环和平行边： graph[i] 中不存在i，并且graph[i]中没有重复的值。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [[1,3], [0,2], [1,3], [0,2]]
 * 输出: true
 * 解释: 
 * 无向图如下:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * 我们可以将节点分成两组: {0, 2} 和 {1, 3}。
 * 
 * 示例 2:
 * 输入: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * 输出: false
 * 解释: 
 * 无向图如下:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * 我们不能将节点分割成两个独立的子集。
 * 
 * 注意:
 *     graph 的长度范围为 [1, 100]。
 *     graph[i] 中的元素的范围为 [0, graph.length - 1]。
 *     graph[i] 不会包含 i 或者有重复的值。
 *     图是无向的: 如果j 在 graph[i]里边, 那么 i 也会在 graph[j]里边。
 * </pre>
 */
public class P0785_IsGraphBipartite {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.isBipartite(new int[][] { { 1, 3 }, { 0, 2 }, { 1, 3 }, { 0, 2 } }));// true
		System.out.println(solution.isBipartite(new int[][] { { 1, 2, 3 }, { 0, 2 }, { 0, 1, 3 }, { 0, 2 } }));// false
	}

	// 深度优先搜索
	// 二分图（Bipartite graph）是一类特殊的图，它可以被划分为两个部分(两个独立的点集)，每个部分内的点互不相连。
	// 算法：
	// ├ 我们任选一个节点开始，将其染成色，并从该节点开始对整个无向图进行遍历
	// ├ 在遍历的过程中，如果我们通过节点 x 遍历到了节点 y （即 x和 y在图中有一条边直接相连），那么会有两种情况：
	// │ ├ 如果 y 未被染色，那么我们将其染成与 x 不同的颜色，并对 y 直接相连的节点进行遍历；
	// │ └ 如果 y 被染色，并且颜色与 x相同，那么说明给定的无向图不是二分图。我们可以直接退出遍历并返回 False作为答案。
	// └ 当遍历结束时，说明给定的无向图是二分图，返回 True 作为答案。
	// 时间复杂度：O(N+M)，其中 N 和 M 分别是无向图中的点数和边数。
	// 空间复杂度：O(N)，存储节点颜色的数组需要 O(N)的空间，在深度优先搜索的过程中，栈的深度最大为 N，需要 O(N)的空间。
	static class Solution {
		public boolean isBipartite(int[][] graph) {
			int n = graph.length;
			int[] colors = new int[n];// 0,1,2
			for (int x = 0; x < n; x++) {
				if (colors[x] == 0) {
					if (!dfs(x, 1, graph, colors)) {
						return false;
					}
				}
			}
			return true;
		}

		private boolean dfs(int x, int color, int[][] graph, int[] colors) {
			colors[x] = color;
			int cNei = color == 1 ? 2 : 1;
			for (int y : graph[x]) {
				if (colors[y] != cNei) {
					return false;
				}
				if (colors[y] == 0) {
					if (!dfs(y, cNei, graph, colors)) {
						return false;
					}
				}
			}
			return true;
		}
	}

	// 广度优先搜索
	// 时间复杂度：O(N+M)，其中 N 和 M 分别是无向图中的点数和边数。
	// 空间复杂度：O(N)，存储节点颜色的数组需要 O(N)的空间，在广度优先搜索的过程中，队列中最多有 N−1 个节点，需要 O(N) 的空间。
	static class Solution2 {
		public boolean isBipartite(int[][] graph) {
			int n = graph.length;
			int[] colors = new int[n];// 0,1,2
			for (int i = 0; i < n; i++) {
				if (colors[i] == 0) {
					Queue<Integer> queue = new ArrayDeque<>();
					queue.offer(i);
					colors[i] = 1;
					while (!queue.isEmpty()) {
						int node = queue.poll();
						int cNei = colors[node] == 1 ? 2 : 1;
						for (int neighbor : graph[node]) {
							if (colors[neighbor] == 0) {
								colors[neighbor] = cNei;
								queue.offer(neighbor);
							} else if (colors[neighbor] != cNei) {
								return false;
							}
						}
					}
				}
			}
			return true;
		}
	}
}
