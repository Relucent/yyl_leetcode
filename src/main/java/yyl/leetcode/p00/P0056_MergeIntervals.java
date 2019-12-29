package yyl.leetcode.p00;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <h3>合并区间</h3><br>
 * 给出一个区间的集合，请合并所有重叠的区间。<br>
 * 
 * <pre>
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * </pre>
 */
public class P0056_MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(solution.merge(new int[][] {{1, 4}, {4, 5}})));
    }

    // 首先根据首位元素排序
    // 排序后依次比较之前一个的右缀和当前的前缀的大小关系
    // 时间复杂度：O(NlogN)，需要算上排序的时间复杂度
    // 空间复杂度：O(N)，存储返回结果
    static class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return intervals;
            }
            Arrays.sort(intervals, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    return Integer.compare(a[0], b[0]);
                }
            });
            List<int[]> result = new ArrayList<>();
            int[] previous = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                if (previous[1] >= intervals[i][0]) {
                    previous[1] = Math.max(previous[1], intervals[i][1]);
                } else {
                    result.add(previous);
                    previous = intervals[i];
                }
            }
            result.add(previous);
            return result.toArray(new int[result.size()][]);
        }
    }
}
