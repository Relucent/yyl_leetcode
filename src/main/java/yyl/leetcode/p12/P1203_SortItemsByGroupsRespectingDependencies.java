package yyl.leetcode.p12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * <h3>项目管理
 * <h3><br>
 * 公司共有 n 个项目和 m 个小组，每个项目要不无人接手，要不就由 m 个小组之一负责。<br>
 * group[i] 表示第 i 个项目所属的小组，如果这个项目目前无人接手，那么 group[i] 就等于 -1。<br>
 * （项目和小组都是从零开始编号的）小组可能存在没有接手任何项目的情况。 <br>
 * 请你帮忙按要求安排这些项目的进度，并返回排序后的项目列表：<br>
 * ├ 同一小组的项目，排序后在列表中彼此相邻。<br>
 * └ 项目之间存在一定的依赖关系，我们用一个列表 beforeItems 来表示，其中 beforeItems[i] 表示在进行第 i 个项目前（位于第 i 个项目左侧）应该完成的所有项目。<br>
 * 如果存在多个解决方案，只需要返回其中任意一个即可。如果没有合适的解决方案，就请返回一个 空列表 。<br>
 * 
 * <pre>
 * 示例 1：
 * |Item|Group|Before|
 * |  0 |  -1 |      |
 * |  1 |  -1 |    6 |
 * |  2 |   1 |    5 |
 * |  3 |   0 |    6 |
 * |  4 |   0 |  3,6 |
 * |  5 |   1 |      |
 * |  6 |   0 |      |
 * |  7 |  -1 |      |
 * 输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3,6],[],[],[]]
 * 输出：[6,3,4,1,5,2,0,7]
 * 
 * 示例 2：
 * 输入：n = 8, m = 2, group = [-1,-1,1,0,0,1,0,-1], beforeItems = [[],[6],[5],[6],[3],[],[4],[]]
 * 输出：[]
 * 解释：与示例 1 大致相同，但是在排序后的列表中，4 必须放在 6 的前面。
 * </pre>
 * 
 * 提示：<br>
 * ├ 1 <= m <= n <= 3 * 104 <br>
 * ├ group.length == beforeItems.length == n <br>
 * ├ -1 <= group[i] <= m - 1 <br>
 * ├ 0 <= beforeItems[i].length <= n - 1 <br>
 * ├ 0 <= beforeItems[i][j] <= n - 1 <br>
 * ├ i != beforeItems[i][j] <br>
 * └ beforeItems[i] 不含重复元素 <br>
 */

public class P1203_SortItemsByGroupsRespectingDependencies {

    public static void main(String[] args) {
        Solution solution = new Solution();
        // 答案不唯一
        // [6,3,4,0,7,1,5,2]
        // [6,3,4,1,5,2,0,7]
        System.out.println(Arrays.toString(solution.sortItems(8, 2, new int[] { -1, -1, 1, 0, 0, 1, 0, -1 }, Arrays.asList(//
                Arrays.asList(), // []
                Arrays.asList(6), // [6]
                Arrays.asList(6), // [5]
                Arrays.asList(6), // [6]
                Arrays.asList(3, 6), // [3,6]
                Arrays.asList(), // []
                Arrays.asList(), // []
                Arrays.asList()// []
        ))));
        // []
        System.out.println(Arrays.toString(solution.sortItems(8, 2, new int[] { -1, -1, 1, 0, 0, 1, 0, -1 }, Arrays.asList(//
                Arrays.asList(), // []
                Arrays.asList(6), // [6]
                Arrays.asList(6), // [5]
                Arrays.asList(6), // [6]
                Arrays.asList(3), // [3]
                Arrays.asList(), // []
                Arrays.asList(4), // [4]
                Arrays.asList()// []
        ))));
    }

