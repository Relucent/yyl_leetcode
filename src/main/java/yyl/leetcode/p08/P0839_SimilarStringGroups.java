package yyl.leetcode.p08;

import yyl.leetcode.util.Assert;

/**
 * <h3>相似字符串组</h3><br>
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。<br>
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。<br>
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。<br>
 * 形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。<br>
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？<br>
 * 
 * <pre>
 * 示例 1：
 * 输入：strs = ["tars","rats","arts","star"]
 * 输出：2
 * 
 * 示例 2：
 * 输入：strs = ["omv","ovm"]
 * 输出：1
 * </pre>
 * 
 * 提示： <br>
 * ├ 1 <= strs.length <= 100 <br>
 * ├ 1 <= strs[i].length <= 1000 <br>
 * ├ sum(strs[i].length) <= 2 * 104 <br>
 * ├ strs[i] 只包含小写字母。 <br>
 * └ strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。 <br>
 * 备注： 字母异位词（anagram），一种把某个字符串的字母的位置（顺序）加以改换所形成的新词。<br>
 */
public class P0839_SimilarStringGroups {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(2, solution.numSimilarGroups(new String[] { "tars", "rats", "arts", "star" }));
        Assert.assertEquals(1, solution.numSimilarGroups(new String[] { "omv", "ovm" }));
    }

    // 并查集
    // 思路： 把每一个字符串看作点，字符串之间是否相似看作边，那么可以发现本题询问的是给定的图中有多少连通分量。节点间的连通性一般可以使用并查集维护。
    // 算法：枚举给定序列中的任意一对字符串，检查其是否具有相似性，如果相似，那么就将这对字符串相连。
    // 优化：可以先判断当前这对字符串是否已经连通，如果没有连通，再检查它们是否具有相似性，可以优化一定的时间复杂度。
    // 时间复杂度：O(n^2m + n*log{n}))，其中 n 是字符串的数量。需要O(n^2) 地枚举任意一对字符串之间的关系，对于任意一对字符串，我们需要 O(m) 的时间检查字符串是否相同。在最坏情况下需要对并查集执行 O(n) 次合并，合并的均摊时间复杂度O(logn)。
    // 空间复杂度：O(n)，其中 nnn 是字符串的数量。并查集需要 O(n)O(n)O(n) 的空间。
    static class Solution {

        public int numSimilarGroups(String[] strs) {
            int n = strs.length;
            if (n < 1) {
                return 0;
            }
            int m = strs[0].length();

            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < n; i++) {
                String a = strs[i];
                for (int j = i + 1; j < n; j++) {
                    int fi = find(i, parent);
                    int fj = find(j, parent);

                    // 已经连通
                    if (fi == fj) {
                        continue;
                    }

                    // 相似，那么；连通两个分量
                    if (isSimilar(a, strs[j], m)) {
                        parent[fi] = fj;
                    }
                }
            }

            // 计算分量个数（根是自己说明是一个分量）
            int answer = 0;
            for (int i = 0; i < n; i++) {
                if (parent[i] == i) {
                    answer++;
                }
            }
            return answer;
        }

        // 因为两个字符是字母异位词，那么只要判断有区别的字符是否小于2即可知道是否是相似
        private boolean isSimilar(String a, String b, int m) {
            int count = 0;
            for (int i = 0; i < m; i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    count++;
                    if (count > 2) {
                        return false;
                    }
                }
            }
            return true;
        }

        private int find(int x, int[] parent) {
            return parent[x] == x ? x : (parent[x] = find(parent[x], parent));
        }
    }
}
