package yyl.leetcode.p02;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>课程表</h3><br>
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。<br>
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]<br>
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: 2, [[1,0]] 
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * </pre>
 * 
 * 提示：<br>
 * 你可以假定输入的先决条件中没有重复的边。<br>
 * 1 <= numCourses <= 10^5<br>
 */
public class P0207_CourseSchedule {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // Assert.assertTrue(solution.canFinish(2, new int[][] { { 1, 0 } }));
        // Assert.assertFalse(solution.canFinish(2, new int[][] { { 1, 0 }, { 0, 1 } }));
        System.out.println(solution.canFinish(12, new int[][] { { 1, 4 }, { 0, 1 } }));
    }

    // 深度优先搜索 + 记忆
    // 对于图中的任意一个节点，它在搜索的过程中有三种状态，即：
    // 0、「未搜索」：我们还没有搜索到这个节点；
    // 1、「搜索中」：我们搜索过这个节点，但还没有回溯到该节点，还有相邻的节点没有搜索完成；
    // 2、「已完成」：我们搜索过并且回溯过这个节点，该节点所有的相邻节点都没有出现环。
    // 通过上述的三种状态，可以得出拓扑排序的算法流程，我们任取一个节点开始进行深度优先搜索。
    // 0、如果节点状态为「未搜索」，进行搜索，标记节点为「搜索中」
    // 1、如果节点状态为「搜索中」，那么说明图中出现一个环，返回false
    // 2、如果节点状态为「已完成」，说明这条路线已经判断过了，不用再进行搜索，返回true
    // 3、当节点的所有相邻节点都为「已完成」时，标记当前节点为「已完成」，返回true
    // 4、中途任意节点的状态返回false，都说明拓扑排序不满足要求，那么最终结果为false，如果都符合要求，那么说明没有出现环状引用，最终结果为true
    // 复杂度分析
    // 时间复杂度: O(n+m)，其中 n 为课程数，m为先修课程的要求数。（对图进行深度优先搜索的时间复杂度）
    // 空间复杂度: O(n+m)，邻接表的空间复杂度为 O(n+m)；空间复杂度为 O(n+m)O(n+m)O(n+m)。在深度优先搜索的过程中，最多需要 O(n)的栈空间（递归）进行深度优先搜索
    static class Solution {
        private static final int USE = 1;// 「搜索中」
        private static final int OK = 2;// 「已完成」

        public boolean canFinish(int numCourses, int[][] prerequisites) {

            // 邻接表
            @SuppressWarnings("unchecked")
            List<Integer>[] preMap = new List[numCourses];
            for (int[] prerequisite : prerequisites) {
                int numCourse = prerequisite[0];
                int preCourse = prerequisite[1];
                List<Integer> preSet = preMap[numCourse];
                if (preSet == null) {
                    preMap[numCourse] = preSet = new ArrayList<>();
                }
                preSet.add(preCourse);
            }

            // 节点访问状态
            int[] visited = new int[numCourses];
            for (int numCourse = 0; numCourse < numCourses; numCourse++) {
                if (!dfs(numCourse, preMap, visited)) {
                    return false;
                }
            }
            return true;
        }

        private boolean dfs(int numCourse, List<Integer>[] preMap, int[] visited) {
            if (visited[numCourse] == OK) {
                return true;
            }
            if (visited[numCourse] == USE) {
                return false;
            }
            visited[numCourse] = USE;
            List<Integer> preSet = preMap[numCourse];
            if (preSet != null) {
                for (int pre : preSet) {
                    if (!dfs(pre, preMap, visited)) {
                        return false;
                    }
                }
            }
            visited[numCourse] = OK;
            return true;
        }
    }
}
