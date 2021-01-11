package yyl.leetcode.p12;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import yyl.leetcode.util.Assert;

/**
 * <h3>换字符串中的元素</h3><br>
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。<br>
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。<br>
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释： 
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 * 
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 * 
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= s.length <= 10^5 <br>
 * ├ 0 <= pairs.length <= 10^5 <br>
 * ├ 0 <= pairs[i][0], pairs[i][1] < s.length<br>
 * └ s 中只含有小写英文字母<br>
 */
public class P1202_SmallestStringWithSwaps {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals("bacd", solution.smallestStringWithSwaps(//
                "dcab", //
                Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2))//
        ));
        Assert.assertEquals("abcd", solution.smallestStringWithSwaps(//
                "dcab", //
                Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2), Arrays.asList(0, 2))//
        ));
        Assert.assertEquals("abc", solution.smallestStringWithSwaps(//
                "cba", //
                Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2))//
        ));

    }

    // 并查集
    // 知识点：
    // ├ 字典序
    // │ └ 是基于字母顺序排列的单词按字母顺序排列的方法，ASCII 值越小的字符位于字符串中的位置越靠前，整个字符串的字典序就越靠前。
    // └ 并查集
    // - ├ 在计算机科学中，并查集是一种树型的数据结构，用于处理一些不交集（Disjoint Sets）的合并及查询问题。
    // - ├ 并查集有一个联合-查找算法（union-find algorithm）定义了两个用于此数据结构的操作：
    // - │ ├ Find：查找元素所在的集合，即根节点。通过不断向上查找找到它的根节点，它可以被用来确定两个元素是否属于同一子集。
    // - │ └ Union：将两个子集合并成同一个集合。合并之前，应先判断两个元素是否属于同一集合，这可用上面的“查找”操作实现。
    // - ├ 因为并查集创建的树可能会严重不平衡，查找性能取决于树深度，所以需要进行优化
    // - │ ├ 按秩合并：总是将更小的树连接至更大的树上，术语“秩”替代了“深度”，单元素的树的秩定义为0，当两棵秩同为r的树联合时，它们的秩r+1。最坏的运行复杂度 Union或Find操作 O(logN)。
    // - │ └ 路径压缩：因为在路径上的每个节点都可以直接连接到根上，所以Find递归地经过树，改变每一个节点的引用到根节点，得到更加扁平的树，为以后直接或者间接引用节点的操作加速。
    // - └ 复杂度
    // - - ├ 并查集的复杂度是很优的，至少比二分贪心O(nlogn2)要好很多。
    // - - └ 并查集进行n次查找的时间复杂度是O(α(n))（执行n-1次合并和m≥n次查找）。这里的α(n) 是阿克曼函数（Ackermann Function）的反函数，一般情况这个值小于5，可以认为是常数级的时间。
    // 思路
    // ├ 如果 x 与 y 能够交换，y 与 z 能够交换，那么 x 与 z 也可以交换，交换关系具有传递性。
    // ├ 索引对的交换具有传递性，两个索引对出现公共索引，那么索引对中出现的下标可以任意交换。
    // ├ 那么可以任意交换次序的部分、按照字典序升序排列，得到的字符串字典序就是最小的。
    // └ 问题转换为找下标连通分量的问题。
    // 算法
    // └ 利用并查集维护任意两点的连通性，将同属一个连通块内的点提取出来，直接排序后放置回其在字符串中的原位置即可。
    // 时间复杂度：O((M+N)α(N)+Nlog⁡N)，这里 M 是数组 pairs 的长度，N 是输入字符串 s 的长度，这里 α 是 Ackermann 函数的反函数。
    // 空间复杂度：O(N)：并查集的长度为 N ，哈希表的长度为 N ，所有的优先队列中加起来一共有 N 个字符，保存结果需要 N 个字符。
    static class Solution {
        public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {

            int n = s.length();
            if (n == 0) {
                return s;
            }
            DisjointSetUnion dsu = new DisjointSetUnion(n);

            // 第1步：将任意交换的结点对输入并查集
            for (List<Integer> pair : pairs) {
                int index0 = pair.get(0);
                int index1 = pair.get(1);
                dsu.union(index0, index1);
            }

            // 第2步：构建映射关系
            // key：连通分量的代表元，value：同一个连通分量的字符集合（保存在一个优先队列中）
            Map<Integer, PriorityQueue<Character>> map = new HashMap<>(n);
            for (int i = 0; i < n; i++) {
                Character c = s.charAt(i);
                int root = dsu.find(i);
                PriorityQueue<Character> queue = map.get(root);
                if (queue != null) {
                    queue.offer(c);
                } else {
                    map.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(c);
                }
            }

            // 第3步：重组字符串
            StringBuilder builder = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                int root = dsu.find(i);
                PriorityQueue<Character> queue = map.get(root);
                Character c = queue.poll();
                builder.append(c);
            }
            return builder.toString();
        }

        // 并查集(用于存储索引)
        class DisjointSetUnion {

            int[] parent;
            int[] rank;

            public DisjointSetUnion(int n) {
                parent = new int[n];
                rank = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            // 查找：根据其父节点的引用向根行进直到到底树根
            public int find(int x) {
                if (x != parent[x]) {
                    // 路径压缩
                    parent[x] = find(parent[x]);
                }
                return parent[x];
            }

            // 联合：将两棵树合并到一起，这通过将一棵树的根连接到另一棵树的根。
            public void union(int x, int y) {
                int xRoot = find(x);
                int yRoot = find(y);
                if (xRoot == yRoot) {
                    return;
                }
                // 按秩合并
                if (rank[xRoot] < rank[yRoot]) {
                    int temp = xRoot;
                    xRoot = yRoot;
                    yRoot = temp;
                }
                parent[yRoot] = xRoot;
                rank[xRoot] += rank[yRoot];
            }
        }
    }
}
