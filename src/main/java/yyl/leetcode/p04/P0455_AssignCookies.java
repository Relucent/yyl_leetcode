package yyl.leetcode.p04;

import java.util.Arrays;

import yyl.leetcode.util.Assert;

/**
 * <h3>分发饼干</h3><br>
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。<br>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释: 
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * 
 * 示例 2:
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释: 
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 * </pre>
 */
public class P0455_AssignCookies {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(1, solution.findContentChildren(new int[] { 1, 2, 3 }, new int[] { 1, 1 }));
        Assert.assertEquals(2, solution.findContentChildren(new int[] { 1, 2 }, new int[] { 1, 2, 3 }));
        Assert.assertEquals(2, solution.findContentChildren(new int[] { 10, 9, 8, 7 }, new int[] { 5, 6, 7, 8 }));
    }

    // 排序 + 贪心算法
    // 为了尽可能满足最多数量的孩子，从贪心的角度考虑，应该按照孩子的胃口从小到大的顺序依次满足每个孩子，且对于每个孩子，应该选择可以满足这个孩子的胃口且尺寸最小的饼干。
    // 首先对数组 g 和 s 排序，然后从小到大遍历 s 中的每个元素，如果找到 g[i] <= s[j]，说明该饼干s[j]分配给孩子g[i]可以满足，选择下一个孩子(i++)。
    // 时间复杂度：O(mlog⁡m+nlog⁡n)，其中 m 和 n 分别是数组 g 和 s 的长度。对两个数组排序的时间复杂度是 O(mlog⁡m+nlog⁡n)，遍历数组的时间复杂度是 O(m+n)，因此总时间复杂度是 O(mlog⁡m+nlog⁡n)。
    // 空间复杂度：O(log⁡m+log⁡n)。空间复杂度主要是排序的额外空间开销。
    static class Solution {
        public int findContentChildren(int[] g, int[] s) {
            Arrays.sort(g);
            Arrays.sort(s);
            int m = g.length;
            int n = s.length;
            int answer = 0;
            for (int i = 0, j = 0; i < m && j < n; j++) {
                if (g[i] <= s[j]) {
                    answer++;
                    i++;
                }
            }
            return answer;
        }
    }
}
