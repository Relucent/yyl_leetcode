package yyl.leetcode.p03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import yyl.leetcode.util.Assert;

/**
 * <h3>重新安排行程</h3><br>
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。<br>
 * 说明:<br>
 * 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前<br>
 * 所有的机场都用三个大写字母表示（机场代码）。<br>
 * 假定所有机票至少存在一种合理的行程。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 * 
 * 示例 2:
 * 输入: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释: 另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 * </pre>
 */
public class P0332_ReconstructItinerary {

    public static void main(String[] args) {
        Solution solution = new Solution();
        {
            List<List<String>> tickets = Arrays.asList(//
                    Arrays.asList("MUC", "LHR"), //
                    Arrays.asList("JFK", "MUC"), //
                    Arrays.asList("SFO", "SJC"), //
                    Arrays.asList("LHR", "SFO"));
            List<String> actual = solution.findItinerary(tickets);
            List<String> expected = Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC");
            Assert.assertEquals(expected, actual);
        }
        {
            List<List<String>> tickets = Arrays.asList(//
                    Arrays.asList("JFK", "SFO"), //
                    Arrays.asList("JFK", "ATL"), //
                    Arrays.asList("SFO", "ATL"), //
                    Arrays.asList("ATL", "JFK"), //
                    Arrays.asList("ATL", "SFO"));
            List<String> actual = solution.findItinerary(tickets);
            List<String> expected = Arrays.asList("JFK", "ATL", "JFK", "SFO", "ATL", "SFO");
            Assert.assertEquals(expected, actual);
        }
        {
            List<List<String>> tickets = Arrays.asList(//
                    Arrays.asList("JFK", "KUL"), //
                    Arrays.asList("JFK", "NRT"), //
                    Arrays.asList("NRT", "JFK"));
            List<String> actual = solution.findItinerary(tickets);
            List<String> expected = Arrays.asList("JFK", "NRT", "JFK", "KUL");
            Assert.assertEquals(expected, actual);
        }
    }

    // 深度优先搜索(DFS)+图遍历
    // 欧拉路径：如果图中的一个路径包括每个边恰好一次，则该路径称为欧拉路径
    // 1、从起点出发，进行深度优先搜索。（按字符自然排序顺序搜索）
    // 2、 每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
    // 3、 如果没有可移动的路径，则将所在节点加入到栈中，并返回。
    // 4、因为存在「死胡同」分支，所以需要改变入栈顺序：遍历完一个节点所连的所有节点后，才将该节点入栈（逆序入栈）。
    // 5、全部遍历完成后将栈中的内容反转，即可得到答案。
    // 时间复杂度：O(m*log{m})，其中 m是边的数量。对于每一条边我们需要 O(log{m}) 地删除它，最终的答案序列长度为 m+1，而与 n 无关。
    // 空间复杂度：O(m)，其中 m是边的数量。需要存储每一条边。
    static class Solution {
        public List<String> findItinerary(List<List<String>> tickets) {
            Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
            List<String> itinerary = new ArrayList<>();
            for (List<String> ticket : tickets) {
                String from = ticket.get(0);
                String to = ticket.get(1);
                PriorityQueue<String> queue = map.get(from);
                if (queue == null) {
                    map.put(from, queue = new PriorityQueue<>());
                }
                queue.offer(to);
            }
            dfs("JFK", map, itinerary);
            Collections.reverse(itinerary);
            return itinerary;
        }

        private void dfs(String from, Map<String, PriorityQueue<String>> map, List<String> itinerary) {
            PriorityQueue<String> queue = map.get(from);
            while (queue != null && queue.size() > 0) {
                String to = queue.poll();
                dfs(to, map, itinerary);
            }
            itinerary.add(from);
        }
    }
}