    // 拓扑排序
    // 拓扑排序简单来说，是对于一张有向图 G，我们需要将 G 的 n 个点排列成一组序列，使得图中任意一对顶点 <u,v>，如果图中存在一条 u→v 的边，那么 u 在序列中需要出现在 v 的前面。
    // 分析
    // ├ 分析题意，可以看出题干要求有很多，主要有以下几种：
    // │ ├ n个项目，m个小组
    // │ ├ 同一个小组的项目必须相邻
    // │ ├ 同一个小组内的任务必须按要求的次序排序
    // │ └ 不同小组内的的任务也必须按要求的次序排序
    // └ 总结
    // - ├ 必须保证组内的所有任务可以按顺序执行
    // - │ └ 组内的任务拓扑关系不存在环
    // - ├ 组与组之间的次序必须按照组内的任务的次序排序
    // - │ └ 不同组内的任务拓扑关系即为组与组之间的拓扑关系，组与组之间拓扑关系不存在环
    // - └ 如果两个组内部的任务拓扑关系相互依赖并形成环，那么组与组之间的任务形成死锁，说明无法完成。
    // 解法（两层拓扑排序）
    // ├ 根据题意一个项目的完成可能需要在另一个项目之前完成，可以用拓扑排序来实现。
    // ├ 题目中要求输出的结果中，所属相同小组的项目需要相邻：
    // │ └ 假设项目a所属项目小组是A，项目b所属项目小组是B，如果 a在b 前面，那么B小组的所有项目一定排在A小组所有项目的前面。
    // ├ 针对没有分组的项目：
    // │ └ 可以假设它是单独一组的，所以可以给他重新标号，安排上一个组号。新的组号就从 m 开始这样每个项目都有它所属的小组。
    // └ 需要维护两个拓扑关系一个是组号之间的拓扑关系，哪个小组需要在哪个小组之前， 另一个是一个小组内部的项目的拓扑关系。
    // 复杂度分析
    // 时间复杂度：O(n+m)，其中 n 为点数，m 为边数。拓扑排序时间复杂度为 O(n+m)。
    // 空间复杂度：O(n+m)。空间复杂度主要取决于存储组间和组内依赖图数组以及存储组间和组内入度数组，存储组间依赖图和入度数组的空间复杂度取决于点数和边数，空间复杂度为 O(n+m)，存储组内依赖图和入度数组的空间复杂度取决于点数和边数，空间复杂度为 O(n)。因此空间复杂度为 O(n+m)。
    static class Solution {

        @SuppressWarnings("unchecked")
        public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
            // 第 1 步：数据预处理，给没有归属于一个组的项目编上组号
            for (int i = 0; i < group.length; i++) {
                if (group[i] == -1) {
                    group[i] = m;
                    m++;
                }
            }

            // 第 2 步：实例化组和项目的邻接表
            List<Integer>[] itemGraph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                itemGraph[i] = new ArrayList<>();
            }

            List<Integer>[] groupGraph = new ArrayList[m];
            for (int i = 0; i < m; i++) {
                groupGraph[i] = new ArrayList<>();
            }

            // 第 3 步：创建项目和组的图和入度数组
            int[] itemIndegree = new int[n];
            for (int i = 0; i < n; i++) {
                // item => 【前置项目】=> 【当前项目】的前置项目
                for (Integer item : beforeItems.get(i)) {
                    // 【前置项目】的后继为【当前项目】
                    itemGraph[item].add(i);
                    // 【当前项目】 入度+1
                    itemIndegree[i]++;
                }
            }

            int[] groupIndegree = new int[m];
            for (int i = 0; i < group.length; i++) {
                // 【当前项目】 => 第i个项目
                // 【当前组】 => 【当前项目】对应的组
                int currentGroup = group[i];
                // 【前置项目】=> 【当前项目】的前置项目
                for (int beforeItem : beforeItems.get(i)) {
                    // 【前置项目对应的组】
                    int beforeGroup = group[beforeItem];
                    if (beforeGroup != currentGroup) {
                        // 【前置项目对应的组】的后继是【当前项目对应组】
                        groupGraph[beforeGroup].add(currentGroup);
                        // 【当前组】 入度+1
                        groupIndegree[currentGroup]++;
                    }
                }
            }

            // 第 4 步：得到项目和组的拓扑排序结果
            Integer[] itemList = topologicalSort(itemGraph, itemIndegree, n);
            if (itemList == null) {
                return new int[0];
            }
            Integer[] groupList = topologicalSort(groupGraph, groupIndegree, m);
            if (groupList == null) {
                return new int[0];
            }

            // 第 5 步：根据项目的拓扑排序结果，项目到组的多对一关系，建立组到项目的一对多关系
            // key：组，value：在同一组的项目列表
            Map<Integer, List<Integer>> group2Item = new HashMap<>();
            for (Integer item : itemList) {
                // group[item] 第item个项目所属的组
                group2Item.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
            }

            // 第 6 步：把组的拓扑排序结果替换成为项目的拓扑排序结果
            int[] result = new int[n];
            int index = 0;
            for (Integer groupId : groupList) {
                List<Integer> items = group2Item.get(groupId);
                if (items != null) {
                    for (Integer item : items) {
                        result[index++] = item;
                    }
                }
            }
            return result;
        }

        /**
         * 拓扑排序
         * @param graph 邻接表(拓扑图)
         * @param inDegree 入度数组
         * @param n 分组个数
         * @return 拓扑排序后的结果，如果排序失败返回null
         */
        private static Integer[] topologicalSort(List<Integer>[] graph, int[] inDegree, int n) {
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
            Integer[] result = new Integer[n];
            int index = 0;
            while (!queue.isEmpty()) {
                Integer front = queue.poll();
                result[index++] = front;
                for (int successor : graph[front]) {
                    inDegree[successor]--;
                    if (inDegree[successor] == 0) {
                        queue.offer(successor);
                    }
                }
            }
            if (index == n) {
                return result;
            }
            return null;
        }
    }
}
