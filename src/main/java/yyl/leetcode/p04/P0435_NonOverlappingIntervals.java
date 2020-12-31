package yyl.leetcode.p04;

import java.util.Arrays;
import java.util.Comparator;

import yyl.leetcode.util.Assert;

/**
 * <h3>无重叠区间</h3><br>
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。<br>
 * 注意:<br>
 * ├ 可以认为区间的终点总是大于它的起点。<br>
 * └ 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 * </pre>
 */
public class P0435_NonOverlappingIntervals {

    public static void main(String[] args) {
        Solution solution = new Solution();
        Assert.assertEquals(1, solution.eraseOverlapIntervals(new int[][] { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } }));
        Assert.assertEquals(2, solution.eraseOverlapIntervals(new int[][] { { 1, 2 }, { 1, 2 }, { 1, 2 } }));
        Assert.assertEquals(0, solution.eraseOverlapIntervals(new int[][] { { 1, 2 }, { 2, 3 } }));
    }

    // 贪心算法
    // 思路
    // ├ 假设在某一种最优的选择方法中
    // ├ [lk,rk] 是首个（即最左侧的）区间，那么它的左侧没有其它区间，右侧有若干个不重叠的区间。
    // ├ 如果此时存在一个区间 [lj,rj]，使得 rj<rk，即区间 j 的右端点在区间 k 的左侧，那么我们将区间 k 替换为区间 j ，[lk,jk] 其与剩余右侧被选择的区间仍然是不重叠的。
    // ├ 可以看出，这种情况，rk 与 lj 是不重叠的（ rk <= lj ）
    // └ 通过这个思路，就得到了另一种最优的选择方法。
    // 算法
    // ├ 可以不断地寻找右端点在首个区间右端点左侧的新区间，将首个区间替换成该区间。
    // │├ 当无法替换时，首个区间就是所有可以选择的区间中右端点最小的那个区间。
    // │└ 因此将所有区间按照右端点从小到大进行排序，那么排完序之后的首个区间，就是选择的首个区间。
    // ├ 如果有多个区间的右端点都同样最小
    // │└因为选择的是首个区间，因此在左侧不会有其它的区间，那么左端点在何处是不重要的，只要任意选择一个右端点最小的区间即可。
    // └ 当确定了首个区间之后，所有与首个区间不重合的区间就组成了一个规模更小的子问题。
    // - ├ 只要找出其中与首个区间不重合并且右端点最小的区间即可。
    // - └ 用相同的方法，可以依次确定后续的所有区间。
    // 时间复杂度：O(nlog⁡n)，其中 n 是区间的数量。需要 O(nlog⁡n) 的时间对所有的区间按照右边界进行升序排序，需要 O(n) 的时间进行遍历。
    // 空间复杂度：O(log⁡n) ，即为排序需要使用的栈空间。
    static class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            int n = intervals.length;
            if (n <= 1) {
                return 0;
            }
            Arrays.sort(intervals, Comparator.comparingInt(x -> x[1]));
            int count = 1;
            int end = intervals[0][1];
            for (int i = 1; i < n; i++) {
                if (intervals[i][0] >= end) {
                    count++;
                    end = intervals[i][1];
                }
            }
            return n - count;
        }
    }
}
