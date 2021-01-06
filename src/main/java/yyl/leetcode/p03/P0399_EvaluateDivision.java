package yyl.leetcode.p03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import yyl.leetcode.util.Assert;

/**
 * <h3>除法求值</h3><br>
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。<br>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。<br>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。<br>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 
 * 示例 2：
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 
 * 示例 3：
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= equations.length <= 20 <br>
 * ├ equations[i].length == 2 <br>
 * ├ 1 <= Ai.length, Bi.length <= 5 <br>
 * ├ values.length == equations.length <br>
 * ├ 0.0 < values[i] <= 20.0 <br>
 * ├ 1 <= queries.length <= 20 <br>
 * ├ queries[i].length == 2 <br>
 * ├ 1 <= Cj.length, Dj.length <= 5 <br>
 * └ Ai, Bi, Cj, Dj 由小写英文字母与数字组成 <br>
 */
public class P0399_EvaluateDivision {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(//
                new double[] { 6.00000, 0.50000, -1.00000, 1.00000, -1.00000 }, //
                solution.calcEquation(//
                        Arrays.asList(//
                                Arrays.asList("a", "b"), //
                                Arrays.asList("b", "c")//
                        ), //
                        new double[] { 2.0, 3.0 }, //
                        Arrays.asList(//
                                Arrays.asList("a", "c"), //
                                Arrays.asList("b", "a"), //
                                Arrays.asList("a", "e"), //
                                Arrays.asList("a", "a"), //
                                Arrays.asList("x", "x")//
                        )//
                )//
        );
        Assert.assertEquals(//
                new double[] { 3.75000, 0.40000, 5.00000, 0.20000 }, //
                solution.calcEquation(//
                        Arrays.asList(//
                                Arrays.asList("a", "b"), //
                                Arrays.asList("b", "c"), //
                                Arrays.asList("bc", "cd")//
                        ), //
                        new double[] { 1.5, 2.5, 5.0 }, //
                        Arrays.asList(//
                                Arrays.asList("a", "c"), //
                                Arrays.asList("c", "b"), //
                                Arrays.asList("bc", "cd"), //
                                Arrays.asList("cd", "bc") //
                        )//
                )//
        );
        Assert.assertEquals(//
                new double[] { 0.50000, 2.00000, -1.00000, -1.00000 }, //
                solution.calcEquation(//
                        Arrays.asList(//
                                Arrays.asList("a", "b") //
                        ), //
                        new double[] { 0.5 }, //
                        Arrays.asList(//
                                Arrays.asList("a", "b"), //
                                Arrays.asList("b", "a"), //
                                Arrays.asList("a", "c"), //
                                Arrays.asList("x", "y")//
                        )//
                )//
        );
    }

    // 图遍历
    // 思路： 因为 a/c = (a/b) * (b/c)，如果知道 (a/b)和 (b/c)，就可以求出 (a/c)。
    // 可以将整个问题建模成一张图：给定图中的一些点（变量），以及某些边的权值（两个变量的比值），试对任意两点（两个变量）求出其路径长（两个变量的比值）。
    // 图形式：(a) --> (b) --> (c) 如果 a-->c 路径确定，那么 a-->c 的解也是确定的
    // 首先需要遍历 equations 数组，找出其中所有不同的字符串，并通过哈希表将每个不同的字符串映射成整数。
    // 在构建完图之后，对于任何一个查询，就可以从起点出发，通过广度优先搜索的方式，不断更新起点与当前点之间的路径长度，直到搜索到终点为止。
    // 时间复杂度：O(ML+Q⋅(L+M)) ，其中 M 为边的数量，Q 为询问的数量，L 为字符串的平均长度。构建图时，需要处理 M 条边，每条边都涉及到 O(L) 的字符串比较；处理查询时，每次查询首先要进行一次 O(L) 的比较，然后至多遍历 O(M) 条边。
    // 空间复杂度：O(NL+M)，其中 N 为点的数量，M 为边的数量，L 为字符串的平均长度。为了将每个字符串映射到整数，需要开辟空间为 O(NL) 的哈希表；随后，需要花费 O(M) 的空间存储每条边的权重；处理查询时，还需要 O(N) 的空间维护访问队列。最终，总的复杂度为 O(NL+M+N)≈O(NL+M)。
    static class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

            int n = equations.size();

            // 遍历 equations 数组，找出其中所有不同的字符串，并通过哈希表将每个不同的字符串映射成整数（便于后续处理）。
            Map<String, Integer> variables = new HashMap<String, Integer>();
            for (int i = 0, index = 0; i < n; i++) {
                List<String> equation = equations.get(i);
                String x = equation.get(0);
                String y = equation.get(1);
                if (!variables.containsKey(x)) {
                    variables.put(x, index++);
                }
                if (!variables.containsKey(y)) {
                    variables.put(y, index++);
                }
            }

            int m = variables.size();

            // 构建图，对于每个点，存储其直接连接到的所有点及对应的值
            @SuppressWarnings("unchecked")
            List<Pair>[] edges = new List[m];
            for (int i = 0; i < m; i++) {
                edges[i] = new ArrayList<Pair>();
            }
            for (int i = 0; i < n; i++) {
                List<String> equation = equations.get(i);
                String x = equation.get(0);
                String y = equation.get(1);
                double value = values[i];
                int xIndex = variables.get(x);
                int yIndex = variables.get(y);
                edges[xIndex].add(new Pair(yIndex, value));
                edges[yIndex].add(new Pair(xIndex, 1D / value));
            }

            // 求解，遍历图
            int q = queries.size();
            double[] answer = new double[q];
            for (int i = 0; i < q; i++) {
                List<String> query = queries.get(i);
                String x = query.get(0);
                String y = query.get(1);
                // 没有找到映射
                if (!variables.containsKey(x) || !variables.containsKey(y)) {
                    answer[i] = -1D;
                    continue;
                }
                int xIndex = variables.get(x);
                int yIndex = variables.get(y);
                // 除数被除数一样
                if (xIndex == yIndex) {
                    answer[i] = 1D;
                    continue;
                }
                // 遍历图求解
                Queue<Integer> points = new LinkedList<Integer>();
                points.offer(xIndex);
                double[] ratios = new double[m];
                Arrays.fill(ratios, -1D);
                ratios[xIndex] = 1D;
                // 题目提示：不存在任何矛盾的结果，所以只要遍历到 yIndex 即可停止
                while (!points.isEmpty() && ratios[yIndex] == -1D) {
                    int aIndex = points.poll();
                    for (Pair pair : edges[aIndex]) {
                        int bIndex = pair.index;
                        double value = pair.value;
                        if (ratios[bIndex] == -1D) {
                            ratios[bIndex] = ratios[aIndex] * value;
                            points.offer(bIndex);
                        }
                    }
                }
                answer[i] = ratios[yIndex];
            }
            return answer;
        }

        class Pair {
            int index;
            double value;

            Pair(int index, double value) {
                this.index = index;
                this.value = value;
            }
        }
    }
}
